package ru.avers.informica.report.source;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.UchDao;
import ru.avers.informica.dao.filtersort.IFieldFilterParams;
import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.infcfg.SchemaConfig;
import ru.avers.informica.infcfg.SourceUch;
import ru.avers.informica.utils.CUtil;
import ru.avers.informica.utils.DateUtil;

import java.util.*;

/**
 *
 * @author Dias
 */
@Slf4j
public class DataSourceUch {

    private final List<SchemaConfig> schemaConfigs;
    private final Date currDate,
                       currEducDate;
    private final UchDao uchDao;

    public DataSourceUch(UchDao p_uchDao,
                List<SchemaConfig> p_schemas,
                Date p_rep_date,
                Date p_curr_educ_year) {
        uchDao = p_uchDao;
        schemaConfigs = p_schemas;
        currDate = p_rep_date;
        currEducDate = p_curr_educ_year;
    }
    
    public static class UchInfSchema extends Pair<UchInf, SchemaConfig> {

        public UchInfSchema(UchInf p_k, SchemaConfig p_t) {
            super(p_k, p_t);
        }                
        
        public UchInf getUchInf() {
            return getFirst();
        }

        public SchemaConfig getSchema() {
            return getSecond();
        }                        
    }
    
    public Pair<Collection<UchInfSchema>, String> getUchInfSchemas() {
        Collection<UchInfSchema> x_uch_schemas = new ArrayList<UchInfSchema>();
        // проверить учреждения на заполнение обязательных полей
        //  считать UchInf
        List<IFieldFilterParams> repForUchFilter = null;
        Set<Integer> notValidUchIds = new HashSet<Integer>();  // id не прошедших проверку учреждений
        // сначала считываем все учреждения
        // 1 - repForUchFilter - null,
        // 2 - currDate - текущая дата со временем
        // 3 - 01.09.2020 00:00:00
/*
        List<UchInf> validateUch = uchDao.getUchsInformica(repForUchFilter,
                currDate, DateUtil.adjustDate(currEducDate, 1));
*/
        List<UchInf> validateUch = uchDao.getUchInformica(repForUchFilter, currDate, currEducDate);
        log.info("Найдено {} uch-source", validateUch.size());

        StringBuilder uchMessage = new StringBuilder();
        String notValidUchMessage = "";
        for (UchInf uchInf : validateUch) {
            String checkRes = checkReportRequiredFields(uchInf);
            if (checkRes != null) {
                uchMessage.append(checkRes);
                notValidUchIds.add(uchInf.getId());
            }
        }
        if (!"".equals(uchMessage.toString())) {
            notValidUchMessage = "Следующие организации не включены в отчет, так как у них не заполнены " +
                    "обязательные поля:\n" + uchMessage.toString() + "\n";
        } else {
            notValidUchMessage = null;
        }

        // использовать validateUch для отбора учреждений
        for (SchemaConfig schemaConfig : schemaConfigs) {
            SourceUch sourceUch = schemaConfig.getSource();
            List<IFieldFilterParams> uchFilters = sourceUch.getFilters();
            if (uchFilters != null) {
                CHelper.setFilterFieldType(uchFilters, UchInf.class);
            }
            List<UchInf> uchInfs = uchDao.getUchInformica(uchFilters,
                    currDate, DateUtil.adjustDate(currEducDate, 1));
            for (UchInf uchInf : uchInfs) {
                if (!notValidUchIds.contains(uchInf.getId()))
                    x_uch_schemas.add(new UchInfSchema(uchInf, schemaConfig));
            }
        }
        return new Pair<Collection<UchInfSchema>, String>(x_uch_schemas, uchMessage.toString());
    }

    static private String checkReportRequiredFields(UchInf uchInf) {
        //ОКТМО
        //code
        //type
        //status
        //structure
        boolean requiredFieldEmpty = CUtil.isStringNullOrBlank(uchInf.getMunicipObrOktmo()) ||
                CUtil.isStringNullOrBlank(uchInf.getCode()) ||
                CUtil.isStringNullOrBlank(uchInf.getOrgLegalFormCode()) ||
                CUtil.isStringNullOrBlank(uchInf.getStatusСode()) ||
                CUtil.isStringNullOrBlank(uchInf.getStructureCode());
        if (requiredFieldEmpty) {
            StringBuilder builder = new StringBuilder();
            builder.append("У организации '")
                    .append(uchInf.getName())
                    .append("' района")
                    .append(uchInf.getTerName())
                    .append("\nне заполнены поля: ");
            String delimiter = "";
            if (CUtil.isStringNullOrBlank(uchInf.getCode())) {
                builder.append("Код");
                delimiter = ", ";
            }
            if (CUtil.isStringNullOrBlank(uchInf.getMunicipObrOktmo())) {
                builder.append(delimiter).append("ОКТМО");
                delimiter = ", ";
            }
            if (CUtil.isStringNullOrBlank(uchInf.getOrgLegalFormCode())) {
                builder.append(delimiter).append("Орг.-правовая форма");
                delimiter = ", ";
            }
            if (CUtil.isStringNullOrBlank(uchInf.getStatusСode())) {
                builder.append(delimiter).append("Статус");
                delimiter = ", ";
            }
            if (CUtil.isStringNullOrBlank(uchInf.getStructureCode())) {
                builder.append(delimiter).append("Структура");
            }
            return builder.append("\n").toString();
        }
        return null;
    }

}

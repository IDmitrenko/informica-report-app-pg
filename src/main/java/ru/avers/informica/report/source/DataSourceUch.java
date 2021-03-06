package ru.avers.informica.report.source;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.avers.informica.dao.UchDao;
import ru.avers.informica.dao.filtersort.IFieldFilterParams;
import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.infcfg.Config;
import ru.avers.informica.infcfg.ReportConfig;
import ru.avers.informica.infcfg.SchemaConfig;
import ru.avers.informica.infcfg.SourceUch;
import ru.avers.informica.report.ReportSetting;
import ru.avers.informica.utils.CHelper;
import ru.avers.informica.utils.CUtil;

import java.util.*;

/**
 * @author Dias
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataSourceUch {

    private final UchDao uchDao;
    private final ReportSetting reportSetting;

    public static class UchInfSchema extends Pair<UchInf, SchemaConfig> {

        public UchInfSchema(UchInf pUchInf, SchemaConfig pSchemaConfig) {
            super(pUchInf, pSchemaConfig);
        }

        public UchInf getUchInf() {
            return getFirst();
        }

        public SchemaConfig getSchema() {
            return getSecond();
        }
    }

    public Pair<Collection<UchInfSchema>, String> getUchInfSchemas() {
        final ReportConfig reportConfig = CHelper.getInformicaConfig().getReport(Config.S_INFORMICA_REPORT);
        List<SchemaConfig> schemaConfigs = reportConfig.getSchemas();

        Collection<UchInfSchema> uchInfSchemas = new ArrayList<UchInfSchema>();
        // проверить учреждения на заполнение обязательных полей
        //  считать UchInf
        List<IFieldFilterParams> repForUchFilter = null;
        reportSetting.setFirstOccurrence(true);
        Set<Long> notValidUchIds = new HashSet<Long>();  // id не прошедших проверку учреждений
        // сначала считываем все учреждения
        // 1 - repForUchFilter - null,
        // 2 - 01.09.2020 00:00:00 - дата начала учебного года

        //System.out.println("1-ый этап - Начало  " + new Date());
        List<UchInf> validateUch = uchDao.getUchInformica(repForUchFilter, reportSetting.getCurrEducDate());
        log.info("Найдено {} uch-source", validateUch.size());
        //System.out.println("1-ый этап - Конец  " + new Date());

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
            reportSetting.setFirstOccurrence(false);

            //System.out.println("2-ой этап - Начало  " + new Date());
            List<UchInf> uchInfs = uchDao.getUchInformica(uchFilters, reportSetting.getCurrEducDate());
            //System.out.println("2-ой этап - Конец  " + new Date());

            for (UchInf uchInf : uchInfs) {
                if (!notValidUchIds.contains(uchInf.getId()))
                    uchInfSchemas.add(new UchInfSchema(uchInf, schemaConfig));
            }
        }
        return new Pair<Collection<UchInfSchema>, String>(uchInfSchemas, notValidUchMessage);
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

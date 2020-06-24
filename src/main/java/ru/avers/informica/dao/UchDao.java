package ru.avers.informica.dao;


import ru.avers.informica.dao.filtersort.IFieldFilterParams;
import ru.avers.informica.dto.informica.UchInf;

import java.util.Date;
import java.util.List;

public interface UchDao {
    /**
     * Отбор учреждений для отчета
     * @param repForUchFilter список условий для фильтрации записей
     * @param currDate дата построения отчета
     * @param currEducDate дата начала текущего учебного года
     * @return список отобранных для отчета учреждений
     */
    List<UchInf> getUchInformica(List<IFieldFilterParams> repForUchFilter,
                                  Date currDate,
                                  Date currEducDate);

}

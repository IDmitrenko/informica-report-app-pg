package ru.avers.informica.dao;


import ru.avers.informica.dto.informica.MunicipalityInf;

import java.util.Date;
import java.util.List;

public interface MunicipalityDao {
    /**
     * Отбор муниципалитетов для отчета
     * @param currDate дата построения отчета
     * @param currEducDate дата начала текущего учебного года
     * @return список всех муниципалитетов
     */
    List<MunicipalityInf> getMunicipalitys(Date currDate, Date currEducDate);

}

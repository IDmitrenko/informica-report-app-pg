package ru.avers.informica.dao;


import ru.avers.informica.dto.informica.MunicipalityInf;

import java.util.List;

public interface MunicipalityDao {
    /**
     * Отбор муниципалитетов для отчета
     * @return список всех муниципалитетов
     */
    List<MunicipalityInf> getMunicipalitys();

}

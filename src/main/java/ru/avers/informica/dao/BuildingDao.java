package ru.avers.informica.dao;

import ru.avers.informica.dto.informica.BuildingInf;

import java.util.List;

public interface BuildingDao {
    /**
     * Отбор зданий учреждения для отчета
     * @param idUch id учреждения
     * @return список отобранных для отчета учреждений
     */
    List<BuildingInf> getBuildingsUch(Long idUch);

}

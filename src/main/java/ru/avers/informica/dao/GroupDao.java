package ru.avers.informica.dao;

import ru.avers.informica.dto.informica.GroupInf;

import java.util.Date;
import java.util.List;

public interface GroupDao {
    /**
     * Отбор групп(классов) учреждения по зданиям учреждения для отчета
     * @param idBuilding id здания учреждения
     * @param currEducYear текущий учебный год
     * @param idCodeBuilding код здания
     * @return список отобранных для отчета групп в здании учреждения
     */
    List<GroupInf> getGroupsBuildingUch(Integer idBuilding,
                                        Integer currEducYear,
                                        String idCodeBuilding);

}

package ru.avers.informica.dao;


import java.util.Collection;

public interface InqryGrpTimeDao {
    /**
     * Отбор режимов посещения заявления
     * @param idApplications id заявления
     * @return список отобранных для заявления режимов посещения (код)
     */
    Collection<String> getGrpTimesInqry(Integer idApplications);

    Collection<Integer> getGrpTimeIdsInqry(Integer idApplications);

}

package ru.avers.informica.dao;


import java.util.Collection;
import java.util.List;

public interface InqryBenefitsDao {
    /**
     * Отбор льгот заявления
     * @param idApplications id заявления
     * @return список отобранных для заявления льгот (код и тип льготы)
     * Тип льготы
     * Концентратор: 1-федеральная, 2-региональная, 3-муниципальная
     */
    List<Collection<String>> getBenefitsInqry(Integer idApplications);

}

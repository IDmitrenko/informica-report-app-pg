package ru.avers.informica.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface GroupActionsDao {
    /**
     * Отбор направленностей групп(классов) для add_cont
     * @param idGroup id группы
     * @return список отобранных id направленностей группы
     */
    Collection<Integer> getGroupHealths(Integer idGroup);

    /**
     * Подсчет количества детей с общеразвивающей направленностью в комбинированной группе
     * @param idGroup id группы
     * @param idHealthCsp id направленностей группы
     * @return количество детей с общеразвивающей направленностью в комбинированной группе
     */
    Integer getCombinedGroupSubtract(Integer idGroup, Collection<Integer> idHealthCsp);
}

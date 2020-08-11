package ru.avers.informica.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface GroupHealthDao {
    /**
     * Отбор направленностей групп(классов) для add_cont
     * @param idClasses id группы
     * @return список отобранных id направленностей группы
     */
    Collection<Integer> getGroupHealths(Integer idClasses);

}

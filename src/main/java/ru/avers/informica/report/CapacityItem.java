package ru.avers.informica.report;

import ru.avers.informica.entities.informica.IInformicaVacantCountable;
import ru.avers.informica.dto.informica.IInformicaCountable;

/**
 * Часть вакантных мест (под диапазон возрастов информики), составляющая вакантных мест (записи таблицы UCH_INFO4INQRY)
 *
 * Задача 11821, пункт 26
 * Например, в группе от 2 до 4 лет шесть свободных мест: пишем 2 места для детей 2-х лет, 
 * два для трех лет, 2 для четырех лет. Если место одно , 
 * привязываемся к крайнему наименьшему диапазону : одно место для детей 2-3 лет.
 * @author lavrov
 */
public class CapacityItem implements IInformicaCountable {
    private final IInformicaVacantCountable m_informica_capacity;
    private final Integer m_item_capacity;

    public CapacityItem(Integer m_item_capacity, IInformicaVacantCountable m_informica_capacity) {
        this.m_informica_capacity = m_informica_capacity;
        this.m_item_capacity = m_item_capacity;
    }

    public IInformicaVacantCountable getCap() {
        return m_informica_capacity;
    }

    @Override
    public int getCount() {
        return m_item_capacity;
    }

    @Override
    public Integer getIdUch() {
        return m_informica_capacity.getIdUch();
    }
    
}

package ru.avers.informica.dao;


import ru.avers.informica.dto.inqry.AgeDto;

import java.util.Date;
import java.util.Map;

public interface CommonDao {
    /**
     * Данные о детях, стоящих на учете в связи с отсутствием ДОО
     * @param currDate дата построения отчета
     * @param nextEducDate дата начала следующего учебного года
     * @param pActiveQueue признак получения места в текущем учебном году (true)
     * @param ageFrom возраст от
     * @param ageTo возраст до
     * @return список отобранных для отчета учреждений
     */
    Map<Integer, Integer> getNoDooCounter(Date currDate,
                                          Date nextEducDate,
                                          boolean pActiveQueue,
                                          AgeDto ageFrom,
                                          AgeDto ageTo);

}

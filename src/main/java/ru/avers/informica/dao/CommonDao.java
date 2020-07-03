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
     * @return количество отобранных для отчета учреждений по территориям
     */
    Map<Integer, Integer> getNoDooCounter(Date currDate,
                                          Date nextEducDate,
                                          boolean pActiveQueue,
                                          AgeDto ageFrom,
                                          AgeDto ageTo);

    /**
     * Данные о детях, не посещающих ДОО по медицинским показаниям
     * @param currDate дата построения отчета
     * @param ageFrom возраст от
     * @param ageTo возраст до
     * @return количество отобранных для отчета учреждений по территориям
     */
    Map<Integer, Integer> getMedicCounter(Date currDate,
                                          AgeDto ageFrom,
                                          AgeDto ageTo);

    /**
     * Данные о детях, получающих дошкольное образование в семейной форме
     * @param currDate дата построения отчета
     * @param currEducYear  текущий учебный год
     * @param ageFrom возраст от
     * @param ageTo возраст до
     * @return количество отобранных для отчета учреждений по территориям
     */
    Map<Integer, Integer> getFamilyCounter(Date currDate,
                                          Integer currEducYear,
                                          AgeDto ageFrom,
                                          AgeDto ageTo);
}

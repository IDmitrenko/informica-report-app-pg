package ru.avers.informica.dao;

import ru.avers.informica.dto.informica.SpecialistsInf;

import java.util.List;

public interface SpecialistsDao {
    /**
     * Отбор специалистов учреждения для отчета
     * @param idUch id учреждения
     * @return отобранные для отчета специалисты учреждения
     */
    SpecialistsInf getSpecialistsUch(Long idUch);

}

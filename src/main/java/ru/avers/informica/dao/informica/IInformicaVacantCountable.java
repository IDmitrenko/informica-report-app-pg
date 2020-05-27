package ru.avers.informica.dao.informica;

import ru.avers.informica.dto.informica.IInformicaCountable;
import ru.avers.informica.entities.Prll4stgAges;

import java.util.List;

/**
 *
 * @author Dias
 */
public interface IInformicaVacantCountable extends IInformicaCountable {
    List<Prll4stgAges> getAges();
}

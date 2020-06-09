package ru.avers.informica.filtersinqry;

import ru.avers.informica.exception.FilterException;

/**
 * @param <T>
 * @author Dias
 */
public interface IFilter<T> {
    boolean isPassed(T p_value) throws FilterException;

    public String getField();

}

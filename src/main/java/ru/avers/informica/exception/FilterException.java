package ru.avers.informica.exception;

/**
 *
 * @author Dias
 */
public class FilterException extends Exception {
    public FilterException() { super(); }
    public FilterException(String msg) { super(msg); }
    public FilterException(String msg, Throwable cause) { super(msg, cause); }
    public FilterException(Throwable cause) { super(cause); }
}

package ru.avers.informica.exception;

/**
 *
 * @author Dias
 */
public class FilterException extends Exception {
    public FilterException() { super(); }
    public FilterException(String p_msg) { super(p_msg); }
    public FilterException(String p_msg, Throwable p_cause) { super(p_msg, p_cause); }
    public FilterException(Throwable cause) { super(cause); }
}

package ru.avers.informica.exception;

/**
 *
 * @author Dias
 */
public class FspeoException extends Exception {
    public FspeoException() { super(); }
    public FspeoException(String p_msg) { super(p_msg); }
    public FspeoException(String p_msg, Throwable p_cause) { super(p_msg, p_cause); }
    public FspeoException(Throwable cause) { super(cause); }
}

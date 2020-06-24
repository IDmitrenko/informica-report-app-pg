package ru.avers.informica.exception;

public class ReportExceprion extends Exception {
    public ReportExceprion(String message, Exception ex) {
        super(message,ex);
    }
}

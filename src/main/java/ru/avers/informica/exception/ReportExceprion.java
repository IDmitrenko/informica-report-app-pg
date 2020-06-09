package ru.avers.informica.exception;

public class ReportExceprion extends Exception {
    public ReportExceprion(String message, ReportExceprion ex) {
        super(message,ex);
    }
}

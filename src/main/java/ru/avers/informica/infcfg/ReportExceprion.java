package ru.avers.informica.infcfg;

public class ReportExceprion extends Exception {
    public ReportExceprion(String message, ReportExceprion ex) {
        super(message,ex);
    }
}

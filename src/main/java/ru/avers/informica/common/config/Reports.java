package ru.avers.informica.common.config;

import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Dias
 */
public class Reports implements IDTO {
    public final static String S_ROOT_NAME = "reports";
    
    public Reports() { 
    }

    private ReportsCommon reportsCommon;
    private ReportInformica reportInformica;

    // Общие настройки отчетов. Наименование города, используется при построении
    // уведомления и путевки.
    @XmlElement(name = ReportsCommon.S_ROOT_NAME, type = ReportsCommon.class)
    public ReportsCommon getReportsCommon() {
        if (reportsCommon == null) reportsCommon = new ReportsCommon();
        return reportsCommon;
    }

    public void setReportsCommon(ReportsCommon pVal) {
        reportsCommon = pVal;
    }       
    
    @XmlElement(name = ReportInformica.S_ROOT_NAME, type = ReportInformica.class)
    public ReportInformica getReportInformica() {
        if (reportInformica == null) reportInformica = new ReportInformica();
        return reportInformica;
    }

    public void setReportInformica(ReportInformica pVal) {
        reportInformica = pVal;
    }        

    public final void set(Reports pVal) {
        if (pVal == null) throw new IllegalArgumentException();
        
        setReportsCommon(pVal.getReportsCommon());
        setReportInformica(pVal.getReportInformica());
    } 
    
}

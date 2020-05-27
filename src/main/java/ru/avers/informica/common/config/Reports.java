package ru.avers.informica.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Dias
 */
public class Reports implements IDTO {
    private static final Logger s_logger = LoggerFactory.getLogger(Reports.class);
    public final static String s_root_name = "reports";
    
    public Reports() { 
    }

    private ReportsCommon m_report_common;
    private ReportInformica m_report_informica;

    // Общие настройки отчетов. Наименование города, используется при построении
    // уведомления и путевки.
    @XmlElement(name = ReportsCommon.s_root_name, type = ReportsCommon.class)
    public ReportsCommon getReportsCommon() {
        if (m_report_common == null) m_report_common = new ReportsCommon();
        return m_report_common;
    }

    public void setReportsCommon(ReportsCommon p_val) {
        m_report_common = p_val;
    }       
    
    @XmlElement(name = ReportInformica.s_root_name, type = ReportInformica.class)
    public ReportInformica getReportInformica() {
        if (m_report_informica == null) m_report_informica = new ReportInformica();
        return m_report_informica;
    }

    public void setReportInformica(ReportInformica p_val) {
        m_report_informica = p_val;
    }        

    public final void set(Reports p_val) {
        if (p_val == null) throw new IllegalArgumentException();
        
        setReportsCommon(p_val.getReportsCommon());
        setReportInformica(p_val.getReportInformica());        
    } 
    
}

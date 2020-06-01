/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avers.informica.report;

import ru.avers.informica.exception.FspeoException;
import ru.avers.informica.infcfg.CounterConfig;
import ru.avers.informica.infcfg.SystemInfo;
import ru.avers.informica.infcfg.TypeSchemaVersion;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.OutputStream;

/**
 *
 * @author Dias
 */
public interface IPushDataRequest {

    public TypeSchemaVersion getSchemaVersion();
    
    void setSchemaVersion(TypeSchemaVersion typeSchemaVersion);    
   
    void setSystem(SystemInfo system);

    void setReportDate(XMLGregorianCalendar x_report_date_xml);

    void setSendTime(XMLGregorianCalendar x_send_time_xml);

    IReport createReport();

    void sortReports();
        
    Counter createCounter(CounterConfig p_counter_config, boolean p_details);

    public IReport getOrgReport();
    
    void toOutputStream(OutputStream p_output_stream) throws JAXBException;

    void toExcel(String p_report_template, String p_temp_file_path) throws FspeoException;

}

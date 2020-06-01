/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avers.informica.report;

import ru.avers.informica.exception.FspeoException;
import ru.avers.informica.infcfg.TypeSchemaVersion;

import javax.xml.bind.JAXBException;
import java.io.*;

/**
 *
 * @author lavrov
 */
public class FspeoReport {
    protected IPushDataRequest m_request;
    private String m_error_message;

    public FspeoReport() {
    }

    public FspeoReport(IPushDataRequest p_request, String p_error_message) {
        this.m_request = p_request;
        this.m_error_message = p_error_message;
    }

    public String getErrorMessage() {
        return m_error_message;
    }    

    public TypeSchemaVersion getSchemaVer() {
        return m_request.getSchemaVersion();
    }
    
    public String saveAsXml(String p_tmp_path) throws FspeoException {
        try {
            String x_temp_file_path = createTempFile(p_tmp_path);
            OutputStream x_temp_file_stream = new BufferedOutputStream(new FileOutputStream(x_temp_file_path));
            m_request.toOutputStream(x_temp_file_stream);
            x_temp_file_stream.flush();
            x_temp_file_stream.close();
            return x_temp_file_path;            
        }
        catch (IOException ex) {
            throw new FspeoException("Ошибка сохранения файла отчета", ex);
        }
        catch (JAXBException ex) {
            throw new FspeoException("Ошибка сохранения файла отчета", ex);
        }
    }
    
    public String saveAsExcel(String p_tmp_path, String p_report_template) throws FspeoException {
        String x_temp_file_path = createTempFile(p_tmp_path);
        m_request.toExcel(p_report_template, x_temp_file_path);
        return x_temp_file_path;
    }
    
    private String createTempFile(String p_app_path) throws FspeoException {
        String x_temp_path = p_app_path + "/temp/";
        File x_temp_file = null;
        try {
            x_temp_file = File.createTempFile("inf", ".tmp", new File(x_temp_path));
        } catch (IOException ex) {
            throw new FspeoException(ex);
        }
        return x_temp_file.getAbsolutePath();
    }    

    public Object getFullOrgReport() {
        return m_request.getOrgReport();               
    }

    public byte[] getBytes() throws FspeoException {
        ByteArrayOutputStream x_baos = new ByteArrayOutputStream();
        try {
            m_request.toOutputStream(x_baos);
            byte[] x_rv = x_baos.toByteArray();
            x_baos.close();
            return x_rv;
        }
        catch (IOException ex) {
            throw new FspeoException("Ошибка сохранения отчета", ex);
        }
        catch (JAXBException ex) {
            throw new FspeoException("Ошибка сохранения отчета", ex);
        }
    }
        
}

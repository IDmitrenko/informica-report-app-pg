/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avers.informica.services;

//import org.avers.dto.config.IMailNotification;

/**
 *
 * @author Dias
 */
public interface IProfile {
    
    String getId();
    void setId(String p_val);
    
    IMisc getMisc();
    void setMisc(IMisc p_val);

    IReports getReports();
    void setReports(IReports p_reports);
    
    void set(IProfile p_val);
    
}

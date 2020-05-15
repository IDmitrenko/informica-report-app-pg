/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avers.informica.services;

/**
 *
 * @author lavrov
 */
public interface IEsia {

    boolean isEnabled();
    
    void setEnabled(boolean p_enabled);
    
    String getUrl();
    
    void setUrl(String p_url);
    
}

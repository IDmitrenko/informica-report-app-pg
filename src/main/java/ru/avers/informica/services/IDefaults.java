/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avers.informica.services;

/**
 * Настройка. Временная зона
 * @author lavrov
 */
public interface IDefaults {
    
    // Значение временной зоны по умолчанию
    String getTimezone();
    void setTimezone(String p_val);
    
    // Идентификатор темы по умолчанию
    String getThemeId();
    void setThemeId(String p_val);
    
    void set(IDefaults p_val);    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avers.informica.services;

/**
 * Настройки общие для всех отчетов
 * @author lavrov
 */
public interface IReportsCommon {
    
    // Значение верхнего регионального уровня, используется в отчетах
    String getAuthority();
    void setAuthority(String p_val);
        
    // Наименование приложения. отображается на главной странице
    // напр., Заявления о зачислении ребенка в ДОУ
    String getAppDescr();
    void setAppDescr(String p_app_descr);
    
    void set(IReportsCommon p_val);       
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avers.informica.services;

/**
 * ��������� �������
 * @author lavrov
 */
public interface IReports {
    
    // ����� ��������� �������. ������������ ������, ������������ ��� ����������
    // ����������� � �������.
    IReportsCommon getReportsCommon();
    void setReportsCommon(IReportsCommon p_val);        
    
    IReportInformica getReportInformica();
    void setReportInformica(IReportInformica p_val);
    
    void set(IReports p_val);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avers.informica.services;

/**
 * ��������� ����� ��� ���� �������
 * @author lavrov
 */
public interface IReportsCommon {
    
    // �������� �������� ������������� ������, ������������ � �������
    String getAuthority();
    void setAuthority(String p_val);
        
    // ������������ ����������. ������������ �� ������� ��������
    // ����., ��������� � ���������� ������� � ���
    String getAppDescr();
    void setAppDescr(String p_app_descr);
    
    void set(IReportsCommon p_val);       
}

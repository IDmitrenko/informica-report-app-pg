/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avers.informica.services;

/**
 * ���������. ��������� ����
 * @author lavrov
 */
public interface IDefaults {
    
    // �������� ��������� ���� �� ���������
    String getTimezone();
    void setTimezone(String p_val);
    
    // ������������� ���� �� ���������
    String getThemeId();
    void setThemeId(String p_val);
    
    void set(IDefaults p_val);    
}

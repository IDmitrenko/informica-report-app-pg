/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.avers.informica.services;

import javax.xml.bind.annotation.XmlEnumValue;

/**
 * 
 * @author lavrov
 */
public interface IReportInformica {    
    // ����������� ������� ������� ��� �������� �������� ������� "����� ���������"
    // (������ �� ������� ����� ����������, ����� "�����. ���������" � ���������� ������ ����� "������")
    // ������������ ������ � ���������� "��������". ���� ������ ������� ��������� ������������ null (���
    // ���������� � ������� � �������� ��������� ������� ������ ������)
    IMisc.AccessLevel getReportAccessLevel();
    void setReportAccessLevel(IMisc.AccessLevel p_value);    

    Boolean isEmailLog();
    void setEmailLog(Boolean p_val);
    
    // ������������ ������� (������������ � ����� �� email)
    String getStateName();
    void setStateName(String p_val);
    
    // �������� �� �������������� �������� ������ ���������
    Boolean isAutoUploadEnabled();
    void setAutoUploadEnabled(Boolean p_val);

    // ���� ����� ����������� ���� �������� ������
    Integer getAutoUploadHours();
    void setAutoUploadHours(Integer p_val);

    // ������ ����� ����������� ���� �������� ������
    Integer getAutoUploadMinutes();
    void setAutoUploadMinutes(Integer p_val);
                
    // �������� ������� ������
    Boolean isDebugEnabled();
    void setDebugEnabled(Boolean p_val);
        
    enum EnrolledSource{CMSN, CNTGN, PLUS}
    // ������ ����� ��������� � ���������� �� �������� "���������"
    EnrolledSource getEnrolledFrom();
    void setEnrolledFrom(EnrolledSource p_source);
    
    // ������� �� ���������� � ��� �������� ���������, � �������
    Integer getConnectionTimeout();
    void setConnectionTimeout(Integer p_connection_timeout);

    // ������� �� �������� ������ �� ��� ������� ���������, � �������
    Integer getRequestTimeout();
    void setRequestTimeout(Integer p_request_timeout);
    
    // �������� ����� ���������� ��������� �������� ������, � �������
    Integer getPushDataDelay();
    void setPushDataDelay(Integer p_val);
    
    Boolean isAsyncUploadEnabled();
    void setAsyncUploadEnabled(Boolean p_val);
    
    // ������������ ��������������� ��� ���������� ������
    Boolean isMt();
    void setMt(Boolean p_val);
    
    enum Version {
        @XmlEnumValue("2")
        Two, 
        @XmlEnumValue("3")
        Three,
        @XmlEnumValue("4")
        Four
    }
    Version getVersion();
    void setVersion(Version p_version);
    
    void set(IReportInformica p_val);
}

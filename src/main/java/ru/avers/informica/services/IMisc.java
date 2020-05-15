/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avers.informica.services;

import java.util.Collection;

/**
 *
 * @author Dias
 */
public interface IMisc {   

    enum TTypeQueue{TOTAL, DISTRICTS, UCHS}
    
    enum AccessLevel{SUPERUSER, ADMIN, COMMISSION, OPERATOR, PRIVILEGED, NONE}
    
    // ��������� �� ��������� (��������� ����, ����)
    IDefaults getDefaults();
    void setDefaults(IDefaults p_val);
    
    // ***************************************************************************
    //  ���� � ����� ������ �������� ����. ������������ ��� ������� ��������� ���������
    //  �� ������ �������� ���� ������������ ��� �������� ������� ���������. (��� ������: ������=0)
    // ***************************************************************************
    IInqryEducYearBegin getInqryEducYearBegin();
    void setInqryEducYearBegin(IInqryEducYearBegin p_val);
    
    /**
     * ������������ �������� ������� ���������.
     * @return 
     */
    IInqryCheckStatus getInqryCheckStatus();
    void setInqryCheckStatus(IInqryCheckStatus p_val);
    
    //        ***************************************************************************
    //        ���� ����� ������� �� ���������� ��� ������ ���������
    //        ***************************************************************************
    Collection<String> getExcludeLgotCodes();
    void setExcludeLgotCodes(Collection<String> p_val);
    
    // ���� ���������� ������� ������� �� ���������� ��� ������ ���������
    Collection<String> getExcludeDocKindChldCodes();
    void setExcludeDocKindChldCodes(Collection<String> p_val);
    
    // ���� �������� ��������� ������� �� ���������� ��� ������ ������ ������� ���������
    Collection<String> getExcludeStatusCodes();
    void setExcludeStatusCodes(Collection<String> p_val);

    // ���� ������ ��������� ������� ��������� ������� �� ���������� ��� ������ ����� �������
    Collection<String> getExcludeStatusReasonCodes();
    void setExcludeStatusReasonCodes(Collection<String> p_val);
    
    // ���� ������� ������� �� ����������
    Collection<String> getExcludeReportCodes();
    void setExcludeReportCodes(Collection<String> p_val);
    
    // ����������� ������� �������, ��� �������� �������� ����� "�����������"
    // (��������� ���������������� ������ ������������)
    AccessLevel getUserRegistrationAccessLevel();
    void setUserRegistrationAccessLevel(AccessLevel p_value);
    
    // ����������� ������� �������, ��� �������� �������� ����� "������������"
    // (��������� ���������������� ������ ������������, ������������� ������ �������������,
    // ������������ ������������� �� �������)
    AccessLevel getUsersManagementAccessLevel();
    void setUsersManagementAccessLevel(AccessLevel p_value);
    
    // ����������� ������� �������, ��� �������� �������� ������ "�������� ���� �����������" �� �����
    // "���������" � "������ ���������". ���� ������ ������� ��������� ������������ null.
    // (������ ��������� ���������� ������������ ���� ���������� �� ������� ��� ���������, �������� �����
    // ������� ��������� �� �������)    
    AccessLevel getManualRegChangeAccessLevel();
    void setManualRegChangeAccessLevel(AccessLevel p_value);

    // ����������� ������� �������, ��� �������� �������� ������� "������ ���������" �� �����
    // "���������" � "������ ���������".
    AccessLevel getMinEventViewerAccessLevel();
    void setMinEventViewerAccessLevel(AccessLevel p_value);
    
    // ������������� ���������� ������ ���������� ������� ������ �� ����� ����������
    Boolean isFormUchsAutoExpandList();
    void setFormUchsAutoExpandList(Boolean p_val);

    /**
     * ������������ ��������� ��������������
     * @return 
     */
    IQueueAllocation getQueueAllocation();
    void setQueueAllocation(IQueueAllocation p_queue_allocation);
    
    /**
     * ��������� ������� ����, ���� �� ����� ���������
     * @return 
     */
    Boolean isFormInqriesMFTabsVisible();
    void setFormInqriesMFTabsVisible(Boolean p_val);

    /**
     * ��������� ���� ��� ���������� ��������
     * @return 
     */
    IEsia getEsiaCommission();
    void setEsiaCommission(IEsia p_esia);
    
    /**
     * ����� ���-������� ��������� ���������� �� �����������
     * 2016-06-20:  ��� ���������� ������ ��������� ������ 4
     * @return 
     */
    String getContingentEndpoint();
    void setContingentEndpoint(String p_val);
    
    void set(IMisc p_val);
    
}

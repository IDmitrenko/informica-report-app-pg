/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avers.informica.services;

import org.avers.inqry.config.IDateElement;

/**
 *
 * @author lavrov
 */
public interface IInqryCheckStatus {
    
    IDateElement getCalculateOnDate();
    void setCalculateOnDate(IDateElement p_date);    
    
    /**
     * ����������� �������� ������� ��������� ��� ����������� �� ������ ��������� � ��-�� � ��������
     * @return 
     */
    Boolean isStatusCheckWoutRegEnabled();
    void setStatusCheckWoutRegEnabled(Boolean p_val);

    /**
     * ���������� ���������, ������������ ���� ����� � ������� ������������ ��
     * ���������� ����������
     * @return 
     */
    IAgeIntervals getAgeIntervals();
    void setAgeIntervals(IAgeIntervals p_age_intervals);
    
    /**
     * �������� ������� � ���������� "���������" �� ������� ��������� ������ "�������� ����� � �������"
     * @return 
     */
    IInquirerCheckTotalByBtn getInquirerCheckTotalByBtn();
    void setInquirerCheckTotalByBtn(IInquirerCheckTotalByBtn p_value);
    
    /**
     * ����������� ������� � ������� ��������� �����
     * @return 
     */
    IViewQueues getInquirerViewQueues();
    void setInquirerViewQueues(IViewQueues p_value);
    
    IViewQueue getAllocViewQueue();
    void setAllocViewQueue(IViewQueue p_val);
    
    void set(IInqryCheckStatus p_val);
    
}

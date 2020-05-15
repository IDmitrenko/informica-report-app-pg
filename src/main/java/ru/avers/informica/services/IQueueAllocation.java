/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.avers.informica.services;

import dal.QueueDatesRule;
import org.avers.common.config.impl.CalcAgeElementTer;
import org.avers.dto.inqry.QueueRule;
import org.avers.inqry.config.IQueueDtRule;

import java.util.Collection;
import java.util.List;

/**
 * ��������� ��������������
 * @author lavrov
 */
public interface IQueueAllocation {
    
    /**
     * ��� ��������� ����� ��������� � ������� �� ����� "��������������"
     */
    enum LoadInqryPosType {
        OFF, // �� ���������� ������
        EAGER, // ������������ ����� ��� �������� �������
        LAZY} // ������������ ����� ����, ��� ������� �������� �� �����
    
    /**
     * ���� � ����� �� ������� ��������� �������
     * @return 
     */
    ICalcAgeElement getCalculateAgeOnDate();
    void setCalculateAgeOnDate(ICalcAgeElement p_date);

    List<CalcAgeElementTer> getCalculateAgeOnDateTer();
    void setCalculateAgeOnDateTer(List<CalcAgeElementTer> p_date_ter);

    ICalcAgeElement getCalculateAgeOnDateAdd();
    void setCalculateAgeOnDateAdd(ICalcAgeElement p_date);
    
    /**
     * ����� "��������������", ���� true = ���������� ������� � ������� � ���������� ���������
     * ������ �����������, false = ���������� ��� �������
     * @return 
     */
    Boolean isRestrictByCurrInUchYear();
    void setRestrictByCurrInUchYear(Boolean p_val);
    
    /**
     * ����� "��������������", ���� true = ���������� ������� ���������, � ������� �������� ���� ���������� �� �����������
     * ���� ������ �������� ����, false = ���������� ��� �������
     * @return 
     */
    Boolean isRestrictByCurrInUchDate();
    void setRestrictByCurrInUchDate(Boolean p_val);
    
    Collection<QueueRule> getQueueRules();
    void setQueueRules(Collection<QueueRule> p_queue_rules_list);
    
    /**
     * @return
     * ������� ��� ����������� ��� ����������, ����������� � ������
     * ��������� �/�� ������� 
     */    
    QueueDatesRule getQueueDatesRule();
    void setQueueDatesRule(QueueDatesRule p_rule);
  
    /**
     * ��� ��������� ����� ��������� � ������� �� ����� "��������������"
     * @return 
     */
    LoadInqryPosType getLoadInqryPosType();
    void setLoadInqryPosType(LoadInqryPosType p_type);

    /**
     * ���-�� ���������, ��� ������� ��������� ����� ��������� � ������� � ����� �������,
     * ������������ ��� LoadInqryPosType=LAZY
     * @return 
     */
    int getLoadInqryPosReqSize();
    void setLoadInqryPosReqSize(int p_load_inqry_pos_req_size);  
    
    Collection<String> getCreateAllocSelectDouTime();
    void setCreateAllocSelectDouTime(Collection<String> p_val);
    
    Collection<IQueueDtRule> getQueueDtRules();
    void setQueueDtRules(Collection<IQueueDtRule> p_queue_dt_rules);
    
    boolean isSpecifyInqryHealthNeeds();
    void setSpecifyInqryHealthNeeds(boolean p_specify_inqry_health_needs);
    
    void set(IQueueAllocation p_val);      
}

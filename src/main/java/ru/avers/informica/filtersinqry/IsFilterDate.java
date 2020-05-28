package ru.avers.informica.filtersinqry;

import java.util.Date;

/**
 *
 * @author Dias
 */
public interface IsFilterDate {
    public static final String filterCurrentDate = "current_date",
                               filterCurrentTime = "current_time";
    
    /**
     * ���� true - �� ��� ���������� �������� ������� ������������ ���� ������ �������� �������� ����,
     * ����� - ������� ����������� ����
     * @return 
     */
    boolean isEducDate();
    
    Date getRepDate();
    void setRepDate(Date p_date);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avers.informica.services;

import org.avers.inqry.config.IEducYearBegin;

/**
 *
 * @author alexanderm
 */
public interface IInqryEducYearBegin extends IEducYearBegin {
//        ***************************************************************************
//        ���� � ����� ������ �������� ����. ������������ ��� ������� ��������� ���������
//         �� ������ �������� ���� ������������ ��� �������� ������� ���������. (��� ������: ������=0)
//        ***************************************************************************
    
    void set(IInqryEducYearBegin p_val);
    
}

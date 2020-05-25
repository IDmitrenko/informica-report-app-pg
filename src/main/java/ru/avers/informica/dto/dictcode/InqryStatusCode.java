package ru.avers.informica.dto.dictcode;

/**
 *
 * @author Dias
 */
public class InqryStatusCode {
    private InqryStatusCode() {}
    
    public static final String CREATED_01 = "01",  // �������
                               ACCEPTED_02 = "02", // �������
                               NEED_CORRECTION_03 = "03", // ��������� ������������� ���������
                               HAS_ARRIVED_04 = "04", // ������            
                               CANCELLED_05 = "05", // ���������
                               APPROVED_06 = "06", // ������� ������� � ������ �������
                               ENROLLED_07 = "07", // ��������   
                               REFUSING_08 = "08", // ����� �� ������
                               ARCHIVED_09 = "09", // �����
                               QUEUE_LEAVE_10 = "10", // ����� � ������� (2014-06 �� ���-��)
                               WAITING_FOR_DOCS_11 = "11", // ������� �������������� ���������� (2014-06 �� ���-��)
                               SENT_TO_DOO_12 = "12", // ��������� � ���
                               DIDNT_ARRIVE_13 = "13", // �� ������                        
                               TEMP_HAS_ARRIVED_104 = "104", // ������ ��� ���������� ����������
                               TEMP_APPROVED_106 = "106", // ������� ������� � ������ ��������� �������
                               TEMP_ENROLLED_107 = "107", // �������� ��������
                               TEMP_SENT_TO_DOO_112 = "112", // ��������� � ��� ��� ���������� ����������
                               CANT_GO_TO_DOO_MEDIC_14 = "14"; // �� ����� �������� ��� �� ����������� ����������
}

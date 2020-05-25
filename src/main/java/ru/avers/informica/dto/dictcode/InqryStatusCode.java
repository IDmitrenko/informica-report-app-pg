package ru.avers.informica.dto.dictcode;

/**
 *
 * @author Dias
 */
public class InqryStatusCode {
    private InqryStatusCode() {}
    
    public static final String CREATED_01 = "01",  // Создано
                               ACCEPTED_02 = "02", // Принято
                               NEED_CORRECTION_03 = "03", // Требуется корректировка заявления
                               HAS_ARRIVED_04 = "04", // Явился            
                               CANCELLED_05 = "05", // Отклонено
                               APPROVED_06 = "06", // Принято решение о выдаче путевки
                               ENROLLED_07 = "07", // Зачислен   
                               REFUSING_08 = "08", // Отказ от услуги
                               ARCHIVED_09 = "09", // Архив
                               QUEUE_LEAVE_10 = "10", // Снято с очереди (2014-06 не исп-ся)
                               WAITING_FOR_DOCS_11 = "11", // Ожидает подтверждающих документов (2014-06 не исп-ся)
                               SENT_TO_DOO_12 = "12", // Направлен в ДОО
                               DIDNT_ARRIVE_13 = "13", // Не явился                        
                               TEMP_HAS_ARRIVED_104 = "104", // Явился для временного зачисления
                               TEMP_APPROVED_106 = "106", // Принято решение о выдаче временной путевки
                               TEMP_ENROLLED_107 = "107", // Временно зачислен
                               TEMP_SENT_TO_DOO_112 = "112", // Направлен в ДОО для временного зачисления
                               CANT_GO_TO_DOO_MEDIC_14 = "14"; // Не может посещать ДОО по медицинским показаниям
}

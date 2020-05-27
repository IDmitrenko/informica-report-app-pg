package ru.avers.informica.entities.dicts;

import ru.avers.informica.dto.dictcode.InqryStatusCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// TODO нет соответствия в новой БД
/**
 *
 * @author Dias
 */
@Entity
@Table(name="V_DICT_76_INQRY_STATUS")
@SequenceGenerator(name="SEQ_GEN")
public class CDict76InqryStatus extends CBaseDict implements ICodedDict, IHasDescr {

    public final static String s_dict_code = "76";

    public static final String s_status_code_created = InqryStatusCode.CREATED_01,  // Создано
            s_status_code_accepted = InqryStatusCode.ACCEPTED_02, // Принято
            s_status_code_need_correction = InqryStatusCode.NEED_CORRECTION_03, // Требуется корректировка заявления
            s_status_code_has_arrived = InqryStatusCode.HAS_ARRIVED_04, // Явился
            s_status_code_cancelled = InqryStatusCode.CANCELLED_05, // Отклонено
            s_status_code_approved = InqryStatusCode.APPROVED_06, // Принято решение о выдаче путевки
            s_status_code_enrolled = InqryStatusCode.ENROLLED_07, // Зачислен
            s_status_code_refusing = InqryStatusCode.REFUSING_08, // Отказ от услуги
            s_status_code_archived = InqryStatusCode.ARCHIVED_09, // Архив
            s_status_code_queue_leave = InqryStatusCode.QUEUE_LEAVE_10, // Снято с очереди (2014-06 не исп-ся)
            s_status_code_waiting_for_docs = InqryStatusCode.WAITING_FOR_DOCS_11, // Ожидает подтверждающих документов (2014-06 не исп-ся)
            s_status_code_sent_to_doo = InqryStatusCode.SENT_TO_DOO_12, // Направлен в ДОО
            s_status_code_didnt_arrive = InqryStatusCode.DIDNT_ARRIVE_13, // Не явился
            s_status_code_temp_has_arrived = InqryStatusCode.TEMP_HAS_ARRIVED_104, // Явился для временного зачисления
            s_status_code_temp_approved = InqryStatusCode.TEMP_APPROVED_106, // Принято решение о выдаче временной путевки
            s_status_code_temp_enrolled = InqryStatusCode.TEMP_ENROLLED_107, // Временно зачислен
            s_status_code_temp_sent_to_doo = InqryStatusCode.TEMP_SENT_TO_DOO_112; // Направлен в ДОО для временного зачисления
                            
    public final static String S_ID = "m_id",
                               S_CODE = "m_code";

    public CDict76InqryStatus() { super(); }
    public CDict76InqryStatus(Integer p_id) { super(p_id); }

    @Column(name="CODE")
    private String m_code;
    @Override
    public String getCode(){ return m_code; }
    public void setCode(String p_val) { m_code = p_val; }

    @Column(name="DESCR")
    private String m_descr;
    @Override
    public String getDescr(){ return m_descr; }
    public void setDescr(String p_val) { m_descr = p_val; }

    @Override
    public String toString() {
        return
                CDict76InqryStatus.class.getName() + " {" +
                super.toString() +
                "; code=" + String.valueOf(m_code) +
                "; descr=" + String.valueOf(m_descr) +
                "}";
    }
    
}

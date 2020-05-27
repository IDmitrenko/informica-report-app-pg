package ru.avers.informica.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avers.informica.dto.IDTO;

/**
 *
 * @author Dias
 */
class CInqryEducYearBegin extends DateElement implements IDTO {
    private static final Logger s_logger = LoggerFactory.getLogger(CInqryEducYearBegin.class);
    
    public static final String s_root_name = "inqry-next-educ-year-begin";
    
    public CInqryEducYearBegin() {
    }        
    
    public CInqryEducYearBegin(Integer p_month, Integer p_day) {
        super(null, p_month, p_day);
    }

//***************************************************************************
// День и месяц начала учебного года. Используется для расчета диапазона возрастов
// на начало учебного года отображаемых при проверке статуса заявления. (для месяца: январь=0)
//***************************************************************************
    public void set(CInqryEducYearBegin p_val) {
        if (p_val == null) throw new IllegalArgumentException();

        setMonth(p_val.getMonth());
        setDay(p_val.getDay());
    }
    
}

package ru.avers.informica.common.config;

import ru.avers.informica.dto.IDTO;

/**
 *
 * @author Dias
 */
public class CInqryEducYearBegin extends DateElement implements IDTO {

    public static final String S_ROOT_NAME = "inqry-next-educ-year-begin";
    
    public CInqryEducYearBegin() {
    }        
    
    public CInqryEducYearBegin(Integer pMonth, Integer pDay) {
        super(null, pMonth, pDay);
    }

//***************************************************************************
// День и месяц начала учебного года. Используется для расчета диапазона возрастов
// на начало учебного года отображаемых при проверке статуса заявления. (для месяца: январь=0)
//***************************************************************************
    public void set(CInqryEducYearBegin pVal) {
        if (pVal == null) throw new IllegalArgumentException();

        setMonth(pVal.getMonth());
        setDay(pVal.getDay());
    }
    
}

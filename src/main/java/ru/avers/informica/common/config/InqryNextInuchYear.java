package ru.avers.informica.common.config;

import ru.avers.informica.dto.IDTO;

/**
 * Дата и месяц до которого можно выбрать текущий год в качестве желаемого года поступления.
 * @author Dias
 */
public class InqryNextInuchYear extends DateElement implements IDTO {

    public static final String S_ROOT_NAME = "inqry-next-inuch-year";
    
    public InqryNextInuchYear() {
        super(null, 2, 1);
    }        
    
    public InqryNextInuchYear(Integer pMonth, Integer pDay) {
        super(null, pMonth, pDay);
    }

    public void set(InqryNextInuchYear pVal) {
        if (pVal == null) throw new IllegalArgumentException();

        setMonth(pVal.getMonth());
        setDay(pVal.getDay());
    }
    
}

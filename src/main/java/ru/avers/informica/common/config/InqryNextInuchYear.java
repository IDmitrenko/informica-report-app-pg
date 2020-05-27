package ru.avers.informica.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avers.informica.dto.IDTO;

/**
 * Дата и месяц до которого можно выбрать текущий год в качестве желаемого года поступления.
 * @author Dias
 */
public class InqryNextInuchYear extends DateElement implements IDTO {
    private static final Logger s_logger = LoggerFactory.getLogger(InqryNextInuchYear.class);
    
    public static final String s_root_name = "inqry-next-inuch-year";
    
    public InqryNextInuchYear() {
        super(null, 2, 1);
    }        
    
    public InqryNextInuchYear(Integer p_month, Integer p_day) {
        super(null, p_month, p_day);
    }

    public void set(InqryNextInuchYear p_val) {
        if (p_val == null) throw new IllegalArgumentException();

        setMonth(p_val.getMonth());
        setDay(p_val.getDay());
    }
    
}

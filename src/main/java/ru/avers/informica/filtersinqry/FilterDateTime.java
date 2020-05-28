package ru.avers.informica.filtersinqry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class FilterDateTime extends FilterDate {

    @Override
    protected Date prepDate(Date p_date) {
        return p_date;
    }    
}
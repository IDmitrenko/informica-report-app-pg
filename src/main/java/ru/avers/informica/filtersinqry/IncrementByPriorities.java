package ru.avers.informica.filtersinqry;

import ru.avers.informica.exception.FilterException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class IncrementByPriorities implements IFilter<Object> {

    private String value;
    private String field;

    @Override
    public boolean isPassed(final Object pValue) throws FilterException {
        return true;
    }

    @Override
    public String getField() {
        return field;
    }

    public void setField(final String field) {
        this.field = field;
    }

    @XmlAttribute(name = "value", required = true)
    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}

package ru.avers.informica.common.config;

import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Dias
 */
public class QueueAllocation implements IDTO {
    public static final String S_ROOT_NAME = "queue-allocation",
                               CALCULATE_AGE_ON_DATE = "calculate-age-on-date",
                               CALCULATE_AGE_ON_DATE_TER = "calculate-age-on-date-ter",
                               CALCULATE_AGE_ON_DATE_ADD = "calculate-age-on-date-add",
                               QUEUE_RULES = "queue-rules",
                               QUEUE_RULE = "queue-rule",
                               S_ATTR_SPECIFY_INQRY_HEALTH_NEEDS = "specify-inqry-health-needs";

    private List<CalcAgeElementTer> calcAgeElementTers;
    private CalcAgeElement calcAgeOnDate,
                            calcAgeOnDateAdd;
    private Collection<QueueRule> queueRules;
    private boolean specifyInqryHealthNeeds = false;
    
    public QueueAllocation() {
    }

    /**
     * День и месяц на который вычислять возраст
     * @return
     */
    @XmlElement(name = CALCULATE_AGE_ON_DATE, type = CalcAgeElement.class)
    public CalcAgeElement getCalculateAgeOnDate() {
        if (calcAgeOnDate == null) calcAgeOnDate = new CalcAgeElement();
        return calcAgeOnDate;
    }
    public void setCalculateAgeOnDate(CalcAgeElement pDate) {
        calcAgeOnDate = pDate;
    }

    @XmlElement(name = CALCULATE_AGE_ON_DATE_TER, type = CalcAgeElementTer.class)
    public List<CalcAgeElementTer> getCalculateAgeOnDateTer() {
        if(calcAgeElementTers == null) calcAgeElementTers = new ArrayList<CalcAgeElementTer>();
        return calcAgeElementTers;
    }
    public void setCalculateAgeOnDateTer(List<CalcAgeElementTer> pDateTer) {
        calcAgeElementTers = pDateTer;
    }

    @XmlElement(name = CALCULATE_AGE_ON_DATE_ADD, type = CalcAgeElement.class)
    public CalcAgeElement getCalculateAgeOnDateAdd() {
        if (calcAgeOnDateAdd == null) calcAgeOnDateAdd = new CalcAgeElement(true);
        return calcAgeOnDateAdd;
    }

    public void setCalculateAgeOnDateAdd(CalcAgeElement pDate) {
        calcAgeOnDateAdd = pDate;
    }    

    @XmlElementWrapper(name= QUEUE_RULES)
    @XmlElement(name = QUEUE_RULE)
    public Collection<QueueRule> getQueueRules() {
        if (queueRules == null) queueRules = new HashSet<QueueRule>();
        return queueRules;
    }
    public void setQueueRules(Collection<QueueRule> pQueueRules) {
        queueRules = pQueueRules;
    }    
    
    @XmlAttribute(name = S_ATTR_SPECIFY_INQRY_HEALTH_NEEDS)
    public boolean isSpecifyInqryHealthNeeds() {
        return specifyInqryHealthNeeds;
    }

    public void setSpecifyInqryHealthNeeds(boolean pSpecifyInqryHealthNeeds) {
        this.specifyInqryHealthNeeds = pSpecifyInqryHealthNeeds;
    }    
        
    public final void set(QueueAllocation pVal) {
        if (pVal == null) throw new IllegalArgumentException();
        
        setCalculateAgeOnDate(pVal.getCalculateAgeOnDate());
        setQueueRules(pVal.getQueueRules());
    }

}

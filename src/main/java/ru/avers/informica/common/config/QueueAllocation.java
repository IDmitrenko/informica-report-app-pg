package ru.avers.informica.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Dias
 */
public class QueueAllocation implements IDTO {
    private static final Logger s_logger = LoggerFactory.getLogger(QueueAllocation.class);
    public static final String s_root_name = "queue-allocation",
                               s_calc_on_date = "calculate-age-on-date",
                               s_calc_on_date_ter = "calculate-age-on-date-ter",
                               s_calc_on_date_add = "calculate-age-on-date-add",
                               s_queue_rules = "queue-rules",
                               s_queue_rule = "queue-rule",
                               s_attr_specify_inqry_health_needs = "specify-inqry-health-needs";

    private List<CalcAgeElementTer> m_calc_age_on_date_ter;
    private CalcAgeElement m_calc_age_on_date,
                            m_calc_age_on_date_add;
    private Boolean m_is_restrict_by_curr_in_uch_year;
    private Boolean m_is_restrict_by_curr_in_uch_date;
    private Collection<QueueRule> m_queue_rules;
    private boolean m_specify_inqry_health_needs = false;
    
    public QueueAllocation() {
    }

    /**
     * ƒень и мес€ц на который вычисл€ть возраст
     * @return
     */
    @XmlElement(name = s_calc_on_date, type = CalcAgeElement.class)
    public CalcAgeElement getCalculateAgeOnDate() {
        if (m_calc_age_on_date == null) m_calc_age_on_date = new CalcAgeElement();
        return m_calc_age_on_date;
    }
    public void setCalculateAgeOnDate(CalcAgeElement p_date) {
        m_calc_age_on_date = p_date;
    }

    @XmlElement(name = s_calc_on_date_ter, type = CalcAgeElementTer.class)
    public List<CalcAgeElementTer> getCalculateAgeOnDateTer() {
        if(m_calc_age_on_date_ter == null) m_calc_age_on_date_ter = new ArrayList<CalcAgeElementTer>();
        return m_calc_age_on_date_ter;
    }
    public void setCalculateAgeOnDateTer(List<CalcAgeElementTer> p_date_ter) {
        m_calc_age_on_date_ter = p_date_ter;
    }

    @XmlElement(name = s_calc_on_date_add, type = CalcAgeElement.class)
    public CalcAgeElement getCalculateAgeOnDateAdd() {
        if (m_calc_age_on_date_add == null) m_calc_age_on_date_add = new CalcAgeElement(true);
        return m_calc_age_on_date_add;
    }

    public void setCalculateAgeOnDateAdd(CalcAgeElement p_date) {
        m_calc_age_on_date_add = p_date;
    }    

    @XmlElementWrapper(name=s_queue_rules)
    @XmlElement(name = s_queue_rule)
    public Collection<QueueRule> getQueueRules() {
        if (m_queue_rules == null) m_queue_rules = new HashSet<QueueRule>();
        return m_queue_rules;
    }
    public void setQueueRules(Collection<QueueRule> p_queue_rules) {
        m_queue_rules = p_queue_rules;
    }    
    
    @XmlAttribute(name = s_attr_specify_inqry_health_needs)
    public boolean isSpecifyInqryHealthNeeds() {
        return m_specify_inqry_health_needs;
    }

    public void setSpecifyInqryHealthNeeds(boolean p_specify_inqry_health_needs) {
        this.m_specify_inqry_health_needs = p_specify_inqry_health_needs;
    }    
        
    public final void set(QueueAllocation p_val) {
        if (p_val == null) throw new IllegalArgumentException();
        
        setCalculateAgeOnDate(p_val.getCalculateAgeOnDate());
        setQueueRules(p_val.getQueueRules());
    }

}

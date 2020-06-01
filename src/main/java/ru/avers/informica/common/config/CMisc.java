package ru.avers.informica.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Collection;

/**
 *
 * @author Dias
 */
public class CMisc implements IDTO {
    private static final Logger s_logger = LoggerFactory.getLogger(CMisc.class);

    public static final String s_root_name = "misc";
    private static final String s_elem_esia = "esia";

    private Defaults m_misc_timezone;
    private Boolean m_is_form_inqries_select_uchs_tab_visible,
                    m_is_form_uchs_auto_expand_list,
                    m_is_form_inqries_mf_tabs_visible;
    private Collection<String> m_exclude_lgot_codes,
                               m_exclude_doc_kind_chld_codes,
                               m_exclude_report_codes,
                               m_exclude_status_codes,
                               m_exclude_status_reason_codes;
    private CInqryEducYearBegin m_inqry_educ_year_begin;
    private InqryNextInuchYear m_inqry_next_inuch_year;
    private QueueAllocation m_queue_allocation;
    private Esia m_esia = new Esia();

    private enum TTypeQueue{TOTAL, DISTRICTS, UCHS}

    protected enum AccessLevel{SUPERUSER, ADMIN, COMMISSION, OPERATOR, PRIVILEGED, NONE}
    
    public CMisc() { 
    }

    @XmlElement(name = Defaults.s_root_name, type = Defaults.class)
    public Defaults getDefaults() {
        if (m_misc_timezone == null) m_misc_timezone = new Defaults();
        return m_misc_timezone;
    }

    public void setDefaults(Defaults p_val) {
        m_misc_timezone = p_val;
    }      
    
    @XmlElement(name = CInqryEducYearBegin.s_root_name, type = CInqryEducYearBegin.class)
    public CInqryEducYearBegin getInqryEducYearBegin() {
        if (m_inqry_educ_year_begin == null) {
            m_inqry_educ_year_begin = new CInqryEducYearBegin();
        }
        return m_inqry_educ_year_begin;
    }
    public void setInqryEducYearBegin(CInqryEducYearBegin p_val) {
        m_inqry_educ_year_begin = p_val;
    }

    @XmlElement(name = InqryNextInuchYear.s_root_name, type = InqryNextInuchYear.class)
    public InqryNextInuchYear getInqryNextInuchYear() {
        if (m_inqry_next_inuch_year == null) m_inqry_next_inuch_year = new InqryNextInuchYear();
        return m_inqry_next_inuch_year;
    }
    public void setInqryNextInuchYear(InqryNextInuchYear p_val) {
        m_inqry_next_inuch_year = p_val;
    }      

    @XmlElement(name = QueueAllocation.s_root_name, type = QueueAllocation.class)
    public QueueAllocation getQueueAllocation() {
        if (m_queue_allocation == null) m_queue_allocation = new QueueAllocation();
        return m_queue_allocation;
    }
    
    public void setQueueAllocation(QueueAllocation p_queue_allocation) {
        m_queue_allocation = p_queue_allocation;
    }    

    @XmlElement(name = s_elem_esia, type = Esia.class)
    public Esia getEsia() {
        return m_esia;
    }

    public void setEsia(Esia p_esia) {
        m_esia = p_esia;
    }

    public void set(CMisc p_val) {
        if (p_val == null) throw new IllegalArgumentException();
        
        setInqryEducYearBegin(p_val.getInqryEducYearBegin());
        setInqryNextInuchYear(p_val.getInqryNextInuchYear());
        setEsia(p_val.getEsia());
    }

}

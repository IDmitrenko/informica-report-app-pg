package ru.avers.informica.common.config;

import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Dias
 */
public class CMisc implements IDTO {

    public static final String S_ROOT_NAME = "misc";
    private static final String S_ELEM_ESIA = "esia";

    private Defaults miscTimezone;
    private CInqryEducYearBegin cInqryEducYearBegin;
    private InqryNextInuchYear inqryNextInuchYear;
    private QueueAllocation queueAllocation;
    private Esia esia = new Esia();

    private enum TTypeQueue{TOTAL, DISTRICTS, UCHS}

    protected enum AccessLevel{SUPERUSER, ADMIN, COMMISSION, OPERATOR, PRIVILEGED, NONE}
    
    public CMisc() { 
    }

    @XmlElement(name = Defaults.S_ROOT_NAME, type = Defaults.class)
    public Defaults getDefaults() {
        if (miscTimezone == null) miscTimezone = new Defaults();
        return miscTimezone;
    }

    public void setDefaults(Defaults pVal) {
        miscTimezone = pVal;
    }      
    
    @XmlElement(name = CInqryEducYearBegin.S_ROOT_NAME, type = CInqryEducYearBegin.class)
    public CInqryEducYearBegin getInqryEducYearBegin() {
        if (cInqryEducYearBegin == null) {
            cInqryEducYearBegin = new CInqryEducYearBegin();
        }
        return cInqryEducYearBegin;
    }
    public void setInqryEducYearBegin(CInqryEducYearBegin pVal) {
        cInqryEducYearBegin = pVal;
    }

    @XmlElement(name = InqryNextInuchYear.S_ROOT_NAME, type = InqryNextInuchYear.class)
    public InqryNextInuchYear getInqryNextInuchYear() {
        if (inqryNextInuchYear == null) inqryNextInuchYear = new InqryNextInuchYear();
        return inqryNextInuchYear;
    }
    public void setInqryNextInuchYear(InqryNextInuchYear pVal) {
        inqryNextInuchYear = pVal;
    }      

    @XmlElement(name = QueueAllocation.S_ROOT_NAME, type = QueueAllocation.class)
    public QueueAllocation getQueueAllocation() {
        if (queueAllocation == null) queueAllocation = new QueueAllocation();
        return queueAllocation;
    }
    
    public void setQueueAllocation(QueueAllocation pQueueAllocation) {
        queueAllocation = pQueueAllocation;
    }    

    @XmlElement(name = S_ELEM_ESIA, type = Esia.class)
    public Esia getEsia() {
        return esia;
    }

    public void setEsia(Esia pEsia) {
        esia = pEsia;
    }

    public void set(CMisc pVal) {
        if (pVal == null) throw new IllegalArgumentException();
        
        setInqryEducYearBegin(pVal.getInqryEducYearBegin());
        setInqryNextInuchYear(pVal.getInqryNextInuchYear());
        setEsia(pVal.getEsia());
    }

}

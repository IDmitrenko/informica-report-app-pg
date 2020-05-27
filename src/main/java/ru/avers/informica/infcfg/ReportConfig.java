package ru.avers.informica.infcfg;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "ReportInformica", propOrder = {
    "id",
    "name",
    "mt",
    "calcCapacityOnCntgData",
    "schemas"
})
public class ReportConfig {
    private String m_id,
                   m_name;
    private List<SchemaConfig> m_schemas;
    private boolean m_mt, m_is_calc_capacity_on_cntg_data;

    @XmlAttribute(name = "mt")
    public boolean isMt() {
        return m_mt;
    }
    public void setMt(boolean p_val) {
        m_mt = p_val; }

    @XmlAttribute(name = "is-calc-capacity-on-cntg-data")
    public boolean isCalcCapacityOnCntgData() { return m_is_calc_capacity_on_cntg_data;}
    public void setCalcCapacityOnCntgData(boolean p_val) { m_is_calc_capacity_on_cntg_data = p_val; }

    @XmlAttribute
    public String getId() {
        return m_id;
    }

    public void setId(String id) {
        this.m_id = id;
    }

    @XmlAttribute
    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        this.m_name = name;
    }
    
    @XmlAttribute(name="schemas-ref")
    @XmlList
    @XmlIDREF
    public List<SchemaConfig> getSchemas() {
        if (m_schemas == null) m_schemas = new ArrayList<SchemaConfig>();
        return m_schemas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.m_id != null ? this.m_id.hashCode() : 0);
        hash = 61 * hash + (this.m_name != null ? this.m_name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReportConfig other = (ReportConfig) obj;
        if ((this.m_id == null) ? (other.m_id != null) : !this.m_id.equals(other.m_id)) {
            return false;
        }
        if ((this.m_name == null) ? (other.m_name != null) : !this.m_name.equals(other.m_name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Report [");
        builder.append("getId=").append(getId());
        builder.append(", getName=").append(getName());
        builder.append("]");
        return builder.toString();
    }
        
}
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
    private String id,
                   name;
    private List<SchemaConfig> schemas;
    private boolean isMt, isCalcCapacityOnCntgData;

    @XmlAttribute(name = "mt")
    public boolean isMt() {
        return isMt;
    }
    public void setMt(boolean pVal) {
        isMt = pVal; }

    @XmlAttribute(name = "is-calc-capacity-on-cntg-data")
    public boolean isCalcCapacityOnCntgData() {
        return isCalcCapacityOnCntgData;
    }
    public void setCalcCapacityOnCntgData(boolean pVal) {
        isCalcCapacityOnCntgData = pVal;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String pId) {
        this.id = pId;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String pName) {
        this.name = pName;
    }
    
    @XmlAttribute(name="schemas-ref")
    @XmlList
    @XmlIDREF
    public List<SchemaConfig> getSchemas() {
        if (schemas == null) {
            schemas = new ArrayList<SchemaConfig>();
        }
        return schemas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 61 * hash + (this.name != null ? this.name.hashCode() : 0);
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
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
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
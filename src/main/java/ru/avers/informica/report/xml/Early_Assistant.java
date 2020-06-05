package ru.avers.informica.report.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for early_assistant complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "early_assistant")
public class Early_Assistant {

    @XmlAttribute(name = "fact")
    protected String fact;
    @XmlAttribute(name = "num_hits_personally")
    protected String num_Hits_Personally;
    @XmlAttribute(name = "num_hits_distant")
    protected String num_Hits_Distant;
    @XmlAttribute(name = "forma_1")
    protected String forma_1;
    @XmlAttribute(name = "forma_2")
    protected String forma_2;
    @XmlAttribute(name = "forma_3")
    protected String forma_3;
    @XmlAttribute(name = "forma_4")
    protected String forma_4;
    @XmlAttribute(name = "num_parent_0_3")
    protected String num_Parent_0_3;
    @XmlAttribute(name = "num_parent_3_8")
    protected String num_Parent_3_8;
    @XmlAttribute(name = "num_child_0_3")
    protected String num_Child_0_3;
    @XmlAttribute(name = "num_child_3_8")
    protected String num_Child_3_8;

    public String getFact() {
        if (fact == null) {
            return "0";
        } else {
            return fact;
        }
    }

    public void setFact(String value) {
        this.fact = value;
    }

    public String getNum_Hits_Personally() {
        if (num_Hits_Personally == null) {
            return "0";
        } else {
            return num_Hits_Personally;
        }
    }

    public void setNum_Hits_Personally(String value) {
        this.num_Hits_Personally = value;
    }

    public String getNum_Hits_Distant() {
        if (num_Hits_Distant == null) {
            return "0";
        } else {
            return num_Hits_Distant;
        }
    }

    public void setNum_Hits_Distant(String value) {
        this.num_Hits_Distant = value;
    }

    public String getForma_1() {
        if (forma_1 == null) {
            return "0";
        } else {
            return forma_1;
        }
    }

    public void setForma_1(String value) {
        this.forma_1 = value;
    }

    public String getForma_2() {
        if (forma_2 == null) {
            return "0";
        } else {
            return forma_2;
        }
    }

    public void setForma_2(String value) {
        this.forma_2 = value;
    }

    public String getForma_3() {
        if (forma_3 == null) {
            return "0";
        } else {
            return forma_3;
        }
    }

    public void setForma_3(String value) {
        this.forma_3 = value;
    }

    public String getForma_4() {
        if (forma_4 == null) {
            return "0";
        } else {
            return forma_4;
        }
    }

    public void setForma_4(String value) {
        this.forma_4 = value;
    }

    public String getNum_Parent_0_3() {
        if (num_Parent_0_3 == null) {
            return "0";
        } else {
            return num_Parent_0_3;
        }
    }

    public void setNum_Parent_0_3(String value) {
        this.num_Parent_0_3 = value;
    }

    public String getNum_Parent_3_8() {
        if (num_Parent_3_8 == null) {
            return "0";
        } else {
            return num_Parent_3_8;
        }
    }

    public void setNum_Parent_3_8(String value) {
        this.num_Parent_3_8 = value;
    }

    public String getNum_Child_0_3() {
        if (num_Child_0_3 == null) {
            return "0";
        } else {
            return num_Child_0_3;
        }
    }

    public void setNum_Child_0_3(String value) {
        this.num_Child_0_3 = value;
    }

    public String getNum_Child_3_8() {
        if (num_Child_3_8 == null) {
            return "0";
        } else {
            return num_Child_3_8;
        }
    }

    public void setNum_Child_3_8(String value) {
        this.num_Child_3_8 = value;
    }

}

package ru.avers.informica.report.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for advisory_centr complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "advisory_centr")
public class AdvisoryCentr {

    @XmlAttribute(name = "fact")
    protected String fact;
    @XmlAttribute(name = "num_hits_personally")
    protected String num_Hits_Personally;
    @XmlAttribute(name = "num_hits_distant")
    protected String num_Hits_Distant;
    @XmlAttribute(name = "num_staff_member")
    protected String num_Staff_Member;
    @XmlAttribute(name = "num_freelancer")
    protected String num_Freelancer;
    @XmlAttribute(name = "forma_1")
    protected String forma_1;
    @XmlAttribute(name = "forma_2")
    protected String forma_2;
    @XmlAttribute(name = "forma_3")
    protected String forma_3;
    @XmlAttribute(name = "forma_4")
    protected String forma_4;
    @XmlAttribute(name = "num_parent")
    protected String num_Parent;
    @XmlAttribute(name = "num_parent_family_0_15")
    protected String num_Parent_Family_0_15;
    @XmlAttribute(name = "num_parent_family_15_3")
    protected String num_Parent_Family_15_3;
    @XmlAttribute(name = "num_parent_family_3_7")
    protected String num_Parent_Family_3_7;
    @XmlAttribute(name = "num_parent_family_7_8")
    protected String num_Parent_Family_7_8;
    @XmlAttribute(name = "num_parent_not_edu_0_3")
    protected String num_Parent_Not_Edu_0_3;
    @XmlAttribute(name = "num_parent_not_edu_3_7")
    protected String num_Parent_Not_Edu_3_7;
    @XmlAttribute(name = "num_parent_not_edu_7_8")
    protected String num_Parent_Not_Edu_7_8;
    @XmlAttribute(name = "num_child")
    protected String num_Child;
    @XmlAttribute(name = "num_child_family_0_15")
    protected String num_Child_Family_0_15;
    @XmlAttribute(name = "num_child_family_15_3")
    protected String num_Child_Family_15_3;
    @XmlAttribute(name = "num_child_family_3_7")
    protected String num_Child_Family_3_7;
    @XmlAttribute(name = "num_child_family_7_8")
    protected String num_Child_Family_7_8;
    @XmlAttribute(name = "num_child_not_edu_0_3")
    protected String num_Child_Not_Edu_0_3;
    @XmlAttribute(name = "num_child_not_edu_3_7")
    protected String num_Child_Not_Edu_3_7;
    @XmlAttribute(name = "num_child_not_edu_7_8")
    protected String num_Child_Not_Edu_7_8;

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

    public String getNum_Staff_Member() {
        if (num_Staff_Member == null) {
            return "0";
        } else {
            return num_Staff_Member;
        }
    }

    public void setNum_Staff_Member(String value) {
        this.num_Staff_Member = value;
    }

    public String getNum_Freelancer() {
        if (num_Freelancer == null) {
            return "0";
        } else {
            return num_Freelancer;
        }
    }

    public void setNum_Freelancer(String value) {
        this.num_Freelancer = value;
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

    public String getNum_Parent() {
        if (num_Parent == null) {
            return "0";
        } else {
            return num_Parent;
        }
    }

    public void setNum_Parent(String value) {
        this.num_Parent = value;
    }

    public String getNum_Parent_Family_0_15() {
        if (num_Parent_Family_0_15 == null) {
            return "0";
        } else {
            return num_Parent_Family_0_15;
        }
    }

    public void setNum_Parent_Family_0_15(String value) {
        this.num_Parent_Family_0_15 = value;
    }

    public String getNum_Parent_Family_15_3() {
        if (num_Parent_Family_15_3 == null) {
            return "0";
        } else {
            return num_Parent_Family_15_3;
        }
    }

    public void setNum_Parent_Family_15_3(String value) {
        this.num_Parent_Family_15_3 = value;
    }

    public String getNum_Parent_Family_3_7() {
        if (num_Parent_Family_3_7 == null) {
            return "0";
        } else {
            return num_Parent_Family_3_7;
        }
    }

    public void setNum_Parent_Family_3_7(String value) {
        this.num_Parent_Family_3_7 = value;
    }

    public String getNum_Parent_Family_7_8() {
        if (num_Parent_Family_7_8 == null) {
            return "0";
        } else {
            return num_Parent_Family_7_8;
        }
    }

    public void setNum_Parent_Family_7_8(String value) {
        this.num_Parent_Family_7_8 = value;
    }

    public String getNum_Parent_Not_Edu_0_3() {
        if (num_Parent_Not_Edu_0_3 == null) {
            return "0";
        } else {
            return num_Parent_Not_Edu_0_3;
        }
    }

    public void setNum_Parent_Not_Edu_0_3(String value) {
        this.num_Parent_Not_Edu_0_3 = value;
    }

    public String getNum_Parent_Not_Edu_3_7() {
        if (num_Parent_Not_Edu_3_7 == null) {
            return "0";
        } else {
            return num_Parent_Not_Edu_3_7;
        }
    }

    public void setNum_Parent_Not_Edu_3_7(String value) {
        this.num_Parent_Not_Edu_3_7 = value;
    }

    public String getNum_Parent_Not_Edu_7_8() {
        if (num_Parent_Not_Edu_7_8 == null) {
            return "0";
        } else {
            return num_Parent_Not_Edu_7_8;
        }
    }

    public void setNum_Parent_Not_Edu_7_8(String value) {
        this.num_Parent_Not_Edu_7_8 = value;
    }

    public String getNum_Child() {
        if (num_Child == null) {
            return "0";
        } else {
            return num_Child;
        }
    }

    public void setNum_Child(String value) {
        this.num_Child = value;
    }

    public String getNum_Child_Family_0_15() {
        if (num_Child_Family_0_15 == null) {
            return "0";
        } else {
            return num_Child_Family_0_15;
        }
    }

    public void setNum_Child_Family_0_15(String value) {
        this.num_Child_Family_0_15 = value;
    }

    public String getNum_Child_Family_15_3() {
        if (num_Child_Family_15_3 == null) {
            return "0";
        } else {
            return num_Child_Family_15_3;
        }
    }

    public void setNum_Child_Family_15_3(String value) {
        this.num_Child_Family_15_3 = value;
    }

    public String getNum_Child_Family_3_7() {
        if (num_Child_Family_3_7 == null) {
            return "0";
        } else {
            return num_Child_Family_3_7;
        }
    }

    public void setNum_Child_Family_3_7(String value) {
        this.num_Child_Family_3_7 = value;
    }

    public String getNum_Child_Family_7_8() {
        if (num_Child_Family_7_8 == null) {
            return "0";
        } else {
            return num_Child_Family_7_8;
        }
    }

    public void setNum_Child_Family_7_8(String value) {
        this.num_Child_Family_7_8 = value;
    }

    public String getNum_Child_Not_Edu_0_3() {
        if (num_Child_Not_Edu_0_3 == null) {
            return "0";
        } else {
            return num_Child_Not_Edu_0_3;
        }
    }

    public void setNum_Child_Not_Edu_0_3(String value) {
        this.num_Child_Not_Edu_0_3 = value;
    }

    public String getNum_Child_Not_Edu_3_7() {
        if (num_Child_Not_Edu_3_7 == null) {
            return "0";
        } else {
            return num_Child_Not_Edu_3_7;
        }
    }

    public void setNum_Child_Not_Edu_3_7(String value) {
        this.num_Child_Not_Edu_3_7 = value;
    }

    public String getNum_Child_Not_Edu_7_8() {
        if (num_Child_Not_Edu_7_8 == null) {
            return "0";
        } else {
            return num_Child_Not_Edu_7_8;
        }
    }

    public void setNum_Child_Not_Edu_7_8(String value) {
        this.num_Child_Not_Edu_7_8 = value;
    }

}

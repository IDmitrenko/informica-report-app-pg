package ru.avers.informica.report.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;


/**
 * Информация о группе
 * 
 * <p>Java class for tag_single_group complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tag_single_group")
public class TagSingleGroup {

    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "age_from", required = true)
    protected String age_From;
    @XmlAttribute(name = "age_to", required = true)
    protected String age_To;
    @XmlAttribute(name = "orientation", required = true)
    protected String orientation;
    @XmlAttribute(name = "worktime_group", required = true)
    protected String worktime_Group;
    @XmlAttribute(name = "activity", required = true)
    protected String activity;
    @XmlAttribute(name = "capacity", required = true)
    protected BigInteger capacity;
    @XmlAttribute(name = "enrolled", required = true)
    protected BigInteger enrolled;
    @XmlAttribute(name = "subgroup", required = true)
    protected String subgroup;
    @XmlAttribute(name = "ovz_deti", required = true)
    protected BigInteger ovz_Deti;
    @XmlAttribute(name = "free_space", required = true)
    protected BigInteger free_Space;
    @XmlAttribute(name = "add_cont", required = true)
    protected BigInteger add_Cont;
    @XmlAttribute(name = "transfer_space", required = true)
    protected BigInteger transfer_Space;
    @XmlAttribute(name = "partner_group", required = true)
    protected String partner_Group;
    @XmlAttribute(name = "partner", required = true)
    protected String partner;
    @XmlAttribute(name = "ovz_type")
    protected String ovz_Type;
    @XmlAttribute(name = "wellness")
    protected String wellness;
    @XmlAttribute(name = "program")
    protected String program;
    @XmlAttribute(name = "program_ovz")
    protected String program_Ovz;
    @XmlAttribute(name = "size")
    protected String size;
    @XmlAttribute(name = "capacity_gkp")
    protected String capacity_Gkp;
    @XmlAttribute(name = "enrolled_gkp")
    protected String enrolled_Gkp;
    @XmlAttribute(name = "invalid")
    protected String invalid;
    @XmlAttribute(name = "add_cont_gkp")
    protected String add_Cont_Gkp;
    @XmlAttribute(name = "add_cont_ovz")
    protected String add_Cont_Ovz;
    @XmlAttribute(name = "reduction_school")
    protected String reduction_School;
    @XmlAttribute(name = "reduction_other")
    protected String reduction_Other;
    @XmlAttribute(name = "educator")
    protected String educator;
    @XmlAttribute(name = "days")
    protected String days;

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getAge_From() {
        return age_From;
    }

    public void setAge_From(String value) {
        this.age_From = value;
    }

    public String getAge_To() {
        return age_To;
    }

    public void setAge_To(String value) {
        this.age_To = value;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String value) {
        this.orientation = value;
    }

    public String getWorktime_Group() {
        return worktime_Group;
    }

    public void setWorktime_Group(String value) {
        this.worktime_Group = value;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String value) {
        this.activity = value;
    }

    public BigInteger getCapacity() {
        return capacity;
    }

    public void setCapacity(BigInteger value) {
        this.capacity = value;
    }

    public BigInteger getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(BigInteger value) {
        this.enrolled = value;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String value) {
        this.subgroup = value;
    }

    public BigInteger getOvz_Deti() {
        return ovz_Deti;
    }

    public void setOvz_Deti(BigInteger value) {
        this.ovz_Deti = value;
    }

    public BigInteger getFree_Space() {
        return free_Space;
    }

    public void setFree_Space(BigInteger value) {
        this.free_Space = value;
    }

    public BigInteger getAdd_Cont() {
        return add_Cont;
    }

    public void setAdd_Cont(BigInteger value) {
        this.add_Cont = value;
    }

    public BigInteger getTransfer_Space() {
        return transfer_Space;
    }

    public void setTransfer_Space(BigInteger value) {
        this.transfer_Space = value;
    }

    public String getPartner_Group() {
        return partner_Group;
    }

    public void setPartner_Group(String value) {
        this.partner_Group = value;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String value) {
        this.partner = value;
    }

    public String getOvz_Type() {
        return ovz_Type;
    }

    public void setOvz_Type(String value) {
        this.ovz_Type = value;
    }

    public String getWellness() {
        return wellness;
    }

    public void setWellness(String value) {
        this.wellness = value;
    }

    public String getProgram() {
        if (program == null) {
            return "1";
        } else {
            return program;
        }
    }

    public void setProgram(String value) {
        this.program = value;
    }

    public String getProgram_Ovz() {
        if (program_Ovz == null) {
            return "0";
        } else {
            return program_Ovz;
        }
    }

    public void setProgram_Ovz(String value) {
        this.program_Ovz = value;
    }

    public String getSize() {
        if (size == null) {
            return "50";
        } else {
            return size;
        }
    }

    public void setSize(String value) {
        this.size = value;
    }

    public String getCapacity_Gkp() {
        if (capacity_Gkp == null) {
            return "0";
        } else {
            return capacity_Gkp;
        }
    }

    public void setCapacity_Gkp(String value) {
        this.capacity_Gkp = value;
    }

    public String getEnrolled_Gkp() {
        if (enrolled_Gkp == null) {
            return "0";
        } else {
            return enrolled_Gkp;
        }
    }

    public void setEnrolled_Gkp(String value) {
        this.enrolled_Gkp = value;
    }

    public String getInvalid() {
        if (invalid == null) {
            return "0";
        } else {
            return invalid;
        }
    }

    public void setInvalid(String value) {
        this.invalid = value;
    }

    public String getAdd_Cont_Gkp() {
        if (add_Cont_Gkp == null) {
            return "0";
        } else {
            return add_Cont_Gkp;
        }
    }

    public void setAdd_Cont_Gkp(String value) {
        this.add_Cont_Gkp = value;
    }

    public String getAdd_Cont_Ovz() {
        if (add_Cont_Ovz == null) {
            return "0";
        } else {
            return add_Cont_Ovz;
        }
    }

    public void setAdd_Cont_Ovz(String value) {
        this.add_Cont_Ovz = value;
    }

    public String getReduction_School() {
        if (reduction_School == null) {
            return "0";
        } else {
            return reduction_School;
        }
    }

    public void setReduction_School(String value) {
        this.reduction_School = value;
    }

    public String getReduction_Other() {
        if (reduction_Other == null) {
            return "0";
        } else {
            return reduction_Other;
        }
    }

    public void setReduction_Other(String value) {
        this.reduction_Other = value;
    }

    public String getEducator() {
        if (educator == null) {
            return "0";
        } else {
            return educator;
        }
    }

    public void setEducator(String value) {
        this.educator = value;
    }

    public String getDays() {
        if (days == null) {
            return "0";
        } else {
            return days;
        }
    }

    public void setDays(String value) {
        this.days = value;
    }

}

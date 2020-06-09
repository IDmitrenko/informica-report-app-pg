package ru.avers.informica.report.xml;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for tag_municipality complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tag_municipality", propOrder = {
    "common",
    "organizations"
})
public class TagMunicipality {

    @XmlElement(required = true)
    protected TagCommon common;
    @XmlElement(required = true)
    protected TagOrganizations organizations;
    @XmlAttribute(name = "oktmo", required = true)
    protected String oktmo;
    @XmlAttribute(name = "epgu_link", required = true)
    protected String epgu_Link;
    @XmlAttribute(name = "rpgu_link", required = true)
    protected String rpgu_Link;
    @XmlAttribute(name = "address_mouo", required = true)
    protected String address_Mouo;
    @XmlAttribute(name = "name_mouo", required = true)
    protected String name_Mouo;
    @XmlAttribute(name = "site_mouo", required = true)
    protected String site_Mouo;
    @XmlAttribute(name = "email_mouo", required = true)
    protected String email_Mouo;
    @XmlAttribute(name = "phones_mouo", required = true)
    protected String phones_Mouo;
    @XmlAttribute(name = "regulation", required = true)
    protected String regulation;
    @XmlAttribute(name = "fix_area", required = true)
    protected String fix_Area;
    @XmlAttribute(name = "time_mouo")
    protected String time_Mouo;
    @XmlAttribute(name = "max_doo")
    protected String max_Doo;
    @XmlAttribute(name = "num_advisory_centr")
    protected String num_Advisory_Centr;
    @XmlAttribute(name = "num_early_assistance")
    protected String num_Early_Assistance;

    public TagCommon getCommon() {
        return common;
    }

    public void setCommon(TagCommon value) {
        this.common = value;
    }

    public TagOrganizations getOrganizations() {
        return organizations;
    }

    public void setOrganizations(TagOrganizations value) {
        this.organizations = value;
    }

    public String getOktmo() {
        return oktmo;
    }

    public void setOktmo(String value) {
        this.oktmo = value;
    }

    public String getEpgu_Link() {
        return epgu_Link;
    }

    public void setEpgu_Link(String value) {
        this.epgu_Link = value;
    }

    public String getRpgu_Link() {
        return rpgu_Link;
    }

    public void setRpgu_Link(String value) {
        this.rpgu_Link = value;
    }

    public String getAddress_Mouo() {
        return address_Mouo;
    }

    public void setAddress_Mouo(String value) {
        this.address_Mouo = value;
    }

    public String getName_Mouo() {
        return name_Mouo;
    }

    public void setName_Mouo(String value) {
        this.name_Mouo = value;
    }

    public String getSite_Mouo() {
        return site_Mouo;
    }

    public void setSite_Mouo(String value) {
        this.site_Mouo = value;
    }

    public String getEmail_Mouo() {
        return email_Mouo;
    }

    public void setEmail_Mouo(String value) {
        this.email_Mouo = value;
    }

    public String getPhones_Mouo() {
        return phones_Mouo;
    }

    public void setPhones_Mouo(String value) {
        this.phones_Mouo = value;
    }

    public String getRegulation() {
        return regulation;
    }

    public void setRegulation(String value) {
        this.regulation = value;
    }

    public String getFix_Area() {
        return fix_Area;
    }

    public void setFix_Area(String value) {
        this.fix_Area = value;
    }

    public String getTime_Mouo() {
        if (time_Mouo == null) {
            return "\u041f\u043d, \u0421\u0440, \u041f\u0442 \u0441 9 \u0434\u043e 17";
        } else {
            return time_Mouo;
        }
    }

    public void setTime_Mouo(String value) {
        this.time_Mouo = value;
    }

    public String getMax_Doo() {
        if (max_Doo == null) {
            return "5";
        } else {
            return max_Doo;
        }
    }

    public void setMax_Doo(String value) {
        this.max_Doo = value;
    }

    public String getNum_Advisory_Centr() {
        if (num_Advisory_Centr == null) {
            return "2";
        } else {
            return num_Advisory_Centr;
        }
    }

    public void setNum_Advisory_Centr(String value) {
        this.num_Advisory_Centr = value;
    }

    public String getNum_Early_Assistance() {
        if (num_Early_Assistance == null) {
            return "0";
        } else {
            return num_Early_Assistance;
        }
    }

    public void setNum_Early_Assistance(String value) {
        this.num_Early_Assistance = value;
    }

}

package ru.avers.informica.report.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * Список ДОО в муниципалитете с их значениями
 * 
 * <p>Java class for tag_organizations complex type.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tag_organizations", propOrder = {
    "organization"
})
public class Tag_Organizations {

    @XmlElement(required = true)
    protected List<Tag_Single_Organization> organization;

    /**
     * Gets the value of the organization property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the organization property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrganization().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tag_Single_Organization }
     * 
     * 
     */
    public List<Tag_Single_Organization> getOrganization() {
        if (organization == null) {
            organization = new ArrayList<Tag_Single_Organization>();
        }
        return this.organization;
    }

}

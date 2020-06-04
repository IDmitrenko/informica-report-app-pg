package ru.avers.informica.report.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * tag reports
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tag_reports", propOrder = {
    "parent_Pay",
    "municipality"
})
public class Tag_Reports {

    @XmlElement(required = true)
    protected List<Tag_Municipality> municipality;
    @XmlElement(name = "parent_pay", required = true)
    protected Tag_Parent_Pay parent_Pay;

    public Tag_Parent_Pay getParent_Pay() {
        return parent_Pay;
    }

    public void setParent_Pay(Tag_Parent_Pay value) {
        this.parent_Pay = value;
    }

    public List<Tag_Municipality> getMunicipality() {
        if (municipality == null) {
            municipality = new ArrayList<Tag_Municipality>();
        }
        return this.municipality;
    }
}

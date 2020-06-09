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
public class TagReports {

    @XmlElement(required = true)
    protected List<TagMunicipality> municipality;
    @XmlElement(name = "parent_pay", required = true)
    protected TagParentPay parent_Pay;

    public TagParentPay getParent_Pay() {
        return parent_Pay;
    }

    public void setParent_Pay(TagParentPay value) {
        this.parent_Pay = value;
    }

    public List<TagMunicipality> getMunicipality() {
        if (municipality == null) {
            municipality = new ArrayList<TagMunicipality>();
        }
        return this.municipality;
    }
}

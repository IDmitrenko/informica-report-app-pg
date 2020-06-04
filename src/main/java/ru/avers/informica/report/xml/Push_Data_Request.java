package ru.avers.informica.report.xml;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "push_data_request")
@Getter
@Setter
public class Push_Data_Request {

    @XmlElement(name = "schema_version", required = true, defaultValue = "5.0")
    protected String schema_Version;
    @XmlElement(required = true)
    protected Tag_System system;
    @XmlElement(required = true)
    protected Tag_Reports reports;

}

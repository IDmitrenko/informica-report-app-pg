package ru.avers.informica.report.xml;

import lombok.Getter;
import lombok.Setter;
import ru.avers.informica.dto.IDTO;
import ru.avers.informica.exception.FspeoException;
import ru.avers.informica.infcfg.CounterConfig;
import ru.avers.informica.infcfg.SystemInfo;
import ru.avers.informica.infcfg.TypeSchemaVersion;
import ru.avers.informica.report.Counter;
import ru.avers.informica.report.IReport;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.OutputStream;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "schemaVersion",
        "system",
        "reports"
})
@XmlRootElement(name = "push_data_request")
@Setter
@Getter
public class PushDataRequest implements IDTO {

    public PushDataRequest() {
    }

    public PushDataRequest(TypeSchemaVersion p_schema_version) {
        this.schemaVersion = p_schema_version;
    }

    @XmlElement(name = "schema_version", required = true, defaultValue = "5.0")
    protected TypeSchemaVersion schemaVersion;
/*
    public TypeSchemaVersion getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(TypeSchemaVersion typeSchemaVersion) {
        this.schemaVersion = typeSchemaVersion;
    }
*/
    @XmlElement(required = true)
    protected TagSystem system;
    @XmlElement(required = true)
    protected TagReports reports;
}

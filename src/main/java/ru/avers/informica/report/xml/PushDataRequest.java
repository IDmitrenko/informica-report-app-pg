package ru.avers.informica.report.xml;

import ru.avers.informica.exception.FspeoException;
import ru.avers.informica.infcfg.CounterConfig;
import ru.avers.informica.infcfg.SystemInfo;
import ru.avers.informica.infcfg.TypeSchemaVersion;
import ru.avers.informica.report.Counter;
import ru.avers.informica.report.IPushDataRequest;
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
public class PushDataRequest implements IPushDataRequest {

    protected TypeSchemaVersion schemaVersion;

    public PushDataRequest() {
    }

    public PushDataRequest(TypeSchemaVersion p_schema_version) {
        this.schemaVersion = p_schema_version;
    }

    @XmlElement(name = "schema_version", required = true, defaultValue = "5.0")
    @Override
    public TypeSchemaVersion getSchemaVersion() {
        return schemaVersion;
    }
    @Override
    public void setSchemaVersion(TypeSchemaVersion typeSchemaVersion) {
        this.schemaVersion = typeSchemaVersion;
    }

    @XmlElement(required = true)
    protected TagSystem system;
    @XmlElement(required = true)
    protected TagReports reports;


    @Override
    public void setSystem(SystemInfo system) {

    }

    @Override
    public void setReportDate(XMLGregorianCalendar x_report_date_xml) {

    }

    @Override
    public void setSendTime(XMLGregorianCalendar x_send_time_xml) {

    }

    @Override
    public IReport createReport() {
        return null;
    }

    @Override
    public void sortReports() {

    }

    @Override
    public Counter createCounter(CounterConfig p_counter_config, boolean p_details) {
        return null;
    }

    @Override
    public IReport getOrgReport() {
        return null;
    }

    @Override
    public void toOutputStream(OutputStream p_output_stream) throws JAXBException {

    }

    @Override
    public void toExcel(String p_report_template, String p_temp_file_path) throws FspeoException {

    }
}

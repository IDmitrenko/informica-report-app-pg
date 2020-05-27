package ru.avers.informica.infcfg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.avers.informica.dto.IDTO;
import ru.avers.informica.utils.xml.CUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "config", propOrder={
    "endpoints",
    "system",
    "reports",
    "schemas",
    "countersDef",
    "ageRangesDef",
    "ovzTypeDef",
    "orientationDef"
})
public class ConfigInformica implements Serializable {
    private static final Logger s_logger = LoggerFactory.getLogger(ConfigInformica.class);
    
    public final static String s_informica_report = "informica";

    final static private String s_root_nm = "config";

    public ConfigInformica() {

    }
    
    private List<ServiceEndpoint> m_informica_service_endpoint;
    private Integer m_version;
    private SystemInfo m_system;
    private List<ReportConfig> m_reports;
    private List<SchemaConfig> m_schemas;
    private List<CounterDef> m_counters_def;
    private List<AgeRangeDef> m_age_ranges_def;
    private List<OvzTypeDef> m_ovz_type_def;
    private List<OrientationDef> m_orientation_def;
            
    @XmlElement(name = "informica-service-endpoint")
    public List<ServiceEndpoint> getEndpoints() {
        if (m_informica_service_endpoint == null) {
            m_informica_service_endpoint = new ArrayList<ServiceEndpoint>();
        }
        return m_informica_service_endpoint;
    }
    
    @XmlElement(name = "system")
    public SystemInfo getSystem() { return m_system; }
    public void setSystem(SystemInfo p_system) {
        m_system = p_system;
    }
    
    @XmlAttribute
    public Integer getVersion() {
        return m_version;
    }
    public void setVersion(Integer p_version) {
        m_version = p_version;
    }

    @XmlElement(name = "report-informica")
    public List<ReportConfig> getReports() {
        if (m_reports == null) {
            m_reports = new ArrayList<ReportConfig>();
        }
        return m_reports;
    }

    @XmlElementWrapper(name = "counters-def")
    @XmlElement(name = "counter-def")
    public List<CounterDef> getCountersDef() {
        if (m_counters_def == null) {
            m_counters_def = new ArrayList<CounterDef>();
        }
        return m_counters_def;
    }

    @XmlElementWrapper(name = "age-ranges-def")
    @XmlElement(name = "age-range-def")
    public List<AgeRangeDef> getAgeRangesDef() {
        if (m_age_ranges_def == null) {
            m_age_ranges_def = new ArrayList<AgeRangeDef>();
        }
        return m_age_ranges_def;
    }

    @XmlElementWrapper(name = "ovz-def")
    @XmlElement(name = "item")
    public List<OvzTypeDef> getOvzTypeDef() {
        if (m_ovz_type_def == null) {
            m_ovz_type_def = new ArrayList<OvzTypeDef>();
        }
        return m_ovz_type_def;
    }

    public Map<String, TypeOvz> getMapTypeOvzDev() {
        Map<String, TypeOvz> x_map = new HashMap<String, TypeOvz>();
        for (OvzTypeDef x_ovz_def : getOvzTypeDef()) {
            x_map.put(x_ovz_def.getKey(), x_ovz_def.getTypeOvz());
        }
        return x_map;
    }    
    
    @XmlElementWrapper(name = "orientation-def")
    @XmlElement(name = "item")
    public List<OrientationDef> getOrientationDef() {
        if (m_orientation_def == null) {
            m_orientation_def = new ArrayList<OrientationDef>();
        }
        return m_orientation_def;
    }    
    
    private volatile Map<String, OrientationDef> m_map_orientation;
    public Map<String, OrientationDef> getMapOrientation() {
        if (m_map_orientation != null) {
            return m_map_orientation;
        }
        synchronized(this) {
            if (m_map_orientation != null) {
                return m_map_orientation;
            }
            Map<String, OrientationDef> x_map = new HashMap<String, OrientationDef>();
            for (OrientationDef x_def : getOrientationDef()) {
                x_map.put(x_def.getKey(), x_def);
            }
            m_map_orientation = Collections.unmodifiableMap(x_map);
            return m_map_orientation;
        }
    }
    
    public ReportConfig getReport(String p_id_report) {
        for (ReportConfig x_report : m_reports) {
            if (p_id_report.equals(x_report.getId())) {
                return x_report;
            }
        }
        return null;
    }
    
    @XmlElement(name="schema-informica")
    public List<SchemaConfig> getSchemas() {
        if (m_schemas == null) {
            m_schemas = new ArrayList<SchemaConfig>();
        }
        return m_schemas;
    }
    
    final private static JAXBContext s_jaxb_ctx;
    public static JAXBContext getJAXBContext() {
        return s_jaxb_ctx;
    }
    static {
        JAXBContext x_jaxb_ctx = null;
        try {
            x_jaxb_ctx = JAXBContext.newInstance(ConfigInformica.class);
        } catch(JAXBException p_ex) {
            s_logger.error("instantiate JAXBContext for " + ConfigInformica.class.getName(), p_ex);
        }
        s_jaxb_ctx = x_jaxb_ctx;
    }

    @Override
    public String toString() {
        return s_jaxb_ctx == null ? 
                                null : 
                                CUtil.toStringXML(
                                        new JAXBElement<ConfigInformica>(
                                                                 new QName(s_root_nm),
                                                                 ConfigInformica.class,
                                                            null,
                                                             this),
                                        s_jaxb_ctx,
                            true);
    }    
    
    public boolean toOutputStream(OutputStream p_os) {
        return s_jaxb_ctx == null ? false : CUtil.toOutputStream(p_os,
            new JAXBElement<ConfigInformica>(new QName(s_root_nm),
                    ConfigInformica.class, null, this), s_jaxb_ctx, true);
    }
    
    static public ConfigInformica reestablish(String p_val) {
        return CUtil.<ConfigInformica> reestablish(p_val, ConfigInformica.class, s_jaxb_ctx);
    }    
    
}

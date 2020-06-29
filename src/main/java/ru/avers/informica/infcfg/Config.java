package ru.avers.informica.infcfg;

import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.*;
import java.util.*;

/**
 *
 * @author Dias
 */
@XmlRootElement
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
public class Config implements IDTO {

    public final static String S_INFORMICA_REPORT = "informica";

    final static private String S_ROOT_NM = "config";

    public Config() {

    }
    
    private List<ServiceEndpoint> endpoints;
    private Integer version;
    private SystemInfoType system;
    private List<ReportConfig> reports;
    private List<SchemaConfig> schemas;
    private List<CounterDef> countersDef;
    private List<AgeRangeDef> ageRangesDef;
    private List<OvzTypeDef> ovzTypeDef;
    private List<OrientationDef> orientationDef;

    @XmlAttribute
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer pVersion) {
        version = pVersion;
    }

    @XmlElement(name = "informica-service-endpoint")
    public List<ServiceEndpoint> getEndpoints() {
        if (endpoints == null) {
            endpoints = new ArrayList<ServiceEndpoint>();
        }
        return endpoints;
    }
    @XmlType(name = "")
    public static class SystemInfoType extends SystemInfo {}

    @XmlElement(name = "system")
    public SystemInfoType getSystem() { return system; }
    public void setSystem(SystemInfoType pSystem) {
        system = pSystem;
    }
    
    @XmlElement(name = "report-informica")
    public List<ReportConfig> getReports() {
        if (reports == null) {
            reports = new ArrayList<ReportConfig>();
        }
        return reports;
    }

    @XmlElementWrapper(name = "counters-def")
    @XmlElement(name = "counter-def")
    public List<CounterDef> getCountersDef() {
        if (countersDef == null) {
            countersDef = new ArrayList<CounterDef>();
        }
        return countersDef;
    }

    @XmlElementWrapper(name = "age-ranges-def")
    @XmlElement(name = "age-range-def")
    public List<AgeRangeDef> getAgeRangesDef() {
        if (ageRangesDef == null) {
            ageRangesDef = new ArrayList<AgeRangeDef>();
        }
        return ageRangesDef;
    }

// 28.05.2020  убрал, так как такой секции нет в файле
    @XmlElementWrapper(name = "ovz-def")
    @XmlElement(name = "item")
    public List<OvzTypeDef> getOvzTypeDef() {
        if (ovzTypeDef == null) {
            ovzTypeDef = new ArrayList<OvzTypeDef>();
        }
        return ovzTypeDef;
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
        if (orientationDef == null) {
            orientationDef = new ArrayList<OrientationDef>();
        }
        return orientationDef;
    }    
    
    private volatile Map<String, OrientationDef> orientationDefMap;
    public Map<String, OrientationDef> getMapOrientation() {
        if (orientationDefMap != null) {
            return orientationDefMap;
        }
        synchronized(this) {
            if (orientationDefMap != null) {
                return orientationDefMap;
            }
            Map<String, OrientationDef> defMap = new HashMap<String, OrientationDef>();
            for (OrientationDef orientationDef : getOrientationDef()) {
                defMap.put(orientationDef.getKey(), orientationDef);
            }
            orientationDefMap = Collections.unmodifiableMap(defMap);
            return orientationDefMap;
        }
    }
    
    public ReportConfig getReport(String pIdReport) {
        for (ReportConfig report : reports) {
            if (pIdReport.equals(report.getId())) {
                return report;
            }
        }
        return null;
    }
    
    @XmlElement(name="schema-informica")
    public List<SchemaConfig> getSchemas() {
        if (schemas == null) {
            schemas = new ArrayList<SchemaConfig>();
        }
        return schemas;
    }

}

package ru.avers.informica.report.xml;

import ru.avers.informica.infcfg.TypeInstall;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Информация об установленной системе
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tag_system")
public class TagSystem {

    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "vendor", required = true)
    protected String vendor;
    @XmlAttribute(name = "version", required = true)
    protected String version;
    @XmlAttribute(name = "email", required = true)
    protected String email;
    @XmlAttribute(name = "install_type", required = true)
    protected TypeInstall install_Type;
    @XmlAttribute(name = "own_server")
    protected String own_Server;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String value) {
        this.vendor = value;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String value) {
        this.version = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public TypeInstall getInstall_Type() {
        return install_Type;
    }

    public void setInstall_Type(TypeInstall value) {
        this.install_Type = value;
    }

    public String getOwn_Server() {
        if (own_Server == null) {
            return "3";
        } else {
            return own_Server;
        }
    }

    public void setOwn_Server(String value) {
        this.own_Server = value;
    }


}

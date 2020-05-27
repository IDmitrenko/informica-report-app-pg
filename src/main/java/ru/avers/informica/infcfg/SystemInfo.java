/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.avers.informica.infcfg;

import org.avers.fspeo.v2.*;

import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="vendor" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="email" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="installType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Региональная"/>
 *             &lt;enumeration value="Муниципальная"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;anyAttribute/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(value = XmlAccessType.PROPERTY)
@XmlTransient
public class SystemInfo {

    protected String name;
    protected String version;
    protected String vendor;
    protected String email;
    protected TypeInstall installType;
    protected String own_server;
    private final Map<QName, String> otherAttributes = new HashMap<QName, String>();

    @XmlAttribute(name = "name", required = true)
    public String getName() {
        return name;
    }
    public void setName(String value) {
        this.name = value;
    }

    @XmlAttribute(name = "version", required = true)
    public String getVersion() {
        return version;
    }
    public void setVersion(String value) {
        this.version = value;
    }

    @XmlAttribute(name = "own_server", required = false)
    public String getOwn_server() {
        return own_server;
    }

    public void setOwn_server(String own_server) {
        this.own_server = own_server;
    }



    @XmlAttribute(name = "vendor", required = true)
    public String getVendor() {
        return vendor;
    }
    public void setVendor(String value) {
        this.vendor = value;
    }

    @XmlAttribute(name = "email", required = true)
    public String getEmail() {
        return email;
    }
    public void setEmail(String value) {
        this.email = value;
    }

    @XmlAttribute(name = "installType", required = true)
    public TypeInstall getInstallType() {
        return installType;
    }
    public void setInstallType(TypeInstall value) {
        this.installType = value;
    }

    @XmlAnyAttribute
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 37 * hash + (this.version != null ? this.version.hashCode() : 0);
        hash = 37 * hash + (this.vendor != null ? this.vendor.hashCode() : 0);
        hash = 37 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 37 * hash + (this.installType != null ? this.installType.hashCode() : 0);
        hash = 37 * hash + (this.otherAttributes != null ? this.otherAttributes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SystemInfo other = (SystemInfo) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.version == null) ? (other.version != null) : !this.version.equals(other.version)) {
            return false;
        }
        if ((this.vendor == null) ? (other.vendor != null) : !this.vendor.equals(other.vendor)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if (this.installType != other.installType) {
            return false;
        }
        if (this.otherAttributes != other.otherAttributes && (this.otherAttributes == null || !this.otherAttributes.equals(other.otherAttributes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SystemInfo [");
        builder.append("getEmail=").append(getEmail());
        builder.append(", getInstallType=").append(getInstallType());
        builder.append(", getName=").append(getName());
        builder.append(", getVendor=").append(getVendor());
        builder.append(", getVersion=").append(getVersion());
        builder.append("]");
        return builder.toString();
    }
    
}

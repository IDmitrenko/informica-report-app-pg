package ru.avers.informica.common.config;

import ru.avers.informica.dto.IDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Configuration implements IDTO {

    public Configuration() {
    }        
    
    private Collection<CProfile> cProfiles;
    
    @XmlElement(name="profile", type=CProfile.class)
    public Collection<CProfile> getProfiles() {
        if (cProfiles == null) cProfiles = new HashSet<CProfile>();
        return cProfiles;
    }
    
    public void setProfiles(Collection<CProfile> pVal) {
        cProfiles = pVal;
    }    

    public void addProfile(CProfile pVal) {
        if (pVal == null) return;
        getProfiles().add(pVal);
    }

    public void removeProfile(String pId) {
        CProfile cProfile = find(pId);
        if (cProfile != null) {
            getProfiles().remove(cProfile);
        }
    }

    public void removeAllProfiles() {
        getProfiles().clear();
    }

    public CProfile find(String pId) {
        if (pId == null) return null;
        
        Collection<CProfile> cProfiles = getProfiles();
        for (CProfile cProfile : cProfiles) {
            if (pId.equalsIgnoreCase(cProfile.getId()))
                return cProfile;
        }
        return null;
    }

    public CProfile getProfile(String pId) {
        CProfile cProfile = find(pId);
        if (cProfile == null) {
            cProfile = new CProfile();
            cProfile.setId(pId);
            getProfiles().add(cProfile);
        }
        return cProfile;
    }

    public void saveConfig() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

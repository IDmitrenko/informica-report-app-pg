/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    
    private Collection<CProfile> m_profiles;
    
    @XmlElement(name="profile", type=CProfile.class)
    public Collection<CProfile> getProfiles() {
        if (m_profiles == null) m_profiles = new HashSet<CProfile>();
        return m_profiles;
    }
    
    public void setProfiles(Collection<CProfile> p_val) {
        m_profiles = p_val;
    }    

    public void addProfile(CProfile p_val) {
        if (p_val == null) return;
        getProfiles().add(p_val);
    }

    public void removeProfile(String p_id) {
        CProfile x_profile = find(p_id);
        if (x_profile != null) {
            getProfiles().remove(x_profile);
        }
    }

    public void removeAllProfiles() {
        getProfiles().clear();
    }

    public CProfile find(String p_id) {
        if (p_id == null) return null;
        
        Collection<CProfile> x_profiles = getProfiles();
        for (CProfile x_profile : x_profiles) {
            if (p_id.equalsIgnoreCase(x_profile.getId()))
                return x_profile;
        }
        return null;
    }

    public CProfile getProfile(String p_id) {
        CProfile x_profile = find(p_id);
        if (x_profile == null) {
            x_profile = new CProfile();
            x_profile.setId(p_id);
            getProfiles().add(x_profile);
        }
        return x_profile;
    }

    public void saveConfig() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

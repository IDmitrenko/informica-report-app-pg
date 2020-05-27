package ru.avers.informica.entities;

import ru.avers.informica.entities.abstraction.CItem;
import ru.avers.informica.entities.dicts.CDict04MunicipObrazov;
import ru.avers.informica.entities.dicts.CDict91AuthProvider;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// TODO нет пока аналога таблицы в новой БД
/**
 *
 * @author Dias
 */
@Entity
//  в этом слое такая аннотация нежелательна
//@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Table(name="USERS")
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_USERS_ID")

@NamedQueries({
    @NamedQuery(name="findUserByLogin",
        query="from CUsers where m_login = :login"),

    @NamedQuery(name="findUserByLoginAndPassw",
        query="from CUsers where m_login = :login and m_password = :password")
})
public class CUsers extends CItem {

    public CUsers() { this(null); }
    public CUsers(Integer p_id) { super(p_id); }

    @Column(name = "LOGIN")
    private String m_login;
    public String getLogin() {return m_login;}
    public void setLogin(String p_val) {m_login = p_val;}

    @Column(name = "PWD")
    private String m_password;
    public String getPassword() {return m_password;}
    public void setPassword(String p_val) {m_password = p_val;}

    @Column(name = "GXT_UI")
    private String m_gxt_ui;
    public String getGXTUI() {return m_gxt_ui;}
    public void setGXTUI(String p_val) {m_gxt_ui = p_val;}

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_MUNICIP_OBRAZOV")
    private CDict04MunicipObrazov m_ter;
    public CDict04MunicipObrazov getTer() { return m_ter; }
    public void setTer(CDict04MunicipObrazov p_ter) { m_ter = p_ter; }
    
//  это не работает с SaveOrUpdate - надо использовать хибернейтовскую @Cascade
//    @OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @OneToOne(cascade={CascadeType.PERSIST})

//  это работает с SaveOrUpdate
    @OneToOne //(cascade=CascadeType.ALL)
    @JoinColumn(name="ID_PERSON")
    private CPerson m_person;
    public CPerson getPerson() {return m_person;}
    public void setPerson(CPerson p_val) {m_person = p_val;}

    @ManyToMany(targetEntity=CUserGroup.class, fetch= FetchType.LAZY,
//        cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
        cascade= CascadeType.ALL)
    @JoinTable(name="USER_GRP", joinColumns=@JoinColumn(name="ID_USER"),
                inverseJoinColumns=@JoinColumn(name="ID_GROUP"))
    private Set<CUserGroup> m_groups = new HashSet<CUserGroup>();
    public Set<CUserGroup> getGroups() {return m_groups;}
    public void setGroups(Set<CUserGroup> p_val) { m_groups = p_val; }
    public void addGroup(CUserGroup p_group) {
        p_group.getUsers().add(this);
        m_groups.add(p_group);
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ID_AUTH_PROV")
    private CDict91AuthProvider m_auth_prov;
    public CDict91AuthProvider getAuthProvider() { return m_auth_prov; }
    public void setAuthProvider(CDict91AuthProvider p_val) { m_auth_prov = p_val; }
    
    @Column(name = "EXT_ID")
    private String m_ext_id;
    public String getExternalId() {return m_ext_id;}
    public void setExternalId(String p_val) {m_ext_id = p_val;}
    
    @Column(name = "EXT_INFO")
    private String m_ext_info;
    public String getExternalInfo() {return m_ext_info;}
    public void setExternalInfo(String p_val) {m_ext_info = p_val;}
    
    
    private String toStringGroups() {
        String x_rv = "[";
        for(CUserGroup x_group: m_groups)
            x_rv += "{id=" + String.valueOf(x_group.getId()) +
                "; name=" + String.valueOf(x_group.getName()) + "}";
        x_rv += "]";
        return x_rv;
    }

    @Override
    public String toString() {
        return CUsers.class.getName() + " {" + super.toString() +
                "; login=" + String.valueOf(getLogin()) +
                "; password=" + String.valueOf(getPassword()) +
                "; gxt_ui=" + String.valueOf(getGXTUI()) +
                "; person=" + String.valueOf(getPerson()) +
//                "; groups=" + toStringGroups() +
                "}";
    }

}

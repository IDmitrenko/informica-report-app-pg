package ru.avers.informica.entities;

import ru.avers.informica.entities.abstraction.CItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

TODO нет пока аналога таблицы в новой БД
/**
 *
 * @author Dias
 */
@Entity
@Table(name="PERSON")
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_PERSON_ID")
public class CPerson extends CItem {

//    @Version
//    @Column(name="OPTLOCK")
//    protected int m_version_num;
    
    public CPerson() { super(); }
    public CPerson(Integer p_id) { super(p_id); }

    //  TODO разобраться каков эффект
//    @Basic(fetch= FetchType.LAZY)
    @Column(name = "NAME1")
    private String m_name;
    public String getName() {return m_name;}
    public void setName(String p_val) {m_name = p_val;}

    @Column(name = "NAME2")
    private String m_patronymic;
    public String getPatronymic() {return m_patronymic;}
    public void setPatronymic(String p_val) {m_patronymic = p_val;}

    @Column(name = "SURNAME")
    private String m_surname;
    public String getSurname() {return m_surname;}
    public void setSurname(String p_val) {m_surname = p_val;}

    @Temporal(value = TemporalType.DATE)
    @Column(name = "BIRTHDATE")
    private Date m_birthdate;
    public Date getBirthdate() {return m_birthdate;}
    public void setBirthdate(Date p_val) {m_birthdate = p_val;}

    static public enum TSex { FEMALE, MALE };
    
    @Column(name = "SEX")
    private Boolean m_sex;
    public TSex getSex() { return m_sex == null ? null : (m_sex ? TSex.MALE : TSex.FEMALE); }
    public void setSex(TSex p_val) { m_sex = (p_val == null ? null : TSex.MALE.equals(p_val)); }
    
//    private Short m_sex;
//    public TSex getSex() {
//        return m_sex == null ? null :
//                (m_sex.intValue() == 0 ? TSex.FEMALE : TSex.MALE);}
//    public void setSex(TSex p_val) {
//        m_sex = (p_val == null ? null : (short)(p_val == TSex.MALE ? 1 : 0));
//    }

    @Transient
    private List<CAddrPerson> m_addresses = new ArrayList<CAddrPerson>();
    @Access(AccessType.PROPERTY)
    @OneToMany(mappedBy="person", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
//    @OneToMany(mappedBy="person", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
//    @OneToMany(mappedBy="person", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    public List<CAddrPerson> getAddresses() { return m_addresses; }
    public void setAddresses(List<CAddrPerson> p_val) { m_addresses = p_val; }

    @OneToMany(cascade= CascadeType.ALL, fetch= FetchType.LAZY, mappedBy="m_person")
    private List<Contact> m_contacts = new ArrayList<Contact>();
    public List<Contact> getContacts() { return m_contacts; }
    public void setContacts(List<Contact> p_contacts) { m_contacts = p_contacts; }
    
//    @Transient
//    private CTemp1 m_temp1;
//    @Access(AccessType.PROPERTY)
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "ID_TEMP1")
//    public CTemp1 getTemp() { return m_temp1; }
//    public void setTemp(CTemp1 p_val) { m_temp1 = p_val; }


//    @OneToOne(cascade=CascadeType.ALL, targetEntity=CUsers.class, mappedBy="m_person", fetch=FetchType.LAZY)
//    private CUsers m_user;
//    public CUsers getUser() {return m_user;}
//    public void setUser(CUsers p_val) {m_user = p_val;}


    @Override
    public String toString() {
        return CPerson.class.getName() + " {" + super.toString() +
                "; name=" + String.valueOf(getName()) +
                "; patronymic=" + String.valueOf(getPatronymic()) +
                "; surname=" + String.valueOf(getSurname()) +
                "; birthdate=" + String.valueOf(getBirthdate()) +
//                "; sex=" + String.valueOf(getSex()) +
//                "; temp=" + String.valueOf(getTemp()) +
                "}";
    }
}

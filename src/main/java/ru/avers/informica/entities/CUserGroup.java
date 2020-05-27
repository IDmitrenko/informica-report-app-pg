package ru.avers.informica.entities;

import ru.avers.informica.entities.dicts.CBaseDict;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// TODO нет соответствия в новой БД
/**
 *
 * @author Dias
 */
@Entity
@Table(name="GRPOFUSERS")
@AttributeOverride(name="m_name", column=@Column(name="NAME"))
@SequenceGenerator(name="SEQ_GEN", sequenceName="GEN_GRPOFUSERS_ID")
public class CUserGroup extends CBaseDict {
    public CUserGroup() { super(); }
    public CUserGroup(Integer p_id) { super(p_id); }

    @ManyToMany(targetEntity = CUsers.class, mappedBy = "m_groups", fetch= FetchType.LAZY,
//        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
        cascade = CascadeType.ALL)
    private Set<CUsers> m_users = new HashSet<CUsers>();
    public Set<CUsers> getUsers() { return m_users; }
    public void setUsers(Set<CUsers> p_val) { m_users = p_val; }
    public void addUser(CUsers p_user) {
        p_user.getGroups().add(this);
        m_users.add(p_user);
    }

    @Override
    public String toString() {
        return
                CUserGroup.class.getName() + " {" +
                super.toString() +
                "; users=" + String.valueOf(getUsers()) +
                "}";
    }

}

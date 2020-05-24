package ru.avers.informica.entities.abstraction;

/**
 *
 * @author Dias
 */
public interface IItem<ID> {
    ID getId();
    void setId(ID p_val);
    
    String toStrSpecJPA();
}

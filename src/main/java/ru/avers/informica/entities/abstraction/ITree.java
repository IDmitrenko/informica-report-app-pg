package ru.avers.informica.entities.abstraction;

import java.util.List;

/**
 *
 * @author Dias
 */
public interface ITree {
    public ITree getParent();
    public List<? extends ITree> getChilds();
    public void addChild(ITree p_item);
}

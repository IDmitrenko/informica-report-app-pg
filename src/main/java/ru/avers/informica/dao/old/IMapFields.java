package ru.avers.informica.dao.old;

/**
 *
 * @author Dias
 */
public interface IMapFields {
    final static public String s_field_delimiter = ".";
    final static public String s_spec_field_delimiter = "^";

    final public static String s_id = "id";
    final public static String s_deleted_id = "deleted-id";

    public static interface ITree {
        final public static String
                s_parent = "m_parent",
                s_childs = "m_childs";
    }

}

package ru.avers.informica.utils;

/**
 *
 * @author Dias
 */
public class Const {
    public final static String S_ENCODING_UTF8 = "UTF-8";
    
    // Регулярное выражение для проверки серии свидетельства о рождении
    public final static String S_REGEX_DOC_KIND_CHILD_SER =
                                        "^M{0,3}(D?C{0,3}|C[DM])(L?X{0,3}|X[LC])(V?I{0,3}|I[VX])-[\\u0410-\\u042f]{2}$";
    // Регулярное выражение для проверки номера свидетельства о рождении
    public final static String S_REGEX_DOC_KIND_CHILD_NUM = "^[0-9]{6}$";
}

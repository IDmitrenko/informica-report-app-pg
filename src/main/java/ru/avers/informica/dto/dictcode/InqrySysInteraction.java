package ru.avers.informica.dto.dictcode;

import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author Dias
 */
public class InqrySysInteraction {
    private InqrySysInteraction() {}

    public static final String s_smev_interaction_name = "SMEV",
            s_informica_interaction_name = "INFORMICA",
            s_concentrator_interaction_name = "CONCENTRATOR";

    public static final Collection<String> s_portal_names = new HashSet<>();
    static {
        s_portal_names.add(s_smev_interaction_name);
        s_portal_names.add(s_concentrator_interaction_name);
    }
}

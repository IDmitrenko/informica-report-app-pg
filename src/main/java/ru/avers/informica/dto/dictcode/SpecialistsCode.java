package ru.avers.informica.dto.dictcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Dias
 */
public class SpecialistsCode {
    private SpecialistsCode() {}

    public static final Integer POSITION_CATEGORY = 550000; // Категория должности - педагог
    public static final Integer POSITION_CATEGORY_SERVICE = 30000;  // Категория должности -
                                                            // обслуживающий персонал

    public static final Integer POSITION_TYPE_BASIC = 1;    // Тип должности - основная
    public static final Integer POSITION_TYPE_BY_WORKER_EXTERNAL = 2;   // Тип должности -
                                                            // совместитель внешний
    public static final Integer POSITION_TYPE_BY_WORKER_INTERNAL = 4;    // Тип должности -
                                                            // совместитель внутренний

    private static List<Integer> POSITION_psychologist = Collections
            .unmodifiableList(Arrays.asList(550012, 550062)); // педагоги-психологи
    public static List<Integer> getPOSITION_psychologist() {
        return POSITION_psychologist;
    }

    private static List<Integer> POSITION_logopedist = Collections
            .unmodifiableList(Arrays.asList(550011, 550059, 550078)); // педагоги-логопеды
    public static List<Integer> getPOSITION_logopedist() {
        return POSITION_logopedist;
    }

    private static List<Integer> POSITION_defectologist = Collections
            .unmodifiableList(Arrays.asList(550035, 550079)); // педагоги-дефектологи
    public static List<Integer> getPOSITION_defectologist() {
        return POSITION_defectologist;
    }

    private static List<Integer> POSITION_lfk = Collections
            .unmodifiableList(Arrays.asList(550015, 550018)); // инструкторы-методисты ЛФК
    public static List<Integer> getPOSITION_lfk() {
        return POSITION_lfk;
    }

    private static List<Integer> POSITION_med = Collections
            .unmodifiableList(Arrays.asList(30088, 30089, 30090, 30091,
                    30092, 30093, 30094, 30095, 30096, 30106, 30107, 30108,
                    30109, 30110, 30111, 30112));           // медицинские работники
    public static List<Integer> getPOSITION_med() {
        return POSITION_med;
    }

    public static final Integer POSITION_social = 550013;   // социальные педагоги

    public static final Integer POSITION_pediatr = 30099;   // врачи-педиатры

    public static final Integer POSITION_neurolog = 30098;  // врачи-неврологи

    public static final Integer POSITION_ophthalmologist = 30105;  // врачи-офтальмологи

    public static final Integer UNKNOWN1_oligophren = 1;    // неизвестная должность 1
                                                            // для олигофрено-педагогов

    public static final Integer UNKNOWN2_surdo = 2;         // неизвестная должность 2
                                                            // для сурдопедагогов

    public static final Integer UNKNOWN3_tiflo = 3;         // неизвестная должность 3
                                                            // для тифлопедагогов

    public static final Integer UNKNOWN4_afk = 4;           // неизвестная должность 4
                                                            // для инструкторов по АФК

    public static final Integer UNKNOWN5_audiologist = 5;   // неизвестная должность 5
                                                            // для врачей-сурдологов

}

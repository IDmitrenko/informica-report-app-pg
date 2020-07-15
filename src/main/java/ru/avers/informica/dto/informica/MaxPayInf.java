package ru.avers.informica.dto.informica;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Информация для тэга max_pay
 * @author Dias
 */
@Getter
@Setter
@NoArgsConstructor
public class MaxPayInf {
    private String typePay,         // ... (type_pay)
            npaPay,                 // ... (npa_pay)
            changePay,              // ... (change_pay)
            or_1_Time_1_Age_0_3,    // ... (or_1_time_1_age_0_3)
            or_1_Time_1_Age_3_8,    // ... (or_1_time_1_age_3_8)
            or_1_Time_1_Age_Mix,    // ... (or_1_time_1_age_mix)
            or_1_Time_2_Age_0_3,    // ... (or_1_time_2_age_0_3)
            or_1_Time_2_Age_3_8,    // ... (or_1_time_2_age_3_8)
            or_1_Time_2_Age_Mix,    // ... (or_1_time_2_age_mix)
            or_1_Time_3_Age_0_3,    // ... (or_1_time_3_age_0_3)
            or_1_Time_3_Age_3_8,    // ... (or_1_time_3_age_3_8)
            or_1_Time_3_Age_Mix,    // ... (or_1_time_3_age_mix)
            or_1_Time_4_Age_0_3,    // ... (or_1_time_4_age_0_3)
            or_1_Time_4_Age_3_8,    // ... (or_1_time_4_age_3_8)
            or_1_Time_4_Age_Mix,    // ... (or_1_time_4_age_mix)
            or_1_Time_5_Age_0_3,    // ... (or_1_time_5_age_0_3)
            or_1_Time_5_Age_3_8,    // ... (or_1_time_5_age_3_8)
            or_1_Time_5_Age_Mix,    // ... (or_1_time_5_age_mix)
            or_2_Time_1_Age_0_3,    // ... (or_2_time_1_age_0_3)
            or_2_Time_1_Age_3_8,    // ... (or_2_time_1_age_3_8)
            or_2_Time_1_Age_Mix,    // ... (or_2_time_1_age_mix)
            or_2_Time_2_Age_0_3,    // ... (or_2_time_2_age_0_3)
            or_2_Time_2_Age_3_8,    // ... (or_2_time_2_age_3_8)
            or_2_Time_2_Age_Mix,    // ... (or_2_time_2_age_mix)
            or_2_Time_3_Age_0_3,    // ... (or_2_time_3_age_0_3)
            or_2_Time_3_Age_3_8,    // ... (or_2_time_3_age_3_8)
            or_2_Time_3_Age_Mix,    // ... (or_2_time_3_age_mix)
            or_2_Time_4_Age_0_3,    // ... (or_2_time_4_age_0_3)
            or_2_Time_4_Age_3_8,    // ... (or_2_time_4_age_3_8)
            or_2_Time_4_Age_Mix,    // ... (or_2_time_4_age_mix)
            or_2_Time_5_Age_0_3,    // ... (or_2_time_5_age_0_3)
            or_2_Time_5_Age_3_8,    // ... (or_2_time_5_age_3_8)
            or_2_Time_5_Age_Mix,    // ... (or_2_time_5_age_mix)
            or_3_Time_1_Age_0_3,    // ... (or_3_time_1_age_0_3)
            or_3_Time_1_Age_3_8,    // ... (or_3_time_1_age_3_8)
            or_3_Time_1_Age_Mix,    // ... (or_3_time_1_age_mix)
            or_3_Time_2_Age_0_3,    // ... (or_3_time_2_age_0_3)
            or_3_Time_2_Age_3_8,    // ... (or_3_time_2_age_3_8)
            or_3_Time_2_Age_Mix,    // ... (or_3_time_2_age_mix)
            or_3_Time_3_Age_0_3,    // ... (or_3_time_3_age_0_3)
            or_3_Time_3_Age_3_8,    // ... (or_3_time_3_age_3_8)
            or_3_Time_3_Age_Mix,    // ... (or_3_time_3_age_mix)
            or_3_Time_4_Age_0_3,    // ... (or_3_time_4_age_0_3)
            or_3_Time_4_Age_3_8,    // ... (or_3_time_4_age_3_8)
            or_3_Time_4_Age_Mix,    // ... (or_3_time_4_age_mix)
            or_3_Time_5_Age_0_3,    // ... (or_3_time_5_age_0_3)
            or_3_Time_5_Age_3_8,    // ... (or_3_time_5_age_3_8)
            or_3_Time_5_Age_Mix,    // ... (or_3_time_5_age_mix)
            or_4_Time_1_Age_0_3,    // ... (or_4_time_1_age_0_3)
            or_4_Time_1_Age_3_8,    // ... (or_4_time_1_age_3_8)
            or_4_Time_1_Age_Mix,    // ... (or_4_time_1_age_mix)
            or_4_Time_2_Age_0_3,    // ... (or_4_time_2_age_0_3)
            or_4_Time_2_Age_3_8,    // ... (or_4_time_2_age_3_8)
            or_4_Time_2_Age_Mix,    // ... (or_4_time_2_age_mix)
            or_4_Time_3_Age_0_3,    // ... (or_4_time_3_age_0_3)
            or_4_Time_3_Age_3_8,    // ... (or_4_time_3_age_3_8)
            or_4_Time_3_Age_Mix,    // ... (or_4_time_3_age_mix)
            or_4_Time_4_Age_0_3,    // ... (or_4_time_4_age_0_3)
            or_4_Time_4_Age_3_8,    // ... (or_4_time_4_age_3_8)
            or_4_Time_4_Age_Mix,    // ... (or_4_time_4_age_mix)
            or_4_Time_5_Age_0_3,    // ... (or_4_time_5_age_0_3)
            or_4_Time_5_Age_3_8,    // ... (or_4_time_5_age_3_8)
            or_4_Time_5_Age_Mix,    // ... (or_4_time_5_age_mix)
            or_56_Time_1_Age_0_3,   // ... (or_56_time_1_age_0_3)
            or_56_Time_1_Age_3_8,   // ... (or_56_time_1_age_3_8)
            or_56_Time_1_Age_Mix,   // ... (or_56_time_1_age_mix)
            or_56_Time_2_Age_0_3,   // ... (or_56_time_2_age_0_3)
            or_56_Time_2_Age_3_8,   // ... (or_56_time_2_age_3_8)
            or_56_Time_2_Age_Mix,   // ... (or_56_time_2_age_mix)
            or_56_Time_3_Age_0_3,   // ... (or_56_time_3_age_0_3)
            or_56_Time_3_Age_3_8,   // ... (or_56_time_3_age_3_8)
            or_56_Time_3_Age_Mix,   // ... (or_56_time_3_age_mix)
            or_56_Time_4_Age_0_3,   // ... (or_56_time_4_age_0_3)
            or_56_Time_4_Age_3_8,   // ... (or_56_time_4_age_3_8)
            or_56_Time_4_Age_Mix,   // ... (or_56_time_4_age_mix)
            or_56_Time_5_Age_0_3,   // ... (or_56_time_5_age_0_3)
            or_56_Time_5_Age_3_8,   // ... (or_56_time_5_age_3_8)
            or_56_Time_5_Age_Mix,   // ... (or_56_time_5_age_mix)
            or_7_Time_1_Age_0_3,    // ... (or_7_time_1_age_0_3)
            or_7_Time_1_Age_3_8,    // ... (or_7_time_1_age_3_8)
            or_7_Time_1_Age_Mix,    // ... (or_7_time_1_age_mix)
            or_7_Time_2_Age_0_3,    // ... (or_7_time_2_age_0_3)
            or_7_Time_2_Age_3_8,    // ... (or_7_time_2_age_3_8)
            or_7_Time_2_Age_Mix,    // ... (or_7_time_2_age_mix)
            or_7_Time_3_Age_0_3,    // ... (or_7_time_3_age_0_3)
            or_7_Time_3_Age_3_8,    // ... (or_7_time_3_age_3_8)
            or_7_Time_3_Age_Mix,    // ... (or_7_time_3_age_mix)
            or_7_Time_4_Age_0_3,    // ... (or_7_time_4_age_0_3)
            or_7_Time_4_Age_3_8,    // ... (or_7_time_4_age_3_8)
            or_7_Time_4_Age_Mix,    // ... (or_7_time_4_age_mix)
            or_7_Time_5_Age_0_3,    // ... (or_7_time_5_age_0_3)
            or_7_Time_5_Age_3_8,    // ... (or_7_time_5_age_3_8)
            or_7_Time_5_Age_Mix;    // ... (or_7_time_5_age_mix)

}
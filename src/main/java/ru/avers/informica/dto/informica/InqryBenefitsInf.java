package ru.avers.informica.dto.informica;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Dias
 */
@Getter
@Setter
@NoArgsConstructor
public class InqryBenefitsInf {
    private int id,             // id applications (заявления)
                idBenefits;     // id льготы (benefits.benefit_csp)

    private String benefitCode; // код льготы

    private String benefitTyp;  // тип льготы
                                // Концентратор: 1-федеральная, 2-региональная, 3-муниципальная

}

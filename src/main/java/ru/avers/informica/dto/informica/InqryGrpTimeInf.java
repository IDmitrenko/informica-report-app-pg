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
public class InqryGrpTimeInf {
    private int id,             // id applications (заявления)
                idGrpTime;      // id режима посещения (grp_time.grp_time__csp)

    private String grpTimeCode; // код режима посещения

}

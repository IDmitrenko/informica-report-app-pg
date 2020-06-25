package ru.avers.informica.old.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class Benefits {
    private Long idBenefits;         // id льготы (InqryLgot)
    private Long appId;              // id applications
    private Date dateConfirm;        // дата подтверждения льготы
    private Long idBenefitsCsp;      // id льготы в справочнике (смотрит на поле sp таблицы spr_b)
    private SprB benefitIndicator;   // расшифровка льготы (справочник)
}

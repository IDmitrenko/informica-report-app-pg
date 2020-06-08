package ru.avers.informica.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Buildings {
    private Long idBuildings;        // id организации (InqryUch)
    private Long appId;              // id applications
    private Short prty;              // приоритет учреждения в заявлении
    private Long idUch;              // id учреждения
}

package ru.avers.informica.old.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Statuses {
    private Long id;                 // id statuses
    private String desc;             // описание статуса
//    private String codeStatus;       // код статуса (TODO пока нет)
}

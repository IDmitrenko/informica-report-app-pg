package ru.avers.informica.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SprB {
    private Long id;                 // id statuses
    private String desc;             // описание статуса
//    private String indicatorCode;  // код показателя (TODO пока нет)
}

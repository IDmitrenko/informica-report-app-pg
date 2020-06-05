package ru.avers.informica.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Status {
    private Long id;                 // id status
    private Long appId;              // id applications
    private Long statusesId;         // id статуса заявления
    private Statuses statuses;       // запись таблицы статусов
}

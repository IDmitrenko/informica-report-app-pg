package ru.avers.informica.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Status {
    private Long id;
    private Long appId;
    private Long statusesId;
}

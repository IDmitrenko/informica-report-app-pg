package ru.avers.informica.entities;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationEntity {
    private Long id;
    private String number;
    private String firstName;

    private List<Status> statusList;
}

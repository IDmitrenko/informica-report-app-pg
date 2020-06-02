package ru.avers.informica.dao;

import ru.avers.informica.entities.ApplicationEntity;

import java.util.List;

public interface ApplicationDao {
    List<ApplicationEntity> getAllApplications();
}

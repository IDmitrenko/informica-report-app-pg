package ru.avers.informica.old.dao;

import ru.avers.informica.old.entities.ApplicationsEntity;

import java.util.List;

public interface ApplicationsDao {
    List<ApplicationsEntity> getAllApplications();
}

package ru.avers.informica.dao;

import ru.avers.informica.entities.ApplicationsEntity;

import java.util.Date;
import java.util.List;

public interface ApplicationsDao {
    List<ApplicationsEntity> getAllApplications();
}

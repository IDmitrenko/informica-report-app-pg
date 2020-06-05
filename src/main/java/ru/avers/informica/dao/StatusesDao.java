package ru.avers.informica.dao;

import ru.avers.informica.entities.Statuses;

import java.util.List;

public interface StatusesDao {
    Statuses getStatus(Long id);
}

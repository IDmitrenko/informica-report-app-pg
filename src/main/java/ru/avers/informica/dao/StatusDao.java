package ru.avers.informica.dao;

import ru.avers.informica.entities.Status;

import java.util.List;

public interface StatusDao {
    List<Status> getStatuses(Long id);
}

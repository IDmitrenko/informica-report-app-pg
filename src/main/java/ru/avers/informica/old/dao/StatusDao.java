package ru.avers.informica.old.dao;

import ru.avers.informica.old.entities.Status;

import java.util.List;

public interface StatusDao {
    List<Status> getStatuses(Long id);
}

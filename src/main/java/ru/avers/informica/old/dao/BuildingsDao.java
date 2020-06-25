package ru.avers.informica.old.dao;

import ru.avers.informica.old.entities.Buildings;

import java.util.List;

public interface BuildingsDao {
    List<Buildings> getBuildings(Long id);
}

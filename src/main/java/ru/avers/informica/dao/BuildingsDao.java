package ru.avers.informica.dao;

import ru.avers.informica.entities.Buildings;

import java.util.List;

public interface BuildingsDao {
    List<Buildings> getBuildings(Long id);
}

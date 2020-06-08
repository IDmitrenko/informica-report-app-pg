package ru.avers.informica.dao;

import ru.avers.informica.entities.Benefits;

import java.util.List;

public interface BenefitsDao {
    List<Benefits> getBenefits(Long id);
}

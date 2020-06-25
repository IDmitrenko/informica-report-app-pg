package ru.avers.informica.old.dao;

import ru.avers.informica.old.entities.Benefits;

import java.util.List;

public interface BenefitsDao {
    List<Benefits> getBenefits(Long id);
}

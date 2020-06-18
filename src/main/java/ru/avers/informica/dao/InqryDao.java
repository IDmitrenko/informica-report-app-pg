package ru.avers.informica.dao;

import ru.avers.informica.dto.informica.InqryInf;
import ru.avers.informica.entities.ApplicationsEntity;

import java.util.Date;
import java.util.List;

public interface InqryDao {
    List<InqryInf> getAllInqry(Date currDate, Date currEducDate, Date beginCurrYear);
}

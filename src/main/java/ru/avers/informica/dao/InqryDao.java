package ru.avers.informica.dao;

import ru.avers.informica.dto.informica.InqryInf;

import java.util.Date;
import java.util.List;

public interface InqryDao {
    List<InqryInf> getAllInqry(Date currDate, Date currEducDate, Date beginCurrYear);
}

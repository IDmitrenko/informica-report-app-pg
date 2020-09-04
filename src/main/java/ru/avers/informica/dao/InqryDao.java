package ru.avers.informica.dao;

import ru.avers.informica.dto.informica.InqryEnrolmentInf;
import ru.avers.informica.dto.informica.InqryInd19_3Inf;
import ru.avers.informica.dto.informica.InqryInd8Inf;
import ru.avers.informica.dto.informica.InqryInf;

import java.util.Date;
import java.util.List;

public interface InqryDao {

    List<InqryInf> getAllInqry(Date currDate, Date currEducDate, Date beginCurrYear);

    List<InqryEnrolmentInf> getIngryEnrolment();

    List<InqryInd8Inf> getInqryInd8();

    List<InqryInd19_3Inf> getInqryInd19_3();
}

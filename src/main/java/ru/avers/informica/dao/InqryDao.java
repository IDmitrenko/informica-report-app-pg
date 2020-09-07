package ru.avers.informica.dao;

import ru.avers.informica.dto.informica.*;

import java.util.Date;
import java.util.List;

public interface InqryDao {

    List<InqryInf> getAllInqry(Date currDate, Date currEducDate, Date beginCurrYear);

    List<InqryEnrolmentInf> getIngryEnrolment();

    List<InqryInd8Inf> getInqryInd8();

    List<InqryInd19_3Inf> getInqryInd19_3();

    List<InqryInd20_1Inf> getInqryInd20_1();
}

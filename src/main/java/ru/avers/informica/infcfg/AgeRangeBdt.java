package ru.avers.informica.infcfg;

import ru.avers.informica.dto.CAge;
import ru.avers.informica.dto.informica.IInformicaChildCountable;
import ru.avers.informica.dto.informica.IInformicaCountable;
import ru.avers.informica.dto.inqry.AgeDto;
import ru.avers.informica.dto.util.Utils;
import ru.avers.informica.exception.FspeoException;
import ru.avers.informica.exception.ReportExceprion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AgeRangeBdt {
    private AgeRangeDef ageRangeDef;
    private Integer calcYear,
            calcMonth,
            calcDay,
                    calcYearInc;
    private String dateField;

    @XmlAttribute(name="def")
    @XmlIDREF
    public AgeRangeDef getAgeRangeDef() {
        return ageRangeDef;
    }
    public void setAgeRangeDef(AgeRangeDef pAgeRangeDef) {
        this.ageRangeDef = pAgeRangeDef;
    }

    @XmlAttribute
    public String getDateField() {
        return dateField;
    }

    public void setDateField(final String pDateField) {
        this.dateField = pDateField;
    }

    @XmlAttribute
    public Integer getCalcYear() {
        return calcYear;
    }
    public void setCalcYear(Integer pYear) {
        this.calcYear = pYear;
    }

    @XmlAttribute
    public Integer getCalcMonth() {
        return calcMonth;
    }
    public void setCalcMonth(Integer pMonth) {
        this.calcMonth = pMonth;
    }

    @XmlAttribute
    public Integer getCalcDay() {
        return calcDay;
    }
    public void setCalcDay(Integer pDay) {
        this.calcDay = pDay;
    }

    @XmlAttribute
    public Integer getCalcYearInc() {
        return calcYearInc;
    }   
    public void setCalcYearInc(Integer pRepYearInc) {
        this.calcYearInc = pRepYearInc;
    }
    
    public Collection<TypeAgeRange> getAgeRanges(Date pDt, Date pBdt) {
        AgeDto ageDto = Utils.calculateAge(getCalcDate(pDt), pBdt);
        CAge chldAge = new CAge((int)ageDto.getYears(),
                (int)ageDto.getMonths(),
                (int)ageDto.getDays());
        return ageRangeDef.getAgeRangesForAge(chldAge);
    }    
    
    public Date getCalcDate(Date pDt) {
        if (calcYear == null && calcMonth == null && calcDay == null && calcYearInc == null) {
            return pDt;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(pDt);
        if (calcYear != null) {
            calendar.set(Calendar.YEAR, calcYear);
        }
        if (calcYearInc != null) {
            calendar.add(Calendar.YEAR, calcYearInc);
        }
        if (calcMonth != null) {
            calendar.set(Calendar.MONTH, calcMonth);
        }
        if (calcDay != null) {
            calendar.set(Calendar.DAY_OF_MONTH, calcDay);
        }
        return calendar.getTime();
    }
    
    public Collection<TypeAgeRange> getAgeRanges(Date pRepDate, IInformicaCountable pCountable)
            throws FspeoException, ReportExceprion {
        if (pCountable instanceof IInformicaChildCountable) {
            if (getDateField() != null && !getDateField().isEmpty()) {
                Object beanValue = new Object(); // временно заглушка
//убрал временно  Object beanValue = CounterConfig.getBeanValue(getDateField(), pCountable);
                if (beanValue != null) {
                    return getAgeRanges((Date) beanValue,
                            ((IInformicaChildCountable) pCountable).getBdt());
                }
            }
            return getAgeRanges(pRepDate, ((IInformicaChildCountable) pCountable).getBdt());
        }
/* TODO понять что в новой БД вместо Prll4stgAges  - public.group_years изучить
        else if (pCountable instanceof IInformicaVacantCountable) {
            return getAgeRanges(((IInformicaVacantCountable)pCountable).getAges());
        }
*/
        else throw new FspeoException("Неизвестный тип элемента для подсчета: " +
                pCountable.getClass().getName());
    }

}

package ru.avers.informica.dto.informica;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.avers.informica.dto.IDTO;

import java.util.Date;

@Data
@NoArgsConstructor
public class InqryInd19_3Inf implements IInformicaChildCountable, Application {

//    private String uchCode;  был код учреждения
    private Integer count,   // количество заявлений по отобранным статусам для даты рождения
            idUch;              // id учреждения

    private Date bDt;       // дата рождения

    @Override
    public Integer getIdUch() {
        return idUch;
    }

    public void setIdUch(Integer pIdUch) {
        idUch = pIdUch;
    }

    @Override
    public Date getBdt() {
        return bDt;
    }

    public void setBdt(Date pBdt) {
        this.bDt = pBdt;
    }

    @Override
    public int getCount() {
        return count;
    }

    public void setCount(final Integer count) {
        this.count = count;
    }


}

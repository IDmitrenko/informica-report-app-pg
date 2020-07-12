package ru.avers.informica.dto.informica;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InqryTransferInf implements IInformicaChildCountable {
    private Integer count = 1;
    private Integer id,
            idUch;
    private Date bDt;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Integer getIdUch() {
        return null;
    }

    @Override
    public Date getBdt() {
        return null;
    }
}

package ru.avers.informica.dto.informica;

import lombok.Data;
import ru.avers.informica.dto.IDTO;

import java.util.Date;

@Data
public class InqryInd8Inf implements IDTO {

    final static public double s_norm_coeff_days2months = 30.4;

//    private String uchCode;  был код учреждения
    private Integer idInqry,    // id заявления
            idUch;              // id учреждения

    private Date wishDt,        // дата TODO
            stsDt,              // дата TODO
            birthDt;            // дата рождения

    private int periodLength;   // TODO

    private boolean lgot,       // признак льготы  TODO
            ovz;                // признак ovz     TODO

}

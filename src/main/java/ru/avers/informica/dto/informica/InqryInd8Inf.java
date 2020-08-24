package ru.avers.informica.dto.informica;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.avers.informica.dto.IDTO;

import java.util.Date;

@Data
@NoArgsConstructor
public class InqryInd8Inf implements IDTO, Application {

//    final static public double s_norm_coeff_days2months = 30.4;

//    private String uchCode;  был код учреждения
    private Integer idInqry,    // id заявления
            idUch;              // id учреждения

    private Date wishDt,        // желаемая дата поступления
            stsDt,              // дата присвоения статуса
            birthDt;            // дата рождения

    private int periodLength;   // количество дней от ЖДП до установки нужного статуса
                                // (задержка предоставления услуги) (CPushDataRequestBuilder)

    private boolean lgot,       // признак льготы
            ovz;                // признак группы с ОВЗ

// TODO
// 1) делается Map на учреждения и его заявления (основная)
// 2) делается Map на заявление и его учреждения со статусами ... (для logger)(В одном заявлении несколько учреждений)
// !!! расчет показателей 8, 8.1, 8.2, 8.3 - метод processForInd_8 в классе CTagSingleOrganizationBuilder
}

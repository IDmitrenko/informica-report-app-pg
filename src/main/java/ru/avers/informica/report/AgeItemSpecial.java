package ru.avers.informica.report;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.avers.informica.infcfg.TypeAgeRange;

@Data
@NoArgsConstructor
public class AgeItemSpecial {

    protected TypeAgeRange category;
    protected String value;

}
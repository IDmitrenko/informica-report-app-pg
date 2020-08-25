package ru.avers.informica.report;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CounterSpecial {

    protected String id;
    protected List<AgeItemSpecial> ageItemSpecials;

}

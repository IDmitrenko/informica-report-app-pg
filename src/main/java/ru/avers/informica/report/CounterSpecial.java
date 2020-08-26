package ru.avers.informica.report;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CounterSpecial {

    protected String id;
    protected List<AgeItemSpecial> ageItemSpecials;

    public List<AgeItemSpecial> getAgeItemSpecials() {
        if (ageItemSpecials == null) {
            ageItemSpecials = new ArrayList<AgeItemSpecial>();
        }
        return this.ageItemSpecials;
    }

}

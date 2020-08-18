package ru.avers.informica.report.informica;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.avers.informica.infcfg.TypeAgeRange;
import ru.avers.informica.report.Counter;
import ru.avers.informica.report.xml.TagAge16;
import ru.avers.informica.report.xml.TagAge8;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AgeTransformer {

    public TagAge16 transformToAge16(List<Counter.AgeItem> ageList) {
        TagAge16 result = new TagAge16();
        if (ageList == null) {
            return result;
        }
        for (Counter.AgeItem item : ageList) {
            TypeAgeRange category = item.getCategory();
            int value = item.getValue();
            if (TypeAgeRange.t_m2_to_m6.equals(category)) result.setH_00_05(value);
            else if (TypeAgeRange.t_m6_to_y1.equals(category)) result.setH_05_10(value);
            else if (TypeAgeRange.t_y1_to_y1m6.equals(category)) result.setH_10_15(value);
            else if (TypeAgeRange.t_y1m6_to_y2.equals(category)) result.setH_15_20(value);
            else if (TypeAgeRange.t_y2_to_y2m6.equals(category)) result.setH_20_25(value);
            else if (TypeAgeRange.t_y2m6_to_y3.equals(category)) result.setH_25_30(value);
            else if (TypeAgeRange.t_y3_to_y3m6.equals(category)) result.setH_30_35(value);
            else if (TypeAgeRange.t_y3m6_to_y4.equals(category)) result.setH_35_40(value);
            else if (TypeAgeRange.t_y4_to_y4m6.equals(category)) result.setH_40_45(value);
            else if (TypeAgeRange.t_y4m6_to_y5.equals(category)) result.setH_45_50(value);
            else if (TypeAgeRange.t_y5_to_y5m6.equals(category)) result.setH_50_55(value);
            else if (TypeAgeRange.t_y5m6_to_y6.equals(category)) result.setH_55_60(value);
            else if (TypeAgeRange.t_y6_to_y6m6.equals(category)) result.setH_60_65(value);
            else if (TypeAgeRange.t_y6m6_to_y7.equals(category)) result.setH_65_70(value);
            else if (TypeAgeRange.t_y7_to_y7m6.equals(category)) result.setH_70_75(value);
            else if (TypeAgeRange.t_y7m6_plus.equals(category)) result.setH_75_E(value);
        }
        return result;
    }

    public TagAge8 transformToAge8(List<Counter.AgeItem> ageList) {
        TagAge8 result = new TagAge8();
        if (ageList == null) {
            return result;
        }
        for (Counter.AgeItem item : ageList) {
            TypeAgeRange category = item.getCategory();
            int value = item.getValue();
            if (TypeAgeRange.t_m2_to_y1.equals(category)) result.setY_0_1(value);
            else if (TypeAgeRange.t_y1_to_y2.equals(category)) result.setY_1_2(value);
            else if (TypeAgeRange.t_y2_to_y3.equals(category)) result.setY_2_3(value);
            else if (TypeAgeRange.t_y3_to_y4.equals(category)) result.setY_3_4(value);
            else if (TypeAgeRange.t_y4_to_y5.equals(category)) result.setY_4_5(value);
            else if (TypeAgeRange.t_y5_to_y6.equals(category)) result.setY_5_6(value);
            else if (TypeAgeRange.t_y6_to_y7.equals(category)) result.setY_6_7(value);
            else if (TypeAgeRange.t_y7_plus.equals(category)) result.setY_7_E(value);
        }
        return result;
    }
}

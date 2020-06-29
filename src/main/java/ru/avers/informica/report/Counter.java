package ru.avers.informica.report;

import ru.avers.informica.dto.CAge;
import ru.avers.informica.dto.CAgeInterval;
import ru.avers.informica.dto.informica.IInformicaCountable;
import ru.avers.informica.infcfg.CounterDef;
import ru.avers.informica.infcfg.TypeAgeRange;

import javax.xml.bind.annotation.*;
import java.util.*;


@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlTransient
public class Counter {

    protected String id;
    protected List<AgeItem> age;

    public Counter() {
    }

    public Counter(CounterDef pCounterDef) {
        id = pCounterDef.getName();
        Collection<TypeAgeRange> ranges = pCounterDef.getAgeRange().getAgeRangeDef().getAgeRanges();
        for (TypeAgeRange range : ranges) {
            AgeItem ageItem = new AgeItem();
            ageItem.setCategory(range);
            ageItem.setValue(0);
            typeAgeRangeAgeItemEnumMap.put(range, ageItem);
            getAge().add(ageItem);
        }
    }

    @XmlAttribute(required = true)
    public String getId() {
        return id;
    }

    public void setId(String pId) {
        this.id = pId;
    }

    @XmlElement(required = true)
    public final List<AgeItem> getAge() {
        if (age == null) {
            age = new ArrayList<AgeItem>();
        }
        return this.age;
    }

    public void addCounter(Counter pCounter) {
        if (!id.equals(pCounter.getId()) || getAge().size() != pCounter.getAge().size()) {
            throw new IllegalArgumentException();
        }
        for (AgeItem ageItem : getAge()) {
            AgeItem found = pCounter.findAgeItem(ageItem.getCategory());
            ageItem.add(found);
        }
    }

    protected Map<TypeAgeRange, AgeItem> typeAgeRangeAgeItemEnumMap =
            new EnumMap<TypeAgeRange, AgeItem>(TypeAgeRange.class);

    /**
     * Найти конкретный счетчик для указанного диапазона возрастов, если не найдено - null
     *
     * @param pAgeRange
     * @return
     */
    public AgeItem findAgeItem(TypeAgeRange pAgeRange) {
        if (pAgeRange == null) {
            return null;
        }
        if (typeAgeRangeAgeItemEnumMap.containsKey(pAgeRange)) {
            return typeAgeRangeAgeItemEnumMap.get(pAgeRange);
        }
        if (age != null) {
            for (AgeItem ageItem : age) {
                if (pAgeRange.equals(ageItem.getCategory())) {
                    typeAgeRangeAgeItemEnumMap.put(pAgeRange, ageItem);
                    return ageItem;
                }
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.age != null ? this.age.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Counter other = (Counter) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if (this.age != other.age && (this.age == null || !this.age.equals(other.age))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Counter [");
        builder.append("getId=").append(getId());
        builder.append(", getAge=").append(getAge());
        builder.append("]");
        return builder.toString();
    }

    private static class TypeAgeRangeComparator implements Comparator<TypeAgeRange> {
        @Override
        public int compare(TypeAgeRange pO1, TypeAgeRange pO2) {
            CAgeInterval ageInterval1 = pO1.getAgeInterval();
            CAgeInterval ageInterval2 = pO2.getAgeInterval();

            int res = compareAge(ageInterval1.getBegin(), ageInterval2.getBegin(), true);
            if (res != 0) {
                return res;
            }
            return compareAge(ageInterval1.getEnd(), ageInterval2.getEnd(), false);
        }

        private int compareAge(CAge pAge1, CAge pAge2, boolean pNullFirst) {
            if (pAge1 != null && pAge2 != null) {
                return pAge1.compareTo(pAge2);
            }
            else {
                if (pAge1 == null && pAge2 == null) {
                    return 0;
                }
                if (pAge1 == null) {
                    return pNullFirst ? -1 : 1;
                }
                else {
                    return pNullFirst ? 1 : -1;
                }
            }
        }
    }

    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(name = "", propOrder = {
            "category",
            "value"
    })
    public static class AgeItem {

        protected TypeAgeRange category;
        protected int value;

        public AgeItem() {
        }

        public AgeItem(TypeAgeRange pCategory, int pValue) {
            this.category = pCategory;
            this.value = pValue;
        }

        @XmlAttribute(required = true)
        public TypeAgeRange getCategory() {
            return category;
        }

        public void setCategory(TypeAgeRange pCategory) {
            this.category = pCategory;
        }

        @XmlValue
        public int getValue() {
            return value;
        }

        public void setValue(int pValue) {
            this.value = pValue;
        }

        public void count(IInformicaCountable pCnt) {
            value = value + pCnt.getCount();
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 73 * hash + (this.category != null ? this.category.hashCode() : 0);
            hash = 73 * hash + this.value;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final AgeItem other = (AgeItem) obj;
            if (this.category != other.category) {
                return false;
            }
            if (this.value != other.value) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Age [");
            builder.append("getCategory=").append(getCategory());
            builder.append(", getValue=").append(getValue());
            builder.append("]");
            return builder.toString();
        }

        protected void add(AgeItem p_item) {
            value += p_item.getValue();
        }
    }

/* TODO понять что в новой БД вместо Prll4stgAges
    public void count(IInformicaCountable p_countable, Collection<TypeAgeRange> p_age_ranges) throws FspeoException {
        if (p_countable instanceof IInformicaChildCountable) {
            for (TypeAgeRange x_age_range : p_age_ranges) {
                AgeItem x_age_item = findAgeItem(x_age_range);
                x_age_item.count(p_countable);
            }
        } else if (p_countable instanceof IInformicaVacantCountable) {
            // Задача 11821, пункт 26
            // Например, в группе от 2 до 4 лет шесть свободных мест: пишем 2 места для детей 2-х лет,
            // два для трех лет, 2 для четырех лет. Если место одно,
            // привязываемся к крайнему наименьшему диапазону : одно место для детей 2-3 лет.
            Map<TypeAgeRange, CapacityItem> x_split_capacity = splitCapacity(p_age_ranges, (IInformicaVacantCountable) p_countable);
            for (TypeAgeRange x_age_range : x_split_capacity.keySet()) {
                Counter.AgeItem x_age_item = findAgeItem(x_age_range);
                x_age_item.count(x_split_capacity.get(x_age_range));
            }
        } else throw new FspeoException("Неизвестный тип элемента для подсчета: " + p_countable.getClass().getName());
    }
    private Map<TypeAgeRange, CapacityItem> splitCapacity(Collection<TypeAgeRange> p_age_ranges, IInformicaVacantCountable p_capacity) {
        // "Если место одно, привязываемся к крайнему наименьшему диапазону: одно место для детей 2-3 лет."
        // Упорядочить p_age_ranges так, чтоб первым с списке был наименьший диапазон
        List<TypeAgeRange> x_sorted_list = new ArrayList<TypeAgeRange>(p_age_ranges);
        Collections.sort(x_sorted_list, new TypeAgeRangeComparator());

        int x_cap_count = p_capacity.getCount();
        int x_ranges_size = x_sorted_list.size();
        int x_div = x_cap_count / x_ranges_size;
        int x_rem = x_cap_count % x_ranges_size;
        Map<TypeAgeRange, CapacityItem> x_res = new EnumMap<TypeAgeRange, CapacityItem>(TypeAgeRange.class);
        for (TypeAgeRange x_age_range : x_sorted_list) {
            int x_count = x_div;
            if (x_rem > 0) {
                x_count += 1;
                x_rem -= 1;
            }
            if (x_count > 0)
                x_res.put(x_age_range, new CapacityItem(x_count, p_capacity));
        }
        return x_res;
    }
*/
}

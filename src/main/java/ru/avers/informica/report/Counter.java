package ru.avers.informica.report;

import ru.avers.informica.dto.CAge;
import ru.avers.informica.dto.CAgeInterval;
import ru.avers.informica.dto.informica.IInformicaChildCountable;
import ru.avers.informica.dto.informica.IInformicaCountable;
import ru.avers.informica.exception.FspeoException;
import ru.avers.informica.infcfg.CounterDef;
import ru.avers.informica.infcfg.TypeAgeRange;

import javax.xml.bind.annotation.*;
import java.util.*;


@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlTransient
public class Counter {

    protected String m_id;
    protected List<AgeItem> m_age;

    public Counter() {
    }

    public Counter(CounterDef p_counter_def) {
        m_id = p_counter_def.getName();
        Collection<TypeAgeRange> x_ranges = p_counter_def.getAgeRange().getAgeRangeDef().getAgeRanges();
        for (TypeAgeRange x_range : x_ranges) {
            AgeItem x_item = new AgeItem();
            x_item.setCategory(x_range);
            x_item.setValue(0);
            m_cache.put(x_range, x_item);
            getAge().add(x_item);
        }
    }

    @XmlAttribute(required = true)
    public String getId() {
        return m_id;
    }

    public void setId(String p_value) {
        this.m_id = p_value;
    }

    @XmlElement(required = true)
    public final List<AgeItem> getAge() {
        if (m_age == null) {
            m_age = new ArrayList<AgeItem>();
        }
        return this.m_age;
    }

    public void addCounter(Counter p_counter) {
        if (!m_id.equals(p_counter.getId()) || getAge().size() != p_counter.getAge().size())
            throw new IllegalArgumentException();
        for (AgeItem x_age_item : getAge()) {
            AgeItem x_found = p_counter.findAgeItem(x_age_item.getCategory());
            x_age_item.add(x_found);
        }
    }

    protected Map<TypeAgeRange, AgeItem> m_cache = new EnumMap<TypeAgeRange, AgeItem>(TypeAgeRange.class);

    /**
     * Найти конкретный счетчик для указанного диапазона возрастов, если не найдено - null
     *
     * @param p_age_range
     * @return
     */
    public AgeItem findAgeItem(TypeAgeRange p_age_range) {
        if (p_age_range == null) return null;
        if (m_cache.containsKey(p_age_range))
            return m_cache.get(p_age_range);
        if (m_age != null) {
            for (AgeItem x_item : m_age) {
                if (p_age_range.equals(x_item.getCategory())) {
                    m_cache.put(p_age_range, x_item);
                    return x_item;
                }
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.m_id != null ? this.m_id.hashCode() : 0);
        hash = 37 * hash + (this.m_age != null ? this.m_age.hashCode() : 0);
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
        if ((this.m_id == null) ? (other.m_id != null) : !this.m_id.equals(other.m_id)) {
            return false;
        }
        if (this.m_age != other.m_age && (this.m_age == null || !this.m_age.equals(other.m_age))) {
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
        public int compare(TypeAgeRange p_o1, TypeAgeRange p_o2) {
            CAgeInterval x_in1 = p_o1.getAgeInterval();
            CAgeInterval x_in2 = p_o2.getAgeInterval();

            int x_res = compareAge(x_in1.getBegin(), x_in2.getBegin(), true);
            if (x_res != 0) return x_res;
            return compareAge(x_in1.getEnd(), x_in2.getEnd(), false);
        }

        private int compareAge(CAge p_age1, CAge p_age2, boolean p_null_first) {
            if (p_age1 != null && p_age2 != null)
                return p_age1.compareTo(p_age2);
            else {
                if (p_age1 == null && p_age2 == null) return 0;
                if (p_age1 == null) return p_null_first ? -1 : 1;
                else return p_null_first ? 1 : -1;
            }
        }
    }

    @XmlAccessorType(XmlAccessType.PROPERTY)
    @XmlType(name = "", propOrder = {
            "category",
            "value"
    })
    public static class AgeItem {

        protected TypeAgeRange m_category;
        protected int m_value;

        public AgeItem() {
        }

        public AgeItem(TypeAgeRange p_category, int p_value) {
            this.m_category = p_category;
            this.m_value = p_value;
        }

        @XmlAttribute(required = true)
        public TypeAgeRange getCategory() {
            return m_category;
        }

        public void setCategory(TypeAgeRange p_value) {
            this.m_category = p_value;
        }

        @XmlValue
        public int getValue() {
            return m_value;
        }

        public void setValue(int p_value) {
            this.m_value = p_value;
        }

        public void count(IInformicaCountable p_cnt) {
            m_value = m_value + p_cnt.getCount();
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 73 * hash + (this.m_category != null ? this.m_category.hashCode() : 0);
            hash = 73 * hash + this.m_value;
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
            if (this.m_category != other.m_category) {
                return false;
            }
            if (this.m_value != other.m_value) {
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
            m_value += p_item.getValue();
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

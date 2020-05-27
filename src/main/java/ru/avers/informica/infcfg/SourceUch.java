package ru.avers.informica.infcfg;

import ru.avers.informica.dao.filtersort.IFieldFilterParams;
import ru.avers.informica.filtersinqry.GenericFilter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dias
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SourceUch {

    private List<IFieldFilterParams> m_filters;
    private List<CounterConfig> m_inqry_counters,
            m_enrolled_counter,
            m_capacity_counters,
            m_transfer_counters;

    @XmlElements(value = {
            @XmlElement(name = "filter", type = GenericFilter.class)
    })
    public List<IFieldFilterParams> getFilters() {
        if (m_filters == null) m_filters = new ArrayList<IFieldFilterParams>();
        return m_filters;
    }

    @XmlElementWrapper(name = "source-inqry")
    @XmlElement(name = "counter")
    public List<CounterConfig> getInqryCounters() {
        if (m_inqry_counters == null) m_inqry_counters = new ArrayList<CounterConfig>();
        return m_inqry_counters;
    }

    @XmlElementWrapper(name = "source-enrolled")
    @XmlElement(name = "counter")
    public List<CounterConfig> getEnrolledCounters() {
        if (m_enrolled_counter == null) m_enrolled_counter = new ArrayList<CounterConfig>();
        return m_enrolled_counter;
    }

    @XmlElementWrapper(name = "source-capacity")
    @XmlElement(name = "counter")
    public List<CounterConfig> getCapacityCounters() {
        if (m_capacity_counters == null) m_capacity_counters = new ArrayList<CounterConfig>();
        return m_capacity_counters;
    }

    @XmlElementWrapper(name = "source-transfer")
    @XmlElement(name = "counter")
    public List<CounterConfig> getTransferCounters() {
        if (m_transfer_counters == null) m_transfer_counters = new ArrayList<CounterConfig>();
        return m_transfer_counters;
    }
}

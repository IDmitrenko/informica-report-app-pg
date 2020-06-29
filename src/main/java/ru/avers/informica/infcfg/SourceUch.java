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

    private List<IFieldFilterParams> filters;
    private List<CounterConfig> inqryCounters,
            enrolledCounters,
            capacityCounters,
            transferCounters;

    @XmlElements(value = {
            @XmlElement(name = "filter", type = GenericFilter.class)
    })
    public List<IFieldFilterParams> getFilters() {
        if (filters == null) {
            filters = new ArrayList<IFieldFilterParams>();
        }
        return filters;
    }

    @XmlElementWrapper(name = "source-inqry")
    @XmlElement(name = "counter")
    public List<CounterConfig> getInqryCounters() {
        if (inqryCounters == null) {
            inqryCounters = new ArrayList<CounterConfig>();
        }
        return inqryCounters;
    }

    @XmlElementWrapper(name = "source-enrolled")
    @XmlElement(name = "counter")
    public List<CounterConfig> getEnrolledCounters() {
        if (enrolledCounters == null) {
            enrolledCounters = new ArrayList<CounterConfig>();
        }
        return enrolledCounters;
    }

    @XmlElementWrapper(name = "source-capacity")
    @XmlElement(name = "counter")
    public List<CounterConfig> getCapacityCounters() {
        if (capacityCounters == null) {
            capacityCounters = new ArrayList<CounterConfig>();
        }
        return capacityCounters;
    }

    @XmlElementWrapper(name = "source-transfer")
    @XmlElement(name = "counter")
    public List<CounterConfig> getTransferCounters() {
        if (transferCounters == null) {
            transferCounters = new ArrayList<CounterConfig>();
        }
        return transferCounters;
    }
}

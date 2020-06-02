package ru.avers.informica.report;

import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.infcfg.CounterConfig;

import java.util.List;

/**
 * @author Dias
 */
public interface IReport {

    void fillData(UchInf p_uch_inf);

    public void addCounters(List<? extends Counter> p_counters);

    public void initCounter(CounterConfig p_counter_config, boolean p_details);

}

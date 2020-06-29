package ru.avers.informica.report;

import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.infcfg.CounterConfig;

import java.util.List;

/**
 * @author Dias
 */
public interface IReport {

    void fillData(UchInf pUchInf);

    public void addCounters(List<? extends Counter> pCounters);

    public void initCounter(CounterConfig pCounterConfig, boolean pDetails);

}

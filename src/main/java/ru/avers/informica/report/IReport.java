package ru.avers.informica.report;

import ru.avers.informica.dto.informica.UchInf;
import ru.avers.informica.entities.informica.InformicaBuilding;
import ru.avers.informica.entities.informica.InformicaUchContact;
import ru.avers.informica.infcfg.Config;
import ru.avers.informica.infcfg.CounterConfig;

import java.util.List;

/**
 *
 * @author Dias
 */
public interface IReport {
    
    void fillData(UchInf p_uch_inf);

    void addOrgContact(InformicaUchContact p_contact);

    void addBuilding(UchInf p_subdiv, InformicaBuilding p_building, Config p_config);
    
    public void addCounters(List<? extends Counter> p_counters);

    public void initCounter(CounterConfig p_counter_config, boolean p_details);
    
}

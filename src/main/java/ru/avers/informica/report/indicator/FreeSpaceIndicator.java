package ru.avers.informica.report.indicator;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.avers.informica.dto.informica.BuildingInf;
import ru.avers.informica.dto.informica.GroupInf;
import ru.avers.informica.report.source.DataSourceUch;
import ru.avers.informica.report.source.Pair;

import java.util.Collection;

@Component
@RequiredArgsConstructor
@Order(2)
public class FreeSpaceIndicator implements Indicator {

    @Override
    public void calculateIndicator(Pair<Collection<DataSourceUch.UchInfSchema>, String> uchInfSchemas) {
        int freeSpace = 0;
        for (DataSourceUch.UchInfSchema uchInfSchema : uchInfSchemas.getFirst()) {
            for (BuildingInf buildingInf : uchInfSchema.getUchInf().getBuildingInfs()) {
                for (GroupInf groupInf : buildingInf.getGroupInfs()) {
                    freeSpace = groupInf.getCapacity() - groupInf.getEnrolled() -
                            groupInf.getTransferSpace() - groupInf.getAddCont();
                    groupInf.setFreeSpace(freeSpace);
                }
            }
        }
    }
}

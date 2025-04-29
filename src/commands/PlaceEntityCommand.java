package commands;

import coordinates.Coordinates;
import entities.Entity;
import entities.EntityFactory;
import simulationmap.SimulationMap;
import util.map.SimulationMapUtils;

public class PlaceEntityCommand extends AbstractCommandMap {
    private final int countEntities;
    private final EntityFactory factory;

    public PlaceEntityCommand(SimulationMap worldMap, int countEntities, EntityFactory factory) {
        super(worldMap);
        this.countEntities = countEntities;
        this.factory = factory;
    }

    @Override
    public void execute() {
        for (int i = 0; i < countEntities; i++) {
            Entity createdEntity = factory.create();
            Coordinates placedCoordinates = SimulationMapUtils.getFreeCoordinates(worldMap);
            worldMap.add(placedCoordinates, createdEntity);
        }
    }
}

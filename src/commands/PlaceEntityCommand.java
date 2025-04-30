package commands;

import coordinates.Coordinates;
import entities.Entity;
import entities.EntityFactory;
import simulation_map.SimulationMap;

import java.util.function.Supplier;

public class PlaceEntityCommand extends AbstractCommandMap {
    private final int countEntities;
    private final EntityFactory factory;
    private final Supplier<Coordinates> coordinatesSupplier;

    public PlaceEntityCommand(SimulationMap worldMap, Supplier<Coordinates> coordinatesSupplier, int countEntities, EntityFactory factory) {
        super(worldMap);
        this.coordinatesSupplier = coordinatesSupplier;
        this.countEntities = countEntities;
        this.factory = factory;
    }

    @Override
    public void execute() {
        for (int i = 0; i < countEntities; i++) {
            Entity createdEntity = factory.create();
            Coordinates placedCoordinates = coordinatesSupplier.get();
            worldMap.add(placedCoordinates, createdEntity);
        }
    }
}

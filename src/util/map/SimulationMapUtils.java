package util.map;

import coordinates.Coordinates;
import entities.Entity;
import simulation_map.SimulationMap;

import java.util.Random;

public class SimulationMapUtils {
    private SimulationMapUtils() {

    }

    public static Coordinates getFreeCoordinates(SimulationMap worldMap) {
        Random coordinatesRandomizer = new Random();
        Coordinates freeCoordinates;

        do {
            int row = coordinatesRandomizer.nextInt(worldMap.getWidth());
            int column = coordinatesRandomizer.nextInt(worldMap.getHeight());
            freeCoordinates = new Coordinates(row, column);
        } while (worldMap.hasEntity(freeCoordinates));

        return freeCoordinates;
    }

    public static void moveEntity(SimulationMap worldMap, Coordinates from, Coordinates to, boolean isTargetRemove) {
        worldMap.checkingAvailability(from);

        if (isTargetRemove) {
            worldMap.remove(to);
        } else {
            worldMap.checkAbsence(to);
        }

        Entity movedEntity = worldMap.get(from);
        worldMap.remove(from);
        worldMap.add(to, movedEntity);
    }
}

package util.map;

import coordinates.Coordinates;
import simulationmap.SimulationMap;

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
}

package simulationmap;

import coordinates.Coordinates;
import entities.Entity;

import java.util.HashMap;
import java.util.Map;

public class SimulationMap {
    private final int width;
    private final int height;
    private final Map<Coordinates, Entity> map;

    public SimulationMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new HashMap<>();
    }

    public boolean hasEntity(Coordinates coordinates) {
        return map.containsKey(coordinates);
    }

    public void add(Coordinates coordinates, Entity entity) {
        validateCoordinates(coordinates);
        map.put(coordinates, entity);
    }

    public void remove(Coordinates coordinates) {
        validateCoordinates(coordinates);
        map.remove(coordinates);
    }

    public void validateCoordinates(Coordinates coordinates) {
        final boolean isRowInvalid = !(coordinates.row() >= 0 && coordinates.row() < height);
        final boolean isColumnInvalid = !(coordinates.column() >= 0 && coordinates.column() < width);

        if (isRowInvalid || isColumnInvalid) {
            throw new IllegalArgumentException("SimulationMap: coordinates " + coordinates + " invalid");
        }
    }
}

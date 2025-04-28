package simulationmap;

import coordinates.Coordinates;
import entities.Entity;

import java.util.HashMap;
import java.util.Map;

public class SimulationMap {
    private final int width;
    private final int height;
    private final Map<Coordinates, Entity> entities;

    protected SimulationMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new HashMap<>();
    }

    public boolean hasEntity(Coordinates coordinates) {
        return entities.containsKey(coordinates);
    }

    public void add(Coordinates coordinates, Entity entity) {
        validateCoordinates(coordinates);
        checkAbsence(coordinates);
        entities.put(coordinates, entity);
    }

    public void remove(Coordinates coordinates) {
        validateCoordinates(coordinates);
        checkingAvailability(coordinates);
        entities.remove(coordinates);
    }

    public Entity get(Coordinates coordinates) {
        validateCoordinates(coordinates);
        checkingAvailability(coordinates);
        return entities.get(coordinates);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void validateCoordinates(Coordinates coordinates) {
        final boolean isRowInvalid = !(coordinates.row() >= 0 && coordinates.row() < height);
        final boolean isColumnInvalid = !(coordinates.column() >= 0 && coordinates.column() < width);
        if (isRowInvalid || isColumnInvalid) {
            throw new IllegalArgumentException("SimulationMap: coordinates " + coordinates + " invalid");
        }
    }

    private void checkingAvailability(Coordinates coordinates) {
        if (!hasEntity(coordinates)) {
            throw new IllegalArgumentException("SimulationMap: worldMap hasn't entity by " + coordinates);
        }
    }

    private void checkAbsence(Coordinates coordinates) {
        if (hasEntity(coordinates)) {
            throw new IllegalArgumentException("SimulationMap: worldMap already has entity by " + coordinates);
        }
    }
}

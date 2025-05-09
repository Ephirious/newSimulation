package simulation_map;

import coordinates.Coordinates;
import entities.Entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

    public boolean hasEntity(Entity entity) {
        for (Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {
            if (entry.getValue() == entity) {
                return true;
            }
        }

        return false;
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

    public void clear() {
        entities.clear();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public <T extends Entity> List<T> getEntitiesByType(Class<T> clazz) {
        List<T> result = new LinkedList<>();

        for (Coordinates currentCoordinates : entities.keySet()) {
            Entity currentEntity = entities.get(currentCoordinates);
            if (clazz.isInstance(currentEntity)) {
                result.add(((T) currentEntity));
            }
        }

        return result;
    }

    public Coordinates getEntityCoordinates(Entity entity) {
        for (Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {
            if (entry.getValue() == entity) {
                return entry.getKey();
            }
        }

        throw new IllegalArgumentException("SimulationMap: worldMap hasn't entity " + entity);
    }

    public boolean isValid(Coordinates coordinates) {
        final boolean isRowValid = (coordinates.row() >= 0 && coordinates.row() < height);
        final boolean isColumnValid = (coordinates.column() >= 0 && coordinates.column() < width);

        return isRowValid && isColumnValid;
    }

    public void validateCoordinates(Coordinates coordinates) {
        if (!isValid(coordinates)) {
            throw new IllegalArgumentException("SimulationMap: coordinates " + coordinates + " invalid");
        }
    }

    public void checkingAvailability(Coordinates coordinates) {
        if (!hasEntity(coordinates)) {
            throw new IllegalArgumentException("SimulationMap: worldMap hasn't entity by " + coordinates);
        }
    }

    public void checkAbsence(Coordinates coordinates) {
        if (hasEntity(coordinates)) {
            throw new IllegalArgumentException("SimulationMap: worldMap already has entity by " + coordinates);
        }
    }
}

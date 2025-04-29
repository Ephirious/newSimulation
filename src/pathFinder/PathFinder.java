package pathFinder;

import coordinates.Coordinates;
import entities.Entity;
import simulationmap.SimulationMap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class PathFinder {
    protected static final Coordinates START_COORDINATE = null;

    private final Map<Coordinates, Coordinates> stepsFromSourceToTarget;

    protected final SimulationMap worldMap;
    protected Coordinates targetCoordinates;


    public PathFinder(SimulationMap worldMap) {
        this.worldMap = worldMap;
        this.stepsFromSourceToTarget = new HashMap<>();
    }

    public abstract List<Coordinates> find(Coordinates source, Class<? extends Entity> target);

    protected List<Coordinates> parsePath(Coordinates target) {
        List<Coordinates> parsedPath = new LinkedList<>();

        while (stepsFromSourceToTarget.get(target) != START_COORDINATE) {
            parsedPath.add(target);
            target = stepsFromSourceToTarget.get(target);
        }

        return parsedPath.reversed();
    }

    protected void addStep(Coordinates from, Coordinates to) {
        stepsFromSourceToTarget.put(from, to);
    }

    protected boolean isVisited(Coordinates coordinates) {
        return stepsFromSourceToTarget.containsKey(coordinates);
    }

    protected void clear() {
        stepsFromSourceToTarget.clear();
    }
}
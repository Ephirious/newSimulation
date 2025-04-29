package pathFinder;

import coordinates.Coordinates;
import entities.Entity;
import util.coordinates.CoordinatesUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch extends PathFinder {
    private final Queue<Coordinates> coordinatesToCheck;

    public BreadthFirstSearch(simulationmap.SimulationMap map) {
        super(map);
        coordinatesToCheck = new LinkedList<>();
    }

    @Override
    public List<Coordinates> find(Coordinates source, Class<? extends Entity> target) {
        worldMap.validateCoordinates(source);

        coordinatesToCheck.add(source);
        addStep(source, START_COORDINATE);

        while (!coordinatesToCheck.isEmpty()) {
            Coordinates current = coordinatesToCheck.poll();

            if (worldMap.get(current).getClass() == target) {
                targetCoordinates = current;
                break;
            }

            if (worldMap.hasEntity(current) && !current.equals(source))
                continue;

            List<Coordinates> neighboringCoordinates = CoordinatesUtils.getNeighboringCoordinates(current);
            neighboringCoordinates.removeIf(coordinates -> !worldMap.isValid(coordinates));

            for (Coordinates neighboring : neighboringCoordinates)
                if (!isVisited(neighboring)) {
                    coordinatesToCheck.add(neighboring);
                    addStep(neighboring, current);
                }
        }

        List<Coordinates> path = parsePath(targetCoordinates);
        clear();

        return path;
    }

    @Override
    protected void clear() {
        super.clear();
        coordinatesToCheck.clear();
    }
}

package pathFinder;

import coordinates.Coordinates;
import entities.Entity;
import util.coordinates.CoordinatesUtils;

import java.util.*;

public class AStar extends PathFinder {
    private static final int COST_FOR_MOVE = 1;

    private final int capacity;
    private final Map<Coordinates, Integer> costsForCoordinates;

    public AStar(simulationmap.SimulationMap worldMap) {
        super(worldMap);

        capacity = (worldMap.getWidth() - 1) * (worldMap.getHeight() - 1);
        costsForCoordinates = new HashMap<>();
    }

    @Override
    public List<Coordinates> find(Coordinates source, Class<? extends Entity> target) {
        int minPathSize = worldMap.getWidth() * worldMap.getHeight();
        List<? extends Entity> targetsEntities = worldMap.getEntitiesByType(target);
        List<Coordinates> resultPath = new LinkedList<>();

        for (Entity current : targetsEntities) {
            Coordinates currentCoordinates = worldMap.getEntityCoordinates(current);
            List<Coordinates> path = findPathToTarget(source, currentCoordinates);

            final boolean isNewPathLessThanMinPath = !path.isEmpty() && path.size() < minPathSize;
            if (isNewPathLessThanMinPath) {
                resultPath = path;
                minPathSize = path.size();
            }
        }

        return resultPath;
    }

    public List<Coordinates> findPathToTarget(Coordinates source, Coordinates target) {
        worldMap.validateCoordinates(source);

        Queue<Coordinates> coordinatesToCheck = new PriorityQueue<>(capacity, getComparator(targetCoordinates));

        addStep(source, START_COORDINATE);
        costsForCoordinates.put(source, COST_FOR_MOVE);
        coordinatesToCheck.add(source);

        while (!coordinatesToCheck.isEmpty()) {
            Coordinates current = coordinatesToCheck.poll();

            if (current.equals(target)) {
                break;
            }

            if (worldMap.hasEntity(current) && !current.equals(source)) {
                continue;
            }

            List<Coordinates> neighboringCoordinates = CoordinatesUtils.getNeighboringCoordinates(current);
            neighboringCoordinates.removeIf(checkedCoordinates -> !worldMap.isValid(checkedCoordinates));

            for (Coordinates neighboring : neighboringCoordinates) {
                int newCost = costsForCoordinates.get(current) + COST_FOR_MOVE;
                if (!costsForCoordinates.containsKey(neighboring) || newCost < costsForCoordinates.get(neighboring)) {
                    costsForCoordinates.put(neighboring, newCost);
                    addStep(neighboring, current);
                    coordinatesToCheck.add(neighboring);
                }
            }
        }

        List<Coordinates> path = parsePath(target);
        clear();

        return path;
    }

    @Override
    protected void clear() {
        super.clear();
        costsForCoordinates.clear();
    }

    private double heuristic(Coordinates source, Coordinates target) {
        double epsilon = 0.001;
        return Math.abs(source.row() - target.row()) + Math.abs(source.column() - target.column()) * (1 + epsilon);
    }

    private Comparator<Coordinates> getComparator(Coordinates target) {
        return (first, second) -> Double.compare(
                heuristic(first, target) + costsForCoordinates.get(first),
                heuristic(second, target) + costsForCoordinates.get(second)
        );
    }
}

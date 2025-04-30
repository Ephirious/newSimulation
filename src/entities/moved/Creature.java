package entities.moved;

import coordinates.Coordinates;
import entities.Entity;
import path_finder.PathFinder;
import simulation_map.SimulationMap;
import util.coordinates.CoordinatesUtils;
import util.enums.Speed;
import util.map.SimulationMapUtils;

import java.util.List;
import java.util.Random;

public abstract class Creature extends Entity {
    public static final int MIN_HP = 0;
    public static final int MAX_HP = 100;
    public static final int DAMAGE_FOR_MOVE = 3;

    private final Speed speed;
    private final Class<? extends Entity> targetFood;
    private final PathFinder finder;
    private int healthPoints;

    public Creature(int healthPoints, Speed speed, Class<? extends Entity> targetFood, PathFinder finder) {
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.targetFood = targetFood;
        this.finder = finder;
    }

    public abstract void eat(SimulationMap worldMap, Coordinates target);

    public int getHealthPoints() {
        return healthPoints;
    }

    public void decreaseHealthPoints(int value) {
        validateValue(value);

        healthPoints -= value;
        if (healthPoints < MIN_HP) {
            healthPoints = MIN_HP;
        }
    }

    public void increaseHealthPoints(int value) {
        validateValue(value);

        healthPoints += value;
        if (healthPoints > MAX_HP) {
            healthPoints = MAX_HP;
        }
    }

    public void move(SimulationMap worldMap) {
        Coordinates creaturesCoordinates = worldMap.getEntityCoordinates(this);
        List<Coordinates> path = finder.find(creaturesCoordinates, targetFood);

        final boolean isPathLengthMoreThanSpeed = path.size() > speed.getSpeed();
        final boolean isPathLengthEqualOrLessThanSpeed = !path.isEmpty() && !isPathLengthMoreThanSpeed;

        if (isPathLengthMoreThanSpeed) {
            moveIfPathMoreThanSpeed(worldMap, creaturesCoordinates, path);
        } else if (isPathLengthEqualOrLessThanSpeed) {
            final boolean isCreatureNotStandingInFrontOfFood = (path.size() != 1);

            if (isCreatureNotStandingInFrontOfFood) {
                moveIfPathLessOrEqualSpeed(worldMap, creaturesCoordinates, path);
            }

            eat(worldMap, path.removeLast());
        } else {
            randomMove(worldMap, creaturesCoordinates);
        }

        decreaseHealthPoints(DAMAGE_FOR_MOVE);
    }

    private void moveIfPathMoreThanSpeed(SimulationMap worldMap, Coordinates from, List<Coordinates> path) {
        int indexDifferenceBetweenSpeedAndPath = 1;
        int coordinatesIndexInPath = speed.getSpeed() - indexDifferenceBetweenSpeedAndPath;
        Coordinates target = path.get(coordinatesIndexInPath);
        SimulationMapUtils.moveEntity(worldMap, from, target, false);
    }

    private void moveIfPathLessOrEqualSpeed(SimulationMap worldMap, Coordinates from, List<Coordinates> path) {
        int indexDifferenceBetweenSpeedAndPath = 2;
        int coordinatesIndexInPath = path.size() - indexDifferenceBetweenSpeedAndPath;
        Coordinates target = path.get(coordinatesIndexInPath);
        SimulationMapUtils.moveEntity(worldMap, from, target, false);
    }

    private void randomMove(SimulationMap worldMap, Coordinates from) {
        List<Coordinates> shifts = CoordinatesUtils.getNeighboringCoordinates(from);
        shifts.removeIf(cord -> worldMap.hasEntity(cord) || !worldMap.isValid(cord));

        if (!shifts.isEmpty()) {
            Random randomizer = new Random();
            int bound = shifts.size();
            int index = randomizer.nextInt(bound);
            Coordinates newCoordinates = shifts.get(index);
            SimulationMapUtils.moveEntity(worldMap, from, newCoordinates, false);
        }
    }

    private void validateValue(int value) {
        final boolean isNegativeValue = value < 0;
        if (isNegativeValue) {
            throw new IllegalArgumentException("Creature: value can't be negative");
        }
    }
}

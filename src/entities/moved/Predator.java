package entities.moved;

import coordinates.Coordinates;
import path_finder.PathFinder;
import simulation_map.SimulationMap;
import util.enums.Damage;
import util.enums.Speed;
import util.map.SimulationMapUtils;

public class Predator extends Creature {
    private final Damage damage;

    protected Predator(int healthPoints, Speed speed, PathFinder finder, Damage damage) {
        super(healthPoints, speed, Herbivore.class, finder);
        this.damage = damage;
    }

    @Override
    public void eat(SimulationMap worldMap, Coordinates target) {
        Herbivore eatenHerbivore = ((Herbivore) worldMap.get(target));

        int reducedHealthPointValue = damage.getDamageWithinBoundaries();
        eatenHerbivore.decreaseHealthPoints(reducedHealthPointValue);

        if (eatenHerbivore.getHealthPoints() <= Creature.MIN_HP) {
            Coordinates currentCoordinates = worldMap.getEntityCoordinates(this);
            SimulationMapUtils.moveEntity(worldMap, currentCoordinates, target, true);
        }
    }
}
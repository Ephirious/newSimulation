package entities.moved;

import coordinates.Coordinates;
import entities.unmoved.Grass;
import path_finder.PathFinder;
import simulation_map.SimulationMap;
import util.enums.Speed;
import util.map.SimulationMapUtils;

public class Herbivore extends Creature {
    protected Herbivore(int healthPoints, Speed speed, PathFinder finder) {
        super(healthPoints, speed, Grass.class, finder);
    }

    @Override
    public void eat(SimulationMap worldMap, Coordinates target) {
        Grass eatenGrass = ((Grass) worldMap.get(target));
        Coordinates herbivoreCoordinates = worldMap.getEntityCoordinates(this);

        int addedHealthPointsValue = eatenGrass.getNutritionValue();
        increaseHealthPoints(addedHealthPointsValue);

        SimulationMapUtils.moveEntity(worldMap, herbivoreCoordinates, target, true);
    }
}

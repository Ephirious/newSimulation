package entities.moved;

import entities.Entity;
import entities.EntityFactory;
import path_finder.PathFinder;
import util.enums.Speed;

public class HerbivoreFactory implements EntityFactory {
    private final int healthPoints;
    private final Speed speed;
    private final PathFinder finder;

    public HerbivoreFactory(int healthPoints, Speed speed, PathFinder finder) {
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.finder = finder;
    }

    @Override
    public Entity create() {
        return new Herbivore(healthPoints, speed, finder);
    }
}

package entities.moved;

import entities.Entity;
import entities.EntityFactory;
import pathFinder.PathFinder;
import util.enums.Damage;
import util.enums.Speed;

public class PredatorFactory implements EntityFactory {
    private final int healthPoints;
    private final Speed speed;
    private final PathFinder finder;
    private final Damage damage;

    public PredatorFactory(int healthPoints, Speed speed, PathFinder finder, Damage damage) {
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.finder = finder;
        this.damage = damage;
    }

    @Override
    public Entity create() {
        return new Predator(healthPoints, speed, finder, damage);
    }
}

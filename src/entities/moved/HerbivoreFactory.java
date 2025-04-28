package entities.moved;

import entities.Entity;
import entities.EntityFactory;
import util.enums.Speed;

public class HerbivoreFactory implements EntityFactory {
    private final int healthPoints;
    private final Speed speed;

    public HerbivoreFactory(int healthPoints, Speed speed) {
        this.healthPoints = healthPoints;
        this.speed = speed;
    }

    @Override
    public Entity create() {
        return new Herbivore(healthPoints, speed);
    }
}

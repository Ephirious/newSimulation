package entities.moved;

import entities.Entity;
import util.enums.Speed;

public abstract class Creature extends Entity {
    public static final int MIN_HP = 0;
    public static final int MAX_HP = 100;

    private final int healthPoints;
    private final Speed speed;
    private final Class<? extends Entity> target;

    public Creature(int healthPoints, Speed speed, Class<? extends Entity> target) {
        this.healthPoints = healthPoints;
        this.speed = speed;
        this.target = target;
    }

    public Class<? extends Entity> getTarget() {
        return target;
    }
}

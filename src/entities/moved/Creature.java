package entities.moved;

import entities.Entity;
import util.enums.Speed;

public class Creature extends Entity {
    public static final int MIN_HP = 0;
    public static final int MAX_HP = 100;

    private final int healthPoints;
    private final Speed speed;

    protected Class<? extends Entity> target;

    protected Creature(int healthPoints, Speed speed) {
        this.healthPoints = healthPoints;
        this.speed = speed;
    }
}

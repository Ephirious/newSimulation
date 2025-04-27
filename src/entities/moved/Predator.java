package entities.moved;

import util.enums.Damage;
import util.enums.Speed;

public class Predator extends Creature {
    private final Damage damage;

    protected Predator(int healthPoints, Speed speed, Damage damage) {
        super(healthPoints, speed);
        this.damage = damage;
        target = Herbivore.class;
    }
}

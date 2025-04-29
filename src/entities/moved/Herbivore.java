package entities.moved;

import entities.unmoved.Grass;
import util.enums.Speed;

public class Herbivore extends Creature {
    protected Herbivore(int healthPoints, Speed speed) {
        super(healthPoints, speed, Grass.class);
    }
}

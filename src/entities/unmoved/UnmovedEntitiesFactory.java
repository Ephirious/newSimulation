package entities.unmoved;

import entities.Entity;
import entities.EntityFactory;

public class UnmovedEntitiesFactory implements EntityFactory {
    public enum UnmovedEntityType {
        ROCK, TREE, GRASS;
    }

    private final UnmovedEntityType type;

    public UnmovedEntitiesFactory(UnmovedEntityType type) {
        this.type = type;
    }

    @Override
    public Entity create() {
        Entity returningEntity = null;

        switch (type) {
            case ROCK -> returningEntity = new Rock();
            case TREE -> returningEntity = new Tree();
            case GRASS -> returningEntity = new Grass();
        }

        return returningEntity;
    }
}

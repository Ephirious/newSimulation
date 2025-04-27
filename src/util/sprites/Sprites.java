package util.sprites;

import entities.Entity;

import java.util.Map;

public class Sprites {
    private final Map<Class<? extends Entity>, String> mapOfSprites;

    public Sprites(Map<Class<? extends Entity>, String> sprites) {
        this.mapOfSprites = sprites;
    }

    public boolean hasSprites(Class<? extends Entity> clazz) {
        return mapOfSprites.containsKey(clazz);
    }

    public String getSprite(Class<? extends Entity> clazz) {
        if (!mapOfSprites.containsKey(clazz)) {
            throw new IllegalArgumentException("Hasn't sprite for " + clazz.getSimpleName() + " class");
        }

        return mapOfSprites.get(clazz);
    }
}

package util.enums;

import java.util.Random;

public enum Damage {
    LOW(10),
    MEDIUM(20),
    HIGH(30);

    private static final Random damageRandomizer = new Random();

    private final int highestDamageBound;

    Damage(int highestDamageBound) {
        this.highestDamageBound = highestDamageBound;
    }

    public static Damage getRandomDamage() {
        return values()[damageRandomizer.nextInt(values().length)];
    }

    public int getDamageWithinBoundaries() {
        int origin = 1;
        int currentDamageIndex = this.ordinal();
        Damage[] damages = values();

        for (int i = 0; i < currentDamageIndex; i++) {
            origin = damages[i].highestDamageBound;
        }

        return damageRandomizer.nextInt(origin, highestDamageBound);
    }
}
package util.enums;

import java.util.Random;

public enum Speed {
    SLOW(1),
    MEDIUM(2),
    FAST(3);

    private static final Random speedRandomizer = new Random();

    private final int speed;

    Speed(int speed) {
        this.speed = speed;
    }

    public static Speed getRandomSpeed() {
        Speed[] speeds = values();
        int countSpeeds = speeds.length;
        int numberOfSpeed = speedRandomizer.nextInt(countSpeeds);

        return speeds[numberOfSpeed];
    }

    public int getSpeed() {
        return speed;
    }
}

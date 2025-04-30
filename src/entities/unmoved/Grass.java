package entities.unmoved;

import commands.Command;
import commands.place.PlaceEntityCommand;
import coordinates.Coordinates;
import entities.Entity;
import simulation_map.SimulationMap;
import util.coordinates.CoordinatesUtils;

import java.util.List;
import java.util.Random;

public class Grass extends Entity {
    private static final int NUTRITION_MIN = 0;
    private static final int NUTRITION_LOWER_BOUND = 1;
    private static final int NUTRITION_UPPER_BOUND = 20;
    private static final int DECREASE_LOWER_BOUND = 2;
    private static final int DECREASE_UPPER_BOUND = 8;

    private static final Random randomizer = new Random();

    private int nutritionValue;
    private final int decreaseValue;

    protected Grass() {
        this.nutritionValue = randomizer.nextInt(NUTRITION_LOWER_BOUND, NUTRITION_UPPER_BOUND);
        this.decreaseValue = randomizer.nextInt(DECREASE_LOWER_BOUND, DECREASE_UPPER_BOUND);
    }

    public int getNutritionValue() {
        return nutritionValue;
    }

    public void decreaseNutrition() {
        nutritionValue -= decreaseValue;

        if (nutritionValue < NUTRITION_MIN) {
            nutritionValue = NUTRITION_MIN;
        }
    }

    public boolean isAlive() {
        return nutritionValue > NUTRITION_MIN;
    }

    public void photosynthesis(SimulationMap worldMap) {
        double photosynthesisChance = 0.5f;

        double photosynthesisChanceMaxBound = 1f;
        double randomChance = randomizer.nextDouble(photosynthesisChanceMaxBound);

        if (randomChance < photosynthesisChance) {
            placeGrass(worldMap);
        }
    }

    private void placeGrass(SimulationMap worldMap) {
        Coordinates currentGrassCoordinates = worldMap.getEntityCoordinates(this);
        List<Coordinates> shifts = CoordinatesUtils.getAroundCoordinates(currentGrassCoordinates);

        shifts.removeIf(coordinates ->
                worldMap.hasEntity(coordinates) ||
                !worldMap.isValid(coordinates)
        );

        if (!shifts.isEmpty()) {
            int countGrass = 1;

            for (Coordinates shift : shifts) {
                Command placeGrass = new PlaceEntityCommand(
                        worldMap,
                        () -> shift,
                        countGrass,
                        new UnmovedEntitiesFactory(UnmovedEntityType.GRASS)
                );

                placeGrass.execute();
            }
        }
    }
}

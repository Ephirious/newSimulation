package simulation;

import entities.moved.Herbivore;
import entities.moved.Predator;
import entities.unmoved.Grass;
import simulation_map.SimulationMap;

public class SimulationInformation {
    private final SimulationMap worldMap;
    private int countTurns;
    private int countGrass;
    private int countHerbivores;
    private int countPredators;

    public SimulationInformation(SimulationMap worldMap) {
        this.worldMap = worldMap;
    }

    public void update() {
        countTurns++;
        countGrass = worldMap.getEntitiesByType(Grass.class).size();
        countHerbivores = worldMap.getEntitiesByType(Herbivore.class).size();
        countPredators = worldMap.getEntitiesByType(Predator.class).size();
    }

    public int getCountTurns() {
        return countTurns;
    }

    public int getCountGrass() {
        return countGrass;
    }

    public int getCountHerbivores() {
        return countHerbivores;
    }

    public int getCountPredators() {
        return countPredators;
    }
}

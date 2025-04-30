package commands.turns;

import coordinates.Coordinates;
import entities.unmoved.Grass;
import simulation_map.SimulationMap;

import java.util.List;

public class MakeGrassTurnCommand extends  AbstractCommandMap {
    public MakeGrassTurnCommand(SimulationMap worldMap) {
        super(worldMap);
    }

    @Override
    public void execute() {
        List<Grass> grasses = worldMap.getEntitiesByType(Grass.class);

        for (Grass concreteGrass : grasses) {
            wiltingGrass(concreteGrass);
            checkDeath(concreteGrass);
            photosynthesis(concreteGrass);
        }
    }

    private void wiltingGrass(Grass grass) {
        grass.decreaseNutrition();
    }

    private void checkDeath(Grass grass) {
        if (!grass.isAlive()) {
            Coordinates coordinates = worldMap.getEntityCoordinates(grass);
            worldMap.remove(coordinates);
        }
    }

    private void photosynthesis(Grass grass) {
        if (grass.isAlive()) {
            grass.photosynthesis(worldMap);
        }
    }
}

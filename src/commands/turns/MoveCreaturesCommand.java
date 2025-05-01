package commands.turns;

import coordinates.Coordinates;
import entities.moved.Creature;
import simulation_map.SimulationMap;

import java.util.List;

public class MoveCreaturesCommand extends AbstractCommandMap {
    public MoveCreaturesCommand(SimulationMap worldMap) {
        super(worldMap);
    }

    @Override
    public void execute() {
        List<Creature> creatures = worldMap.getEntitiesByType(Creature.class);
        for (Creature current : creatures) {
            if (current.getHealthPoints() > Creature.MIN_HP) {
                current.move(worldMap);
            } else {
                if (worldMap.hasEntity(current)) {
                    Coordinates currentCreaturesCoordinates = worldMap.getEntityCoordinates(current);
                    worldMap.remove(currentCreaturesCoordinates);
                }
            }
        }
    }
}

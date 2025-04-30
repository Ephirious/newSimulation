package commands;

import entities.moved.Creature;
import entities.moved.HerbivoreFactory;
import entities.moved.PredatorFactory;
import entities.unmoved.UnmovedEntitiesFactory;
import entities.unmoved.UnmovedEntityType;
import path_finder.PathFinder;
import simulation_map.SimulationMap;
import util.enums.Damage;
import util.enums.Speed;
import util.map.SimulationMapUtils;

import java.util.LinkedList;
import java.util.List;

public final class InitCommandsFactory extends AbstractCommandsFactory {
    private static final double ROCK_PERCENT = 0.1f;
    private static final double TREE_PERCENT = 0.1f;
    private static final double GRASS_PERCENT = 0.25f;
    private static final double HERBIVORE_PERCENT = 0.1f;
    private static final double PREDATOR_PERCENT = 0.05f;

    private final SimulationMap worldMap;
    private final PathFinder finder;

    public InitCommandsFactory(SimulationMap worldMap, PathFinder finder) {
        this.worldMap = worldMap;
        this.finder = finder;
    }

    @Override
    public List<Command> getCommands() {
        List<Command> initCommands = new LinkedList<>();

        int mapSize = worldMap.getWidth() * worldMap.getHeight();
        int countRocks = ((int) (mapSize * ROCK_PERCENT));
        int countTrees = ((int) (mapSize * TREE_PERCENT));
        int countGrasses = ((int) (mapSize * GRASS_PERCENT));
        int countHerbivores = ((int) (mapSize * HERBIVORE_PERCENT));
        int countPredators = ((int) (mapSize * PREDATOR_PERCENT));

        initCommands.add(new PlaceEntityCommand(
                worldMap,
                () -> SimulationMapUtils.getFreeCoordinates(worldMap),
                countRocks,
                new UnmovedEntitiesFactory(UnmovedEntityType.ROCK)
        ));
        initCommands.add(new PlaceEntityCommand(
                worldMap,
                () -> SimulationMapUtils.getFreeCoordinates(worldMap),
                countTrees,
                new UnmovedEntitiesFactory(UnmovedEntityType.TREE)
        ));
        initCommands.add(new PlaceEntityCommand(
                worldMap,
                () -> SimulationMapUtils.getFreeCoordinates(worldMap),
                countGrasses,
                new UnmovedEntitiesFactory(UnmovedEntityType.GRASS)
        ));

        initCommands.add(new PlaceEntityCommand(
                worldMap,
                () -> SimulationMapUtils.getFreeCoordinates(worldMap),
                countHerbivores,
                new HerbivoreFactory(Creature.MAX_HP, Speed.getRandomSpeed(), finder)
        ));
        initCommands.add(new PlaceEntityCommand(
                worldMap,
                () -> SimulationMapUtils.getFreeCoordinates(worldMap),
                countPredators,
                new PredatorFactory(Creature.MAX_HP, Speed.getRandomSpeed(), finder, Damage.getRandomDamage())
        ));

        return initCommands;
    }
}

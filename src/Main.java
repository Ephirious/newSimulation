import commands.Command;
import commands.factories.InitCommandsFactory;
import commands.factories.TurnCommandsFactory;
import path_finder.AStar;
import path_finder.PathFinder;
import renderer.Renderer;
import renderer.SimulationRenderer;
import simulation.Simulation;
import simulation.SimulationInformation;
import simulation_map.MediumMapFactory;
import simulation_map.SimulationMap;
import util.sprites.SpriteFactory;
import util.sprites.Sprites;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        SimulationMap worldMap = new MediumMapFactory().createMap();
        SimulationInformation information = new SimulationInformation(worldMap);
        Sprites sprites = SpriteFactory.getDefaultSprites();
        Renderer renderer = new SimulationRenderer(worldMap, sprites, information);

        PathFinder finder = new AStar(worldMap);
        List<Command> initCommands = new InitCommandsFactory(worldMap, finder).getCommands();
        List<Command> turnCommand = new TurnCommandsFactory(worldMap).getCommands();

        Simulation simulation = new Simulation(worldMap, renderer, information, initCommands, turnCommand);
        simulation.startSimulation();
    }
}

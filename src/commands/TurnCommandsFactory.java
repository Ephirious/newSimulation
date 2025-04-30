package commands;

import simulationmap.SimulationMap;

import java.util.LinkedList;
import java.util.List;

public class TurnCommandsFactory extends AbstractCommandsFactory {
    private final SimulationMap worldMap;

    public TurnCommandsFactory(SimulationMap worldMap) {
        this.worldMap = worldMap;
    }

    @Override
    public List<Command> getCommands() {
        List<Command> turnCommands = new LinkedList<>();

        turnCommands.add(new MoveCreaturesCommand(worldMap));
        turnCommands.add(new MakeGrassTurnCommand(worldMap));

        return turnCommands;
    }
}

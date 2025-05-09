package commands.turns;

import commands.Command;
import simulation_map.SimulationMap;

public abstract class AbstractCommandMap implements Command {
    protected final SimulationMap worldMap;

    public AbstractCommandMap(SimulationMap worldMap) {
        this.worldMap = worldMap;
    }
}

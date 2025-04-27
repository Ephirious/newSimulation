package commands;

import simulationmap.SimulationMap;

public abstract class AbstractCommandMap implements Command {
    protected final SimulationMap worldMap;

    public AbstractCommandMap(SimulationMap worldMap) {
        this.worldMap = worldMap;
    }
}

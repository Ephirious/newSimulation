package simulation;

import commands.Command;
import key_reader.KeyReader;
import renderer.Renderer;
import simulation_map.SimulationMap;

import java.util.List;

public class Simulation {
    private static final int SLEEP_DURATION_IN_MILLIS = 1000;

    private final SimulationMap worldMap;
    private final Renderer renderer;
    private final SimulationInformation information;
    private final List<Command> initCommands;
    private final List<Command> turnCommands;

    private final Thread readerKeysThread;

    private volatile boolean isClosed = false;
    private volatile boolean isPause = false;

    public Simulation(SimulationMap worldMap, Renderer renderer, SimulationInformation information, List<Command> initCommands, List<Command> turnCommands) {
        this.worldMap = worldMap;
        this.renderer = renderer;
        this.information = information;
        this.initCommands = initCommands;
        this.turnCommands = turnCommands;

        this.readerKeysThread = new Thread(new KeyReader(this));
    }

    public void startSimulation() {
        readerKeysThread.start();
        initCommands.forEach(Command::execute);
        while (!isClosed) {
            nextTurn();
        }
    }

    public void nextTurn() {
        while (!isClosed && !isPause) {
            information.update();

            renderer.render();

            turnCommands.forEach(Command::execute);

            sleep(SLEEP_DURATION_IN_MILLIS);
        }
    }

    public void restart() {
        worldMap.clear();
        initCommands.forEach(Command::execute);
        information.reset();
        isPause = false;
    }

    public void pause() {
        isPause = true;
    }

    public void unpause() {
        isPause = false;
    }

    public void close() {
        isClosed = true;
    }

    public boolean isClosed() {
        return isClosed;
    }

    private void sleep(int durationInMilliseconds) {
        try {
            Thread.sleep(durationInMilliseconds);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}

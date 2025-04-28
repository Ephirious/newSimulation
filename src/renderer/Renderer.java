package renderer;

import simulationmap.SimulationMap;

public interface Renderer {
    void render();
    void setWorldMap(SimulationMap worldMap);
}

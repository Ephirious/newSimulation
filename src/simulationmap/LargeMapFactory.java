package simulationmap;

public class LargeMapFactory extends AbstractMapFactory {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;


    @Override
    public SimulationMap createMap() {
        SimulationMap worldMap = new SimulationMap(WIDTH, HEIGHT);
        placeEntity(worldMap);
        return worldMap;
    }
}

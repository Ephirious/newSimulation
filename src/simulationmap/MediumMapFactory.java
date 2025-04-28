package simulationmap;

public class MediumMapFactory extends AbstractMapFactory {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;


    @Override
    public SimulationMap createMap() {
        SimulationMap worldMap = new SimulationMap(WIDTH, HEIGHT);
        placeEntity(worldMap);
        return worldMap;
    }
}

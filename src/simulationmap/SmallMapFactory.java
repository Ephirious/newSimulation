package simulationmap;

public class SmallMapFactory extends AbstractMapFactory {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;


    @Override
    public SimulationMap createMap() {
        return new SimulationMap(WIDTH, HEIGHT);
    }
}

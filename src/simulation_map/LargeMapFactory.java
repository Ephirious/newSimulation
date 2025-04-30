package simulationmap;

public class LargeMapFactory extends AbstractMapFactory {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;


    @Override
    public SimulationMap createMap() {
        return new SimulationMap(WIDTH, HEIGHT);
    }
}

package simulationmap;

public class MediumMapFactory extends AbstractMapFactory {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;


    @Override
    public SimulationMap createMap() {
        return new SimulationMap(WIDTH, HEIGHT);
    }
}

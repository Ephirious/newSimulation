package simulationmap;

public class SmallMapFactory extends AbstractMapFactory {
    @Override
    public SimulationMap createMap() {
        int width = 10;
        int height = 10;
        return new SimulationMap(width, height);
    }
}

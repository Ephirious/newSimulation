package simulationmap;

public class LargeMapFactory extends AbstractMapFactory {
    @Override
    public SimulationMap createMap() {
        int width = 30;
        int height = 30;
        return new SimulationMap(width, height);
    }
}

package simulationmap;

public class MediumMapFactory extends AbstractMapFactory {
    @Override
    public SimulationMap createMap() {
        int width = 20;
        int height = 20;
        return new SimulationMap(width, height);
    }
}

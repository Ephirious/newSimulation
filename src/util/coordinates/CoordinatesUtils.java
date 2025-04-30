package util.coordinates;

import coordinates.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class CoordinatesUtils {
    private CoordinatesUtils() {

    }

    public static List<Coordinates> getNeighboringCoordinates(Coordinates currentCoordinate) {
        List<Coordinates> neighboringCoordinates = new ArrayList<>();

        int row = currentCoordinate.row();
        int col = currentCoordinate.column();

        neighboringCoordinates.add(new Coordinates(row + 1, col));
        neighboringCoordinates.add(new Coordinates(row - 1, col));
        neighboringCoordinates.add(new Coordinates(row, col + 1));
        neighboringCoordinates.add(new Coordinates(row, col - 1));

        return neighboringCoordinates;
    }

    public static List<Coordinates> getAroundCoordinates(Coordinates currentCoordinates) {
        List<Coordinates> aroundCoordinates = getNeighboringCoordinates(currentCoordinates);

        int row = currentCoordinates.row();
        int col = currentCoordinates.column();

        aroundCoordinates.add(new Coordinates(row + 1, col + 1));
        aroundCoordinates.add(new Coordinates(row + 1, col - 1));
        aroundCoordinates.add(new Coordinates(row - 1, col + 1));
        aroundCoordinates.add(new Coordinates(row - 1, col - 1));

        return aroundCoordinates;
    }
}

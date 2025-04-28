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
}

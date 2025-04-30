package renderer;

import coordinates.Coordinates;
import entities.Entity;
import key_reader.KeyReader;
import simulation.SimulationInformation;
import simulation_map.SimulationMap;
import util.sprites.Sprites;

public class SimulationRenderer implements Renderer {
    private final SimulationMap worldMap;
    private final Sprites sprites;
    private final SimulationInformation information;

    public SimulationRenderer(SimulationMap worldMap, Sprites sprites, SimulationInformation information) {
        this.worldMap = worldMap;
        this.sprites = sprites;
        this.information = information;
    }

    public void render() {
        renderInfo();
        renderButtons();
        renderWorldMap();
    }

    private void renderInfo() {
        System.out.println("Number of turn: " + information.getCountTurns());
        System.out.println("Number of grasses: " + information.getCountGrass());
        System.out.println("Number of herbivores: " + information.getCountHerbivores());
        System.out.println("Number of predators: " + information.getCountPredators());
    }

    private void renderButtons() {
        System.out.printf("%c - pause; %c - continue; %c - restart; %c - quit\n",
                KeyReader.KEY_PAUSE,
                KeyReader.KEY_CONTINUE,
                KeyReader.KEY_RESTART,
                KeyReader.KEY_QUIT
        );
    }

    private void renderWorldMap() {
        String currentSprite;
        String spriteForVoid = "\uD83D\uDFEB";

        for (int row = 0; row < worldMap.getWidth(); row++) {
            for (int column = 0; column < worldMap.getHeight(); column++) {
                Coordinates currentCoordinates = new Coordinates(row, column);

                if (worldMap.hasEntity(currentCoordinates)) {
                    Entity renderignEntity = worldMap.get(currentCoordinates);
                    Class<? extends Entity> renderingClass = renderignEntity.getClass();
                    currentSprite = sprites.getSprite(renderingClass);
                } else {
                    currentSprite = spriteForVoid;
                }

                System.out.print(currentSprite);
            }
            System.out.println();
        }
        System.out.println();
    }
}

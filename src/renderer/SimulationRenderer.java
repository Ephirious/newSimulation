package renderer;

import coordinates.Coordinates;
import entities.Entity;
import simulationmap.SimulationMap;
import util.sprites.SpriteFactory;
import util.sprites.Sprites;

public class SimulationRenderer {
    private final SimulationMap worldMap;
    private final Sprites sprites;

    public SimulationRenderer(SimulationMap worldMap) {
        this.worldMap = worldMap;
        this.sprites = SpriteFactory.getDefaultSprites();
    }

    public void render() {
        renderWorldMap();
    }

    private void renderWorldMap() {
        String currentSprite;
        String spriteForVoid = "\uD83D\uDFEB";

        for (int row = 0; row < worldMap.getWidth(); row++) {
            for (int column = 0; column < worldMap.getHeight(); column++) {
                Coordinates currentCoordinates = new Coordinates(row, column);

                if (worldMap.hasEntity(currentCoordinates)) {
                    Entity renderignEntity = worldMap.get(currentCoordinates);
                    currentSprite = sprites.getSprite(renderignEntity.getClass());
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

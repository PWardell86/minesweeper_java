package src.test;

import org.junit.jupiter.api.Test;
import src.main.Tile;
import src.main.TileGenerator;

import java.util.Arrays;

import static src.main.Tile.getNearTiles;

public class TileGeneratorTest {
    private final int WIDTH = 10;
    private final int HEIGHT = 10;
    private final int MAX_BOMBS = WIDTH * HEIGHT;
    private final TileGenerator TILE_GEN = new TileGenerator(MAX_BOMBS, HEIGHT, WIDTH);

    @Test
    void generateBombs_ShouldNotPlaceBombsNearStart() {
        int start_x = WIDTH / 2;
        int start_y = HEIGHT / 2;
        Tile[] tiles = TILE_GEN.generateBombs(start_x, start_y);
        Arrays.fill(tiles, new Tile((byte) 0, 0, 0));
        getNearTiles(start_x, start_y, WIDTH, HEIGHT, tiles);
    }

}

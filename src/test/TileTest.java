package src.test;

import org.junit.jupiter.api.Test;
import src.main.Tile;
import src.main.TileGenerator;

import static src.main.Tile.getNearTiles;
public class TileTest {
    private final int WIDTH = 10;
    private final int HEIGHT = 10;
    private final int MAX_BOMBS = WIDTH * HEIGHT;
    @Test
    void getNearTiles_NoBombs_ShouldReturnAllEmpty() {
        int start_x = WIDTH / 2;
        int start_y = HEIGHT / 2;
        Tile[] tiles = new Tile[WIDTH * HEIGHT];
        getNearTiles(start_x, start_y, WIDTH, HEIGHT, tiles);
    }
}

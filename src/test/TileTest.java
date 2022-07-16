package src.test;

import org.junit.Test;
import src.main.Tile;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static src.main.Tile.getNearTiles;
public class TileTest {
    private final int WIDTH = 10;
    private final int HEIGHT = 10;
    private final int MAX_BOMBS = WIDTH * HEIGHT;
    @Test
    public void getNearTiles_NoBombs_ShouldReturnAllEmpty() {
        //TODO fix this test
        int start_x = WIDTH / 2;
        int start_y = HEIGHT / 2;
        Tile[] tiles = new Tile[WIDTH * HEIGHT];
        Arrays.fill(tiles, new Tile((byte) 0, 0, 0));
        for (int x = -1; x < 2; x ++) {
            for (int y = -1; y < 2; y++) {
                int index = (start_x + x) * WIDTH + (start_y + y);
                tiles[index] = new Tile((byte) 9, 0, 0);
            }
        }
        Tile[] nearTiles = getNearTiles(start_x, start_y, WIDTH, HEIGHT, tiles);

        Arrays.stream(nearTiles).forEach(tile -> assertTrue(tile == null || tile.getValue() == 9));

    }
}

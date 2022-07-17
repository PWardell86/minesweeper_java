package src.test;

import org.junit.Test;
import src.main.Tile;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static src.main.Tile.getNearTiles;
public class TileTest {
    private final int WIDTH = 10;
    private final int HEIGHT = 10;
    private final int MAX_BOMBS = WIDTH * HEIGHT;
    @Test
    public void getNearTiles_NoBombs_ShouldReturnAllEmpty() {
        int start_x = WIDTH / 2;
        int start_y = HEIGHT / 2;
        int index;
        Tile[] tiles = new Tile[WIDTH * HEIGHT];
        for (int i = 0; i < 9; i++) {
            //Convert i to a coordinate from (-1, -1) to (1, 1)
            index = (start_x + (i % 3 - 1)) * WIDTH + (start_y + (i / 3 - 1));
            tiles[index] = new Tile((byte) 9, 0, 0);
        }
        Tile[] nearTiles = getNearTiles(start_x, start_y, WIDTH, HEIGHT, tiles);
        //Since we only assign values to the tiles near our tile, the others should be null
        Arrays.stream(nearTiles).forEach(t -> assertNotNull("None of the near tiles should be null", t));
    }
}

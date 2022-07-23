package src.test;

import org.junit.Test;
import src.main.Tile;

import java.util.Arrays;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
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
            tiles[index] = new Tile((byte) 9, 0);
        }
        Tile[] nearTiles = getNearTiles(start_x, start_y, WIDTH, HEIGHT, tiles);
        //Since we only assign values to the tiles near our tile, the others should be null
        Arrays.stream(nearTiles).forEach(t -> assertNotNull("None of the near tiles should be null", t));
    }

    @Test
    public void getNearTiles_NoBombs_ProperNumberOfTilesShouldBeNull() {
        Tile[] tiles = new Tile[WIDTH * HEIGHT];
        Arrays.fill(tiles, new Tile((byte) 0, 0));
        Tile[] corner1 = getNearTiles(0, 0, WIDTH, HEIGHT, tiles);
        assertTileHasCorrectNullTiles("A corner tile should have exatcly 5 null near tiles", corner1, 5);

        Tile[] corner2 = getNearTiles(WIDTH - 1, HEIGHT - 1, WIDTH, HEIGHT, tiles);
        assertTileHasCorrectNullTiles("A corner tile should have exatcly 5 null near tiles", corner2, 5);

        Tile[] side1 = getNearTiles(0, HEIGHT / 2, WIDTH, HEIGHT, tiles);
        assertTileHasCorrectNullTiles("A side tile should have exactly 3 null tiles", side1, 3);

        Tile[] side2 = getNearTiles(WIDTH / 2, 0, WIDTH, HEIGHT, tiles);
        assertTileHasCorrectNullTiles("A side tile should have exactly 3 null tiles", side2, 3);

        Tile[] center = getNearTiles(WIDTH / 2, HEIGHT / 2, WIDTH, HEIGHT, tiles);
        assertTileHasCorrectNullTiles("A center tile should have exactly 0 null tiles", center, 0);
    }

    private void assertTileHasCorrectNullTiles(String message, Tile[] nearTiles, int expected) {
        assertEquals(message, expected, (int) Arrays.stream(nearTiles).filter(Objects::isNull).count());
    }

}

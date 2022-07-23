package src.test;

import org.junit.Test;
import src.main.Tile;
import src.main.TileGenerator;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static src.main.Tile.getNearTiles;

public class TileGeneratorTest {
    private final int WIDTH = 10;
    private final int HEIGHT = 10;
    private final int MAX_BOMBS = WIDTH * HEIGHT;
    private final TileGenerator TILE_GEN = new TileGenerator(MAX_BOMBS, HEIGHT, WIDTH);

    @Test
    public void generateBombs_ShouldNotPlaceBombsNearStart() {
        int start_x = WIDTH / 2;
        int start_y = HEIGHT / 2;
        Tile[] tiles = TILE_GEN.generateBombs(start_x, start_y);
        Tile[] nearTiles = getNearTiles(start_x, start_y, WIDTH, HEIGHT, tiles);
        Arrays.stream(nearTiles).forEach(t -> assertTrue("No bombs should be near the start",t == null || t.getValue() != 9));
    }

    @Test
    public void generateBombs_ShouldGenerateProperAmount_StartInCenter() {
        Tile[] tiles = TILE_GEN.generateBombs(WIDTH / 2, HEIGHT / 2);
        long emptyTiles = Arrays.stream(tiles).filter(t -> t.getValue() == 0).count();
        assertEquals("There should be exactly 9 blank tiles", 9, emptyTiles);
    }
    @Test
    public void generateBombs_ShouldGenerateProperAmount_StartInCorner() {
        //This will only pass when WIDTH >= 2 && HEIGHT >= 2
        Tile[] tiles = TILE_GEN.generateBombs(0, 0);
        long emptyTiles = Arrays.stream(tiles).filter(t -> t.getValue() == 0).count();
        assertEquals("There should be exactly 4 blank tiles", 4, emptyTiles);
    }

    @Test
    public void generateBombs_ShouldGenerateProperAmount_StartOnSide() {
        //This will only pass when WIDTH >= 3 && HEIGHT >= 2
        Tile[] tiles = TILE_GEN.generateBombs(WIDTH / 2, 0);
        long emptyTiles = Arrays.stream(tiles).filter(t -> t.getValue() == 0).count();
        assertEquals("There should be exactly 6 blank tiles", 6, emptyTiles);
    }


}

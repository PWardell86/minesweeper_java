package src.main;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

import static java.lang.Math.abs;
import static java.util.logging.Logger.getLogger;
import static src.main.Tile.getNearTiles;

public class TileGenerator {
    private final Logger LOG = getLogger(TileGenerator.class.toString());
    private int height, width;
    private int bombs;

    /**
     * Creates a new tile generator that generates a map of tiles containing bombs
     * and numbered tiles
     * 
     * @param bombs  The number of bombs to have in the map
     * @param height The vertical number of tiles
     * @param width  The horizontal number of tiles
     */
    public TileGenerator(int bombs, int height, int width) {
        this.height = height;
        this.width = width;
        this.bombs = bombs;
    }

    /**
     * Generates a new bomb map with the number of bombs specified in the object,
     * centered around (openX, openY)
     * 
     * @param openX The x coordinate of the centered tile
     * @param openY The y coordinate of the centered tile
     */
    public Tile[] generateBombs(int openX, int openY) {
        // Generate bombs
        int placedBombs = 0;
        int tilesDone = 0;
        int area = this.width * this.height;
        Tile[] tiles = new Tile[area];
        int dy, dx;
        float bombChance;
        Random isBomb = new Random();

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                // Make sure no bombs are within 1 tile of the start position
                dx = abs(openX - x);
                dy = abs(openY - y);
                int tileIndex = (y * this.width) + x;
                if (dy < 2 && dx < 2) {
                    tiles[tileIndex] = new Tile((byte) 0, tileIndex);
                    tilesDone++;
                    continue;
                }
                //Make sure the bombChance is not an integer
                bombChance = (float) (this.bombs - placedBombs) / (area - tilesDone);
                if (bombChance > isBomb.nextFloat()) {
                    tiles[tileIndex] = new Tile((byte) 9, tileIndex);
                    placedBombs++;
                } else {
                    tiles[tileIndex] = new Tile((byte) 0, tileIndex);
                }
                tilesDone++;
            }
        }
        return tiles;
    }

    /**
     * Generates the tile values based on a bomb map
     * 
     * @param bombs The array containing the index of each bomb
     */
    private Tile[] generateTileValues(Tile[] bombs) {
        // Generate Tile values
        byte count = 0;
        int index;
        Tile tile;
        for (int x = 0; x < this.height; x++) {
            for (int y = 0; y < this.width; y++) {
                tile = bombs[(x * this.width) + y];
                if (tile.isBomb()) {
                    continue;
                }
                count = (byte) Arrays.stream(getNearTiles(x, y, this.width, this.height, bombs))
                        .filter(t -> t != null && t.isBomb())
                        .count();
                tile.setValue(count);
                count = 0;
            }
        }
        return bombs;
    }

    /**
     * Generates tiles centered on the tile at (openX, openY)
     * 
     * @param openX The x coordinate of the centered tile
     * @param openY The y coordinate of the centered tile
     */
    public Tile[] generateTiles(int openX, int openY) {
        return generateTileValues(generateBombs(openX, openY));
    }

}

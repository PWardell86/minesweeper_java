package src.main;

import java.util.Random;

import static java.lang.Math.abs;

public class TileGenerator {
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
                int tileIndex = (x * this.width) + y;
                if (dy < 2 && dx < 2) {
                    tiles[tileIndex] = new Tile((byte) 0, x, y);
                    tilesDone++;
                    continue;
                }
                //Make sure the bombChance is not an integer
                bombChance = (float) (this.bombs - placedBombs) / (area - tilesDone);
                if (bombChance > isBomb.nextFloat()) {
                    tiles[tileIndex] = new Tile((byte) 9, x, y);
                    placedBombs++;
                } else {
                    tiles[tileIndex] = new Tile((byte) 0, x, y);
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
        byte count;
        Tile tile;
        for (int x = 0; x < this.height; x++) {
            for (int y = 0; y < this.width; y++) {
                tile = bombs[(x * this.width) + y];
                if (tile.isBomb()) {
                    continue;
                }
                count = 0;
                for (int checkX = -1; checkX < 2; checkX++) {
                    for (int checkY = -1; checkY < 2; checkY++) {
                        int index = ((x + checkX) * this.width) + (y + checkY);
                        if (index >= 0 && index < this.height * this.width && bombs[index].isBomb()) {
                                count++;
                        }
                    }
                }
                tile.setValue(count);
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

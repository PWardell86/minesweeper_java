package src.main;

import java.util.Arrays;

public class Minesweeper {
    private int height, width;
    private int bombs;
    private Tile[] tiles;
    private TileGenerator tg;

    /**
     * Creates a new minesweeper grid
     * 
     * @param height The number of tiles in the y direction
     * @param width  The number of tiles in the x direction
     * @param bombs  The fraction of tiles that will be bombs
     */
    public Minesweeper(int height, int width, float bombs) {
        this.height = height;
        this.width = width;
        this.bombs = (int) (height * width * bombs);
        this.tg = new TileGenerator(this.bombs, height, width);
    }

    public Tile[] getTiles() {
        return this.tiles;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    /**
     * Sets the tiles back to being unrevealed
     */
    public void resetTiles() {
        Arrays.stream(this.tiles).forEach(tile -> tile.setVisible(false));

    }

    /**
     * Toggles the flagged state of the tile at the coordinates (x, y)
     * 
     * @param x The x coordinate of the tile
     * @param y The y coordinate of the tile
     */
    public void toggleTileFlag(int x, int y) {
        Tile tile = this.tiles[(x * this.width) + y];
        if (!tile.isVisible()) {
            tile.setFlagged(!tile.isFlagged());
        }

    }

    /**
     * Hides the tiles and generates a new bomb map and tile values
     * 
     * @param x The x coordinate of the first tile clicked
     * @param y The y coordinate of the first tile clicked
     */
    public void newGame(int x, int y) {
        this.tiles = this.tg.generateTiles(x, y);
    }

    public void clearBlanks(int x, int y) {
        int index;
        for (int col = -1; col < 2; col++) {
            for (int row = -1; row < 2; row++) {
                index = ((x + col) * this.width) + (y + row);
                if ((col != 0 || row != 0) && (index >= 0 && index < this.width * this.height)
                        && !this.tiles[index].isVisible()) {
                    revealTile(x + col, y + row);
                }
            }
        }
    }

    /**
     * Reveals the tile at the grid coordinates (x, y)
     * 
     * @param x The x coordinate of the tile
     * @param y The y coordinate of the tile
     */
    public boolean revealTile(int x, int y) throws NullPointerException {
        Tile tile = this.tiles[(x * this.width) + y];
        tile.setVisible(true);

        if (tile.getValue() == 0) {
            clearBlanks(x, y);
        }
        return !tile.isBomb();

    }
}

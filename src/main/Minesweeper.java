package src.main;

import java.util.Arrays;

public class Minesweeper {
    private final int height;
    private final int width;
    private Tile[] tiles;
    private final TileGenerator tg;
    private boolean started = false;

    /**
     * Creates a new minesweeper grid
     *
     * @param height The number of tiles in the y direction
     * @param width  The number of tiles in the x direction
     * @param bombFraction  The fraction of tiles that will be bombs
     */
    public Minesweeper(int height, int width, float bombFraction) {
        this.height = height;
        this.width = width;
        this.tg = new TileGenerator((int) (height * width * bombFraction), height, width);
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

    public boolean isStarted() {
        return this.started;
    }

    /**
     * Sets the tiles back to being unrevealed
     */
    public void resetTiles() {
        Arrays.stream(this.tiles).forEach(tile -> tile.setVisible(false));
        this.started = false;
    }

    /**
     * Tries to toggle the flagged state of the tile at the coordinates (x, y)
     * Will only toggle if the tile is not visible
     */
    public boolean toggleTileFlag(int index) {
        Tile tile = this.tiles[index];
        return tile.toggleFlagged();
    }

    /**
     * Hides the tiles and generates a new bomb map and tile values
     *
     * @param x The x coordinate of the first tile clicked
     * @param y The y coordinate of the first tile clicked
     */
    public void newGame(int x, int y) {
        this.tiles = this.tg.generateTiles(x, y);
        this.started = true;
    }

    public void clearBlanks(int x, int y) {
        int index;
        for (int col = -1; col < 2; col++) {
            for (int row = -1; row < 2; row++) {
                if (!(col == 0 && row == 0)) {
                    int new_x = x + col;
                    int new_y = y + row;
                    index = (new_y * this.width) + new_x;
                    try {
                        if (!this.tiles[index].isVisible() && isNearTileCoordinateGood(new_x, new_y)) {
                            revealTile(new_x, new_y);
                        }
                    } catch (IndexOutOfBoundsException ignored) {}
                }
            }
        }
    }
    private boolean isNearTileCoordinateGood(int x, int y) {
        return x >= 0
                && y >= 0
                && x < this.width
                && y < this.height;
    }
    /**
     * Reveals the tile at the grid coordinates (x, y)
     *
     * @param x The x coordinate of the tile
     * @param y The y coordinate of the tile
     */
    public void revealTile(int x, int y) {
        Tile tile = this.tiles[y * this.width + x];
        tile.setVisible(true);

        if (tile.getValue() == 0) {
            clearBlanks(x, y);
        }
    }
}

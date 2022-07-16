package src.main;

public class Tile {
    private byte value;
    private boolean visible, flagged;
    private int x, y;

    /**
     *
     * @param value The value to assign to the tile. In range: [0, 9]
     * @param x x-coordinate of the tile
     * @param y y-coordinate of the tile
     */
    public Tile(byte value, int x, int y) {
        this.visible = false;
        this.flagged = false;
        this.value = value;
        this.x = x;
        this.y = y;
    }

    public byte getValue() {
        return this.value;
    }

    /**
     * Set the value of this Tile
     * @param value The value to assign to the tile. In range: [0, 9]
     */
    public void setValue(byte value) {
        if (value < 0 || value > 9){
            throw new IllegalArgumentException("Tile value must be in range: [0, 9]");
        }
    }

    public boolean isBomb() {
        return this.value == 9;
    }

    public boolean isFlagged() {
        return this.flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int[] getPosition() {
        return new int[] { this.x, this.y };
    }

    /**
     * Get an array of tiles containing the tiles directly around the given coordinate
     * @param x The x coordinate of the tile to check
     * @param y The y coordinate of the tile to check
     * @param width The width of the board
     * @param height The height of the board
     * @param tiles The Tile array to check
     * @return The array of tiles near the given tile
     */
    public static Tile[] getNearTiles(int x, int y, int width, int height, Tile[] tiles) {
        int index;
        Tile[] nearTiles = new Tile[9];
        for (int x_off = -1; x < 2; x++){
            for (int y_off = -1; y < 2; y++) {
                index = (x + x_off) * width + (y + y_off);
                if (index >= 0 && index < width * height) {
                    nearTiles[(y_off + 1) * (x_off + 1)] = tiles[index];
                }
            }
        }
        return nearTiles;
    }

}

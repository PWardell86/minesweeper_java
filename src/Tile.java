package src;

public class Tile {
    private byte value;
    private boolean visible, flagged;
    private int x, y;

    /**
     * @param value Sets the value for the tile. Can be 0 to 8 for a numbered tile.
     *              9 is a bomb
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

    public void setValue(byte value) {
        if (value > 9) {
            this.value = 9; 
        }else if (value < 0) {
            this.value = 0;
        } else {
            this.value = value;
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

}

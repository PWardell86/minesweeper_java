package src;

import src.main.Minesweeper;
import src.main.MinesweeperGUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

public class Main {
    private static final Logger LOG = getLogger(Main.class.toString());

    public static void main(String[] args) {
        try {
            new MinesweeperGUI(new Minesweeper(10, 10, 0.5f), 25);
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Failed to make a new game due to: ", e);
        }
    }
}
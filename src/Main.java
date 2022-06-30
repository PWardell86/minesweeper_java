package src;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new msGUI(new Minesweeper(10, 10, 0.5f), 25);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
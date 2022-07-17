package src.main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import static java.awt.event.MouseEvent.BUTTON3;


public class MinesweeperGUI extends JFrame {
    private final Logger LOG = Logger.getLogger(getClass().toString());
    private JPanel pnlBoard;
    private final String filePath = "resources/default/";
    private int tileSize;

    public MinesweeperGUI(Minesweeper ms, int tileSize) throws IOException {
        this.tileSize = tileSize;
        pnlBoard = new JPanel();
        pnlBoard.setSize(900, 900);
        GridLayout test = new GridLayout(ms.getWidth(), ms.getHeight());
        test.setHgap(0);
        test.setVgap(0);
        pnlBoard.setLayout(test);
        JLabel JTile;

        for (int i = 0; i < ms.getWidth() * ms.getHeight(); i++) {
            ImageIcon tileImage = new ImageIcon(ImageIO.read(new File(this.filePath + "blank.png")));
            Image i2 = tileImage.getImage().getScaledInstance(tileSize, tileSize, java.awt.Image.SCALE_SMOOTH);
            tileImage = new ImageIcon(i2);
            JTile = new JLabel(tileImage);
            JTile.setSize(tileSize, tileSize);
            JTile.setMinimumSize(new Dimension(tileSize, tileSize));
            pnlBoard.add(JTile);
        }
        pnlBoard.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int x = event.getX() / tileSize;
                int y = event.getY() / tileSize;
                if (!ms.isStarted()){
                    ms.newGame(x, y);
                }
                if (event.getButton() == BUTTON3){
                    if (ms.toggleTileFlag(x, y)){
                        flagTile(y * ms.getWidth() + x);
                    }
                    return;
                }

                ms.revealTile(x, y);
                //Reveal all tiles that are visible
                Arrays.stream(ms.getTiles()).forEach(tile -> {
                    if (tile.isVisible() && !tile.isFlagged()) {
                        int[] pos = tile.getPosition();
                        int index = (pos[1] * ms.getWidth()) + pos[0];
                        makeVisible(index, tile.getValue());
                    }
                });
                LOG.info(String.format("Clicked at (%d, %d) \nIndex: %d", x, y, x * ms.getWidth() + y));
            }

            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}

        });

        add(pnlBoard);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void makeVisible(int index, int value){
        ImageIcon img;
        String name;

        switch (value) {
            case 0 -> name = "none.png";
            case 1 -> name = "one.png";
            case 2 -> name = "two.png";
            case 3 -> name = "three.png";
            case 4 -> name = "four.png";
            case 5 -> name = "five.png";
            case 6 -> name = "six.png";
            case 7 -> name = "seven.png";
            case 8 -> name = "eight.png";
            case 9 -> name = "bomb.png";
            default -> name = "";
        }
        try {
            img = new ImageIcon(ImageIO.read(new File(this.filePath + name)));
        } catch (IOException e){
            LOG.severe(String.format("Problem making image from %s", this.filePath + name));
            return;
        }
        Image i2 = img.getImage();
        i2 = i2.getScaledInstance(this.tileSize, this.tileSize, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(i2);
        pnlBoard.remove(index);

        JLabel tile = new JLabel(img);
        tile.setSize(new Dimension(this.tileSize, this.tileSize));
        pnlBoard.add(tile, index);
        pack();
        
    }
    private void flagTile(int index) {
        ImageIcon img;
        try {
            img = new ImageIcon(ImageIO.read(new File(this.filePath + "flag.png")));
        } catch (IOException e){
            LOG.severe(String.format("Problem making image from %s", this.filePath + "flag.png"));
            return;
        }
        Image i2 = img.getImage();
        i2 = i2.getScaledInstance(this.tileSize, this.tileSize, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(i2);
        pnlBoard.remove(index);

        JLabel tile = new JLabel(img);
        tile.setSize(new Dimension(this.tileSize, this.tileSize));
        pnlBoard.add(tile, index);
        pack();

    }


}
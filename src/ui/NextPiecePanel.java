package ui;

import model.Block;
import model.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class NextPiecePanel extends JComponent {

    private Block[][] blocks;

    public NextPiecePanel() {
        super();
        setPreferredSize(new Dimension(6 * Block.BLOCK_SIZE, 4 * Block.BLOCK_SIZE));
        setFocusable(false);

        blocks = new Block[4][6];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                blocks[i][j] = new Block(i, j);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                blocks[i][j].paint(g);
            }
        }
    }

    public void setPiece(Shape shape) {
        int[][] initialCoordinates = new int[][]{};
        Color color = new Color(0x000000);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                blocks[i][j].setColor(color);
            }
        }

        switch (shape){
            case T -> {
                initialCoordinates = new int[][] {{0,3},{0,4},{0,5},{1,4}};
                color = Color.MAGENTA;
            }
            case O -> {
                initialCoordinates = new int[][] {{0,4},{0,5},{1,4},{1,5}};
                color = Color.YELLOW;
            }
            case I -> {
                initialCoordinates = new int[][] {{0,3},{0,4},{0,5},{0,6}};
                color = Color.CYAN;
            }
            case J -> {
                initialCoordinates = new int[][] {{0,3},{1,3},{1,4},{1,5}};
                color = Color.BLUE;
            }
            case L -> {
                initialCoordinates = new int[][] {{1,3},{1,4},{1,5},{0,5}};
                color = Color.ORANGE;
            }
            case S -> {
                initialCoordinates = new int[][] {{1,3},{1,4},{0,4},{0,5}};
                color = Color.GREEN;
            }
            case Z -> {
                initialCoordinates = new int[][] {{0,3},{0,4},{1,4},{1,5}};
                color = Color.RED;
            }
            default -> color = Color.BLACK;
        }
        for (int i = 0; i < initialCoordinates.length; i++) {
            blocks[initialCoordinates[i][0]+1][initialCoordinates[i][1]-2].setColor(color);
        }
        repaint();
    }
}

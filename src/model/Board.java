package model;

import java.awt.*;
import java.util.Arrays;

public class Board {
    public static final int ROW_COUNT = 20;
    public static final int COLUMN_COUNT = 10;
    private Block[][] blocks;

    public Board() {
        blocks = new Block[ROW_COUNT][COLUMN_COUNT];
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                blocks[i][j] = new Block(i, j);
            }
        }
    }

    public Block getBlock(int row, int column){
        return blocks[row][column];
    }
    public void paint(Graphics g) {
        for (int i = 0; i < ROW_COUNT; i++){
            for (int j = 0; j < COLUMN_COUNT; j++) {
                blocks[i][j].paint(g);
            }
        }
    }
}

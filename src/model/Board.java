package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    public static final int ROW_COUNT = 20;
    public static final int COLUMN_COUNT = 10;
    private Block[][] blocks;
    private Piece piece;

    public Board() {
        blocks = new Block[ROW_COUNT][COLUMN_COUNT];
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                blocks[i][j] = new Block(i, j);
            }
        }
        piece = new Piece(this);
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

    public void moveLeft(){
        List<Block> pieceBlocks = piece.getBlocks();
        List<Block> newBlocks = new ArrayList<>();
        for (Block b : pieceBlocks) {
            newBlocks.add(blocks[b.getRow()][b.getColumn()-1]);
            b.setColor(Color.BLACK);
        }
        for (Block b : newBlocks) {
            b.setColor(piece.getColor());
        }
        piece.setBlocks(newBlocks);
    }
    public void moveRight(){
        List<Block> pieceBlocks = piece.getBlocks();
        List<Block> newBlocks = new ArrayList<>();
        for (Block b : pieceBlocks) {
            newBlocks.add(blocks[b.getRow()][b.getColumn()+1]);
            b.setColor(Color.BLACK);
        }
        for (Block b : newBlocks) {
            b.setColor(piece.getColor());
        }
        piece.setBlocks(newBlocks);
    }
}

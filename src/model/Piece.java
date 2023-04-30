package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Piece {

    private final Shape shape;
    private List<Block> blocks;
    private int[][] initialCoordinates;
    private List<Block> preparedBlocks;
    private final Color color;
    private Block pivotBlock;
    public Piece(Board board, Shape shape) {
        this.shape = shape;
        this.blocks = new ArrayList<>();
        initialCoordinates = new int[][]{};
        switch (shape){
            case T -> {
                initialCoordinates = new int[][] {{0,3},{0,4},{0,5},{1,4}};
                color = Color.MAGENTA;
                pivotBlock = board.getBlock(0,4);
            }
            case O -> {
                initialCoordinates = new int[][] {{0,4},{0,5},{1,4},{1,5}};
                color = Color.YELLOW;
            }
            case I -> {
                initialCoordinates = new int[][] {{0,3},{0,4},{0,5},{0,6}};
                color = Color.CYAN;
                pivotBlock = board.getBlock(0,5);
            }
            case J -> {
                initialCoordinates = new int[][] {{0,3},{1,3},{1,4},{1,5}};
                color = Color.BLUE;
                pivotBlock = board.getBlock(1,4);
            }
            case L -> {
                initialCoordinates = new int[][] {{1,3},{1,4},{1,5},{0,5}};
                color = Color.ORANGE;
                pivotBlock = board.getBlock(1,4);
            }
            case S -> {
                initialCoordinates = new int[][] {{1,3},{1,4},{0,4},{0,5}};
                color = Color.GREEN;
                pivotBlock = board.getBlock(1,4);
            }
            case Z -> {
                initialCoordinates = new int[][] {{0,3},{0,4},{1,4},{1,5}};
                color = Color.RED;
                pivotBlock = board.getBlock(1,4);
            }
            default -> color = Color.BLACK;
        }

        for (int i = 0; i < initialCoordinates.length; i++) {
            if (board.getBlock(initialCoordinates[i][0], initialCoordinates[i][1]).getColor() != Color.BLACK) {
                this.blocks = null;
                return;
            }
            blocks.add(board.getBlock(initialCoordinates[i][0], initialCoordinates[i][1]));
            board.getBlock(initialCoordinates[i][0], initialCoordinates[i][1]).setColor(color);

        }
    }

    public Shape getShape() {
        return shape;
    }
    public List<Block> getBlocks(){
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public Color getColor() {
        return color;
    }

    public Block getPivotBlock() {
        return pivotBlock;
    }

    public void setPivotBlock(Block pivotBlock) {
        this.pivotBlock = pivotBlock;
    }

    public int[][] getInitialCoordinates() {
        return initialCoordinates;
    }
}

package model;

import model.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Piece {

    private final Shape shape;
    private final List<Block> blocks;
    private final Board board;
    public Piece(Board board) {
        this.board = board;
        this.shape = randomShape();
        this.blocks = new ArrayList<>();
        int[][] initialCoordinates = new int[][]{};
        Color color = Color.BLACK;
        switch (shape){
            case T -> {
                initialCoordinates = new int[][] {{0,3},{0,4},{0,5},{1,4}};
                color = Color.MAGENTA;
            }
            case O -> {
                initialCoordinates = new int[][] {{0,5},{0,6},{1,5},{1,6}};
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
        }
        for (int i = 0; i < initialCoordinates.length; i++) {
            blocks.add(board.getBlock(initialCoordinates[i][0], initialCoordinates[i][1]));
            board.getBlock(initialCoordinates[i][0], initialCoordinates[i][1]).setColor(color);

        }
    }

    private Shape randomShape(){
        Shape[] values = Shape.values();
        Random random = new Random();
        return values[random.nextInt(values.length)];
    }

    public Shape getShape() {
        return shape;
    }
}

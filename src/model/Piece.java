package model;

import model.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Piece {

    private final Shape shape;
    private final List<Block> blocks;
    public Piece() {
        this.shape = randomShape();
        this.blocks = new ArrayList<>();
    }

    private Shape randomShape(){
        Shape[] values = Shape.values();
        Random random = new Random();
        return values[random.nextInt(values.length)];
    }
}

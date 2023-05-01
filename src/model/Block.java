package model;

import java.awt.*;

public class Block {
    public static final int BLOCK_SIZE = 20;
    private int row;
    private final int column;
    private Color color;

    public Block(int row, int column) {
        this.row = row;
        this.column = column;
        this.color = Color.BLACK;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public void paint(Graphics g){
        g.setColor(color);
        g.fillRect(column*BLOCK_SIZE,row*BLOCK_SIZE,BLOCK_SIZE,BLOCK_SIZE);
    }

    @Override
    public String toString() {
        return "[" + row +" : "+ column +" : "+ color.getRGB() + "]";
    }
}

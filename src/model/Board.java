package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board{
    public static final int ROW_COUNT = 20;
    public static final int COLUMN_COUNT = 10;
    private Block[][] blocks;
    private Piece piece;
    public boolean isGameOver;
    public boolean isGamePaused;

    public Board() {
        blocks = new Block[ROW_COUNT][COLUMN_COUNT];
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                blocks[i][j] = new Block(i, j);
            }
        }
        piece = new Piece(this);
        isGameOver = false;
        isGamePaused = false;
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
        for (Block b : pieceBlocks) {if (isOutOfTheBoard(b.getRow(), b.getColumn()-1)) return;}
        for (Block b : pieceBlocks) {
            if (isOutOfTheBoard(b.getRow(), b.getColumn()-1)) return;
            newBlocks.add(blocks[b.getRow()][b.getColumn()-1]);
            if (b.equals(piece.getPivotBlock())) {
                piece.setPivotBlock(blocks[b.getRow()][b.getColumn()-1]);
            }
        }
        for (Block block : newBlocks) {
            if (!pieceBlocks.contains(block)) {
                if (isInCollision(block.getRow(), block.getColumn())) {
                    return;
                }
            }
        }
        for (Block b : pieceBlocks) {
            b.setColor(Color.BLACK);
        }
        for (Block b : newBlocks) {
            b.setColor(piece.getColor());
        }
        piece.setBlocks(newBlocks);
        checkUnder();
    }
    public void moveRight(){
        List<Block> pieceBlocks = piece.getBlocks();
        List<Block> newBlocks = new ArrayList<>();
        for (Block b : pieceBlocks) {if (isOutOfTheBoard(b.getRow(), b.getColumn()+1)) return;}
        for (Block b : pieceBlocks) {
            newBlocks.add(blocks[b.getRow()][b.getColumn()+1]);
            if (b.equals(piece.getPivotBlock())) {
                piece.setPivotBlock(blocks[b.getRow()][b.getColumn()+1]);
            }
        }
        for (Block block : newBlocks) {
            if (!pieceBlocks.contains(block)) {
                if (isInCollision(block.getRow(), block.getColumn())) {
                    return;
                }
            }
        }
        for (Block b : pieceBlocks) {
            b.setColor(Color.BLACK);
        }
        for (Block b : newBlocks) {
            b.setColor(piece.getColor());
        }
        piece.setBlocks(newBlocks);
        checkUnder();
    }
    public void moveDown(){
        List<Block> pieceBlocks = piece.getBlocks();
        List<Block> newBlocks = new ArrayList<>();
        for (Block b : pieceBlocks) {if (isOutOfTheBoard(b.getRow()+1, b.getColumn())) return;}
        for (Block b : pieceBlocks) {
            newBlocks.add(blocks[b.getRow()+1][b.getColumn()]);
            if (b.equals(piece.getPivotBlock())) {
                piece.setPivotBlock(blocks[b.getRow()+1][b.getColumn()]);
            }
        }
        for (Block block : newBlocks) {
            if (!pieceBlocks.contains(block)) {
                if (isInCollision(block.getRow(), block.getColumn())) {
                    return;
                }
            }
        }
        for (Block b : pieceBlocks) {
            b.setColor(Color.BLACK);
        }
        for (Block b : newBlocks) {
            b.setColor(piece.getColor());
        }
        piece.setBlocks(newBlocks);
        checkUnder();
    }
    public void rotate() {
        if (piece.getShape() == Shape.O) {
            return;
        }
        List<Block> pieceBlocks = piece.getBlocks();
        List<Block> newBlocks = new ArrayList<>();
        int pivotRow = piece.getPivotBlock().getRow();
        int pivotCol = piece.getPivotBlock().getColumn();

        for (Block block : pieceBlocks) {
            int rowDiff = block.getRow() - pivotRow;
            int colDiff = block.getColumn() - pivotCol;
            int newRow = pivotRow + colDiff;
            int newCol = pivotCol - rowDiff;
            if(isOutOfTheBoard(newRow, newCol)){
                return;
            }
            newBlocks.add(blocks[newRow][newCol]);
        }

        for (Block block : newBlocks) {
            if (!pieceBlocks.contains(block)) {
                if (isInCollision(block.getRow(), block.getColumn())) {
                    return;
                }
            }
        }

        for (Block block : pieceBlocks) {
            block.setColor(Color.BLACK);
        }
        for (Block block : newBlocks) {
            block.setColor(piece.getColor());
        }
        piece.setBlocks(newBlocks);
        checkUnder();
    }

    private boolean isOutOfTheBoard(int row, int col) {
            if (row < 0 || row >= Board.ROW_COUNT || col < 0 || col >= Board.COLUMN_COUNT) {
                return true;
            }
        return false;
    }

    private boolean isInCollision(int row, int col) {
            if (blocks[row][col].getColor() != Color.BLACK) {
                return true;
            }
        return false;
    }

    public void newPiece(){
        piece = new Piece(this);
    }

    private void checkUnder() {
        for (Block b : piece.getBlocks()) {
            if (b.getRow() == Board.ROW_COUNT-1  || (blocks[b.getRow()+1][b.getColumn()].getColor() != Color.BLACK && !piece.getBlocks().contains(blocks[b.getRow()+1][b.getColumn()]))) {
                checkForCompleteRows();
                piece = new Piece(this);
                gameOver(piece.getBlocks() == null);
                return;
            }
        }
    }

    private void deleteRow(int rowIndex) {
        for (int row = rowIndex; row > 0; row--) {
            blocks[row] = blocks[row-1].clone();
            Arrays.stream(blocks[row]).forEach(block -> block.setRow(block.getRow()+1));
        }
        for (int col = 0; col < COLUMN_COUNT; col++) {
            blocks[0][col] = new Block(0,col);
        }
    }

    private void checkForCompleteRows() {
        for (int row = 0; row < ROW_COUNT; row++) {
            if (Arrays.stream(blocks[row]).allMatch(block -> block.getColor() != Color.BLACK)) {
                deleteRow(row);
            }
        }
    }

    private void gameOver(boolean isGameOver) {
        if (!isGameOver) return;
        else {
            JOptionPane.showMessageDialog(null, "Game Over!");
            this.isGameOver = true;
        }
    }
}

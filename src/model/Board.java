package model;

import ui.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Board{
    public static final int ROW_COUNT = 20;
    public static final int COLUMN_COUNT = 10;
    private final GamePanel gamePanel;
    private Block[][] blocks;
    private Piece piece;
    private Shape nextPiece;
    public boolean isGameOver;
    public boolean isGamePaused;
    public int level;
    private int totalRowsCompleted;
    private boolean hardDropStop;
    private final ReentrantLock lock = new ReentrantLock();

    public Board(GamePanel gamePanel) {
        level = 1;
        hardDropStop = true;
        this.gamePanel = gamePanel;
        blocks = new Block[ROW_COUNT][COLUMN_COUNT];
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COLUMN_COUNT; j++) {
                blocks[i][j] = new Block(i, j);
            }
        }
        piece = new Piece(this, randomShape());
        nextPiece = randomShape();
        gamePanel.setNextPiece(nextPiece);
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


    public void move(int down, int sideways){
        lock.lock();
        try {
            List<Block> pieceBlocks = piece.getBlocks();
            List<Block> newBlocks = new ArrayList<>();
            for (Block b : pieceBlocks) {if (isOutOfTheBoard(b.getRow()+down, b.getColumn()+sideways)) return;}
            boolean pivotSet = false;
            for (Block b : pieceBlocks) {
                newBlocks.add(blocks[b.getRow()+down][b.getColumn()+sideways]);
                if (!pivotSet && b.equals(piece.getPivotBlock())) {
                    piece.setPivotBlock(blocks[b.getRow()+down][b.getColumn()+sideways]);
                    pivotSet = true;
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
        } finally {
            lock.unlock();
        }
    }
    public void hardDrop() {
        hardDropStop = true;
        while (hardDropStop) {
            move(1,0);
        }
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

    private void checkUnder() {
        for (Block b : piece.getBlocks()) {
            if (b.getRow() == Board.ROW_COUNT-1  || (blocks[b.getRow()+1][b.getColumn()].getColor() != Color.BLACK && !piece.getBlocks().contains(blocks[b.getRow()+1][b.getColumn()]))) {
                hardDropStop = false;
                checkForCompleteRows();
                piece = new Piece(this, nextPiece);
                nextPiece = randomShape();
                gamePanel.setNextPiece(nextPiece);

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
        if (totalRowsCompleted != 0 && totalRowsCompleted % 10 == 0) {
            level++;
        }
    }

    private void checkForCompleteRows() {
        int completeRows = 0;
        for (int row = 0; row < ROW_COUNT; row++) {
            if (Arrays.stream(blocks[row]).allMatch(block -> block.getColor() != Color.BLACK)) {
                completeRows++;
                totalRowsCompleted++;
                deleteRow(row);
            }
        }
        updateScore(completeRows);
    }

    private void gameOver(boolean isGameOver) {
        if (!isGameOver) {}
        else {
            this.isGameOver = true;
            JOptionPane.showMessageDialog(null, "Game Over!");
            gamePanel.gameOver(gamePanel.getScore());
        }
    }

    private Shape randomShape(){
        Shape[] values = Shape.values();
        Random random = new Random();
        return values[random.nextInt(values.length)];
    }
    private void updateScore(int completeRows) {
        int originalScore = gamePanel.getScore();
        int newScore = gamePanel.getScore();
        switch (completeRows) {
            case 1 -> newScore = originalScore + 100 + (10 * level);
            case 2 -> newScore = originalScore + 300 + (30 * level);
            case 3 -> newScore = originalScore + 500 + (50 * level);
            case 4 -> newScore = originalScore + 800 + (80 * level);
        }
        gamePanel.setScore(newScore, level);
    }
}

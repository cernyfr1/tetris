package ui;

import model.Block;
import model.Board;
import model.Piece;
import model.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JComponent implements KeyListener, Runnable {
    private final Board board;
    private final InfoPanel infoPanel;
    private int speed;

    public GamePanel(InfoPanel infoPanel) {
        speed = 300;
        this.infoPanel = infoPanel;
        board = new Board(this);
        setPreferredSize(new Dimension(Board.COLUMN_COUNT* Block.BLOCK_SIZE, Board.ROW_COUNT*Block.BLOCK_SIZE));
        setFocusable(true);
        addKeyListener(this);
        new Thread(this).start();

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.paint(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT -> {
                board.moveLeft();
                repaint();
            }
            case KeyEvent.VK_RIGHT -> {
                board.moveRight();
                repaint();
            }
            case KeyEvent.VK_DOWN -> {
                board.hardDrop();
                repaint();
            }
            case KeyEvent.VK_UP -> {
                board.rotate();
                repaint();
            }
            case KeyEvent.VK_ESCAPE -> {
                if (board.isGamePaused) {
                    new Thread(this).start();
                    board.isGamePaused = !board.isGamePaused;
                } else board.isGamePaused = !board.isGamePaused;
            }
        }
    }
    @Override
    public void run() {
        while (!board.isGameOver) {
            while (!board.isGamePaused) {

                try {
                    board.moveDown();
                    repaint();
                    Thread.sleep(speed - (board.level * 10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void setNextPiece(Shape shape) {
        infoPanel.setNextPiece(shape);
    }

    public void setScore(int score, int level) {
        infoPanel.setScore(score, level);
    }
    public int getScore() {
        return infoPanel.getScore();
    }
}

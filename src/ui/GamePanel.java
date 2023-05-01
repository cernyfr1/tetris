package ui;

import model.Block;
import model.Board;
import model.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JComponent implements KeyListener, Runnable {
    private final Board board;
    private final InfoPanel infoPanel;
    private final Frame frame;
    private final int speed;

    public GamePanel(Frame frame, InfoPanel infoPanel) {
        speed = 300;
        this.infoPanel = infoPanel;
        this.frame = frame;
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
                board.move(0,-1);
                repaint();
            }
            case KeyEvent.VK_RIGHT -> {
                board.move(0,1);
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
            case KeyEvent.VK_ESCAPE ->
                frame.pausePlay();
        }
    }
    @Override
    public void run() {
        while (!board.isGameOver && !board.isGamePaused) {
                try {
                    board.move(1,0);
                    repaint();
                    Thread.sleep(speed - (board.level * 10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
    public void setGameStatus(boolean status) {
        board.isGamePaused = status;
        if (!board.isGamePaused) {
            new Thread(this).start();
        }
    }
    public void gameOver(int score) {
        frame.gameOver(score);
    }
}

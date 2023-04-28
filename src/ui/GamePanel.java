package ui;

import model.Block;
import model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JComponent implements KeyListener, Runnable {
    private final Board board;

    public GamePanel() {
        board = new Board();
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
                board.moveDown();
                repaint();
            }
            case KeyEvent.VK_UP -> {
                board.rotate();
                repaint();
            }
            case KeyEvent.VK_SPACE -> {
                board.newPiece();
                repaint();
            }
            case KeyEvent.VK_ESCAPE -> {
                System.out.println(board.isGamePaused);
                if (board.isGamePaused) {
                    new Thread(this).start();
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
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

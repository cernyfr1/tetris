package ui;

import model.Block;
import model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JComponent implements KeyListener {
    private final Board board;

    public GamePanel() {
        board = new Board();
        setPreferredSize(new Dimension(Board.COLUMN_COUNT* Block.BLOCK_SIZE, Board.ROW_COUNT*Block.BLOCK_SIZE));
        setFocusable(true);
        addKeyListener(this);

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
        }
    }
}

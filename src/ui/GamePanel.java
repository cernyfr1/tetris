package ui;

import model.Block;
import model.Board;
import model.Piece;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JComponent{
    private final Board board;

    public GamePanel() {
        board = new Board();
        Piece piece = new Piece(board);

        setPreferredSize(new Dimension(Board.COLUMN_COUNT* Block.BLOCK_SIZE, Board.ROW_COUNT*Block.BLOCK_SIZE));

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.paint(g);
    }
}

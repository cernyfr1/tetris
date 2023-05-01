package ui;

import model.Block;
import model.Board;
import model.Shape;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    private int score, highScore;
    private NextPiecePanel nextPiecePanel = new NextPiecePanel();
    private JLabel highscoreLabel = new JLabel(highScore + "  ");
    private JLabel scoreLabel = new JLabel("0   ");
    private JLabel levelLable = new JLabel("1");
    private JTextArea infoArea = new JTextArea("move left - ←\n move right - →\n rotate - ↑\n hard drop - ↓\n pause - ESC");

    public InfoPanel() {
        super();
        setPreferredSize(new Dimension(150, Board.ROW_COUNT* Block.BLOCK_SIZE));
        setFocusable(false);
        setLayout(new FlowLayout());

        add(new JLabel("Tetris")).setFont(new Font("Serif", Font.BOLD, 24));
        add(new JLabel("Next piece:"));
        add(nextPiecePanel);
        add(new JLabel("Highscore:"));
        highscoreLabel.setFont(new Font("Serif", Font.BOLD, 18));
        add(highscoreLabel);
        add(new JLabel("Your score:"));
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 18));
        add(scoreLabel);
        add(new JLabel("Level:"));
        levelLable.setFont(new Font("Serif", Font.BOLD, 18));
        add(levelLable);
        infoArea.setEditable(false);
        add(infoArea);

    }

    public void setNextPiece(Shape shape) {
        nextPiecePanel.setPiece(shape);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score, int level) {
        this.score = score;
        scoreLabel.setText(score + "   ");
        levelLable.setText(String.valueOf(level));
        if (score > highScore) {
            highScore = score;
            highscoreLabel.setText(String.valueOf(highScore));
        }
    }
}

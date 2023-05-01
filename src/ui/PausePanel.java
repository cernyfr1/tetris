package ui;

import model.Block;
import model.Board;

import javax.swing.*;
import java.awt.*;

public class PausePanel extends JPanel {

    private final JButton playPauseBtn;
    private final JButton resetBtn;
    private final JButton quitButton;
    private final Dimension btnDim;

    public PausePanel(Frame frame) {
        super();
        setPreferredSize(new Dimension(Board.COLUMN_COUNT*Block.BLOCK_SIZE, Board.ROW_COUNT* Block.BLOCK_SIZE));
        setFocusable(false);
        setLayout(new GridLayout(3,1));
        btnDim = new Dimension(100, 20);

        playPauseBtn = new JButton("Resume");
        playPauseBtn.addActionListener(e -> frame.pausePlay());
        playPauseBtn.setSize(btnDim);
        add(playPauseBtn);

        resetBtn = new JButton("Reset");
        resetBtn.addActionListener(e -> frame.resetGamePanel());
        resetBtn.setSize(btnDim);
        add(resetBtn);

        quitButton = new JButton("Quit game");
        quitButton.addActionListener(e -> System.exit(0));
        quitButton.setSize(btnDim);
        add(quitButton);
    }
}

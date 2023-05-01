package ui;

import model.Block;
import model.Board;

import javax.swing.*;
import java.awt.*;

public class InitPanel extends JPanel {

    private final JButton startBtn, quitBtn;
    public InitPanel(Frame frame, int score) {
        setPreferredSize(new Dimension(Board.COLUMN_COUNT* Block.BLOCK_SIZE, Board.ROW_COUNT*Block.BLOCK_SIZE));
        setLayout(new GridLayout(3,1));

        if (!(score == -1)) {

            JPanel labelPanel = new JPanel();
            Font font = new Font("Serif", Font.BOLD, 20);
            JLabel label1 = new JLabel("You scored");
            label1.setFont(font);
            JLabel label2 = new JLabel(String.valueOf(score));
            label2.setFont(font);
            JLabel label3 = new JLabel("points!");
            label3.setFont(font);
            labelPanel.add(label1);
            labelPanel.add(label2);
            labelPanel.add(label3);

            add(labelPanel);
        }

        startBtn = new JButton("Start game");
        startBtn.addActionListener(e -> frame.resetGamePanel());
        add(startBtn);

        quitBtn = new JButton("Quit game");
        quitBtn.addActionListener(e -> System.exit(0));
        add(quitBtn);

    }
}

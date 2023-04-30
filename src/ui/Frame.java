package ui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private GamePanel gamePanel;
    private final InfoPanel infoPanel;
    private final PausePanel pausePanel;
    private final InitPanel initPanel;
    private JComponent activePanel;
    public Frame(){
        infoPanel = new InfoPanel();
        pausePanel = new PausePanel(this);
        initPanel = new InitPanel(this, -1);
        activePanel = initPanel;
        add(activePanel, BorderLayout.WEST);
        add(infoPanel,BorderLayout.EAST);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void pausePlay() {
        remove(activePanel);
        if (activePanel.equals(gamePanel)) {
            activePanel = pausePanel;
            gamePanel.setGameStatus(true);
        }
        else if (activePanel.equals(pausePanel)) {
            activePanel = gamePanel;
            gamePanel.setGameStatus(false);
        }
        add(activePanel, BorderLayout.WEST);
        gamePanel.requestFocusInWindow();
        revalidate();
        repaint();
    }

    public void resetGamePanel() {
        infoPanel.setScore(0,1);
        this.gamePanel = new GamePanel(this, infoPanel);
        remove(activePanel);
        activePanel = gamePanel;
        add(activePanel, BorderLayout.WEST);
        gamePanel.requestFocusInWindow();
        revalidate();
        repaint();
    }
    public void gameOver(int score) {
        remove(activePanel);
        activePanel = new InitPanel(this, score);
        add(activePanel, BorderLayout.WEST);
        revalidate();
        repaint();
    }
}

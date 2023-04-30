package ui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private final GamePanel gamePanel;
    private final InfoPanel infoPanel;
    public Frame(){
        //setLayout(new BorderLayout(20,20));
        infoPanel = new InfoPanel();
        gamePanel = new GamePanel(infoPanel);
        add(gamePanel, BorderLayout.WEST);
        add(infoPanel,BorderLayout.EAST);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

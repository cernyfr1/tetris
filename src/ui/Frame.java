package ui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame(){
        add(new GamePanel());
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

package com.jg.ui;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("tic tac toe");
        setResizable(false);
        setSize(450, 450);
        setVisible(true);
        setLocationRelativeTo(null);
        add(new GamePanel());

    }
}

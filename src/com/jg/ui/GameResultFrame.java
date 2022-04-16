package com.jg.ui;

import com.jg.game.GameResult;

import javax.swing.*;
import java.awt.*;

public class GameResultFrame extends JFrame {
    public GameResultFrame(GameResult gameResult) {
        String result = null;
        switch (gameResult) {
            case DRAW -> result = "Draw";
            case X_WON -> result = "X won";
            case O_WON -> result = "O won";
        }

        GridLayout layout = new GridLayout(2,1);

        JLabel textLabel = new JLabel(result, SwingConstants.CENTER);
        textLabel.setFont(new Font("Calibri", Font.PLAIN, 16));

        JButton button = new JButton("ok");
        button.addActionListener(e -> dispose());

        setPreferredSize(new Dimension(100, 100));
        setLocationRelativeTo(null);
        setLayout(layout);
        add(textLabel);
        add(button);
        pack();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

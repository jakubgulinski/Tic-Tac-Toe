package com.jg.ui;

import com.jg.game.GameResult;
import com.jg.game.Board;
import com.jg.game.Game;
import com.jg.players.PlayerType;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {
    volatile Board board;

    public GamePanel() {
        setLayout(new BorderLayout());


        JPanel optionPanel = makeOptionPanel();

        JPanel boardPanel = makeBoardPanel();

        this.add(optionPanel, BorderLayout.NORTH);
        this.add(boardPanel, BorderLayout.CENTER);
    }

    private JPanel makeBoardPanel() {
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        this.board = new Board();
        JButton[][] buttons = this.board.getButtons();


        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                JButton button = new JButton();
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }
        return boardPanel;
    }

    private JPanel makeOptionPanel() {
        JPanel optionPanel = new JPanel(new GridLayout(1, 3));
        PlayerType[] player = new PlayerType[]{PlayerType.PLAYER, PlayerType.EASY, PlayerType.MEDIUM, PlayerType.HARD};

        JComboBox<PlayerType> player1 = new JComboBox<>(player);
        JComboBox<PlayerType> player2 = new JComboBox<>(player);

        JButton startResetButton = new JButton();
        startResetButton.setText("Start/Reset");

        startResetButton.addActionListener(e -> startNewGame((PlayerType) player1.getSelectedItem(), (PlayerType) player2.getSelectedItem()));

        optionPanel.add(player1);
        optionPanel.add(startResetButton);
        optionPanel.add(player2);

        return optionPanel;
    }

    private void startNewGame(PlayerType player1, PlayerType player2) {
        JButton[][] buttons = this.board.getButtons();

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setText("");
                board.getBoardArray()[i][j] = "";
            }
        }
        board.setGameResult(GameResult.GAME_IN_PROGRESS);

        Game game = new Game(board, player1, player2);
        game.start();
    }
}

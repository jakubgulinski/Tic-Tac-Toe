package com.jg.game;

import com.jg.players.BasePlayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Board {
    private final JButton[][] buttons;
    private volatile GameResult gameResult;

    public Board() {
        this.buttons = new JButton[3][3];
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public GameResult getGameResult() {

        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public void checkResult(BasePlayer basePlayer) {
        if (hasWon(basePlayer)) {
            gameResult = basePlayer.getSign() == Sign.X ? GameResult.X_WON : GameResult.O_WON;
        }
        else if (isDraw()) {
            gameResult = GameResult.DRAW;
        } else {
            gameResult = GameResult.GAME_IN_PROGRESS;
        }
    }

    private boolean hasWon(BasePlayer basePlayer) {
        String sign = basePlayer.getSign() == Sign.X ? "X" : "O";


        for (int column = 0; column < buttons.length; column++) {
            boolean columnsCheck = (Arrays.stream(buttons[column])
                    .allMatch(button -> sign.equals(button.getText())));
            if (columnsCheck) {
                return true;
            }
        }

        for (int row = 0; row < buttons.length; row++) {
            boolean rowCheck = Stream.of(buttons[0][row], buttons[1][row], buttons[2][row])
                    .allMatch(button -> sign.equals(button.getText()));
            if (rowCheck) {
                return true;
            }
        }
        boolean diagonalCheck = Stream.of(buttons[0][0], buttons[1][1], buttons[2][2])
                .allMatch(button -> sign.equals(button.getText()));

        return diagonalCheck ||
                Stream.of(buttons[0][2], buttons[1][1], buttons[2][0])
                .allMatch(button -> sign.equals(button.getText()));
    }

    private boolean isDraw() {
        for (JButton[] row : buttons) {
            for (JButton jButton : row) {
                if ("".equals(jButton.getText())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        List<String> board = new ArrayList<>();
        for (JButton[] row : buttons) {
            for (JButton jButton : row) {
                board.add(jButton.getText());
            }
        }
        return board.toString();
    }
}

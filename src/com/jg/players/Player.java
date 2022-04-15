package com.jg.players;

import com.jg.game.GameResult;
import com.jg.game.Board;
import com.jg.game.Game;
import com.jg.game.Sign;

import javax.swing.*;

public class Player implements BasePlayer {
    private final Board board;
    private final Sign sign;
    private BasePlayer opponent;

    public Player(Game game, Sign sign, BasePlayer opponent) {
        this.board = game.getBoard();
        this.sign = sign;
        this.opponent = opponent;
    }

    public Player(Game game, Sign sign) {
        this.board = game.getBoard();
        this.sign = sign;
    }

    public void setOpponent(BasePlayer opponent) {
        this.opponent = opponent;
    }

    @Override
    public Sign getSign() {
        return sign;

    }

    @Override
    public void makeAMove() {
        JButton[][] buttons = board.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(e ->
                {
                    if (board.getGameResult() == GameResult.GAME_IN_PROGRESS) {
                        buttonPressed(buttons[finalI][finalJ]);
                    }
                });
            }
        }
    }

    private void buttonPressed(JButton button) {
        String sign = this.sign == Sign.X ? "X" : "O";
        if ("".equals(button.getText())) {
            button.setText(sign);
            board.checkResult(this);
            GameResult gameResult = board.getGameResult();
            if (gameResult == GameResult.GAME_IN_PROGRESS) {
                opponent.makeAMove();
            } else {
                System.out.println(gameResult);
            }

        }
    }
}
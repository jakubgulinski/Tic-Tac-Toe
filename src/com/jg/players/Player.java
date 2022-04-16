package com.jg.players;

import com.jg.game.Board;
import com.jg.game.GameResult;
import com.jg.game.Sign;

import javax.swing.*;

public class Player extends BasePlayer {

    public Player(Board board, Sign sign) {
        super(board, sign);
    }

    public Player(Board board, Sign sign, BasePlayer opponent) {
        super(board, sign, opponent);
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
                        buttonPressed(buttons[finalI][finalJ], finalI, finalJ);

                    }
                });
            }
        }
    }

    private void buttonPressed(JButton button, int x, int y) {
        if ("".equals(button.getText())) {
            board.setCell(x, y, sign.name());
            board.checkResult(this);
            GameResult gameResult = board.getGameResult();
            if (gameResult == GameResult.GAME_IN_PROGRESS) {
                opponent.makeAMove();
            } else {
                board.showResult();
            }
        }
    }
}
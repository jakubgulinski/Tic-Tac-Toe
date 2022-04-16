package com.jg.players;

import com.jg.game.Board;
import com.jg.game.GameResult;
import com.jg.game.Sign;

import javax.swing.*;

public class Easy extends BasePlayer {

    public Easy(Board board, Sign sign) {
        super(board, sign);
    }

    public Easy(Board board, Sign sign, BasePlayer opponent) {
        super(board, sign, opponent);
    }

    @Override
    public void makeAMove() {
        int x, y;
        JButton[][] buttons = board.getButtons();
        do {
            x = (int) (Math.random() * 3);
            y = (int) (Math.random() * 3);
        } while (!"".equals(buttons[x][y].getText()));

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

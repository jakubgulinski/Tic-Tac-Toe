package com.jg.players;

import com.jg.game.GameResult;
import com.jg.game.Board;
import com.jg.game.Game;
import com.jg.game.Sign;

import javax.swing.*;

public class Easy implements BasePlayer {
    private final Board board;
    private final Sign sign;
    private BasePlayer opponent;

    public Easy(Game game, Sign sign) {
        this.board = game.getBoard();
        this.sign = sign;
    }

    public Easy(Game game, Sign sign, BasePlayer opponent) {
        this.board = game.getBoard();
        this.sign = sign;
        this.opponent = opponent;
    }

    @Override
    public void makeAMove() {
        int x, y;
        String sign = this.sign == Sign.X ? "X" : "O";
        JButton[][] buttons = board.getButtons();
        do {
            x = (int) (Math.random() * 3);
            y = (int) (Math.random() * 3);
        } while (!"".equals(buttons[x][y].getText()));

        buttons[x][y].setText(sign);

        board.checkResult(this);
        GameResult gameResult = board.getGameResult();
        if (gameResult == GameResult.GAME_IN_PROGRESS) {
            opponent.makeAMove();
        } else {
            System.out.println(gameResult);
        }
    }

    @Override
    public void setOpponent(BasePlayer opponent) {
        this.opponent = opponent;
    }

    @Override
    public Sign getSign() {
        return sign;
    }
}

package com.jg.players;

import com.jg.game.Board;
import com.jg.game.Sign;

public abstract class BasePlayer implements BasePlayerInterface {
    protected final Board board;
    protected final Sign sign;
    protected BasePlayer opponent;

    public BasePlayer(Board board, Sign sign) {
        this.board = board;
        this.sign = sign;
    }

    public BasePlayer(Board board, Sign sign, BasePlayer opponent) {
        this.board = board;
        this.sign = sign;
        this.opponent = opponent;
    }

    @Override
    public final void setOpponent(BasePlayer opponent) {
        this.opponent = opponent;
    }

    @Override
    public final Sign getSign() {
        return sign;
    }

}

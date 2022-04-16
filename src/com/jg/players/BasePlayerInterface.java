package com.jg.players;


import com.jg.game.Sign;

public interface BasePlayerInterface {

    void makeAMove();

    void setOpponent(BasePlayer opponent);

    Sign getSign();

}

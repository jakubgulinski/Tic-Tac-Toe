package com.jg.players;


import com.jg.game.Sign;

public interface BasePlayer {



    void makeAMove();

    void setOpponent(BasePlayer opponent);

    Sign getSign();

}

package com.jg.game;

import com.jg.players.*;


public class Game {
    private final Board board;
    private BasePlayer player1;
    private BasePlayer player2;

    public Game(Board board, PlayerType playerType1, PlayerType playerType2) {
        this.board = board;
        switch (playerType1) {
            case PLAYER -> player1 = new Player(this, Sign.X);
            case EASY -> player1 = new Easy(this, Sign.X);
//            case MEDIUM -> player1 = new Medium();
//            case HARD -> player1 = new Hard();
        }
        switch (playerType2) {
            case PLAYER -> player2 = new Player(this, Sign.O, player1);
            case EASY -> player2 = new Easy(this, Sign.O, player1);
//            case MEDIUM -> player2 = new Medium();
//            case HARD -> player2 = new Hard();
        }
    }

    public BasePlayer getPlayer1() {
        return player1;
    }

    public BasePlayer getPlayer2() {
        return player2;
    }

    public Board getBoard() {
        return board;
    }


    public void start() {
        player1.setOpponent(player2);
        player1.makeAMove();
    }


}

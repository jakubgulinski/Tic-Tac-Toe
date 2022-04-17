package com.jg.game;

import com.jg.players.*;


public class Game {
    private final Board board;
    private BasePlayer player1;
    private BasePlayer player2;

    public Game(Board board, PlayerType playerType1, PlayerType playerType2) {
        this.board = board;
        switch (playerType1) {
            case PLAYER -> player1 = new Player(board, Sign.X);
            case EASY -> player1 = new Easy(board, Sign.X);
            case MEDIUM -> player1 = new Medium(board, Sign.X);
            case HARD -> player1 = new Hard(board, Sign.X);
        }
        switch (playerType2) {
            case PLAYER -> player2 = new Player(board, Sign.O, player1);
            case EASY -> player2 = new Easy(board, Sign.O, player1);
            case MEDIUM -> player2 = new Medium(board, Sign.O, player1);
            case HARD -> player2 = new Hard(board, Sign.O, player1);
        }
    }

    public void start() {
        player1.setOpponent(player2);
        player1.makeAMove();
    }


}

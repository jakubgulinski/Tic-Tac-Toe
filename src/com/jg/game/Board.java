package com.jg.game;

import com.jg.players.BasePlayerInterface;
import com.jg.ui.GameResultFrame;

import javax.swing.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class Board {
    private final JButton[][] buttons;
    private String[][] boardArray;
    private GameResult gameResult;

    public Board() {
        this.buttons = new JButton[3][3];
        this.boardArray = new String[3][3];

        for (String[] row : boardArray) {
            Arrays.fill(row, "");
        }
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public String[][] getBoardArray() {
        return boardArray;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public void setCell(int x, int y, String sign) {
        boardArray[x][y] = sign;
        buttons[x][y].setText(sign);
    }

    public GameResult checkResult(Sign sign) {
        if (hasWon(sign)) {
            gameResult = sign == Sign.X ? GameResult.X_WON : GameResult.O_WON;
        } else if (isDraw()) {
            gameResult = GameResult.DRAW;
        } else {
            gameResult = GameResult.GAME_IN_PROGRESS;
        }
        return gameResult;
    }

    public boolean hasWon(Sign sign) {


        for (int column = 0; column < boardArray.length; column++) {
            boolean columnsCheck = (Arrays.stream(boardArray[column])
                    .allMatch(sign.name()::equals));
            if (columnsCheck) {
                return true;
            }
        }

        for (int row = 0; row < boardArray.length; row++) {
            boolean rowCheck = Stream.of(boardArray[0][row], boardArray[1][row], boardArray[2][row])
                    .allMatch(sign.name()::equals);
            if (rowCheck) {
                return true;
            }
        }
        boolean diagonalCheck = Stream.of(boardArray[0][0], boardArray[1][1], boardArray[2][2])
                .allMatch(sign.name()::equals);

        return diagonalCheck ||
                Stream.of(boardArray[0][2], boardArray[1][1], boardArray[2][0])
                        .allMatch(sign.name()::equals);
    }

    public boolean isDraw() {
        for (String[] row : boardArray) {
            for (String cell : row) {
                if ("".equals(cell)) {
                    return false;
                }
            }
        }
        return true;
    }


    public void showResult() {
        new GameResultFrame(gameResult);
    }
}

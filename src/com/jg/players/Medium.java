package com.jg.players;

import com.jg.game.Board;
import com.jg.game.GameResult;
import com.jg.game.Sign;

public class Medium extends BasePlayer {

    public Medium(Board board, Sign sign) {
        super(board, sign);
    }

    public Medium(Board board, Sign sign, BasePlayer opponent) {
        super(board, sign, opponent);
    }

    @Override
    public void makeAMove() {
        Board boardCopy = board.makeBoardCopy();
        String[][] boardCopyArray = boardCopy.getBoardArray();
        Sign opponentSign = sign == Sign.X ? Sign.O : Sign.X;
        boolean moveMade = false;

        for (int i = 0; i < boardCopyArray.length; i++) {
            for (int j = 0; j < boardCopyArray[i].length; j++) {
                if ("".equals(boardCopyArray[i][j]) && !moveMade) {
                    boardCopyArray[i][j] = opponentSign.name();
                    if (boardCopy.hasWon(opponentSign)) {
                        board.setCell(i, j, sign.name());
                        moveMade = true;
                    } else {
                        boardCopyArray[i][j] = "";
                    }
                }
            }
        }
        if (!moveMade) {
            int x, y;
            do {
                x = (int) (Math.random() * 3);
                y = (int) (Math.random() * 3);
            } while (!"".equals(board.getBoardArray()[x][y]));

            board.setCell(x, y, sign.name());
        }
        GameResult gameResult = board.checkResult(sign);
        if (gameResult == GameResult.GAME_IN_PROGRESS) {
            opponent.makeAMove();
        } else {
            board.showResult();
        }
    }
}


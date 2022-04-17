package com.jg.players;

import com.jg.game.Board;
import com.jg.game.GameResult;
import com.jg.game.Sign;

import static com.jg.game.GameResult.*;

public class Hard extends BasePlayer {

    public Hard(Board board, Sign sign) {
        super(board, sign);
    }

    public Hard(Board board, Sign sign, BasePlayer opponent) {
        super(board, sign, opponent);
    }

    @Override
    public void makeAMove() {
        Board boardCopy = board.makeBoardCopy();
        String[][] boardCopyArray = boardCopy.getBoardArray();

        int bestScore = Integer.MIN_VALUE;
        int[] bestMovePosition = new int[2];

        for (int i = 0; i < boardCopyArray.length; i++) {
            for (int j = 0; j < boardCopyArray[i].length; j++) {
                if ("".equals(boardCopyArray[i][j])) {
                    boardCopyArray[i][j] = sign.name();
                    int score = minimax(boardCopy, 0, false);
                    boardCopyArray[i][j] = "";
                    if (score > bestScore) {
                        bestScore = score;
                        bestMovePosition[0] = i;
                        bestMovePosition[1] = j;
                    }
                }

            }
        }
        board.setCell(bestMovePosition[0], bestMovePosition[1], sign.name());
        GameResult gameResult = board.checkResult(sign);
        if (gameResult == GAME_IN_PROGRESS) {
            opponent.makeAMove();
        } else {
            board.showResult();
        }
    }

    private int minimax(Board board, int depth, boolean isMaximizing) {
        String[][] boardArray = board.getBoardArray();
        Sign opponentSign = sign == Sign.X ? Sign.O : Sign.X;
        if (board.hasWon(sign)) {
            return 1;
        } else if (board.hasWon(opponentSign)) {
            return -1;
        } else if (board.isDraw()) {
            return 0;
        }

        int bestScore;

        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < boardArray.length; i++) {
                for (int j = 0; j < boardArray[i].length; j++) {
                    if ("".equals(boardArray[i][j])) {
                        boardArray[i][j] = sign.name();
                        int score = minimax(board, depth + 1, false);
                        boardArray[i][j] = "";
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < boardArray.length; i++) {
                for (int j = 0; j < boardArray[i].length; j++) {
                    if ("".equals(boardArray[i][j])) {
                        boardArray[i][j] = opponentSign.name();
                        int score = minimax(board, depth + 1, true);
                        boardArray[i][j] = "";
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }
}

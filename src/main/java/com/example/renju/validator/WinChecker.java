package com.example.renju.validator;

import org.springframework.stereotype.Component;

@Component
public class WinChecker {
    private static final int BOARD_SIZE = 19;
    private static final int WIN_LENGTH = 5;

    public static WinResult checkWin(int[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                int stone = board[row][col];
                if (stone == 0) continue;

                if (checkDirection(board, row, col, 0, 1, stone) || // horizontal
                        checkDirection(board, row, col, 1, 0, stone) || // vertical
                        checkDirection(board, row, col, 1, 1, stone) || // diagonal \
                        checkDirection(board, row, col, 1, -1, stone)) { // diagonal /
                    return new WinResult(stone, row + 1, col + 1);
                }
            }
        }
        return new WinResult(0, -1, -1);
    }

    private static boolean checkDirection(int[][] board, int row, int col, int dRow, int dCol, int stone) {
        int count = 1;
        int prevRow = row - dRow;
        int prevCol = col - dCol;
        if (isValid(prevRow, prevCol) && board[prevRow][prevCol] == stone) {
            return false; // do not more than 5
        }

        for (int i = 1; i < WIN_LENGTH; i++) {
            int newRow = row + dRow * i;
            int newCol = col + dCol * i;
            if (isValid(newRow, newCol) && board[newRow][newCol] == stone) {
                count++;
            } else {
                break;
            }
        }

        int nextRow = row + dRow * WIN_LENGTH;
        int nextCol = col + dCol * WIN_LENGTH;
        if (isValid(nextRow, nextCol) && board[nextRow][nextCol] == stone) {
            return false;
        }

        return count == WIN_LENGTH;
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }
}



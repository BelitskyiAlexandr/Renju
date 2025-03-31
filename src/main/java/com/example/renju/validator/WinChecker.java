package com.example.renju.validator;

import com.example.renju.model.CellState;
import org.springframework.stereotype.Component;

@Component
public class WinChecker {
    private static final int BOARD_SIZE = 19;
    private static final int WIN_LENGTH = 5;

    public static WinResult checkWin(CellState[][] board) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                CellState stone = board[row][col];
                if (stone == CellState.EMPTY) continue;

                if (checkDirection(board, row, col, 0, 1, stone) ||
                        checkDirection(board, row, col, 1, 0, stone) ||
                        checkDirection(board, row, col, 1, 1, stone)) {

                    return new WinResult(stone, row + 1, col + 1);
                }

                if (checkDirection(board, row, col, 1, -1, stone)) {
                    return new WinResult(stone, row + (WIN_LENGTH - 1) + 1,
                            col - (WIN_LENGTH - 1) + 1);
                }
            }
        }
        return new WinResult(CellState.EMPTY, -1, -1);
    }

    private static boolean checkDirection(CellState[][] board, int row, int col,
                                          int dRow, int dCol, CellState stone) {
        int count = 0;
        int prevRow = row - dRow;
        int prevCol = col - dCol;
        if (isValid(prevRow, prevCol) && board[prevRow][prevCol] == stone) {
            return false;
        }

        for (int i = 0; i < WIN_LENGTH; i++) {
            int newRow = row + dRow * i;
            int newCol = col + dCol * i;

            if (!isValid(newRow, newCol) || board[newRow][newCol] != stone) {
                if (i == WIN_LENGTH) return false;
                break;
            }
            count++;
        }

        return count == WIN_LENGTH;
    }


    private static boolean isValid(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }
}

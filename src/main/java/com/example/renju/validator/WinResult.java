package com.example.renju.validator;

public class WinResult {
    int winner;
    int row;
    int col;

    public WinResult(int winner, int row, int col) {
        this.winner = winner;
        this.row = row;
        this.col = col;
    }

    public int getWinner() {
        return winner;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

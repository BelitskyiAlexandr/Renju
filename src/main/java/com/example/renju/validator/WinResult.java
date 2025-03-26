package com.example.renju.validator;

import com.example.renju.model.CellState;

public class WinResult {
    private CellState winner;
    private int row;
    private int col;

    public WinResult(CellState winner, int row, int col) {
        this.winner = winner;
        this.row = row;
        this.col = col;
    }

    public CellState getWinner() {
        return winner;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

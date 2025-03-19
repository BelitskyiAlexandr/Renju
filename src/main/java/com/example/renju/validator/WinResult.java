package com.example.renju.validator;

import lombok.Getter;

@Getter
public class WinResult {
    int winner;
    int row;
    int col;

    public WinResult(int winner, int row, int col) {
        this.winner = winner;
        this.row = row;
        this.col = col;
    }
}

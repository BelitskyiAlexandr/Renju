package com.example.renju.model;

public enum CellState {
    EMPTY(0), BLACK(1), WHITE(2);

    private final int value;

    CellState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CellState fromValue(int value) {
        for (CellState state : values()) {
            if (state.getValue() == value) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid cell state value: " + value);
    }
}

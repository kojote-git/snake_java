package com.jkojote.snakegame.game.obj.base;

public enum Direction {
    LEFT(0),

    UP(1),

    RIGHT(2),

    DOWN(3);

    private int value;

    Direction(int value) {
        this.value = value;
    }

    public int toInteger() {
        return value;
    }
}

package com.jkojote.snakegame.game.obj.base;

public enum Direction {
    LEFT(0),

    UP(1),

    RIGHT(2),

    DOWN(3);

    private int value;

    private Direction opposite;

    Direction(int value) {
        this.value = value;

    }

    public int toInteger() {
        return value;
    }

    public Direction getOpposite() {
        if (opposite == null) {
            Direction d;
            switch (this) {
                case RIGHT:
                    opposite = LEFT;
                    return opposite;
                case DOWN:
                    opposite = UP;
                    return opposite;
                case LEFT:
                    opposite = RIGHT;
                    return opposite;
                case UP:
                    opposite = DOWN;
                    return opposite;
            }
        }
        return opposite;
    }
}

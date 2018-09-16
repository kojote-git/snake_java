package com.jkojote.snakegame.game.obj.base;

public enum Effect {
    SNAKE_GROW("SNAKE_GROW");

    String value;

    Effect(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

package com.jkojote.snakegame.game;

public class GameOverException extends RuntimeException {
    public GameOverException() {
        super("Game Over");
    }

    public GameOverException(String message) {
        super(message);
    }
}

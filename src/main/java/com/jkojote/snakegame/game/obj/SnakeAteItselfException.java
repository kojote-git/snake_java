package com.jkojote.snakegame.game.obj;

public class SnakeAteItselfException extends RuntimeException {

    private static final String MESSAGE =
        "Snake ate itself!";

    public SnakeAteItselfException() {
        super(MESSAGE);
    }

    public SnakeAteItselfException(String message) {
        super(message);
    }
}

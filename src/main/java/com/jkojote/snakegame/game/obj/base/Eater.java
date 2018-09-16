package com.jkojote.snakegame.game.obj.base;

/**
 * Object that is able to eat {@link Eatable} objects
 */
public interface Eater extends GameObject {
    /**
     * Eats the {@code eatable}, and the {@code eatable} may somehow affect this eater
     * @param eatable object to be eaten
     */
    void eat(Eatable eatable);
}

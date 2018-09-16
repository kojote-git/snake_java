package com.jkojote.snakegame.game.obj.base;

/**
 * Represents active
 */
public interface Movable extends GameObject {

    /**
     * Move object, considering its direction and speed.
     * May throw <b>RuntimeException</b> or its subclass if this operation cannot be performed
     */
    void move();

    /**
     * @return direction of the object
     */
    Direction getDirection();

    /**
     * Change direction in which the object moves
     * @param direction
     * @return {@code true} if direction changed;
     *         {@code false} if direction cannot be changed due to some reason
     */
    boolean changeDirection(Direction direction);

    int getSpeed();

    void changeSpeed(int newSpeed);
}

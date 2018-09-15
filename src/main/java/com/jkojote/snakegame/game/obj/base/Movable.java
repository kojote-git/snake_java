package com.jkojote.snakegame.game.obj.base;

public interface Movable extends GameObject {
    void move();

    Direction getDirection();

    boolean changeDirection(Direction direction);

    int getSpeed();

    void changeSpeed(int newSpeed);
}

package com.jkojote.snakegame.game.borders;

import com.jkojote.snakegame.game.obj.Cell;
import com.jkojote.snakegame.game.obj.Snake;

/**
 * Performs teleportation of snake when it collides with border
 */
public class TeleportBorderCollisionStrategy implements BorderCollisionStrategy {

    private int gameFieldWidth;

    private int gameFieldHeight;

    public TeleportBorderCollisionStrategy(int gameFieldWidth, int gameFieldHeight) {
        this.gameFieldHeight = gameFieldHeight;
        this.gameFieldWidth = gameFieldWidth;
    }

    @Override
    public void performAction(Snake snake, Border border) {
        Cell position = snake.getHead().getPosition();
        switch (border) {
            case LEFT:
                snake.teleport(new Cell(gameFieldWidth, position.getY()));
                break;
            case DOWN:
                snake.teleport(new Cell(position.getX(), 0));
                break;
            case RIGHT:
                snake.teleport(new Cell(0, position.getY()));
                break;
            case UPPER:
                snake.teleport(new Cell(position.getX(), gameFieldHeight));
        }
    }
}

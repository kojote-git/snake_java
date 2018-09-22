package com.jkojote.snakegame.game;

import com.jkojote.snakegame.Border;
import com.jkojote.snakegame.BorderCollisionStrategy;
import com.jkojote.snakegame.game.obj.FieldCell;
import com.jkojote.snakegame.game.obj.Snake;
import com.jkojote.snakegame.game.obj.base.GameObject;

import java.util.List;

public class GameState {

    private Snake snake;

    private int gameFieldWidth;

    private int gameFieldHeight;

    private List<GameObject> gameObjects;

    private BorderCollisionStrategy borderCollisionStrategy;

    private void checkCollisions() {
        FieldCell head = snake.getHead().getPosition();
        checkBorderCollisions(head);
        // Checks collision with other game objects
        /*
        for (int i = 1; i < gameObjects.size(); i++) {

        }
        */
    }

    private void checkBorderCollisions(FieldCell head) {
        if (head.getX() > gameFieldWidth)
            borderCollisionStrategy.performAction(snake, Border.RIGHT);
        if (head.getX() < 0)
            borderCollisionStrategy.performAction(snake, Border.LEFT);
        if (head.getY() > gameFieldHeight)
            borderCollisionStrategy.performAction(snake, Border.DOWN);
        if (head.getY() < 0)
            borderCollisionStrategy.performAction(snake, Border.UPPER);
    }
}

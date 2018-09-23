package com.jkojote.snakegame.game.borders;

import com.jkojote.snakegame.game.GameOverException;
import com.jkojote.snakegame.game.obj.Snake;

/**
 * Ends game when snake collides with border
 */
public class EndGameBorderCollisionStrategy implements BorderCollisionStrategy {
    @Override
    public void performAction(Snake snake, Border border) {
        throw new GameOverException();
    }
}

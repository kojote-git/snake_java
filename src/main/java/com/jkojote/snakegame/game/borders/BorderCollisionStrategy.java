package com.jkojote.snakegame.game.borders;

import com.jkojote.snakegame.game.obj.Snake;
import com.jkojote.snakegame.game.GameOverException;

/**
 * Determines action that is to be performed when snake collides with borders
 */
public interface BorderCollisionStrategy {

    /**
     * Performs certain action when {@code snake} collides with border
     * @param snake snake that collided with border
     * @param border border which snake collided with
     * @throws GameOverException if chosen {@link EndGameBorderCollisionStrategy}
     */
    void performAction(Snake snake, Border border);
}

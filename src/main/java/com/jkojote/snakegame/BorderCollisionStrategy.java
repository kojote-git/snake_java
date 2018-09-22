package com.jkojote.snakegame;

import com.jkojote.snakegame.game.obj.Snake;

public interface BorderCollisionStrategy {

    void performAction(Snake snake, Border border);
}

package com.jkojote.snakegame.game.obj.base;

import com.jkojote.snakegame.game.obj.Cell;

public interface Teleportable extends GameObject {

    void teleport(Cell destination);
}

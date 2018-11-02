package com.jkojote.snakegame.game.obj.base;

import java.util.List;

/**
 * Base interface for all game objects
 */
public interface GameObject {

    List<BoundingCollisionBox> collisionBoxes();

}

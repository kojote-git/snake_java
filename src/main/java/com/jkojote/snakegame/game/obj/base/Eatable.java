package com.jkojote.snakegame.game.obj.base;

import java.util.List;

/**
 * Eatable object
 */
public interface Eatable extends GameObject {

    /**
     * A list of effects eatable can cause to the object that ate this eatable
     * @return a list of effects eatable can cause to the object that ate this eatable
     */
    List<Effect> effects();

    /**
     * Change the {@code eater}'s state accordingly to the effects this eatable has.
     * For example, if an apple causes growth,
     * then snake grows in length by one when it eats the apple
     * @param eater an object that ate this eatable
     */
    void affect(Eater eater);
}

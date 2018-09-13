package com.jkojote.snakegame.game.obj.base;

import com.jkojote.snakegame.game.obj.Effect;

import java.util.List;

public interface Eatable extends GameObject {

    List<Effect> effects();

    void affect(Eater eater);
}

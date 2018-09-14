package com.jkojote.snakegame.game.rendering;

import com.jkojote.snakegame.game.obj.base.GameObject;

import java.awt.*;

public interface GameObjectRenderer<T extends GameObject>  {
    void render(Graphics g, T gameObject);
}

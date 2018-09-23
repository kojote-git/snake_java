package com.jkojote.snakegame.game.rendering;

import com.jkojote.snakegame.game.obj.Apple;
import com.jkojote.snakegame.game.obj.Snake;
import com.jkojote.snakegame.game.obj.base.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Renderers {
    public static final int CELL_WIDTH_PIXELS = 16;

    private static final AppleRenderer APPLE_RENDERER;

    private static final SnakeRenderer SNAKE_RENDERER;

    static {
        APPLE_RENDERER = new AppleRenderer();
        SNAKE_RENDERER = new SnakeRenderer();
    }

    @SuppressWarnings("unchecked")
    public static  <T extends GameObject> GameObjectRenderer<T> getRendererFor(Class<T> clazz) {
       if (clazz == Apple.class) {
           return (GameObjectRenderer<T>) APPLE_RENDERER;
       }
       if (clazz == Snake.class) {
           return (GameObjectRenderer<T>) SNAKE_RENDERER;
       }
       return null;
    }

    public static void renderAll(Graphics g, List<GameObject> gameObjects) {
        for (GameObject go : gameObjects) {
            GameObjectRenderer r = getRendererFor(go.getClass());
            r.render(g, go);
        }
    }
}

package com.jkojote.snakegame.game.rendering;

import com.jkojote.snakegame.game.obj.Apple;
import com.jkojote.snakegame.game.obj.Cell;

import java.awt.*;
import static com.jkojote.snakegame.game.rendering.Renderers.CELL_WIDTH_PIXELS;

public class AppleRenderer implements GameObjectRenderer<Apple> {

    @Override
    public void render(Graphics g, Apple apple) {
        Cell fc = apple.getPosition();
        g.setColor(Color.GREEN);
        g.fillRect(
            fc.getX() * CELL_WIDTH_PIXELS,
            fc.getY() * CELL_WIDTH_PIXELS,
            CELL_WIDTH_PIXELS, CELL_WIDTH_PIXELS
        );
    }
}

package com.jkojote.snakegame.game.rendering;

import com.jkojote.snakegame.game.obj.Apple;
import com.jkojote.snakegame.game.obj.FieldCell;

import java.awt.*;
import static com.jkojote.snakegame.game.rendering.Renderers.CELL_WIDTH_PIXELS;

public class AppleRenderer implements GameObjectRenderer<Apple> {

    @Override
    public void render(Graphics g, Apple apple) {
        FieldCell fc = apple.getFieldCell();
        g.setColor(Color.GREEN);
        g.drawRect(
            fc.getX() * CELL_WIDTH_PIXELS,
            fc.getY() * CELL_WIDTH_PIXELS,
            CELL_WIDTH_PIXELS, CELL_WIDTH_PIXELS
        );
    }
}

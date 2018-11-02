package com.jkojote.snakegame.game.rendering;

import com.jkojote.snakegame.game.obj.Cell;
import com.jkojote.snakegame.game.obj.Portal;

import java.awt.*;
import static com.jkojote.snakegame.game.rendering.Renderers.CELL_WIDTH_PIXELS;

public class PortalRenderer implements GameObjectRenderer<Portal> {

    @Override
    public void render(Graphics g, Portal portal) {
        Cell enter = portal.getEnter();
        Cell exit = portal.getExit();
        g.setColor(Color.ORANGE);
        g.fillRect(
            enter.getX() * CELL_WIDTH_PIXELS,
            enter.getY() * CELL_WIDTH_PIXELS,
            CELL_WIDTH_PIXELS, CELL_WIDTH_PIXELS
        );
        g.setColor(Color.CYAN);
        g.fillRect(
            exit.getX() * CELL_WIDTH_PIXELS,
            exit.getY() * CELL_WIDTH_PIXELS,
            CELL_WIDTH_PIXELS, CELL_WIDTH_PIXELS
        );
    }
}

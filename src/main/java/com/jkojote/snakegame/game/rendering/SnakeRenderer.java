package com.jkojote.snakegame.game.rendering;

import com.jkojote.snakegame.game.obj.FieldCell;
import com.jkojote.snakegame.game.obj.Snake;

import java.awt.*;
import static com.jkojote.snakegame.game.obj.Snake.*;
import static com.jkojote.snakegame.game.rendering.Renderers.CELL_WIDTH_PIXELS;

public class SnakeRenderer implements GameObjectRenderer<Snake> {


    @Override
    public void render(Graphics g, Snake snake) {
        FieldCell bodyPart = snake.getHead().getPosition();
        g.setColor(Color.RED);
        g.fillRect(
            bodyPart.getX() * CELL_WIDTH_PIXELS,
            bodyPart.getY() * CELL_WIDTH_PIXELS,
            CELL_WIDTH_PIXELS - 1, CELL_WIDTH_PIXELS - 1
        );
        g.setColor(Color.BLUE);
        for (SnakePart sp : snake.getBody()) {
            bodyPart = sp.getPosition();
            g.fillRect(
                    bodyPart.getX() * CELL_WIDTH_PIXELS,
                    bodyPart.getY() * CELL_WIDTH_PIXELS,
                    CELL_WIDTH_PIXELS - 1, CELL_WIDTH_PIXELS - 1
            );
        }
    }
}

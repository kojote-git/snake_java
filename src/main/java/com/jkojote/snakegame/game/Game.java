package com.jkojote.snakegame.game;

import com.jkojote.snakegame.game.input.SnakeControlKeysListener;
import com.jkojote.snakegame.game.obj.FieldCell;
import com.jkojote.snakegame.game.obj.Snake;
import com.jkojote.snakegame.game.obj.SnakeAteItselfException;
import com.jkojote.snakegame.game.obj.base.Direction;
import com.jkojote.snakegame.game.rendering.Renderers;
import com.jkojote.snakegame.game.rendering.SnakeRenderer;

import java.awt.*;

import static com.jkojote.snakegame.game.obj.Snake.SnakePart;
import static com.jkojote.snakegame.game.rendering.Renderers.CELL_WIDTH_PIXELS;

public class Game implements Runnable {

    private Window window;

    private Snake snake;

    private final int gameFieldWidth = 42;

    private final int gameFieldHeight = 42;

    private SnakeRenderer snakeRenderer;

    public Game() {
        this.snake = new Snake(new FieldCell(4, 4), Direction.RIGHT, 16);
        this.snakeRenderer = (SnakeRenderer) Renderers.getRendererFor(Snake.class);
        this.window = new Window(
            "snake",
            gameFieldWidth * CELL_WIDTH_PIXELS,
            gameFieldHeight * CELL_WIDTH_PIXELS,
            this);
        window.addKeyListener(new SnakeControlKeysListener(this.snake));
    }

    public void render(Graphics graphics) {
        snakeRenderer.render(graphics, snake);
    }

    @Override
    public void run() {
        window.render();
        while (true) {
            try {
                try {
                    snake.move();
                } catch (SnakeAteItselfException saie) {
                    // snake grows when it eats itself)
                    snake.grow();
                }
                window.render();
                checkCollisions();
                Thread.sleep(60);
            } catch (Exception e) { }
        }
    }

    private void checkCollisions() {
        SnakePart head = snake.getHead();
        FieldCell headPosition = head.getPosition();
        if (headPosition.getX() > gameFieldWidth) {
            snake.teleport(new FieldCell(0, headPosition.getY()));
            return;
        }
        if (headPosition.getX() < 0) {
            snake.teleport(new FieldCell(gameFieldWidth, headPosition.getY()));
            return;
        }
        if (headPosition.getY() >= gameFieldHeight) {
            snake.teleport(new FieldCell(headPosition.getX(), 0));
            return;
        }
        if (headPosition.getY() < 0) {
            snake.teleport(new FieldCell(headPosition.getX(), gameFieldHeight));
            return;
        }
    }
}

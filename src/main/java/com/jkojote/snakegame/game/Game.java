package com.jkojote.snakegame.game;

import com.jkojote.snakegame.game.input.SnakeControlKeysListener;
import com.jkojote.snakegame.game.obj.FieldCell;
import com.jkojote.snakegame.game.obj.Snake;
import com.jkojote.snakegame.game.obj.base.Direction;
import com.jkojote.snakegame.game.rendering.Renderers;
import com.jkojote.snakegame.game.rendering.SnakeRenderer;

import java.awt.*;

public class Game implements Runnable {

    private Window window;

    private Snake snake;

    private SnakeRenderer snakeRenderer;

    public Game() {
        this.snake = new Snake(new FieldCell(4, 4), Direction.RIGHT);
        this.snakeRenderer = (SnakeRenderer) Renderers.getRendererFor(Snake.class);
        this.window = new Window("snake", 800, 600, this);
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
                window.render();
                snake.move();
                Thread.sleep(30);
            } catch (Exception e) { }
        }
    }
}

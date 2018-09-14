package com.jkojote.snakegame.game.input;

import com.jkojote.snakegame.game.obj.Snake;
import com.jkojote.snakegame.game.obj.base.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeControlKeysListener implements KeyListener {

    private Snake snake;

    public SnakeControlKeysListener(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggleDirection(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void toggleDirection(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                snake.changeDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.changeDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_UP:
                snake.changeDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.changeDirection(Direction.DOWN);
                break;
        }
    }
}

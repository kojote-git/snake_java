package com.jkojote.snakegame.game;


import com.jkojote.snakegame.game.input.SnakeControlKeysListener;
import com.jkojote.snakegame.game.obj.SnakeAteItselfException;
import com.jkojote.snakegame.game.rendering.Renderers;

public class Game implements Runnable {

    private Window window;

    private GameState gameState;

    public Game() {
        this.gameState = GameState.defaultSettings();
        this.window = new Window("Snake",
            (gameState.getGameFieldWidth() + 1)  * Renderers.CELL_WIDTH_PIXELS,
            (gameState.getGameFieldHeight() + 1) * Renderers.CELL_WIDTH_PIXELS,
            this
        );
        this.window.addKeyListener(new SnakeControlKeysListener(this.gameState.getSnake()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(40);
                window.renderAllObjects(gameState.getGameObjects());
                gameState.update();
            }
        } catch (InterruptedException ie) {

        } catch (GameOverException | SnakeAteItselfException e) {
            window.renderAllObjects(gameState.getGameObjects());
            System.out.println("Game Over");
        }
    }
}

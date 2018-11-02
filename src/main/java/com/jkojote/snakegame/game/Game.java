package com.jkojote.snakegame.game;


import com.jkojote.snakegame.game.borders.TeleportBorderCollisionStrategy;
import com.jkojote.snakegame.game.input.SnakeControlKeysListener;
import com.jkojote.snakegame.game.obj.SnakeAteItselfException;
import com.jkojote.snakegame.game.rendering.Renderers;

import static com.jkojote.snakegame.game.GameState.GameStateBuilder;

public class Game implements Runnable {

    private Window window;

    private GameState gameState;

    public Game() {
        this.gameState = GameStateBuilder.aGameState()
                .withGameFieldWidth(40)
                .withGameFieldHeight(30)
                .withMaxPortalsCount(3)
                .withBorderCollisionStrategy(new TeleportBorderCollisionStrategy(40, 30))
                .build();
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
                Thread.sleep(25);
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

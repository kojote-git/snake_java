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
                .withMaxPortalsCount(10)
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
            double amountOfTicks = 25.0;
            double lastTime = System.nanoTime();
            double ns = 1000000000 / amountOfTicks;
            double delta = 0;
            long timer = System.currentTimeMillis();
            int frames = 0;
            while (true) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                while (delta >= 1) {
                    gameState.update();
                    delta--;
                }
                window.renderAllObjects(gameState.getGameObjects());
                frames++;
                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    System.out.println(frames);
                    frames = 0;
                }
            }
        } catch (GameOverException | SnakeAteItselfException e) {
            window.renderAllObjects(gameState.getGameObjects());
            System.out.println("Game Over");
        }
    }
}

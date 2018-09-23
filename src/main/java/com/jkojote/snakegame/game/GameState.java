package com.jkojote.snakegame.game;

import com.jkojote.snakegame.game.borders.Border;
import com.jkojote.snakegame.game.borders.BorderCollisionStrategy;
import com.jkojote.snakegame.game.borders.EndGameBorderCollisionStrategy;
import com.jkojote.snakegame.game.borders.TeleportBorderCollisionStrategy;
import com.jkojote.snakegame.game.obj.Apple;
import com.jkojote.snakegame.game.obj.Cell;
import com.jkojote.snakegame.game.obj.Snake;
import com.jkojote.snakegame.game.obj.base.BoundingCollisionBox;
import com.jkojote.snakegame.game.obj.base.Direction;
import com.jkojote.snakegame.game.obj.base.Eatable;
import com.jkojote.snakegame.game.obj.base.GameObject;
import com.jkojote.snakegame.game.obj.envets.GameEvent;
import com.jkojote.snakegame.game.obj.envets.GameEventListener;
import com.jkojote.snakegame.game.obj.envets.SnakeAte;
import static com.jkojote.snakegame.game.obj.Snake.SnakePart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameState implements GameEventListener {

    public static final int MIN_GAME_FIELD_WIDTH = 20;

    public static final int MIN_GAME_FIELD_HEIGHT = 20;

    private Snake snake;

    private int gameFieldWidth;

    private int gameFieldHeight;

    private List<GameObject> gameObjects;

    private BorderCollisionStrategy borderCollisionStrategy;

    private Random random = new Random(99999999);

    public static GameState defaultSettings() {
        return GameStateBuilder.aGameState()
                .withBorderCollisionStrategy(new TeleportBorderCollisionStrategy(40, 30))
                .withGameFieldHeight(30)
                .withGameFieldWidth(40)
                .build();
    }

    private void checkCollisions() {
        Cell head = snake.getHead().getPosition();
        checkBorderCollisions(head);
        BoundingCollisionBox snakeCB = snake.collisionBox();
        for (int i = 1; i < gameObjects.size(); i++) {
            GameObject go = gameObjects.get(i);
            if (snakeCB.checkCollides(go.collisionBox())) {
                if (go instanceof Eatable) {
                    snake.eat((Eatable) go);
                }
            }
        }
    }

    private void checkBorderCollisions(Cell head) {
        if (head.getX() > gameFieldWidth)
            borderCollisionStrategy.performAction(snake, Border.RIGHT);
        if (head.getX() < 0)
            borderCollisionStrategy.performAction(snake, Border.LEFT);
        if (head.getY() > gameFieldHeight)
            borderCollisionStrategy.performAction(snake, Border.DOWN);
        if (head.getY() < 0)
            borderCollisionStrategy.performAction(snake, Border.UPPER);
    }

    public void update() {
        updateApple();
        checkCollisions();
        snake.move();
    }

    private void updateApple() {
        if (!hasApple()) {
            Cell applePosition = null;
            boolean collided = true;
            while (collided) {
                collided = false;
                int x = (Math.abs(random.nextInt()) % (gameFieldWidth - 2)) + 1;
                int y = (Math.abs(random.nextInt()) % (gameFieldHeight - 2)) + 1;
                applePosition = new Cell(x, y);
                if (applePosition.equals(snake.getHead().getPosition())) {
                    collided = true;
                    continue;
                }
                for (SnakePart sp : snake.getBody()) {
                    if (applePosition.equals(sp.getPosition())) {
                        collided = true;
                        break;
                    }
                }
            }
            Apple apple = new Apple(applePosition);
            gameObjects.add(apple);
        }
    }

    private boolean hasApple() {
        for (GameObject go : gameObjects) {
            if (go instanceof Apple)
                return true;
        }
        return false;
    }

    @Override
    public void performAction(GameEvent event) {
        if (event instanceof SnakeAte) {
            SnakeAte sae = (SnakeAte) event;
            Eatable eatable = sae.getEatable();
            gameObjects.remove(eatable);
        }
    }

    public List<GameObject> getGameObjects() {
        return Collections.unmodifiableList(this.gameObjects);
    }

    public BorderCollisionStrategy getBorderCollisionStrategy() {
        return borderCollisionStrategy;
    }

    public int getGameFieldHeight() {
        return gameFieldHeight;
    }

    public int getGameFieldWidth() {
        return gameFieldWidth;
    }

    public Snake getSnake() {
        return snake;
    }

    public static final class GameStateBuilder {
        private Snake snake;

        private int gameFieldWidth = 20;

        private int gameFieldHeight = 20;

        private BorderCollisionStrategy borderCollisionStrategy;

        private GameStateBuilder() {

        }

        public static GameStateBuilder aGameState() {
            return new GameStateBuilder();
        }

        public GameStateBuilder withSnake(Snake snake) {
            this.snake = snake;
            return this;
        }

        public GameStateBuilder withGameFieldWidth(int gameFieldWidth) {
            if (gameFieldWidth < MIN_GAME_FIELD_WIDTH)
                throw new IllegalArgumentException("Field width must be > " + MIN_GAME_FIELD_WIDTH);
            this.gameFieldWidth = gameFieldWidth;
            return this;
        }

        public GameStateBuilder withGameFieldHeight(int gameFieldHeight) {
            if (gameFieldHeight < MIN_GAME_FIELD_HEIGHT)
                throw new IllegalArgumentException("Field height must be >" + MIN_GAME_FIELD_HEIGHT);
            this.gameFieldHeight = gameFieldHeight;
            return this;
        }

        public GameStateBuilder withBorderCollisionStrategy(BorderCollisionStrategy borderCollisionStrategy) {
            this.borderCollisionStrategy = borderCollisionStrategy;
            return this;
        }

        public GameState build() {
            GameState gameState = new GameState();
            if (this.snake == null) {
                this.snake = new Snake(new Cell(5, 5), Direction.RIGHT, 3);
            }
            if (this.borderCollisionStrategy == null) {
                this.borderCollisionStrategy = new EndGameBorderCollisionStrategy();
            }
            this.snake.addEventListener(gameState);
            gameState.snake                   = this.snake;
            gameState.gameFieldHeight         = this.gameFieldHeight;
            gameState.gameFieldWidth          = this.gameFieldWidth;
            gameState.borderCollisionStrategy = this.borderCollisionStrategy;
            gameState.gameObjects             = new ArrayList<>();
            gameState.gameObjects.add(this.snake);
            return gameState;
        }
    }
}

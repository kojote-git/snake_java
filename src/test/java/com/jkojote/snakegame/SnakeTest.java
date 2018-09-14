package com.jkojote.snakegame;

import com.jkojote.snakegame.game.obj.base.Direction;
import com.jkojote.snakegame.game.obj.FieldCell;
import com.jkojote.snakegame.game.obj.Snake;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static com.jkojote.snakegame.game.obj.Snake.SnakePart;

public class SnakeTest {

    private Snake snake;

    private int speed;

    @Before
    public void init() {
        snake = new Snake(new FieldCell(4, 4), Direction.UP);
        snake.changeSpeed(2);
        speed = snake.getSpeed();
    }

    @After
    public void clean() {
        snake = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeSpeed() {
        snake.changeSpeed(2);
        assertEquals(2, snake.getSpeed());
        snake.changeSpeed(1);
        assertEquals(1, snake.getSpeed());
        snake.changeSpeed(-1);
    }

    @Test
    public void move_MoveUp() {
        moveAndCheck(snake, Direction.UP, 0, -speed);
    }

    @Test
    public void move_MoveLeft() {
        moveAndCheck(snake, Direction.LEFT, -speed, 0);
    }

    @Test
    public void move_MoveDown() {
        moveAndCheck(snake, Direction.DOWN, 0, speed);
    }

    @Test
    public void move_MoveRight() {
        moveAndCheck(snake, Direction.RIGHT, speed, 0);
    }

    @Test
    public void grow() {
        growAndCheck(snake, Direction.UP, 0, 1);
        growAndCheck(snake, Direction.RIGHT, -1, 0);
        growAndCheck(snake, Direction.DOWN, 0, -1);
        growAndCheck(snake, Direction.LEFT, 1, 0);
    }

    /**
     * Move snake in direction of {@code d} and check whether
     * coordinates of its head and body parts moved correctly.
     *
     * Substitute speed into {@code x} or {@code y} depending on its direction
     * @param snake
     * @param d
     * @param x
     * @param y
     */
    private void moveAndCheck(Snake snake, Direction d, int x, int y) {
        FieldCell headBeforeMove, headAfterMove;
        List<FieldCell> bodyBeforeMove, bodyAfterMove;
        headBeforeMove = snake.getHead().getPosition();
        bodyBeforeMove = snake.getBody().stream()
                .map(SnakePart::getPosition)
                .collect(Collectors.toList());
        snake.changeDirection(d);
        snake.move();
        headAfterMove = snake.getHead().getPosition();
        bodyAfterMove = snake.getBody().stream()
                .map(SnakePart::getPosition)
                .collect(Collectors.toList());
        assertEquals(headAfterMove, new FieldCell(headBeforeMove.getX() + x, headBeforeMove.getY() + y));
        assertEquals(
            bodyAfterMove,
            bodyBeforeMove.stream()
                .map(fc -> new FieldCell(fc.getX() + x, fc.getY() + y))
                .collect(Collectors.toList())
        );
    }

    /**
     * Grow snake and check whether its tail has been set into
     * correct position on game field
     *
     * @param snake
     * @param d
     * @param x
     * @param y
     */
    private void growAndCheck(Snake snake, Direction d, int x, int y) {
        SnakePart prevTail, newTail;
        snake.changeDirection(d);
        prevTail = snake.getTail();
        snake.grow();
        newTail = snake.getTail();
        assertEquals(newTail.getPosition(), new FieldCell(
                prevTail.getPosition().getX() + x,
                prevTail.getPosition().getY() + y
        ));
    }
}

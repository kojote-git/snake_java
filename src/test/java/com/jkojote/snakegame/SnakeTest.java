package com.jkojote.snakegame;

import com.jkojote.snakegame.game.obj.Cell;
import com.jkojote.snakegame.game.obj.Snake;
import com.jkojote.snakegame.game.obj.SnakeAteItselfException;
import com.jkojote.snakegame.game.obj.base.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SnakeTest {

    private Snake snake;

    @Test
    public void move_MoveSnakeAndCheckWhetherItsHeadHasExpectedPosition() {
        Cell prevPosition, expectedPosition, currentPosition;
        int speed;

        snake = new Snake(new Cell(10, 10), Direction.RIGHT, 4);
        speed = snake.getSpeed();
        prevPosition = snake.getHead().getPosition();
        snake.move();
        expectedPosition = new Cell(prevPosition.getX() + speed, prevPosition.getY());
        currentPosition = snake.getHead().getPosition();
        assertEquals(expectedPosition, currentPosition);

        snake.changeDirection(Direction.UP);
        prevPosition = currentPosition;
        expectedPosition = new Cell(prevPosition.getX(), prevPosition.getY() - speed);
        snake.move();
        currentPosition = snake.getHead().getPosition();
        assertEquals(expectedPosition, currentPosition);

        snake.changeDirection(Direction.LEFT);
        prevPosition = currentPosition;
        expectedPosition = new Cell(prevPosition.getX() - speed, prevPosition.getY());
        snake.move();
        currentPosition = snake.getHead().getPosition();
        assertEquals(expectedPosition, currentPosition);
    }

    @Test(expected = SnakeAteItselfException.class)
    public void move_MoveSnakeUntilItEatsItself() {
        snake = new Snake(new Cell(10, 10), Direction.RIGHT, 8);
        snake.changeDirection(Direction.UP);
        snake.move();
        snake.move();
        snake.changeDirection(Direction.LEFT);
        snake.move();
        snake.move();
        snake.changeDirection(Direction.DOWN);
        snake.move();
        snake.move();
    }

    @Test
    public void grow() {
        snake = new Snake(new Cell(10, 10), Direction.RIGHT, 3);
        assertEquals(4, snake.getSize());
        snake.grow();
        assertEquals(5, snake.getSize());
        snake.grow();
        assertEquals(6, snake.getSize());
    }
}

package com.jkojote.snakegame;

import com.jkojote.snakegame.game.obj.base.Direction;
import com.jkojote.snakegame.game.obj.FieldCell;
import com.jkojote.snakegame.game.obj.Snake;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SnakeTest {

    private Snake snake;

    @Before
    public void init() {
        snake = new Snake(new FieldCell(4, 4), Direction.UP);
    }

    @After
    public void clean() {
        snake = null;
    }

    @Test
    public void move_MoveUp() {
        snake.changeDirection(Direction.UP);

    }
}

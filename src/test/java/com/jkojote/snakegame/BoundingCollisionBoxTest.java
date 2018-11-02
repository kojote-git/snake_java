package com.jkojote.snakegame;

import com.jkojote.snakegame.game.obj.Cell;
import com.jkojote.snakegame.game.obj.base.BoundingCollisionBox;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoundingCollisionBoxTest {

    private BoundingCollisionBox cb1;

    private BoundingCollisionBox cb2;

    @After
    public void clear() {
        cb1 = null;
        cb2 = null;
    }

    /*
     * At the beginning boxes are arranged as this:
     *
     *       _____
     * A -->|   __|__
     *      |  |  |  |
     *       --|--   | <-- B
     *          -----
     *
     * Then B moves clockwise until it reaches initial position.
     *
     *   _____         _____      _____
     *  |_____|     __|__   |    |  |  |
     *  |     | -> |  |  |  | -> |  |  |   ......
     *  |     |    |   --|--      -----
     *   -----      -----
     *
     */
    @Test
    public void checkCollides_CheckWhetherTwoCollisionBoxesCollide() {
        cb1 = new BoundingCollisionBox(new Cell(5, 5), 2, 2);
        cb2 = new BoundingCollisionBox(new Cell(6, 6), 2, 2);
        assertTrue(cb1.checkCollides(cb2));
        moveAndCheckCollide(5, 6);
        moveAndCheckCollide(4, 6);
        moveAndCheckCollide(4, 5);
        moveAndCheckCollide(4, 4);
        moveAndCheckCollide(5, 4);
        moveAndCheckCollide(6, 4);
        moveAndCheckCollide(6, 5);
    }

    @Test
    public void checkCollides_CheckThatTwoBoxesDoNotCollide() {
        cb1 = new BoundingCollisionBox(new Cell(5, 5), 2, 2);
        cb2 = new BoundingCollisionBox(new Cell(8, 6), 2, 2);
        assertFalse(cb1.checkCollides(cb2));
        moveAndCheckNotCollide(8, 7);
        moveAndCheckNotCollide(7, 8);
        moveAndCheckNotCollide(6, 8);
        moveAndCheckNotCollide(5, 8);
        moveAndCheckNotCollide(4, 8);
        moveAndCheckNotCollide(3, 8);
        moveAndCheckNotCollide(2, 8);
        moveAndCheckNotCollide(2, 7);
        moveAndCheckNotCollide(2, 6);
        moveAndCheckNotCollide(2, 5);
        moveAndCheckNotCollide(2, 4);
        moveAndCheckNotCollide(2, 3);
        moveAndCheckNotCollide(2, 2);
        moveAndCheckNotCollide(3, 2);
        moveAndCheckNotCollide(4, 2);
        moveAndCheckNotCollide(5, 2);
        moveAndCheckNotCollide(6, 2);
        moveAndCheckNotCollide(7, 2);
        moveAndCheckNotCollide(8, 2);
        moveAndCheckNotCollide(8, 3);
        moveAndCheckNotCollide(8, 4);
        moveAndCheckNotCollide(8, 5);

        cb1 = new BoundingCollisionBox(new Cell(5, 5), 2, 2);
        cb2 = new BoundingCollisionBox(new Cell(7, 7), 2, 2);
    }

    private void moveAndCheckCollide(int x, int y) {
        cb2.moveTo(new Cell(x, y));
        assertTrue(cb1.checkCollides(cb2));
    }

    private void moveAndCheckNotCollide(int x, int y) {
        cb2.moveTo(new Cell(x, y));
        assertFalse(cb1.checkCollides(cb2));
    }
}

package com.jkojote.snakegame.game.obj;

import java.util.Objects;

/**
 * Field of the game consists of cells and
 * these cells are represented by this class. <br/>
 *
 * <b>x</b> - number of the column in which the cell resides<br/>
 * <b>y</b> - number of the row in which the cell resides
 * intersection x-th column and y-th row is the actual position of the cell
 */
public class Cell {

    private int x;

    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Cell) {
            Cell that = (Cell) o;
            return x == that.x &&
                   y == that.y;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Cell: { x: " + x + ", y: " + y + "}";
    }
}

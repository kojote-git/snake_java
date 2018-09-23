package com.jkojote.snakegame.game.obj.base;

import com.jkojote.snakegame.game.obj.Cell;

import static com.google.common.base.Preconditions.checkNotNull;

public class BoundingCollisionBox {

    private int width;

    private int height;

    private Cell topLeft;

    private float area;

    public BoundingCollisionBox(Cell topLeft, int width, int height) {
        checkNotNull(topLeft);
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.area = this.width * this.height;
    }

    public boolean checkCollides(BoundingCollisionBox cb) {
        final Cell cbTopLeft = cb.topLeft;
        if ((topLeft.getX() <= cbTopLeft.getX() + cb.width) &&
                (topLeft.getX() + width >= cbTopLeft.getX()) &&
                (topLeft.getY() <= cbTopLeft.getY() + cb.height) &&
                (topLeft.getY() + height >= cbTopLeft.getY())) {
            return true;
        }
        return false;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public void moveTo(Cell topLeft) {
        checkNotNull(topLeft);
        this.topLeft = topLeft;
    }

    public Cell topLeft() {
        return topLeft;
    }

    public float area() {
        return area;
    }

}

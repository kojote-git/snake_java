package com.jkojote.snakegame.game.obj;

import com.jkojote.snakegame.game.obj.base.Direction;
import com.jkojote.snakegame.game.obj.base.Eatable;
import com.jkojote.snakegame.game.obj.base.Eater;
import com.jkojote.snakegame.game.obj.base.Movable;
import com.jkojote.snakegame.game.obj.envets.SnakeAte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class Snake extends BaseGameObject
implements Movable, Eater {

    /**
     * direction of the snake
     */
    private Direction direction;

    /**
     * speed of the snake
     */
    private int speed;

    /**
     * size of snake body + 1 (head)
     */
    private int size;

    private SnakePart head;

    private List<SnakePart> body;

    /**
     * size of body
     */
    private int bodySize;

    /**
     * Create new snake with head that resides in {@code head} cell
     * and specify direction for it
     * @param head
     * @param direction
     */
    public Snake(FieldCell head, Direction direction) {
        this.body = new ArrayList<>();
        this.head = new SnakePart(head);
        this.direction = direction;
        this.appendTail();
        this.speed = 1;
        this.size = body.size() + 1;
        this.bodySize = body.size();
    }

    @Override
    public void move() {
        switch (direction) {
            case UP:
                move(0, -speed);
                break;
            case DOWN:
                move(0, speed);
                break;
            case LEFT:
                move(-speed, 0);
                break;
            case RIGHT:
                move(speed, 0);
                break;
        }
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void changeDirection(Direction direction) {
        if (direction == null)
            throw new NullPointerException("direction cannot be null");
        this.direction = direction;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void changeSpeed(int newSpeed) {
        checkArgument(newSpeed > 0, "speed must be positive integer");
        this.speed = newSpeed;
    }

    public List<SnakePart> getBody() {
        return Collections.unmodifiableList(body);
    }

    public SnakePart getHead() {
        return this.head;
    }

    public SnakePart getTail() {
        return bodySize == 0 ? head :this.body.get(bodySize - 1);
    }

    /**
     * Moves snake head into intersection of {@code x}-th column and {@code y}-th row.
     * Then moves its body
     * @param x
     * @param y
     */
    private void move(int x, int y) {
        FieldCell prevPos = head.getPosition();
        FieldCell newHeadPosition = new FieldCell(
            prevPos.getX() + x,
            prevPos.getY() + y
        );
        this.head.setPosition(newHeadPosition);
        for (SnakePart part : body) {
            prevPos = part.getPosition();
            part.setPosition(
                new FieldCell(
                    prevPos.getX() + x,
                    prevPos.getY() + y
                )
            );
        }
    }

    @Override
    public void eat(Eatable eatable) {
        eatable.affect(this);
        notifyAllListeners(new SnakeAte(this, eatable));
    }

    public void grow() {
        appendTail();
    }

    /**
     * @return size of the snake body + head
     */
    public int getSize() {
        return size;
    }

    /**
     * Appends new tail after snake's previous tail.
     * Position of the tail depends on snake's direction
     */
    private void appendTail() {
        SnakePart tail;
        if (bodySize == 0)
            tail = head;
        else
            tail = body.get(bodySize - 1);
        FieldCell fc = tail.getPosition();
        switch (direction) {
            case UP:
                appendPart(fc.getX(), fc.getY() + 1);
                break;
            case DOWN:
                appendPart(fc.getX(), fc.getY() - 1);
                break;
            case LEFT:
                appendPart(fc.getX() + 1, fc.getY());
                break;
            case RIGHT:
                appendPart(fc.getX() - 1, fc.getY());
                break;
        }
    }

    /**
     * Appends part that resides in intersection of {@code x}-th column and {@code y}-th row
     * @param x
     * @param y
     */
    private void appendPart(int x, int y) {
        size++;
        bodySize++;
        body.add(new SnakePart(new FieldCell(x, y)));
    }

    /**
     * Represents a part of snake's body or its head
     */
    public static class SnakePart {

        private FieldCell cell;

        SnakePart(FieldCell cell) {
            this.cell = cell;
        }

        private void setPosition(FieldCell fc) {
            this.cell = fc;
        }

        public FieldCell getPosition() {
            return cell;
        }
    }
}

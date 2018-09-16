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
        this(head, direction, 3);
    }

    public Snake(FieldCell head, Direction direction, int size) {
        this.body = new ArrayList<>();
        this.head = new SnakePart(head);
        this.direction = direction;
        for (int i = 0; i < size; i++)
            this.appendTail();
        this.speed = 1;
        this.size = body.size() + 1;
        this.bodySize = body.size();
    }

    @Override
    public void move() {
        FieldCell fc = head.getPosition();
        switch (direction) {
            case UP:
                move(fc.getX(), fc.getY() - speed);
                break;
            case DOWN:
                move(fc.getX(), fc.getY() + speed);
                break;
            case LEFT:
                move(fc.getX() - speed, fc.getY());
                break;
            case RIGHT:
                move(fc.getX() + speed, fc.getY());
                break;
        }
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean changeDirection(Direction direction) {
        if (direction == null)
            throw new NullPointerException("direction cannot be null");
        // direction can be changed if snake doesn't have body yet or
        // if it changes direction, next cell into which snake's move
        // won't be its body part that precedes head
        if (bodySize == 0) {
            this.direction = direction;
            return true;
        }
        if (!headNextPosition(direction).equals(body.get(0).getPosition())) {
            this.direction = direction;
            return true;
        }
        return false;
    }

    private FieldCell headNextPosition(Direction direction) {
        FieldCell head = getHead().getPosition();
        switch (direction) {
            case RIGHT:
                return new FieldCell(head.getX() + speed, head.getY());
            case UP:
                return new FieldCell(head.getX(), head.getY() - speed);
            case DOWN:
                return new FieldCell(head.getX(), head.getY() + speed);
            case LEFT:
                return new FieldCell(head.getX() - speed, head.getY());
        }
        return head;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    /**
     * Do nothing
     * @param newSpeed
     */
    @Override
    public void changeSpeed(int newSpeed) {

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
     * Moves snake's head into cell with coordinates ({@code x}, {@code y}).
     * Then moves its body after head
     * @param x
     * @param y
     * @throws SnakeAteItselfException if, while moving, head collides with some part of snake's body
     */
    private void move(int x, int y) {
        FieldCell prevPos = head.getPosition();
        FieldCell newHeadPosition = new FieldCell(
            x,
            y
        );
        this.head.setPosition(newHeadPosition);
        for (SnakePart part : body) {
            FieldCell temp = part.getPosition();
            // head collides with moving part of the body
            if (newHeadPosition.equals(prevPos))
                throw new SnakeAteItselfException();
            part.setPosition(
                new FieldCell(
                    prevPos.getX(),
                    prevPos.getY()
                )
            );
            prevPos = temp;
        }
    }

    @Override
    public void eat(Eatable eatable) {
        eatable.affect(this);
        notifyAllListeners(new SnakeAte(this, eatable));
    }

    /**
     * Performs teleportation of the snake's head into the {@code cell}
     * @param cell
     */
    public void teleport(FieldCell cell) {
        move(cell.getX(), cell.getY());
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

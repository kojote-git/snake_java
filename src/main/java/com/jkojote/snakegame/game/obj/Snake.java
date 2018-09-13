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

    private Direction direction;

    private int speed;

    private int size;

    private SnakePart head;

    private List<SnakePart> body;

    public Snake(FieldCell head, Direction direction) {
        this.body = new ArrayList<>();
        this.head = new SnakePart(head);
        this.direction = direction;
        this.speed = 1;
        this.size = body.size() + 1;
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

    public void move(int x, int y) {
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
        size++;
        appendTail();
    }
    private void appendTail() {
        SnakePart tail;
        if (body.size() == 0)
            tail = head;
        else
            tail = body.get(size - 2);
        FieldCell fc = tail.getPosition();
        switch (direction) {
            case UP:
                appendPart(fc.getX(), fc.getY() - 1);
                break;
            case DOWN:
                appendPart(fc.getX(), fc.getY() + 1);
                break;
            case LEFT:
                appendPart(fc.getX() + 1, fc.getY());
                break;
            case RIGHT:
                appendPart(fc.getX() - 1, fc.getX());
                break;
        }
    }

    private void appendPart(int x, int y) {
        body.add(new SnakePart(new FieldCell(x, y)));
    }

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

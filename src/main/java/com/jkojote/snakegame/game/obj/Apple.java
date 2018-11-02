package com.jkojote.snakegame.game.obj;

import com.jkojote.snakegame.game.obj.base.BoundingCollisionBox;
import com.jkojote.snakegame.game.obj.base.Eatable;
import com.jkojote.snakegame.game.obj.base.Eater;
import com.jkojote.snakegame.game.obj.base.Effect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.jkojote.snakegame.game.obj.base.Effect.*;

/**
 * An apple takes one position on game field.
 * When snake eats apple, snake grows new tail
 */
public class Apple implements Eatable {

    private static final List<Effect> effects;

    static {
        effects = Arrays.asList(SNAKE_GROW);
    }

    private Cell position;

    private List<BoundingCollisionBox> collisionBoxes;

    public Apple(Cell position) {
        this.position = position;
        this.collisionBoxes = new ArrayList<>();
        this.collisionBoxes.add(new BoundingCollisionBox(position, 1, 1));
    }

    public Cell getPosition() {
        return position;
    }

    @Override
    public List<Effect> effects() {
        return Collections.unmodifiableList(effects);
    }

    @Override
    public void affect(Eater eater) {
        if (!(eater instanceof Snake)) return;
        Snake snake = (Snake) eater;
        snake.grow();
    }

    @Override
    public List<BoundingCollisionBox> collisionBoxes() {
        return collisionBoxes;
    }
}

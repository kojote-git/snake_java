package com.jkojote.snakegame.game.obj;

import com.jkojote.snakegame.game.obj.base.Eatable;
import com.jkojote.snakegame.game.obj.base.Eater;
import com.jkojote.snakegame.game.obj.base.Effect;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.jkojote.snakegame.game.obj.base.Effect.*;

/**
 * An apple takes one cell on game field.
 * When snake eats apple, snake grows new tail
 */
public class Apple implements Eatable {

    private static final List<Effect> effects;

    static {
        effects = Arrays.asList(SNAKE_GROW);
    }

    private Cell cell;

    public Apple(Cell position) {
        this.cell = position;
    }

    public Cell getPosition() {
        return cell;
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
}

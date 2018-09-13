package com.jkojote.snakegame.game.obj.envets;

import com.jkojote.snakegame.game.obj.base.Eatable;
import com.jkojote.snakegame.game.obj.Snake;

public class SnakeAte extends GameEvent {

    private Eatable eatable;

    public SnakeAte(Snake target, Eatable eatable, String description) {
        super(target, description);
        this.eatable = eatable;
    }

    public SnakeAte(Snake target, Eatable eatable) {
        super(target, "Snake ate something...");
        this.eatable = eatable;
    }

    public Eatable getEatable() {
        return eatable;
    }
}

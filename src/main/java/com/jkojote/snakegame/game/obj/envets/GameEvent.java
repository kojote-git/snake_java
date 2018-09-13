package com.jkojote.snakegame.game.obj.envets;

import com.jkojote.snakegame.game.obj.base.GameObject;

public abstract class GameEvent {

    private GameObject target;

    private String description;

    public GameEvent(GameObject target, String description) {
        this.target = target;
        this.description = description;
    }

    public GameObject getTarget() {
        return target;
    }

    public String getDescription() {
        return description;
    }
}

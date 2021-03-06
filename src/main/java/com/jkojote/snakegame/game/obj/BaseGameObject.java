package com.jkojote.snakegame.game.obj;

import com.jkojote.snakegame.game.obj.base.GameObject;
import com.jkojote.snakegame.game.obj.envets.GameEvent;
import com.jkojote.snakegame.game.obj.envets.GameEventListener;

import java.util.LinkedList;
import java.util.List;

/**
 * Base class for some classes that allow event sourcing
 */
public abstract class BaseGameObject implements GameObject {

    private List<GameEventListener> eventListeners;

    protected BaseGameObject() {
        eventListeners = new LinkedList<>();
    }

    public void addEventListener(GameEventListener eventListener) {
        eventListeners.add(eventListener);
    }

    public void removeEventListener(GameEventListener eventListener) {
        eventListeners.remove(eventListener);
    }

    protected void notifyAllListeners(GameEvent gameEvent) {
        for (GameEventListener eventListener : eventListeners)
            eventListener.performAction(gameEvent);
    }
}

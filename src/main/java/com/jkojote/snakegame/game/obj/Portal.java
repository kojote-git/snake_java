package com.jkojote.snakegame.game.obj;

import com.jkojote.snakegame.game.obj.base.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Portal implements Eatable {

    private Cell enter;

    private Cell exit;

    private List<BoundingCollisionBox> collisionBoxes;

    public Portal(Cell enter, Cell exit) {
        this.enter = enter;
        this.exit  = exit;
        this.collisionBoxes = new ArrayList<>();
        this.collisionBoxes.add(new BoundingCollisionBox(enter, 1, 1));
        this.collisionBoxes.add(new BoundingCollisionBox(exit, 1, 1));
    }

    @Override
    public List<Effect> effects() {
        return null;
    }

    @Override
    public void affect(Eater eater) {
        if (eater instanceof Teleportable) {
            Teleportable t = (Teleportable) eater;
            BoundingCollisionBox enterBox = this.collisionBoxes.get(0);
            BoundingCollisionBox exitBox = this.collisionBoxes.get(1);
            for (int i = 0; i < t.collisionBoxes().size(); i++) {
                BoundingCollisionBox bcb = t.collisionBoxes().get(i);
                if (enterBox.checkCollides(bcb)) {
                    t.teleport(exit);
                    return;
                } else if (exitBox.checkCollides(bcb)) {
                    t.teleport(enter);
                    return;
                }
            }
        }
    }

    public Cell getEnter() {
        return enter;
    }

    public Cell getExit() {
        return exit;
    }

    @Override
    public List<BoundingCollisionBox> collisionBoxes() {
        return Collections.unmodifiableList(collisionBoxes);
    }
}

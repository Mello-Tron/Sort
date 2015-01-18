package com.ptrh.gameworld;

import com.ptrh.gameobjects.Dot;

/**
 *
 * @author Patrick
 */
public class GameWorld {
    
    private Dot dot;

    public GameWorld() {
        dot = new Dot(136, 0, 20, 20);
    }

    public void update(float delta) {
        dot.update(delta);
    }

    public Dot getDot() {
        return dot;

    }
}

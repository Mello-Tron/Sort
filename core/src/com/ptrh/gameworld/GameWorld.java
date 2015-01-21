package com.ptrh.gameworld;

import com.ptrh.gameobjects.Dot;

/**
 *
 * @author Patrick
 */
public class GameWorld {
    private Dot dot;

    public GameWorld(float screenWRatio, float screenHRatio) {
        dot = new Dot(70, 50, 7, 7, screenWRatio, screenHRatio); //Starting position in world
    }

    public void update(float delta) {
        dot.update(delta);
    }

    public Dot getDot() {
        return dot;

    }
}

package com.ptrh.gameworld;

import com.ptrh.gameobjects.Dot;
import com.ptrh.gameobjects.DotCreator;

/**
 *
 * @author Patrick
 */
public class GameWorld {
    private DotCreator dotCreator;

    public GameWorld(float screenWRatio, float screenHRatio) {
        dotCreator = new DotCreator(screenWRatio, screenHRatio);
    }

    public void update(float delta) {
        dotCreator.update(delta);
    }

    public DotCreator getDotCreator() {
        return dotCreator;

    }
}

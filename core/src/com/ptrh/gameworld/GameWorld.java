package com.ptrh.gameworld;

import com.ptrh.gameobjects.Dot;
import com.ptrh.gameobjects.DotCreator;
import com.ptrh.gameobjects.Square;
import com.ptrh.helpers.AssetLoader;

/**
 *
 * @author Patrick
 */
public class GameWorld {
    private DotCreator dotCreator;
    private Square[] squares;

    public GameWorld(float screenWRatio, float screenHRatio) {
        dotCreator = new DotCreator(screenWRatio, screenHRatio);
        squares = new Square[4];
        squares[0] = new Square(0, 0, 25, 25, AssetLoader.sqr);
        squares[1] = new Square(110, 0, 25, 25, AssetLoader.sqrB);
        squares[2] = new Square(0, 178, 25, 25, AssetLoader.sqrG);
        squares[3] = new Square(110, 178, 25, 25, AssetLoader.sqrR);
   }

    public void update(float delta) {
        dotCreator.update(delta);
    }

    public DotCreator getDotCreator() {
        return dotCreator;

    }
    
    public Square[] getSquares()
    {
        return squares;
    }
}

package com.ptrh.gameworld;

import com.badlogic.gdx.Gdx;
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
    private GameState currentState;

    public enum GameState {
        READY, RUNNING, GAMEOVER
    }
    
    public GameWorld(float screenWRatio, float screenHRatio) {
        currentState = GameState.READY;
        squares = new Square[4];
        squares[0] = new Square(0, 0, 25, 25, AssetLoader.sqr);
        squares[1] = new Square(110, 0, 25, 25, AssetLoader.sqrB);
        squares[2] = new Square(0, 178, 25, 25, AssetLoader.sqrG);
        squares[3] = new Square(110, 178, 25, 25, AssetLoader.sqrR);
        
        dotCreator = new DotCreator(screenWRatio, screenHRatio, this);
   }

    public void update(float delta) {
        
        switch (currentState) {
            case READY:
                updateReady(delta);
                Gdx.app.log("currentState", "Ready");
                break;
            case RUNNING:
                updateRunning(delta);
                Gdx.app.log("currentState", "Running");
                break;
        }
    }
    
    public void updateRunning(float delta) {
        dotCreator.update(delta);
    }
    
    public void updateReady(float delta) {
        //nothing
    }
    
    public DotCreator getDotCreator() {
        return dotCreator;

    }
    
    public Square[] getSquares()
    {
        return squares;
    }
    
    public void setGameOver() {
        currentState = GameState.GAMEOVER;
    }
    
    public boolean isReady() {
        if (currentState == GameState.READY)
            return true;
        return false;
    }
    
    public boolean isGameOver() {
        if (currentState == GameState.GAMEOVER)
            return true;
        return false;
    }
    
    public void start() {
        currentState = GameState.RUNNING;
    }
    
    public void restart() {
        currentState = GameState.READY;
        dotCreator.onRestart();
        currentState = GameState.RUNNING;
    }
}

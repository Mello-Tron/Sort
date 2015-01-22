package com.ptrh.gameworld;

import com.badlogic.gdx.Gdx;
import com.ptrh.gameobjects.DotCreator;
import com.ptrh.gameobjects.Square;
import com.ptrh.helpers.AssetLoader;
import com.ptrh.helpers.IOHandler;

/**
 *
 * @author Patrick
 */
public class GameWorld {
    private DotCreator dotCreator;
    private Square[] squares;
    private GameState currentState;
    private int score = 0;
    private IOHandler io;
    private float squareTimer;
    private float timerSetValue;

    public enum GameState {
        READY, RUNNING, GAMEOVER
    }
    
    public GameWorld(float screenWRatio, float screenHRatio, IOHandler io) {
        currentState = GameState.READY;
        squares = new Square[4];
        squares[0] = new Square(0, 0, 25, 25, AssetLoader.sqr);
        squares[1] = new Square(110, 0, 25, 25, AssetLoader.sqrB);
        squares[2] = new Square(0, 178, 25, 25, AssetLoader.sqrG);
        squares[3] = new Square(110, 178, 25, 25, AssetLoader.sqrR);
        
        dotCreator = new DotCreator(screenWRatio, screenHRatio, this);
        
        this.io = io;
        
        squareTimer = 20;
        timerSetValue = 20;
   }

    public void update(float delta) {
        
        switch (currentState) {
            case READY:
                updateReady(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
        }
    }
    
    public void updateRunning(float delta) {
        dotCreator.update(delta);
        squareTimer -= delta;
        checkSquareTime();
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
        io.writeHighScore(score);
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
    
    public boolean isRunning() {
        if (currentState == GameState.RUNNING)
            return true;
        return false;
    }
    
    public void start() {
        currentState = GameState.RUNNING;
    }
    
    public void restart() {
        currentState = GameState.READY;
        score = 0;
        dotCreator.onRestart();
        currentState = GameState.RUNNING;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public void addScore(int increment) {
        score += increment;
    }
    
    public void checkSquareTime() {
        int[] id = new int[4];
        
        if (squareTimer <= 0) {
            for (int i = 0; i < 4; i++)
            {
                if (squares[i].getX() == 0 && squares[i].getY() == 0)
                    id[0] = i;
                if (squares[i].getX() == 110 && squares[i].getY() == 0)
                    id[1] = i;
                if (squares[i].getX() == 110 && squares[i].getY() == 178)
                    id[2] = i;
                if (squares[i].getX() == 0 && squares[i].getY() == 178)
                    id[3] = i;
            }
            
            squares[id[0]].setX(110);
            squares[id[1]].setY(178);
            squares[id[2]].setX(0);
            squares[id[3]].setY(0);
            
            if (timerSetValue > 8)
                timerSetValue--;
            squareTimer = timerSetValue;
        }
    }
    
    public float getSquareTimer() {
        return squareTimer;
    }
}

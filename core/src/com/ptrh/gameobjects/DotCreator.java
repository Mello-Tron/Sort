package com.ptrh.gameobjects;

import com.ptrh.gameworld.GameWorld;
import com.ptrh.helpers.AssetLoader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Contains dots in an ArrayList that it cycles through to create the illusion of
 * many, many dots. Updates the creation of dots.
 * @author Patrick
 */
public class DotCreator {
    private ArrayList<Dot> dots = new ArrayList<Dot>();
    private Random r = new Random();
    private GameWorld world;
    private float timer;
    private float timerCap;
    private float timerCapChange;
    
    /**
     * Set up of preferences.
     * @param screenWRatio
     * @param screenHRatio
     * @param gameWorld 
     */
    public DotCreator(float screenWRatio, float screenHRatio, GameWorld gameWorld)
    {
        world = gameWorld;
        
        timer = 0;
        timerCap = 1.3f;
        timerCapChange = 0.05f;
        
        dots.add(new Dot(AssetLoader.dot, 60, screenWRatio, screenHRatio, this, gameWorld));
        dots.add(new Dot(AssetLoader.dot, 40, screenWRatio, screenHRatio, this, gameWorld));
        dots.add(new Dot(AssetLoader.dot, 80, screenWRatio, screenHRatio, this, gameWorld));
        dots.add(new Dot(AssetLoader.dotB, 60, screenWRatio, screenHRatio, this, gameWorld));
        dots.add(new Dot(AssetLoader.dotB, 40, screenWRatio, screenHRatio, this, gameWorld));
        dots.add(new Dot(AssetLoader.dotB, 80, screenWRatio, screenHRatio, this, gameWorld));
        dots.add(new Dot(AssetLoader.dotG, 60, screenWRatio, screenHRatio, this, gameWorld));
        dots.add(new Dot(AssetLoader.dotG, 40, screenWRatio, screenHRatio, this, gameWorld));
        dots.add(new Dot(AssetLoader.dotG, 80, screenWRatio, screenHRatio, this, gameWorld));
        dots.add(new Dot(AssetLoader.dotR, 60, screenWRatio, screenHRatio, this, gameWorld));
        dots.add(new Dot(AssetLoader.dotR, 80, screenWRatio, screenHRatio, this, gameWorld));
        dots.add(new Dot(AssetLoader.dotR, 40, screenWRatio, screenHRatio, this, gameWorld));
    }
    
    /**
     * Increases speed and creation speed of all dots over time. Adds dots to a timer.
     */
    public void update(float delta)
    {
        timer += delta;
        
        if (timer > timerCap) {
            ArrayList<Integer> choices = new ArrayList<Integer>();
            
            for (int i = 0; i < dots.size(); i++)
            {
                if (dots.get(i).getY() < -50.0f)
                    choices.add(i);
            }
            
            if (choices.size() > 0)
            {
                int choice = choices.get(r.nextInt(choices.size()));
                dots.get(choice).beginFalling();
            }
            
            timer = 0; //reset timer
            timerCap -= timerCapChange; //reduce cap time
            if (timerCap <= 1.2f)
                timerCapChange = .007f;
            if (timerCap <= 1.01f)
                timerCapChange = .006f;
            if (timerCap <= 0.75f)
                timerCapChange = .003f;
            if (timerCap <= 0.71f)
                timerCapChange = .001f;
            if (timerCap <= 0.68f)
                timerCapChange = .0001f;
        }
        
        for (int i = 0; i < dots.size(); i++)
            dots.get(i).update(delta);
    }
    
    /**
     * Returns true if a dot is already dragging.
     * @return 
     */
    public boolean areAnyDragging()
    {
        for (int i = 0; i < dots.size(); i++)
        {
            if (dots.get(i).getIsDragging() == true)
                return true;
        }
        
        return false;
    }
    
    public ArrayList<Dot> getDots()
    {
        return dots;
    }
    
    /**
     * Resets all settings when game restarts.
     */
    public void onRestart() {
        timer = 0;
        timerCap = 1.3f;
        timerCapChange = 0.05f;
        
        for (int i = 0; i < dots.size(); i++)
        {
            dots.get(i).setX(-100);
            dots.get(i).setY(-100);
            dots.get(i).setVelocityToZero();
            dots.get(i).setBeginVelocity(25);
            dots.get(i).resetBooleans();
        }
    }
    
    public float getTimerCap() {
        return timerCap;
    }
    
    public void incrementBeginVelocities() {
        for (int i = 0; i < dots.size(); i++)
        {
            dots.get(i).increaseBeginVelocity(0.3f);
            dots.get(i).increaseTouchRange(0.01f);
        }
    }
    
    /**
     * Checks if a dot will collide with another dot when returning to its lane.
     * @param y
     * @param resX
     * @param range
     * @return 
     */
    public boolean isDotInPath(float y, float resX, int range) {
        int count = 0;
        
        for (int i = 0; i < dots.size(); i++)
        {
            if (y > dots.get(i).getY() - range && y < dots.get(i).getY() + range)
            {
                if (resX == dots.get(i).getRestartX())
                    count++;
            }
        }
        return count > 1;
    }
}

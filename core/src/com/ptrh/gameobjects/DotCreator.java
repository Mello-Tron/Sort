package com.ptrh.gameobjects;

import com.ptrh.gameworld.GameWorld;
import com.ptrh.helpers.AssetLoader;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Patrick
 */
public class DotCreator {
    private ArrayList<Dot> dots = new ArrayList();
    private Random r = new Random();
    private GameWorld world;
    private float timer;
    private float timerCap;
    private float timerCapChange;
    
    public DotCreator(float screenWRatio, float screenHRatio, GameWorld gameWorld)
    {
        world = gameWorld;
        
        timer = 0;
        timerCap = 2.1f;
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
     * Update all dots.
     */
    public void update(float delta)
    {
        timer += delta;
        
        if (timer > timerCap) {
            ArrayList<Integer> choices = new ArrayList();
            
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
            if (timerCap < 1.2f)
                timerCapChange = .005f;
            if (timerCap < 0.8f)
                timerCapChange = .002f;

        }
        
        for (int i = 0; i < dots.size(); i++)
            dots.get(i).update(delta);
    }
    
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
    
    public void onRestart() {
        timer = 0;
        timerCap = 2.1f;
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
            dots.get(i).increaseTouchRange(0.1f);
        }
    }
    
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

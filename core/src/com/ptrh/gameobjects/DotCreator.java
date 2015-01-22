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
    
    public DotCreator(float screenWRatio, float screenHRatio, GameWorld gameWorld)
    {
        world = gameWorld;
        
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
        for (int i = 0; i < dots.size(); i++)
        {
            if (r.nextInt(400) == 0)
                if (dots.get(i).getY() < -10)
                    dots.get(i).beginFalling();
            dots.get(i).update(delta);
        }
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
        for (int i = 0; i < dots.size(); i++)
        {
            dots.get(i).setX(-100);
            dots.get(i).setY(-100);
            dots.get(i).setVelocityToZero();
        }
    }
}

package com.ptrh.gameobjects;

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
    
    public DotCreator(float screenWRatio, float screenHRatio)
    {
        dots.add(new Dot(AssetLoader.dot, 60, screenWRatio, screenHRatio, this));
        dots.add(new Dot(AssetLoader.dot, 40, screenWRatio, screenHRatio, this));
        dots.add(new Dot(AssetLoader.dot, 80, screenWRatio, screenHRatio, this));
        dots.add(new Dot(AssetLoader.dotB, 60, screenWRatio, screenHRatio, this));
        dots.add(new Dot(AssetLoader.dotB, 40, screenWRatio, screenHRatio, this));
        dots.add(new Dot(AssetLoader.dotB, 80, screenWRatio, screenHRatio, this));
        dots.add(new Dot(AssetLoader.dotG, 60, screenWRatio, screenHRatio, this));
        dots.add(new Dot(AssetLoader.dotG, 40, screenWRatio, screenHRatio, this));
        dots.add(new Dot(AssetLoader.dotG, 80, screenWRatio, screenHRatio, this));
        dots.add(new Dot(AssetLoader.dotR, 60, screenWRatio, screenHRatio, this));
    }
    
    /**
     * Update all dots.
     */
    public void update(float delta)
    {
        for (int i = 0; i < dots.size(); i++)
        {
            if (r.nextInt(200) == 0)
                if (dots.get(i).getY() > 220)
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
}

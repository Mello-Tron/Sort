package com.ptrh.gameobjects;

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
        for (int i = 0; i < 10; i++)
            dots.add(new Dot(60, screenWRatio, screenHRatio));
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
    
    public ArrayList<Dot> getDots()
    {
        return dots;
    }
}

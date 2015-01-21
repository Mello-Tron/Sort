package com.ptrh.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.ptrh.helpers.InputHandler;

/**
 *
 * @author Patrick
 */
public class Dot {
    private Vector2 position;
    private Vector2 velocity;
    private int width;
    private int height;
    private boolean isDragging;
    private float screenWRatio;
    private float screenHRatio;
    private float beginX;
    
    public Dot(float beginX, float screenWRatio, float screenHRatio) {
        this.width = 12;
        this.height = 12;
        position = new Vector2(300, 300);
        velocity = new Vector2(0, 0);
        isDragging = false;
        this.screenWRatio = screenWRatio;
        this.screenHRatio = screenHRatio;
        this.beginX = beginX;
    }
    
    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
        
        if (position.y > 220)
            velocity.y = 0;
    }

    public void drag(int screenX, int screenY)
    {
        if (isDragging)
        {
            position.x = (screenX * screenWRatio) - (width / 2);
            position.y = (screenY * screenHRatio) - (height / 2);
        }
    }
    
    public void onClick(int screenX, int screenY) {
        float x = (screenX * screenWRatio);
        float y = (screenY * screenHRatio);
        
        if (x >= position.x && x <= position.x + width)
        {
            if (y >= position.y && y <= position.y + height)
            {
                isDragging = true;
                velocity.y = 0;
            }
        }
    }
    
    public void doneDragging() {
        if (isDragging == true)
        {
            isDragging = false;
            position.x = 300;
            position.y = 300;
        }
    }
    
    public void beginFalling() {
        position.x = beginX;
        position.y = -10;
        velocity.y = 35;
    }
    
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}

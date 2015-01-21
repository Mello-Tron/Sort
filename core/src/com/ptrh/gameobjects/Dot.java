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
    
    public Dot(float x, float y, int width, int height, float screenWRatio, float screenHRatio) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0); //CHANGE Y SPEED HERE!!!
        isDragging = false;
        this.screenWRatio = screenWRatio;
        this.screenHRatio = screenHRatio;
    }
    
    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
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
            }
        }
    }

    public void doneDragging() {
        isDragging = false;
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

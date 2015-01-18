package com.ptrh.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Patrick
 */
public class Dot {
    private Vector2 position;
    private Vector2 velocity;
    private int width;
    private int height;
    
    public Dot(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 15); //CHANGE Y SPEED HERE!!!
    }
    
    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
    }

    public void onClick() {
        velocity.y = 0; //STOP Dot
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

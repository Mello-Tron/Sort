package com.ptrh.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Patrick
 */
public class Square {
    private Vector2 position;
    private int width;
    private int height;
    private TextureRegion textureRegion;
    
    public Square(float x, float y, int width, int height, TextureRegion tregion) {
        position = new Vector2(x,y);
        this.width = width;
        this.height = height;
        textureRegion = tregion;
    }
    
    public float getX()
    {
        return position.x;
    }
    
    public float getY()
    {
        return position.y;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public TextureRegion getTextureRegion()
    {
        return textureRegion;
    }
}

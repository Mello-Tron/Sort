package com.ptrh.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ptrh.gameworld.GameSound;
import com.ptrh.gameworld.GameWorld;
import static com.ptrh.helpers.AssetLoader.*;
import static java.lang.Math.abs;

/**
 * 
 * @author Patrick
 */
public class Dot {
    private Vector2 position;
    private Vector2 velocity;
    private float beginVelocity;
    
    private static int width = 12;
    private static int height = 12;
    private static int touchRange = 5;
    
    private boolean isDragging;
    private boolean isReturning;
    
    private float screenWRatio;
    private float screenHRatio;
    private float restartX;
    private float ghostX;
    private float ghostY;
    private float ghostVelocityY;
    
    private TextureRegion textureRegion;
    private DotCreator dotCreator;
    private GameWorld world;
    private Square[] squares;
    
    /**
     * Dot's starting parameters
     */
    public Dot(TextureRegion textureRegion, float restartX, float screenWRatio, float screenHRatio, DotCreator dotCreator, GameWorld gameWorld) {
        this.restartX = restartX;
        position = new Vector2(-100, -100);
        velocity = new Vector2(0, 0);
        beginVelocity = 25;
        
        isDragging = false;
        isReturning = false;
        
        this.screenWRatio = screenWRatio;
        this.screenHRatio = screenHRatio;
        
        this.textureRegion = textureRegion;
        this.dotCreator = dotCreator;
        world = gameWorld;
        squares = gameWorld.getSquares();
    }
    
    public void update(float delta) {
        if (isReturning) {
            
            if (dotCreator.isDotInPath(position.y, restartX, 16))
            {
                float tweenV = (ghostVelocityY - velocity.y) * 0.2f;
                velocity.y -= tweenV;
            }
            else {
                float tweenX = (ghostX - position.x) * 0.1f;
                //float tweenY = (ghostY - position.y) * 0.1f;
                position.x += tweenX;
                //position.y += tweenY;
            
                float tweenV = (ghostVelocityY - velocity.y) * 0.9f;
                velocity.y += tweenV;
                
                if (abs(ghostX - position.x) < 0.25f) {
                    isReturning = false;
                    position.x = ghostX;
                    //position.y = ghostY;
                    velocity.y = ghostVelocityY;
                }
            }
        }
        
        if (!isDragging)
            position.add(velocity.cpy().scl(delta));
        
        if (position.y >= 203 - height && !isDragging) {
            velocity.y = 0;
            world.setGameOver();
        }
    }

    public void drag(int screenX, int screenY) {
        if (isDragging) {
            position.x = (screenX * screenWRatio) - (width / 2);
            position.y = (screenY * screenHRatio) - (height / 2);
        }
    }
    
    public void onClick(int screenX, int screenY) {
        float x = (screenX * screenWRatio);
        float y = (screenY * screenHRatio);
        
        if (x >= position.x - touchRange && x <= position.x + width + touchRange) {
            if (y >= position.y - touchRange && y <= position.y + height + touchRange) {
                if (!dotCreator.areAnyDragging()) {
                    ghostX = position.x;
                    ghostY = position.y;
                    ghostVelocityY = velocity.y;
                    
                    isDragging = true;
                    isReturning = false;
                    velocity.y = 0;
                }
            }
        }
    }
    
    public void doneDragging() {
        if (isDragging)
        {
            //do this before changing position
            if (inSquare()) {
                world.addScore(1);
                position.x = -100;
                position.y = -100;
                setVelocityToZero();
                isDragging = false;
                GameSound.playPop();
                dotCreator.incrementBeginVelocities();
            }
            else {
                isDragging = false;
                isReturning = true;
            }
        }
    }
    
    public void beginFalling() {
        position.x = restartX;
        position.y = -10;
        velocity.y = beginVelocity; //40 60
    }
    
    public boolean inSquare() {
        float x = position.x;
        float y = position.y;
        float leftX;
        float rightX;
        float topY;
        float bottomY;
        
        for (int i = 0; i < 4; i++) 
        {
            leftX = squares[i].getX();
            rightX = squares[i].getX() + squares[i].getWidth();
            topY = squares[i].getY();
            bottomY = squares[i].getY() + squares[i].getHeight();
            
            if (textureRegion == dot && squares[i].getTextureRegion() == sqr)
            {
                if (x + width >= leftX && x <= rightX && y + height >= topY && y <= bottomY)
                    return true;
            }
            else if (textureRegion == dotB && squares[i].getTextureRegion() == sqrB)
            {
                if (x + width >= leftX && x <= rightX && y + height >= topY && y <= bottomY)
                    return true;
            }
            else if (textureRegion == dotG && squares[i].getTextureRegion() == sqrG)
            {
                if (x + width >= leftX && x <= rightX && y + height >= topY && y <= bottomY)
                    return true;
            }
            else if (textureRegion == dotR && squares[i].getTextureRegion() == sqrR)
            {
                if (x + width >= leftX && x <= rightX && y + height >= topY && y <= bottomY)
                    return true;
            }
        }
  
        return false;
    }
    
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }
    
    public void setX(float x) {
        position.x = x;
    }
    
    public void setY(float y) {
        position.y = y;
    }

    public void setVelocityToZero() {
        velocity.x = 0;
        velocity.y = 0;
    }
    
    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
    
    public boolean getIsDragging() {
        return isDragging;
    }
    
    public TextureRegion getTextureRegion()
    {
        return textureRegion;
    }
    
    public void increaseBeginVelocity(float increment) {
        beginVelocity += increment;
    }
    
    public void setBeginVelocity(float v) {
        beginVelocity = v;
    }

    public void resetBooleans() {
        isDragging = false;
        isReturning = false;
    }
    
    public float getRestartX() {
        return restartX;
    }
}

package com.ptrh.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.ptrh.helpers.InputHandler;
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
    private int width;
    private int height;
    private boolean isDragging;
    private boolean isReturning;
    private float screenWRatio;
    private float screenHRatio;
    private float beginX;
    private TextureRegion textureRegion;
    private DotCreator myDotCreator;
    private GameWorld myGameWorld;
    private Square[] mySquares;
    private int touchRange = 5;
    private float ghostX;
    private float ghostY;
    private float ghostVelocityY;
    
    public Dot(TextureRegion tr, float beginX, float screenWRatio, float screenHRatio, DotCreator myDotCreator, GameWorld gameWorld) {
        this.width = 12;
        this.height = 12;
        position = new Vector2(-100, -100);
        velocity = new Vector2(0, 0);
        isDragging = false;
        isReturning = false;
        this.screenWRatio = screenWRatio;
        this.screenHRatio = screenHRatio;
        this.beginX = beginX;
        textureRegion = tr;
        this.myDotCreator = myDotCreator;
        myGameWorld = gameWorld;
        mySquares = gameWorld.getSquares();
    }
    
    public void update(float delta) {
        if (!isReturning)
            position.add(velocity.cpy().scl(delta));
        else {
            float tweenX = (ghostX - position.x) * 0.1f;
            float tweenY = (ghostY - position.y) * 0.1f;
            position.x += tweenX;
            position.y += tweenY;
            
            if (abs(ghostX - position.x) < 2 && abs(ghostY - position.y) < 2)
            {
                isReturning = false;
                position.x = ghostX;
                position.y = ghostY;
                velocity.y = ghostVelocityY;
            }
        }
        if (position.y >= 203 - height && !isDragging)
        {
            velocity.y = 0;
            myGameWorld.setGameOver();
        }
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
        
        if (x >= position.x - touchRange && x <= position.x + width + touchRange)
        {
            if (y >= position.y - touchRange && y <= position.y + height + touchRange)
            {
                if (!myDotCreator.areAnyDragging())
                {
                    ghostX = position.x;
                    ghostY = position.y;
                    ghostVelocityY = velocity.y;
                    
                    isDragging = true;
                    velocity.y = 0;
                }
            }
        }
    }
    
    public void doneDragging() {
        if (isDragging == true)
        {
            //do this before changing position
            if (inSquare()) {
                myDotCreator.addScore(1);
                position.x = -100;
                position.y = -100;
                setVelocityToZero();
                isDragging = false;
                GameSound.playPing();
            }
            else {
                isDragging = false;
                isReturning = true;
            }
        }
    }
    
    public void beginFalling() {
        position.x = beginX;
        position.y = -10;
        velocity.y = 40;
    }
    
    public boolean inSquare()
    {
        float x = position.x;
        float y = position.y;
        float leftX;
        float rightX;
        float topY;
        float bottomY;
        
        for (int i = 0; i < 4; i++)
        {
            leftX = mySquares[i].getX();
            rightX = mySquares[i].getX() + mySquares[i].getWidth();
            topY = mySquares[i].getY();
            bottomY = mySquares[i].getY() + mySquares[i].getHeight();
            
            if (textureRegion == dot && mySquares[i].getTextureRegion() == sqr)
            {
                if (x + width >= leftX && x <= rightX && y + height >= topY && y <= bottomY)
                    return true;
            }
            else if (textureRegion == dotB && mySquares[i].getTextureRegion() == sqrB)
            {
                if (x + width >= leftX && x <= rightX && y + height >= topY && y <= bottomY)
                    return true;
            }
            else if (textureRegion == dotG && mySquares[i].getTextureRegion() == sqrG)
            {
                if (x + width >= leftX && x <= rightX && y + height >= topY && y <= bottomY)
                    return true;
            }
            else if (textureRegion == dotR && mySquares[i].getTextureRegion() == sqrR)
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
}

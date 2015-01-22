package com.ptrh.helpers;

import com.badlogic.gdx.InputProcessor;
import com.ptrh.gameobjects.DotCreator;
import com.ptrh.gameworld.GameWorld;

/**
 *
 * @author Patrick
 */
public class InputHandler implements InputProcessor{
    private DotCreator myDotCreator;
    private GameWorld myWorld;
    
    public InputHandler(GameWorld gameWorld)
    {
        myWorld = gameWorld;
        myDotCreator = myWorld.getDotCreator();
    }
    
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (myWorld.isReady())
            myWorld.start();
        
        for (int i = 0; i < myDotCreator.getDots().size(); i++)
        {
            myDotCreator.getDots().get(i).onClick(screenX, screenY);
        }
        
        if (myWorld.isGameOver()) {
            myWorld.restart();
        }
        
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (int i = 0; i < myDotCreator.getDots().size(); i++)
        {
            myDotCreator.getDots().get(i).doneDragging();
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        for (int i = 0; i < myDotCreator.getDots().size(); i++)
        {
            myDotCreator.getDots().get(i).drag(screenX, screenY);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

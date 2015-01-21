package com.ptrh.helpers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Circle;
import com.ptrh.gameobjects.Dot;
import com.ptrh.gameobjects.DotCreator;

/**
 *
 * @author Patrick
 */
public class InputHandler implements InputProcessor{
    private DotCreator myDotCreator;
    
    public InputHandler(DotCreator dotCreator)
    {
        myDotCreator = dotCreator;
    }
    
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (int i = 0; i < myDotCreator.getDots().size(); i++)
        {
            myDotCreator.getDots().get(i).onClick(screenX, screenY);
        }
        return false;
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

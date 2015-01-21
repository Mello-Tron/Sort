package com.ptrh.helpers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Circle;
import com.ptrh.gameobjects.Dot;

/**
 *
 * @author Patrick
 */
public class InputHandler implements InputProcessor{
    private Dot myDot;
    
    public InputHandler(Dot dot)
    {
        myDot = dot;
    }
    
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        myDot.onClick(screenX, screenY);
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
        myDot.doneDragging();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        myDot.drag(screenX, screenY);
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

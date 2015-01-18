package com.ptrh.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.ptrh.gameworld.GameRenderer;
import com.ptrh.gameworld.GameWorld;
import com.ptrh.helpers.InputHandler;

/**
 *
 * @author Patrick
 */
public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;
    
    public GameScreen() {
        Gdx.app.log("GameScreen", "Attached");
        
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 272;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        
        world = new GameWorld();
        renderer = new GameRenderer(world, (int) gameHeight);
        
        Gdx.input.setInputProcessor(new InputHandler(world.getDot()));
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");     
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");        
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");       
    }

    @Override
    public void dispose() {
        //blank
    }
}

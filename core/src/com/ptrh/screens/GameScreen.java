package com.ptrh.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.ptrh.gameworld.GameRenderer;
import com.ptrh.gameworld.GameWorld;
import com.ptrh.helpers.InputHandler;
import com.ptrh.gameworld.GameSound;

/**
 *
 * @author Patrick
 */
public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    private GameSound sound;
    private float runTime;
    
    public GameScreen() {
        Gdx.app.log("GameScreen", "Attached");
        
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 135;
        float gameHeight = 203;
        float screenWRatio = (gameWidth / screenWidth);
        float screenHRatio = (gameHeight / screenHeight);
        
        world = new GameWorld(screenWRatio, screenHRatio);
        renderer = new GameRenderer(world, (int) gameWidth, (int) gameHeight);
        
        Gdx.input.setInputProcessor(new InputHandler(world));
        
        sound = new GameSound();
        sound.onCreate();
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
        sound.dispose();
    }
}

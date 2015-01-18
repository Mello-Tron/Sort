package com.ptrh.sort;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.ptrh.helpers.AssetLoader;
import com.ptrh.screens.GameScreen;

public class SortGame extends Game {
    @Override
    public void create() {
        Gdx.app.log("SortGame", "created");
        AssetLoader.load();
        setScreen(new GameScreen());
    }
    
    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}

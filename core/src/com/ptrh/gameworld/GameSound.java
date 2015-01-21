package com.ptrh.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 *
 * @author Patrick
 */
public class GameSound {
    Music StrawFields = Gdx.audio.newMusic(Gdx.files.internal("data/SFields.mp3"));
    
    public void onCreate()
    {
        StrawFields.play();
    }
    
    public void dispose()
    {
        StrawFields.dispose();
    }
}

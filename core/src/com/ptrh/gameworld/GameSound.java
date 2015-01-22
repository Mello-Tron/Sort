package com.ptrh.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 *
 * @author Patrick
 */
public class GameSound {
    static Music StrawFields = Gdx.audio.newMusic(Gdx.files.internal("data/SFields.mp3"));
    static Sound pop = Gdx.audio.newSound(Gdx.files.internal("data/pop.mp3"));
    
    public void onCreate()
    {
        //StrawFields.play();
    }
    
    public static void playPop()
    {
        pop.play(0.15f);
    }
    
    public static void dispose()
    {
        StrawFields.dispose();
        pop.dispose();
    }
}

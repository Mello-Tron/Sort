package com.ptrh.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

/**
 * Handles high score input and output.
 * @author Patrick
 */
public class IOHandler {
    FileHandle scoreHandle = Gdx.files.internal("data/highscore.txt");
    Preferences prefs = Gdx.app.getPreferences("Sort Preferences");
    
    /**
     * Writes high score to preferences and retrieves the high score.
     * @param score 
     */
    public void writeHighScore(int score) {
//        String stringFromFile = scoreHandle.readString();
//        int scoreFromFile = Integer.parseInt(stringFromFile);
//        
//        if (score > scoreFromFile) {
//            String temp = String.format("%d", score);
//            scoreHandle.writeString(temp, false);
//        }
        
        int scoreFromFile = prefs.getInteger("highscore", 0);
        
        if (score > scoreFromFile) {
            prefs.putInteger("highscore", score);
            prefs.flush();
        }
    }
    
    public int getHighScore() {
        return prefs.getInteger("highscore", 0);
    }
}

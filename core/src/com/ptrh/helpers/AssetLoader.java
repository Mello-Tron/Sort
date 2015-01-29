package com.ptrh.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Loads in local assets and gives them variable names.
 * @author Patrick
 */
public class AssetLoader {
    public static Texture dotTexture;
    public static Texture squareTexture;
    
    public static TextureRegion dot;
    public static TextureRegion dotB;
    public static TextureRegion dotG;
    public static TextureRegion dotR;
    public static TextureRegion sqr;
    public static TextureRegion sqrB;
    public static TextureRegion sqrG;
    public static TextureRegion sqrR;
    
    public static BitmapFont font, shadow;
    
    public static void load() {
        dotTexture = new Texture(Gdx.files.internal("data/dots.png"));
        dotTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        dot = new TextureRegion(dotTexture, 0, 0, 39, 36);
        dotB = new TextureRegion(dotTexture, 51, 0, 39, 36);
        dotG = new TextureRegion(dotTexture, 101, 0, 39, 36);
        dotR = new TextureRegion(dotTexture, 151, 0, 39, 36);
        
        squareTexture = new Texture(Gdx.files.internal("data/squares.png"));
        squareTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        sqr = new TextureRegion(squareTexture, 0, 0, 71, 72);
        sqrB = new TextureRegion(squareTexture, 73, 0, 71, 72);
        sqrG = new TextureRegion(squareTexture, 145, 0, 71, 72);
        sqrR = new TextureRegion(squareTexture, 216, 0, 71, 72);
        
        font = new BitmapFont(Gdx.files.internal("data/sortFont.fnt"));
        font.setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.setScale(.25f, -.25f);
    }

    public static void dispose() {
        dotTexture.dispose();
        squareTexture.dispose();
        
        font.dispose();
        shadow.dispose();
    }
}

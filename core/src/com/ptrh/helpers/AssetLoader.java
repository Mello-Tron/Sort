package com.ptrh.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Patrick
 */
public class AssetLoader {
    public static Texture dotTexture;
    public static TextureRegion dot;
    public static TextureRegion dotB;
    public static TextureRegion dotG;
    public static TextureRegion dotR;
    
    public static void load() {
        dotTexture = new Texture(Gdx.files.internal("data/dots.png"));
        dotTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        dot = new TextureRegion(dotTexture, 0, 0, 39, 36);
        dotB = new TextureRegion(dotTexture, 40, 0, 39, 36);
        dotG = new TextureRegion(dotTexture, 80, 0, 39, 36);
        dotR = new TextureRegion(dotTexture, 120, 0, 39, 36);
    }

    public static void dispose() {
        dotTexture.dispose();
    }
}

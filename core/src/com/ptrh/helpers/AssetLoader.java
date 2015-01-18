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
    
    public static void load() {
        dotTexture = new Texture(Gdx.files.internal("data/dots.png"));
        dotTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        dot = new TextureRegion(dotTexture, 0, 0, 13, 12);
        dot.flip(false, true);
    }

    public static void dispose() {
        dotTexture.dispose();
    }
}

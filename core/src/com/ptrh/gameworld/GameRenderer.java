package com.ptrh.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.ptrh.gameobjects.Dot;
import com.ptrh.gameobjects.DotCreator;
import com.ptrh.gameobjects.Square;
import com.ptrh.helpers.AssetLoader;

/**
 *
 * @author Patrick
 */
public class GameRenderer {
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    
    private SpriteBatch batcher;
    
    private int gameHeight;
    private int gameWidth;
    
    public GameRenderer(GameWorld world, int gameWidth, int gameHeight)
    {
        myWorld = world;
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
        
        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidth, gameHeight);
        
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }
    
    public void render(float runTime) {
        // We will move these outside of the loop for performance later.
        DotCreator myDotCreator = myWorld.getDotCreator();

        // Fill the entire screen with black, to prevent potential flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin ShapeRenderer
        shapeRenderer.begin(ShapeType.Filled);

        // Draw Background color
        shapeRenderer.setColor(255.0f, 255.0f, 255.0f, 1);
        shapeRenderer.rect(0, 0, gameWidth, gameHeight);

        // End ShapeRenderer
        shapeRenderer.end();

        // Begin SpriteBatch
        batcher.begin();
        // Disable transparency
        // This is good for performance when drawing images that do not require
        // transparency.
        Square[] mySquares = myWorld.getSquares();
        for (int i = 0; i < 4; i++)
        {
            batcher.draw(mySquares[i].getTextureRegion(), mySquares[i].getX(), mySquares[i].getY(), mySquares[i].getWidth(), mySquares[i].getHeight());
        }
        
        //draw Score
        String score = myWorld.getDotCreator().getScore() + "";
        //AssetLoader.shadow.draw(batcher, score, 20, 20);
        AssetLoader.font.draw(batcher, score, 20, 20);
        
        batcher.disableBlending();
        //

        // The bird needs transparency, so we enable that again.
        batcher.enableBlending();

        // Draw bird at its coordinates. Retrieve the Animation object from
        // AssetLoader
        // Pass in the runTime variable to get the current frame.
        for (int i = 0; i < myDotCreator.getDots().size(); i++)
        {
            batcher.draw(myDotCreator.getDots().get(i).getTextureRegion(), myDotCreator.getDots().get(i).getX(), 
                    myDotCreator.getDots().get(i).getY(), myDotCreator.getDots().get(i).getWidth(), 
                    myDotCreator.getDots().get(i).getHeight());
        }

        // End SpriteBatch
        batcher.end();
    }
}

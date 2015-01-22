package com.ptrh.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.ptrh.gameobjects.DotCreator;
import com.ptrh.gameobjects.Square;
import com.ptrh.helpers.AssetLoader;
import com.ptrh.helpers.IOHandler;

/**
 *
 * @author Patrick
 */
public class GameRenderer {
    private GameWorld world;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    DotCreator myDotCreator;
    
    private SpriteBatch batcher;
    
    private int gameHeight;
    private int gameWidth;
    
    private IOHandler io;
    
    int timerDisplay;
    
    public GameRenderer(GameWorld world, int gameWidth, int gameHeight, IOHandler io)
    {
        this.world = world;
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
        
        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidth, gameHeight);
        
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        
        myDotCreator = world.getDotCreator();
        
        this.io = io;
    }
    
    public void render(float runTime) {
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

        if (world.isRunning())
        {
            // Begin SpriteBatch
            batcher.begin();
            // Disable transparency
            // This is good for performance when drawing images that do not require
            // transparency.
            batcher.disableBlending();
            
            Square[] mySquares = world.getSquares();
            for (int i = 0; i < 4; i++)
            {
                batcher.draw(mySquares[i].getTextureRegion(), mySquares[i].getX(), mySquares[i].getY(), mySquares[i].getWidth(), mySquares[i].getHeight());
            }

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

            //draw Score
            String score = world.getScore() + "";
            //AssetLoader.shadow.draw(batcher, score, 20, 20);
            AssetLoader.font.setScale(0.2f,-0.2f);
            AssetLoader.font.draw(batcher, "Score:", 31, 7);
            AssetLoader.font.setScale(0.25f,-0.25f);
            AssetLoader.font.draw(batcher, score, 55, 5);
            
            //TESTING TIMECAP
//            AssetLoader.font.setScale(0.25f,-0.25f);
//            String temp = String.format("%f", myDotCreator.getTimerCap());
//            AssetLoader.font.draw(batcher, temp, 5, 100);
            
            //TESTING SQUARETIMER
            if (world.getSquareTimer() < 6) {
                AssetLoader.font.setScale(0.8f,-0.8f);
                timerDisplay = (int) world.getSquareTimer();
                String temp = String.format("%d", timerDisplay);
                AssetLoader.font.draw(batcher, temp, (gameWidth / 2) - 10, (gameHeight / 2) - 38);
            }
            
            // End SpriteBatch
            batcher.end();
        }
        else if (world.isGameOver()) {
            batcher.begin();
            batcher.enableBlending();
            
            AssetLoader.font.setScale(0.4f,-0.4f);
            AssetLoader.font.draw(batcher, "Game Over", (gameWidth / 2) - 40, (gameHeight / 2) - 40);
            
            AssetLoader.font.setScale(0.2f,-0.2f);
            AssetLoader.font.draw(batcher, "High Score:", (gameWidth / 2) - 30, (gameHeight / 2));
            AssetLoader.font.setScale(0.3f,-0.3f);
            String highscore = String.format("%d", io.getHighScore());
            AssetLoader.font.draw(batcher, highscore, (gameWidth / 2) + 17, (gameHeight / 2) - 3);
            
            String score = world.getScore() + "";
            AssetLoader.font.setScale(0.15f,-0.15f);
            AssetLoader.font.draw(batcher, "Your Score:", (gameWidth / 2) - 20, (gameHeight / 2) + 20);
            AssetLoader.font.setScale(0.175f,-0.175f);
            AssetLoader.font.draw(batcher, score, (gameWidth / 2) + 13, (gameHeight / 2) + 19);
            
            batcher.end();
        }
        else if (world.isReady()) {
            batcher.begin();
            batcher.enableBlending();
            
            AssetLoader.font.setScale(0.35f,-0.35f);
            AssetLoader.font.draw(batcher, "Click the screen", (gameWidth / 2) - 50, (gameHeight / 2) - 40);
            AssetLoader.font.draw(batcher, "to begin!", (gameWidth / 2) - 30, (gameHeight / 2) - 15);
            
            batcher.end();
        }
        
    }
}

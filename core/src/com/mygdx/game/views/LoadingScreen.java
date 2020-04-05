package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mygdx.game.MyGdxGame;

import static com.badlogic.gdx.Gdx.gl20;

public class LoadingScreen implements Screen {

    private MyGdxGame parent;
    private SpriteBatch sb;
    private TextureAtlas atlas;
    private TextureAtlas.AtlasRegion title;
    private TextureAtlas.AtlasRegion dash;


    public final int IMAGE = 0;		// loading images
    public final int FONT = 1;		// loading fonts
    public final int PARTY = 2;		// loading particle effects
    public final int SOUND = 3;		// loading sounds
    public final int MUSIC = 4;		// loading music

    private int currentLoadingStage = 0;

    // timer for exiting loading screen
    public float countDown = 5f; // 5 seconds of waiting before menu screen

    public LoadingScreen(MyGdxGame g) {
        parent = g;
        System.out.println("|Loading screen");
        System.out.println("|___         ");
        System.out.println("    |");
        sb = new SpriteBatch();
        System.out.println("    |-Create new SpriteBatch\n    |");
        sb.setBlendFunction(GL20.GL_SRC_ALPHA , GL20.GL_ONE);
        System.out.println("    |-Set a blend mode\n    |");

    }

    @Override
    public void show() {
        // load loading images and wait until finished
        parent.assManager.queueAddLoadingImages();
        parent.assManager.manager.finishLoading();


// get images used to display loading progress
        atlas = parent.assManager.manager.get("output/Game.atlas");
        title = atlas.findRegion("player");
//        dash = atlas.findRegion("loading-dash");

// initiate queueing of images but don't start loading
        parent.assManager.queueAddImages();
        System.out.println("    |-Loading images....\n    |");

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1); //  clear the screen
        System.out.println("    |-clearning the screen\n    |");
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        System.out.println("    |-set blend mode\n    |");
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        System.out.println("    |-next step clear screen\n    |");

        // start SpriteBatch and draw the logo
        System.out.println(title);
        sb.begin();
        System.out.println("    |___");
        System.out.println("        |-Draw:run ");
        sb.draw(title, 135, 250);
        System.out.println("        |-Draw:draw");
        sb.end();
        System.out.println("        |-Draw:end");
        System.out.println("     ___");
        System.out.println("    |");


        // check if the asset manager has finished loading
        if (parent.assManager.manager.update()) { // Load some, will return true if done loading
            currentLoadingStage+= 1;
            switch(currentLoadingStage){
                case FONT:
                    System.out.println("Loading fonts....");
                    parent.assManager.queueAddFonts(); // first load done, now start fonts
                    break;
                case PARTY:
                    System.out.println("Loading Particle Effects....");
                    parent.assManager.queueAddParticleEffects(); // fonts are done now do party effects
                    break;
                case SOUND:
                    System.out.println("Loading Sounds....");
                    parent.assManager.queueAddSounds();
                    break;
                case MUSIC:
                    System.out.println("Loading fonts....");
                    parent.assManager.queueAddMusic();
                    break;
                case 5:
                    System.out.println("Finished"); // all done
                    break;
            }
            if (currentLoadingStage >5){
                countDown -= delta;  // timer to stay on loading screen for short preiod once done loading
                currentLoadingStage = 5;  // cap loading stage to 5 as will use later to display progress bar anbd more than 5 would go off the screen
                if(countDown < 0){ // countdown is complete
                    parent.changeScreen(MyGdxGame.MENU);  /// go to menu screen
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

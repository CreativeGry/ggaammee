package com.mygdx.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;

public class MenuScreen implements Screen {

    private MyGdxGame parent;
    private Stage stage;
    private Skin skin;

    public MenuScreen(MyGdxGame g) {
        parent = g;
        stage = new Stage(new ScreenViewport());

        parent.assManager.queueAddSkin();
        parent.assManager.manager.finishLoading();
        skin = parent.assManager.manager.get("input/game/skins/glass/glassy-ui.json");

        stage.act(Math.min(Gdx.graphics.getDeltaTime() , 1 / 30f));
        stage.draw();


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        TextButton newGame = new TextButton("New Game" , skin);
        TextButton prefferences = new TextButton("Prefferences" , skin);
        TextButton exit = new TextButton("Exit" , skin);
        table.add(newGame).fillX().uniformX();
        table.row().pad(10 , 0 , 10 , 0);
        table.add(prefferences).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();
        System.out.println("MEnu screen");
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(MyGdxGame.APPLICATION);
                stage.dispose();
            }
        });
        prefferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(MyGdxGame.PREFERENCES);

            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0 , 0 , 0 , 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime() , 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width , height , true);
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
        stage.dispose();
    }
}

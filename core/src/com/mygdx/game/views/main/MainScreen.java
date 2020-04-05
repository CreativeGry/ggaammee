package com.mygdx.game.views.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.views.main.G_Model;

public class MainScreen implements Screen {

    private MyGdxGame parent;
    private OrthographicCamera cam;
    private G_Model model;
    private Box2DDebugRenderer debugRenderer;

    public MainScreen(MyGdxGame g) {
        parent = g;
        model = new G_Model();
        cam = new OrthographicCamera(32,24);
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        model.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(model.world, cam.combined);
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
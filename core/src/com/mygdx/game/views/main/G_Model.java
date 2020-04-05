package com.mygdx.game.views.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controller.KeyBoardController;
import com.mygdx.game.loader.AssestManager;

public class G_Model {

    public static final int KEYS_SOUND = 0;
    public static final int PICK_SOUND = 1;
    public World world;
    private Body bp; //player
    private Body floor; //floor
    private Body stati; //static body
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    public boolean isSwimming = false;
    public Body player;
    private KeyBoardController controller;
    private AssestManager assestManager;
    private Sound pick;
    private MainScreen parent;

    //private Sound keys;



    public G_Model(KeyBoardController controller , OrthographicCamera cam , AssestManager ass , MainScreen mainScreen)
    {
        parent = mainScreen;
        assestManager = ass;
        world = new World(new Vector2(0 , -10f) , true);
        world.setContactListener(new  ContactListener(this));
        camera = cam;
        this.controller = controller;
        createFloor();

        // get our body factory singleton and store it in bodyFactory
        BodyFactory bodyFactory = BodyFactory.getInstance(world);
        //assestManager.queueAddSounds();
        player  = bodyFactory.makeBoxPolyBody(1 , 1, 2, 2 , BodyFactory.WOOD , BodyDef.BodyType.DynamicBody , false);
        //assestManager.manager.finishLoading();



        //pick = assestManager.manager.get("sounds/pick.wav" , Sound.class);
        //keys = assestManager.manager.get("sounds/keys.wav" , Sound.class);
        // add some water
        //Body water =  bodyFactory.makeBoxPolyBody(1, -8, 40, 4, BodyFactory.RUBBER, BodyDef.BodyType.StaticBody,false);

        // make the water a sensor so it doesn't obstruct our player
        //bodyFactory.makeAllFixturesSensors(water);
        //water.setUserData("WATER");
    }
    public void logicStep(float delta)
    {

        if(controller.left){
            player.applyForceToCenter(-10, 0,true);
        }else if(controller.right){
            player.applyForceToCenter(10, 0,true);
        }else if(controller.up){
            player.applyForceToCenter(0, 10,true);
        }else if(controller.down){
            player.applyForceToCenter(0, -10,true);
        }else if(controller.escape)
        {
            //parent.parent.changeScreen(MyGdxGame.MENU);
            Gdx.app.debug("Exit" , "Exit with listen the key 'escape'");
            Gdx.app.exit();

        }

        if(controller.isMouse1Down && controller.pointIntersectsBody(player,controller.mouseLocation)){
            System.out.println("Player was clicked");
        }

        if(isSwimming)
        {
            player.applyForceToCenter(0 , 5 , true);
        }

        world.step(delta , 3 , 3);
    }
    private void createObject(){

        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,0);


        // add it to the world
         bp = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        bp.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }

        private void createFloor() {
        // create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -10);
        // add it to the world
        floor = world.createBody(bodyDef);
        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 1);
        // create the physical object in our body)
        // without this our body would just be data in the world
        floor.createFixture(shape, 0.0f);
        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }

    private void createMovingObject(){

        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0,-12);


        // add it to the world
        stati = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        stati.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();

        stati.setLinearVelocity(0, 0.75f);
        //Siema
    }

    public void playSound(int sound){

    }

}

package com.mygdx.game.views.main;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class G_Model {
    public World world;
    private Body bp; //player
    private Body floor; //floor
    private Body stati; //static body
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    public boolean isSwimming = false;
    private Body player;


    public G_Model()
    {
        world = new World(new Vector2(0 , -10f) , true);
        world.setContactListener(new  ContactListener(this));
        createFloor();
//        createObject();
//        createMovingObject();

        // get our body factory singleton and store it in bodyFactory
        BodyFactory bodyFactory = BodyFactory.getInstance(world);

        player  = bodyFactory.makeBoxPolyBody(1 , 1, 2, 2 , BodyFactory.RUBBER , BodyDef.BodyType.DynamicBody , false);

        // add some water
        Body water =  bodyFactory.makeBoxPolyBody(1, -8, 40, 4, BodyFactory.RUBBER, BodyDef.BodyType.StaticBody,false);

        // make the water a sensor so it doesn't obstruct our player
        bodyFactory.makeAllFixturesSensors(water);
        water.setUserData("WATER");
    }
    public void logicStep(float delta)
    {
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

}

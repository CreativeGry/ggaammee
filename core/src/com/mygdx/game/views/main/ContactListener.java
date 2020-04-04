package com.mygdx.game.views.main;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {

    private G_Model parent;

    public ContactListener(G_Model model)
    {
        parent = model;
    }

    private void shootUpInAir(Fixture staticFixture, Fixture otherFixture){
        System.out.println("Adding Force");
        otherFixture.getBody().applyForceToCenter(new Vector2(00000,100000), true);
    }

    @Override
    public void beginContact(Contact contact) {
        System.out.println("Contact");
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        System.out.println(a.getBody().getType()+" has hit "+b.getBody().getType());

        if(a.getBody().getType() == BodyDef.BodyType.StaticBody){
            this.shootUpInAir(a, b);
        }else if(b.getBody().getType() == BodyDef.BodyType.StaticBody){
            this.shootUpInAir(b, a);
        }else{
            // neither a nor b are static so do nothing
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

package com.mygdx.game.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssestManager {

    public final AssetManager manager = new AssetManager();

    //images

    public final String gameImages = "output/Game.atlas";

 //   public final String loadingImages = "output/";

    //sounds

    //public final String keySound = "sounds/keys.wav";
    //public final String pickSound = "sounds/pick.wav";

    //skin

    public final String skin = "input/game/skins/glass/glassy-ui.json";

    public void queueAddImages()
    {
       manager.load(gameImages , TextureAtlas.class);
    }
    public void queueAddSounds(){
        //manager.load(keySound , Sound.class);
        //manager.load(pickSound , Sound.class);

    }

    public void queueAddMusic()
    {

    }
    public void queueAddSkin()
    {
        SkinParameter params = new SkinParameter("input/game/skins/glass/glassy-ui.atlas");
        manager.load(skin , Skin.class , params);
    }

    public void queueAddLoadingImages()
    {
       // manager.load(loadingImages , TextureAtlas.class);
        manager.load("output/Game.atlas" , TextureAtlas.class);
    }

    public void queueAddFonts() {
    }

    public void queueAddParticleEffects() {
    }
}

package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.views.*;
import com.mygdx.game.views.main.MainScreen;

public class MyGdxGame extends Game {

	private LoadingScreen loadingScreen;
	private PreferencesScreen preferencesScreen;
	private MainScreen mainScreen;
	private MenuScreen menuScreen;
	private EndScreen endScreen;

	public static final int MENU = 0;
	public static final int PREFERENCES = 1;
	public static final int APPLICATION = 2;
	public static final int ENDGAME = 3;

	public void changeScreen(int screen)
	{
		switch (screen)
		{
			case MENU :
				if (menuScreen == null)menuScreen = new MenuScreen(this);
				this.setScreen(menuScreen);
				break;
			case PREFERENCES :
				if (preferencesScreen == null)preferencesScreen = new PreferencesScreen(this);
				this.setScreen(preferencesScreen);
				break;
			case APPLICATION :
				if (mainScreen == null)mainScreen = new MainScreen(this);
				this.setScreen(mainScreen);
				break;
			case ENDGAME :
				if(endScreen == null)endScreen = new EndScreen(this);
				this.setScreen(endScreen);
				break;

		}
	}

	@Override
	public void create() {
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}
	public AppPreferences getPreferences()
	{
		return new AppPreferences();
	}
}

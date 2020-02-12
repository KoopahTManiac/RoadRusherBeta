package com.koopahtmaniac.roadrusher.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.koopahtmaniac.roadrusher.GameCore;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Road Rusher";
		config.useGL30 = true;
		config.width = 400;
		config.height = 756;
		config.samples = 16;
		new LwjglApplication(new GameCore(), config);
	}
}

package com.ptrh.sort.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ptrh.sort.SortGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.title = "Sort";
            config.width = 272;
            config.height = 408;
            new LwjglApplication(new SortGame(), config);
	}
}

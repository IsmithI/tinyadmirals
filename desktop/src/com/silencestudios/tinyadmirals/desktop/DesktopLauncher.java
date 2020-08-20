package com.silencestudios.tinyadmirals.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.silencestudios.gdxengine.entity.EntitiesLoader;
import com.silencestudios.gdxengine.screen.ScreenLoader;
import com.silencestudios.tinyadmirals.GameMain;

import java.io.File;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        String path = System.getProperty("user.dir");
        File screensDir = new File(path + "/screens");

        ScreenLoader.init(screensDir);
        EntitiesLoader.get("com.silencestudios.tinyadmirals");

        new LwjglApplication(new GameMain(), config);
    }
}

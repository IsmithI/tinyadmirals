package com.silencestudios.tinyadmirals;

import com.badlogic.gdx.assets.AssetManager;
import com.kotcrab.vis.ui.VisUI;
import com.silencestudios.gdxengine.EngineGame;
import com.silencestudios.gdxengine.component.SpriteRenderer;
import com.silencestudios.gdxengine.config.ScreenConfig;
import com.silencestudios.gdxengine.screen.BaseScreen;
import com.silencestudios.tinyadmirals.screens.GameScreen;

public class GameMain extends EngineGame {

    @Override
    public void create() {
        VisUI.load();

        ScreenConfig screenConfig = ScreenConfig.get();
        screenConfig.worldWidth = 16f;
        screenConfig.worldHeight = 16f / BaseScreen.getRatio();

        SpriteRenderer.SPRITE_SCALE = 64f;

        AssetManager assetManager = new AssetManager();

        GameScreen gameScreen = new GameScreen(assetManager, screenConfig, this);

        screens.add("game", gameScreen);

        setScreen("game");
    }
}

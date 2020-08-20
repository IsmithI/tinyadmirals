package com.silencestudios.tinyadmirals.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.silencestudios.gdxengine.config.ScreenConfig;
import com.silencestudios.gdxengine.screen.SerializedBaseScreen;

public class GameScreen extends SerializedBaseScreen {

    public GameScreen(AssetManager assetManager, ScreenConfig screenConfig, Game game) {
        super(assetManager, screenConfig, game);
    }

    @Override
    protected void configureEngine() {
        loadAssets();
        super.configureEngine();
    }

    private void loadAssets() {
        assetManager.clear();
        assetManager.load("atlas/tiles.atlas", TextureAtlas.class);
        assetManager.load("atlas/ships.atlas", TextureAtlas.class);
        assetManager.load("atlas/ships-parts.atlas", TextureAtlas.class);
        assetManager.load("atlas/sand-tileset.atlas", TextureAtlas.class);
        assetManager.finishLoading();
    }
}

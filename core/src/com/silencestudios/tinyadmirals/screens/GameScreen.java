package com.silencestudios.tinyadmirals.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.silencestudios.gdxengine.config.ScreenConfig;
import com.silencestudios.gdxengine.instance.Instance;
import com.silencestudios.gdxengine.screen.BaseScreen;
import com.silencestudios.tinyadmirals.entity.land.Land;
import com.silencestudios.tinyadmirals.entity.land.generator.DefaultLandGenerator;
import com.silencestudios.tinyadmirals.entity.tile.TileMap;
import com.silencestudios.tinyadmirals.system.LandGenerationSystem;

public class GameScreen extends BaseScreen {

    public GameScreen(AssetManager assetManager, ScreenConfig screenConfig, Game game) {
        super(assetManager, screenConfig, game);
    }

    @Override
    protected void configureEngine() {
        loadAssets();

        TextureAtlas textureAtlas = assetManager.get("atlas/sand-tileset.atlas");

        JsonReader jsonReader = new JsonReader();
        JsonValue jsonValue = jsonReader.parse(Gdx.files.internal("sand.json"));
        for (JsonValue value : jsonValue.iterator()) {
            TileMap.map.put(Integer.parseInt(value.name), value.asInt());
        }

        addSystem(LandGenerationSystem.make(new DefaultLandGenerator(textureAtlas)));

        Instance.get().create(Land.class);
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

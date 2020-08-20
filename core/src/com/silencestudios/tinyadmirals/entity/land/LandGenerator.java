package com.silencestudios.tinyadmirals.entity.land;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.silencestudios.gdxengine.component.Lifecycle;
import com.silencestudios.gdxengine.component.SpriteRenderer;
import com.silencestudios.gdxengine.component.Transform;
import com.silencestudios.gdxengine.component.annotations.ComponentProvider;
import com.silencestudios.gdxengine.component.annotations.Resource;
import com.silencestudios.gdxengine.instance.Instance;
import com.silencestudios.tinyadmirals.entity.tile.Tile;
import com.silencestudios.tinyadmirals.entity.tile.TileProperties;
import com.silencestudios.tinyadmirals.entity.tile.TileType;
import com.silencestudios.tinyadmirals.utils.Layers;
import com.silencestudios.tinyadmirals.utils.OpenSimplexNoise;

public class LandGenerator extends Entity {

    @Resource(key = "atlas/tiles.atlas")
    private TextureAtlas textureAtlas;

    @Resource(key = "atlas/sand-tileset.atlas")
    private TextureAtlas sandTileset;

    @ComponentProvider
    private Transform transform;

    @ComponentProvider
    private LandProperties landProperties;

    @ComponentProvider
    private Lifecycle lifecycle() {
        Lifecycle lifecycle = new Lifecycle();

        lifecycle.onCreate = this::generate;

        lifecycle.onUpdate = dt -> {
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.U)) {
                Instance.get().remove(transform.getChildren());
                transform.removeChildren();
                generate();
            }
        };

        return lifecycle;
    }

    public void generate() {
        LandData landData = new LandData(landProperties.width, landProperties.height);

        OpenSimplexNoise openSimplexNoise = new OpenSimplexNoise();

        double seed = MathUtils.random(1000);
        for (int y = 0; y < landProperties.height; y++) {
            for (int x = 0; x < landProperties.width; x++) {
                double value = openSimplexNoise.eval((x + seed) / landProperties.scale, (y + seed) / landProperties.scale);

                TileProperties tileProperties = new TileProperties();
                tileProperties.tileType = value > landProperties.seaLevel ? TileType.GRASS : TileType.WATER;
                tileProperties.tileIndex = value > landProperties.seaLevel ? 39 : 73;

                landData.setAt(x, y, tileProperties);
            }
        }

        for (int y = 0; y < landProperties.height; y++) {
            for (int x = 0; x < landProperties.width; x++) {
                TileProperties tileProperties = landData.getAt(x, y);
                if (tileProperties.tileType == TileType.GRASS && hasNearbyWater(x, y, landData)) {
                    tileProperties.tileType = TileType.SAND;
                    tileProperties.tileIndex = 69;
                }
            }
        }

        for (int y = 0; y < landProperties.height; y++) {
            for (int x = 0; x < landProperties.width; x++) {
                TileProperties tileProperties = landData.getAt(x, y);

                Tile tile = Instance.get().create(Tile.class);
                tile.add(tileProperties);
                tile.transform.moveTo(x, y);
                tile.transform.setZ(Layers.TILES);

                SpriteRenderer spriteRenderer = tile.getComponent(SpriteRenderer.class);
                spriteRenderer.setSprite(textureAtlas.createSprite("tile", tileProperties.tileIndex));

                transform.addChild(tile);
            }
        }
    }

    private boolean isWaterAt(int x, int y, LandData landData) {
        TileProperties tileProperties = landData.getAt(x, y);
        return tileProperties == null || tileProperties.tileType == TileType.WATER;
    }

    private boolean hasNearbyWater(int x, int y, LandData landData) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (isWaterAt(x + i, y + j, landData)) {
                    return true;
                }
            }
        }
        return false;
    }
}

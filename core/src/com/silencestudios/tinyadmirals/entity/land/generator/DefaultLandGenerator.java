package com.silencestudios.tinyadmirals.entity.land.generator;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.silencestudios.gdxengine.component.RepeatedSpriteDrawer;
import com.silencestudios.gdxengine.component.SpriteRenderer;
import com.silencestudios.gdxengine.component.Transform;
import com.silencestudios.gdxengine.instance.Instance;
import com.silencestudios.tinyadmirals.entity.TileLabel;
import com.silencestudios.tinyadmirals.entity.land.LandProperties;
import com.silencestudios.tinyadmirals.entity.land.Water;
import com.silencestudios.tinyadmirals.entity.tile.Tile;
import com.silencestudios.tinyadmirals.entity.tile.TileMap;
import com.silencestudios.tinyadmirals.entity.tile.TileProperties;
import com.silencestudios.tinyadmirals.entity.tile.TileType;
import com.silencestudios.tinyadmirals.utils.Layers;
import com.silencestudios.tinyadmirals.utils.OpenSimplexNoise;

import java.util.Arrays;
import java.util.List;

public class DefaultLandGenerator implements LandGenerator {

    private TextureAtlas textureAtlas;

    public DefaultLandGenerator(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    @Override
    public void generate(Entity land) {
        Transform transform = land.getComponent(Transform.class);
        LandProperties landProperties = land.getComponent(LandProperties.class);

        List<Integer> unsupportedTiles = Arrays.asList(0, 2, 8, 16, 24, 64, 66);

        // Clear entity before filling it with tiles
        Instance.get().remove(transform.getChildren());
        transform.removeChildren();

        LandData landData = new LandData(landProperties.width, landProperties.height);
        OpenSimplexNoise openSimplexNoise = new OpenSimplexNoise();

        Water water = Instance.get().create(Water.class);
        water.getComponent(Transform.class).setZ(Layers.WATER);

        RepeatedSpriteDrawer repeatedSpriteDrawer = water.getComponent(RepeatedSpriteDrawer.class);
        Vector2 origin = new Vector2();
        origin.x = -repeatedSpriteDrawer.getSprite().getWidth() * landProperties.width / 2f;
        origin.y = -repeatedSpriteDrawer.getSprite().getHeight() * landProperties.height / 2f;

        repeatedSpriteDrawer.setOrigin(origin);
        repeatedSpriteDrawer.setRepeatX(landProperties.width);
        repeatedSpriteDrawer.setRepeatY(landProperties.height);

        transform.addChild(water);

        double seed = MathUtils.random(1000);
        for (int y = 0; y < landProperties.height; y++) {
            for (int x = 0; x < landProperties.width; x++) {
                TileProperties tileProperties = new TileProperties();
                if (x == 0 || y == 0 || x == landProperties.width - 1 || y == landProperties.height - 1) {
                    tileProperties.tileType = TileType.WATER;
                } else {
                    double value = openSimplexNoise.eval((x + seed) / landProperties.scale, (y + seed) / landProperties.scale);
                    tileProperties.tileType = value > landProperties.seaLevel ? TileType.LAND : TileType.WATER;
                }

                landData.setAt(x, y, tileProperties);
            }
        }

        for (int y = 0; y < landProperties.height; y++) {
            for (int x = 0; x < landProperties.width; x++) {
                TileProperties tileProperties = landData.getAt(x, y);
                int index = landData.getBitValue(x, y, TileType.LAND);
                if (unsupportedTiles.contains(index) && tileProperties.tileType == TileType.LAND) {
                    tileProperties.tileType = TileType.WATER;
                    landData.setAt(x, y, tileProperties);
                }
            }
        }

        for (int y = 0; y < landProperties.height; y++) {
            for (int x = 0; x < landProperties.width; x++) {
                TileProperties tileProperties = landData.getAt(x, y);
                int index = landData.getBitValue(x, y, TileType.LAND);

                if (tileProperties.tileType == TileType.LAND) {
                    Tile tile = Instance.get().create(Tile.class);
                    tile.add(tileProperties);
                    tile.transform.moveTo(x, y);
                    tile.transform.setZ(Layers.TILES);

                    if (TileMap.map.containsKey(index))
                        tileProperties.tileIndex = TileMap.map.get(index);
                    else
                        tileProperties.tileIndex = 23;

                    TileLabel tileLabel = Instance.get().create(TileLabel.class);
                    tileLabel.label.setText(index + ": " + tileProperties.tileType);
                    tile.transform.addChild(tileLabel);

                    Sprite sprite = textureAtlas.createSprite("tile", tileProperties.tileIndex);
                    if (sprite != null) {
                        SpriteRenderer spriteRenderer = tile.getComponent(SpriteRenderer.class);
                        spriteRenderer.setSprite(sprite);
                    }

                    transform.addChild(tile);
                }
            }
        }
    }
}

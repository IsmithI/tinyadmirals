package com.silencestudios.tinyadmirals.entity.tile;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.silencestudios.gdxengine.component.SpriteRenderer;
import com.silencestudios.gdxengine.component.Transform;
import com.silencestudios.gdxengine.component.annotations.ComponentProvider;
import com.silencestudios.gdxengine.component.annotations.Resource;
import com.silencestudios.gdxengine.instance.Instance;

public class Tile extends Entity {

    @Resource(key = "atlas/tiles.atlas")
    private TextureAtlas textureAtlas;

    @ComponentProvider
    public Transform transform;

    @ComponentProvider
    public TileProperties tileProperties;

    @ComponentProvider
    private SpriteRenderer spriteRenderer() {
        return new SpriteRenderer(textureAtlas.createSprite("tile", 73));
    }

    public static Tile water() {
        Tile tile = Instance.get().create(Tile.class);

        SpriteRenderer spriteRenderer = tile.getComponent(SpriteRenderer.class);
        spriteRenderer.setSprite(tile.textureAtlas.createSprite("tile", 73));

        tile.tileProperties.tileType = TileType.WATER;

        return tile;
    }

    public static Tile sand() {
        Tile tile = Instance.get().create(Tile.class);

        SpriteRenderer spriteRenderer = tile.getComponent(SpriteRenderer.class);
        spriteRenderer.setSprite(tile.textureAtlas.createSprite("tile", 18));

        tile.tileProperties.tileType = TileType.SAND;

        return tile;
    }

    public static Tile grass() {
        Tile tile = Instance.get().create(Tile.class);

        SpriteRenderer spriteRenderer = tile.getComponent(SpriteRenderer.class);
        spriteRenderer.setSprite(tile.textureAtlas.createSprite("tile", 39));

        tile.tileProperties.tileType = TileType.GRASS;

        return tile;
    }

}

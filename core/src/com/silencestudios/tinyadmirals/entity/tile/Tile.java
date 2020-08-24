package com.silencestudios.tinyadmirals.entity.tile;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.silencestudios.gdxengine.component.SpriteRenderer;
import com.silencestudios.gdxengine.component.Transform;
import com.silencestudios.gdxengine.component.annotations.ComponentProvider;
import com.silencestudios.gdxengine.component.annotations.Resource;

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

}

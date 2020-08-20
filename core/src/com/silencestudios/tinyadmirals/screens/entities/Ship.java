package com.silencestudios.tinyadmirals.screens.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.silencestudios.gdxengine.component.SpriteRenderer;
import com.silencestudios.gdxengine.component.Transform;
import com.silencestudios.gdxengine.component.annotations.ComponentProvider;
import com.silencestudios.gdxengine.component.annotations.Resource;
import com.silencestudios.tinyadmirals.utils.Layers;

public class Ship extends Entity {

    @Resource(key = "atlas/ships.atlas")
    private TextureAtlas textureAtlas;

    @ComponentProvider
    private Transform transform() {
        Transform transform = new Transform();
        transform.setZ(Layers.SHIPS);
        return transform;
    }

    @ComponentProvider
    private SpriteRenderer spriteRenderer() {
        return new SpriteRenderer(textureAtlas.createSprite("ship (3)"));
    }
}

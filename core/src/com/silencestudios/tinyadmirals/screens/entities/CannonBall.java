package com.silencestudios.tinyadmirals.screens.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.silencestudios.gdxengine.component.SpriteRenderer;
import com.silencestudios.gdxengine.component.Transform;
import com.silencestudios.gdxengine.component.annotations.ComponentProvider;
import com.silencestudios.gdxengine.component.annotations.Resource;
import com.silencestudios.tinyadmirals.component.Projectile;

public class CannonBall extends Entity {

    @Resource(key = "atlas/ships-parts.atlas")
    private TextureAtlas textureAtlas;

    @ComponentProvider
    public Transform transform;

    @ComponentProvider
    private Projectile projectile;

    @ComponentProvider
    private SpriteRenderer spriteRenderer() {
        return new SpriteRenderer(textureAtlas.createSprite("cannonBall"));
    }
}

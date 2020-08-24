package com.silencestudios.tinyadmirals.entity.land;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.silencestudios.gdxengine.component.RepeatedSpriteDrawer;
import com.silencestudios.gdxengine.component.Transform;
import com.silencestudios.gdxengine.component.annotations.ComponentProvider;
import com.silencestudios.gdxengine.component.annotations.Resource;

public class Water extends Entity {

    @Resource(key = "atlas/tiles.atlas")
    private TextureAtlas textureAtlas;

    @ComponentProvider
    private Transform transform;

    @ComponentProvider
    private RepeatedSpriteDrawer repeatedSpriteDrawer() {
        return new RepeatedSpriteDrawer(textureAtlas.createSprite("tile", 73));
    }
}

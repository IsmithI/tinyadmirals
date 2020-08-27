package com.silencestudios.tinyadmirals.input;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.silencestudios.gdxeditor.input.WorldInput;
import com.silencestudios.tinyadmirals.component.Pathfinder;

public class PathInput implements WorldInput {

    private Entity entity;

    public PathInput(Entity entity) {
        this.entity = entity;
    }

    @Override
    public boolean touchDown(float v, float v1, int i) {
        return false;
    }

    @Override
    public boolean touchDragged(float v, float v1, int i) {
        return false;
    }

    @Override
    public boolean touchUp(float x, float y, int i) {
        Pathfinder pathfinder = entity.getComponent(Pathfinder.class);
        pathfinder.target = new Vector2(Math.round(x), Math.round(y));
        return false;
    }

    @Override
    public boolean mouseMove(float v, float v1) {
        return false;
    }
}

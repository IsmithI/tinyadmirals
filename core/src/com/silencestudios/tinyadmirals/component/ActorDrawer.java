package com.silencestudios.tinyadmirals.component;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.silencestudios.gdxengine.component.Drawer;

import static com.silencestudios.gdxengine.component.SpriteRenderer.SPRITE_SCALE;

public class ActorDrawer implements Drawer {

    private Container<Actor> container;
    private Container<Actor> inner;

    public ActorDrawer(Actor actor) {
        inner = new Container<>(actor);
        inner.setTransform(true);

        container = new Container<>(inner);
        container.setTransform(true);
    }

    @Override
    public void draw(Batch batch, float dt, float x, float y, float scaleX, float scaleY, float rotation) {
        container.act(dt);
        container.setPosition(x, y);
        container.setScale(scaleX / SPRITE_SCALE / 2.8f, scaleY / SPRITE_SCALE / 2.8f);
        container.setRotation(rotation);
        container.draw(batch, 1f);
    }

    public Container<Actor> getContainer() {
        return inner;
    }
}

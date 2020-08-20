package com.silencestudios.tinyadmirals.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.silencestudios.gdxengine.component.Transform;
import com.silencestudios.gdxengine.component.annotations.ComponentProvider;
import com.silencestudios.tinyadmirals.component.ActorDrawer;

public class TileLabel extends Entity {

    public VisLabel label = new VisLabel();

    @ComponentProvider
    private Transform transform;

    @ComponentProvider
    private ActorDrawer actorDrawer() {
        label.setColor(Color.BLACK);
        Container<VisLabel> container = new Container<>(label);
        container.setTransform(true);
        container.scaleBy(1.5f);

        return new ActorDrawer(container);
    }
}

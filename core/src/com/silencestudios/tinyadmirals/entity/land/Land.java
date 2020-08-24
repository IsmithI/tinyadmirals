package com.silencestudios.tinyadmirals.entity.land;

import com.badlogic.ashley.core.Entity;
import com.silencestudios.gdxengine.component.Transform;
import com.silencestudios.gdxengine.component.annotations.ComponentProvider;

public class Land extends Entity {

    @ComponentProvider
    private Transform transform;

    @ComponentProvider
    private LandProperties landProperties;
}

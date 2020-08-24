package com.silencestudios.tinyadmirals.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.silencestudios.gdxengine.component.Transform;
import com.silencestudios.gdxengine.instance.Instance;
import com.silencestudios.tinyadmirals.entity.land.LandProperties;
import com.silencestudios.tinyadmirals.entity.land.generator.LandGenerator;

public class LandGenerationSystem extends EntitySystem {

    private LandGenerator landGenerator;
    private static final Family family = Family.all(Transform.class, LandProperties.class).get();

    @Override
    public void addedToEngine(Engine engine) {
        engine.addEntityListener(family, new EntityListener() {
            @Override
            public void entityAdded(Entity entity) {
                landGenerator.generate(entity);
            }

            @Override
            public void entityRemoved(Entity entity) {

            }
        });
    }

    @Override
    public void update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            for (Entity entity : getEngine().getEntitiesFor(family)) {
                landGenerator.generate(entity);
            }
        }
    }

    public static LandGenerationSystem make(LandGenerator landGenerator) {
        LandGenerationSystem system = Instance.get().createSystem(LandGenerationSystem.class);
        system.landGenerator = landGenerator;
        return system;
    }
}

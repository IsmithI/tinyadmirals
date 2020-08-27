package com.silencestudios.tinyadmirals.screens.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.silencestudios.gdxeditor.input.WorldInputProcessorAdapter;
import com.silencestudios.gdxengine.component.Camera;
import com.silencestudios.gdxengine.component.InputProcessorContainer;
import com.silencestudios.gdxengine.component.PhysicsBody;
import com.silencestudios.gdxengine.component.SpriteRenderer;
import com.silencestudios.gdxengine.component.Transform;
import com.silencestudios.gdxengine.component.annotations.ComponentProvider;
import com.silencestudios.gdxengine.component.annotations.Dependency;
import com.silencestudios.gdxengine.component.annotations.Resource;
import com.silencestudios.gdxengine.entity.MainCamera;
import com.silencestudios.tinyadmirals.component.Movable;
import com.silencestudios.tinyadmirals.component.Pathfinder;
import com.silencestudios.tinyadmirals.input.PathInput;
import com.silencestudios.tinyadmirals.utils.Layers;

public class Ship extends Entity {

    @Resource(key = "atlas/ships.atlas")
    private TextureAtlas textureAtlas;

    @Dependency(ID = "mainCamera")
    private MainCamera mainCamera;

    @ComponentProvider
    private Pathfinder pathfinder;

    @ComponentProvider
    private Movable movable;

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

    @ComponentProvider
    private InputProcessorContainer inputProcessorContainer() {
        PathInput pathInput = new PathInput(this);
        OrthographicCamera camera = (OrthographicCamera) mainCamera.getComponent(Camera.class).getCamera();
        return new InputProcessorContainer(new WorldInputProcessorAdapter(pathInput, camera));
    }

    @ComponentProvider
    private PhysicsBody physicsBody() {
        PhysicsBody physicsBody = new PhysicsBody();

        physicsBody.bodyDef.type = BodyDef.BodyType.DynamicBody;

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(0.2f, 0.2f);

        physicsBody.fixtureDef.shape = polygonShape;
        physicsBody.fixtureDef.isSensor = true;
        physicsBody.bodyDef.gravityScale = 0;

        return physicsBody;
    }
}

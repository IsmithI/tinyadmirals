package com.silencestudios.tinyadmirals.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.silencestudios.gdxengine.component.Transform;
import com.silencestudios.tinyadmirals.component.Movable;
import com.silencestudios.tinyadmirals.component.Pathfinder;

public class PathfinderMovingSystem extends IteratingSystem {

    public PathfinderMovingSystem() {
        super(Family.all(Pathfinder.class, Movable.class, Transform.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Movable movable = entity.getComponent(Movable.class);
        Transform transform = entity.getComponent(Transform.class);
        Pathfinder pathfinder = entity.getComponent(Pathfinder.class);

        if (!pathfinder.path.isEmpty()) {
            if (movable.target == null) {
                Pathfinder.Node node = pathfinder.path.peek();
                movable.target = new Vector2(node.x, node.y);
            }

            if (transform.getPosition().dst2(movable.target) <= movable.speed * deltaTime) {
                pathfinder.path.remove();
                movable.target = null;
                return;
            }

            Vector2 velocity = new Vector2(movable.target)
                    .sub(transform.getPosition())
                    .setLength(movable.speed * deltaTime);


            transform.moveBy(velocity);
            transform.setRotation(velocity.angle() + 90f);
        }
    }
}

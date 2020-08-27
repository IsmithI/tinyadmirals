package com.silencestudios.tinyadmirals.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.silencestudios.gdxengine.component.Transform;
import com.silencestudios.gdxengine.instance.Instance;
import com.silencestudios.tinyadmirals.component.Movable;
import com.silencestudios.tinyadmirals.component.Pathfinder;
import com.silencestudios.tinyadmirals.component.Projectile;
import com.silencestudios.tinyadmirals.entity.PathfinderLandData;
import com.silencestudios.tinyadmirals.screens.entities.CannonBall;

import java.util.Iterator;
import java.util.LinkedList;

public class PathfinderSystem extends IteratingSystem {

    public PathfinderSystem() {
        super(Family.all(Pathfinder.class, Movable.class, Transform.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Movable movable = entity.getComponent(Movable.class);
        Transform transform = entity.getComponent(Transform.class);
        Pathfinder pathfinder = entity.getComponent(Pathfinder.class);
        if (pathfinder.target == null || pathfinder.landData == null)
            return;

        Vector2 target = pathfinder.target;
        PathfinderLandData landData = pathfinder.landData;

        int posX = MathUtils.floor(transform.getPosition().x);
        int posY = MathUtils.floor(transform.getPosition().y);

        Pathfinder.Node node = landData.start(new Vector2(posX, posY), target);

        if (node == null) {
            pathfinder.target = null;
            return;
        }

        Instance.get().remove(Family.all(Projectile.class).get());
        LinkedList<Pathfinder.Node> nodes = new LinkedList<>();
        do {
            nodes.add(node);
            node = node.parent;
        }
        while (node.parent != null);

        pathfinder.path.clear();
        movable.target = null;

        Iterator<Pathfinder.Node> iterator = nodes.descendingIterator();
        while (iterator.hasNext()) {
            Pathfinder.Node next = iterator.next();
            pathfinder.path.add(next);

            CannonBall ball = Instance.get().create(CannonBall.class);
            ball.transform.moveTo(next.x, next.y);
        }


        pathfinder.target = null;
    }

}

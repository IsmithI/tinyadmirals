package com.silencestudios.tinyadmirals.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.silencestudios.tinyadmirals.entity.PathfinderLandData;

import java.util.LinkedList;
import java.util.Queue;

public class Pathfinder implements Component {

    public float movespeed = 2f;
    public Vector2 target = null;
    public PathfinderLandData landData;

    public Queue<Node> path = new LinkedList<>();

    public static class Node {
        public Node parent;
        public boolean canPass;
        public int x;
        public int y;
        public float Gcost = -1;
        public float Hcost = -1;

        public float getValue() {
            return this.Gcost + this.Hcost;
        }

        public void clear() {
            parent = null;
            Gcost = -1;
            Hcost = -1;
        }

        @Override
        public String toString() {
            return x + ":" + y + " => " + getValue();
        }
    }
}

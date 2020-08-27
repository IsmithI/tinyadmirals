package com.silencestudios.tinyadmirals.entity;

import com.badlogic.gdx.math.Vector2;
import com.silencestudios.tinyadmirals.component.Pathfinder;
import com.silencestudios.tinyadmirals.entity.land.generator.LandData;

import java.util.LinkedList;
import java.util.List;

public class PathfinderLandData {

    private Pathfinder.Node[][] nodes;

    public PathfinderLandData(LandData landData) {
        int width = landData.getTiles()[0].length;
        int height = landData.getTiles().length;

        nodes = new Pathfinder.Node[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pathfinder.Node node = new Pathfinder.Node();
                node.x = x;
                node.y = y;
                node.canPass = !landData.isLandAt(x, y);

                nodes[x][y] = node;
            }
        }
    }

    public float distance(Vector2 start, Vector2 end) {
        float diagonalCost = 14f;
        float linearCost = 10f;

        float diagonalMin = Math.min(Math.abs(start.x - end.x), Math.abs(start.y - end.y));
        float diagonalMax = Math.max(Math.abs(start.x - end.x), Math.abs(start.y - end.y));

        return diagonalCost * diagonalMin + linearCost * (diagonalMax - diagonalMin);
    }

    public void evaluate(Pathfinder.Node node, Vector2 start, Vector2 target) {
        Vector2 pos = new Vector2(node.x, node.y);
        node.Gcost = distance(pos, start);
        node.Hcost = distance(pos, target);
    }

    public Pathfinder.Node start(Vector2 start, Vector2 target) {
        clearCosts();
        List<Pathfinder.Node> openNodes = new LinkedList<>();
        List<Pathfinder.Node> closedNodes = new LinkedList<>();

        Vector2 pointer = new Vector2(start);

        int nodeX = (int) pointer.x;
        int nodeY = (int) pointer.y;

        Pathfinder.Node startNode = nodes[nodeX][nodeY];
        openNodes.add(startNode);

        while (true) {
            for (Pathfinder.Node openNode : openNodes) {
                evaluate(openNode, start, target);
            }

            Pathfinder.Node current = getMinNode(openNodes);

            openNodes.remove(current);
            closedNodes.add(current);

            try {
                if (current.Hcost == 0) {
                    return current;
                }
            } catch (NullPointerException ignored) {
                System.out.println("Null");
                return null;
            }

            for (int y = -1; y < 2; y++) {
                for (int x = -1; x < 2; x++) {
                    if (x == 0 && y == 0) continue;

                    try {
                        Pathfinder.Node neighbor = nodes[current.x + x][current.y + y];
                        if (!neighbor.canPass || closedNodes.contains(neighbor)) {
                            continue;
                        }

                        boolean pathIsShorter = current.getValue() > neighbor.getValue();
                        if (pathIsShorter || !openNodes.contains(neighbor)) {
                            evaluate(neighbor, start, target);
                            neighbor.parent = current;

                            if (!openNodes.contains(neighbor))
                                openNodes.add(neighbor);
                        }


                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
            }

        }
    }

    public void clearCosts() {
        for (int y = 0; y < getWidth(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                Pathfinder.Node node = nodes[x][y];
                node.clear();
            }
        }
    }

    private Pathfinder.Node getMinNode(List<Pathfinder.Node> nodes) {
        if (nodes.isEmpty())
            return null;

        Pathfinder.Node min = nodes.get(0);
        for (Pathfinder.Node node : nodes) {
            if (node.getValue() < min.getValue()) {
                min = node;
            }
        }
        return min;
    }

    public Pathfinder.Node get(int x, int y) {
        return nodes[x][y];
    }

    public int getWidth() {
        return nodes[0].length;
    }

    public int getHeight() {
        return nodes.length;
    }
}

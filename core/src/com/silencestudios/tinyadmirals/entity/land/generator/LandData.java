package com.silencestudios.tinyadmirals.entity.land.generator;

import com.silencestudios.tinyadmirals.entity.tile.TileProperties;
import com.silencestudios.tinyadmirals.entity.tile.TileType;

public class LandData {

    private TileProperties[][] tiles;

    LandData(int width, int height) {
        tiles = new TileProperties[width][height];
    }

    void setAt(int x, int y, TileProperties tileStack) {
        tiles[x][y] = tileStack;
    }

    TileProperties getAt(int x, int y) {
        if (tiles == null)
            return null;

        if (x < 0 || y < 0)
            return null;

        if (x > tiles.length - 1 || y > tiles.length - 1)
            return null;

        return tiles[x][y];
    }

    public TileProperties[][] getTiles() {
        return tiles;
    }

    public void setTiles(TileProperties[][] tiles) {
        this.tiles = tiles;
    }

    public int getBitValue(int x, int y, TileType tileType) {
        int topLeft = getFor(x - 1, y + 1, tileType) * getFor(x, y + 1, tileType) * getFor(x - 1, y, tileType);
        int topRight = getFor(x + 1, y + 1, tileType) * getFor(x, y + 1, tileType) * getFor(x + 1, y, tileType);
        int bottomLeft = getFor(x - 1, y - 1, tileType) * getFor(x - 1, y, tileType) * getFor(x, y - 1, tileType);
        int bottomRight = getFor(x + 1, y - 1, tileType) * getFor(x, y - 1, tileType) * getFor(x + 1, y, tileType);

        return topLeft +
                2 * getFor(x, y + 1, tileType) +
                4 * topRight +

                8 * getFor(x - 1, y, tileType) +
                16 * getFor(x + 1, y, tileType) +

                32 * bottomLeft +
                64 * getFor(x, y - 1, tileType) +
                128 * bottomRight;
    }

    private int getFor(int x, int y, TileType tileType) {
        try {
            TileProperties tileProperties = tiles[x][y];
            return tileProperties.tileType == tileType ? 1 : 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    public boolean isLandAt(int x, int y) {
        return tiles[x][y].tileType == TileType.LAND;
    }
}

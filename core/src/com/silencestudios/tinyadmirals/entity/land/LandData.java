package com.silencestudios.tinyadmirals.entity.land;

import com.silencestudios.tinyadmirals.entity.tile.TileProperties;

public class LandData {

    private TileProperties[][] tiles;

    public LandData(int width, int height) {
        tiles = new TileProperties[width][height];
    }

    public void setAt(int x, int y, TileProperties tileStack) {
        tiles[x][y] = tileStack;
    }

    public TileProperties getAt(int x, int y) {
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

}

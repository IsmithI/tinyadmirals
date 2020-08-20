package com.silencestudios.tinyadmirals.entity.tile;

public enum TileType {
    WATER(new int[] { 73 }),
    SAND(new int[] { 6, 7, 9, 22, 25, 54, 55, 57 }),
    GRASS(new int[] { 23 });

    private final int[] indexes;

    TileType(int[] indexes) {
        this.indexes = indexes;
    }

    public boolean contains(int index) {
        for (int i : indexes) {
            if (i == index) return true;
        }
        return false;
    }
}

package com.silencestudios.tinyadmirals.entity.tile;

import java.util.HashMap;
import java.util.Map;

public class TilePatch {

    public int TOP_LEFT;
    public int TOP_CENTER;
    public int TOP_RIGHT;

    public int MIDDLE_LEFT;
    public int MIDDLE_CENTER;
    public int MIDDLE_RIGHT;

    public int BOTTOM_LEFT;
    public int BOTTOM_CENTER;
    public int BOTTOM_RIGHT;

    public Map<String, Integer> getPatternMap() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("X00X", TOP_LEFT);
        map.put("X000", TOP_CENTER);
        map.put("XX00", TOP_RIGHT);

        map.put("000X", MIDDLE_LEFT);
        map.put("0000", MIDDLE_CENTER);
        map.put("0X00", MIDDLE_RIGHT);

        map.put("00XX", BOTTOM_LEFT);
        map.put("00X0", BOTTOM_CENTER);
        map.put("0XX0", BOTTOM_RIGHT);

        return map;
    }

    public Map<String, Integer> getInnerPatternMap() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("X00X", TOP_LEFT);
        map.put("XX00", TOP_RIGHT);
        map.put("00XX", BOTTOM_LEFT);
        map.put("0XX0", BOTTOM_RIGHT);

        return map;
    }

    public static TilePatch sandToGrass() {
        TilePatch tilePatch = new TilePatch();
        tilePatch.TOP_LEFT = 6;
        tilePatch.TOP_CENTER = 7;
        tilePatch.TOP_RIGHT = 9;

        tilePatch.MIDDLE_LEFT = 22;
        tilePatch.MIDDLE_RIGHT = 25;

        tilePatch.BOTTOM_LEFT = 54;
        tilePatch.BOTTOM_CENTER = 55;
        tilePatch.BOTTOM_RIGHT = 57;

        return tilePatch;
    }

    public static TilePatch grassToSand() {
        TilePatch tilePatch = new TilePatch();

        tilePatch.TOP_LEFT = 36;
        tilePatch.TOP_RIGHT = 37;
        tilePatch.BOTTOM_LEFT = 52;
        tilePatch.BOTTOM_RIGHT = 53;

        return tilePatch;
    }

}

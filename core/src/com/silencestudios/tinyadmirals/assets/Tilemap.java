package com.silencestudios.tinyadmirals.assets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Tilemap extends TextureAtlas {

    public Sprite createByTileIndex(int index) {
        return createSprite(index + "");
    }
}

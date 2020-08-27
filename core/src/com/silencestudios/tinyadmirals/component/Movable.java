package com.silencestudios.tinyadmirals.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class Movable implements Component {
    public float speed = 2f;
    public Vector2 target;
}

package com.silencestudios.tinyadmirals.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class Shooter implements Component {
    public float fireRate = 0.5f;
    public Class<? extends Entity> ball;
}

package com.silencestudios.tinyadmirals.entity.land.generator;

import com.badlogic.ashley.core.Entity;

public interface LandGenerator {
    LandData generate(Entity land);
}

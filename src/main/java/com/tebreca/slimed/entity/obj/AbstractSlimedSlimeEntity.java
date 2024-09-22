package com.tebreca.slimed.entity.obj;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

public abstract class AbstractSlimedSlimeEntity extends Mob {

    protected AbstractSlimedSlimeEntity(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
    }
}

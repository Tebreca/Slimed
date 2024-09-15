package com.tebreca.slimed.inject;

import com.google.inject.Binder;
import com.google.inject.Module;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class SlimedBlockModule implements Module{
    @Override
    public void configure(Binder binder) {
        binder.bind(BlockBehaviour.Properties.class).toProvider(BlockBehaviour.Properties::of);
    }


}

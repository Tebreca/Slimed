package com.tebreca.Slimed.blockentity.obj;

import com.tebreca.Slimed.blockentity.SlimedBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GlobeBlockEntity extends BlockEntity {

    public GlobeBlockEntity(BlockPos pos, BlockState blockState) {
        super(SlimedBlockEntities.GLOBE.get(), pos, blockState);
    }

}

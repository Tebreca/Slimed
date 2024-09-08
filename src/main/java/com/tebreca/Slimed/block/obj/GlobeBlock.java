package com.tebreca.Slimed.block.obj;

import com.tebreca.Slimed.blockentity.obj.GlobeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class GlobeBlock extends Block implements EntityBlock {

    public GlobeBlock() {
        super(Properties.of().dynamicShape().noOcclusion());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new GlobeBlockEntity(blockPos, blockState);
    }
}

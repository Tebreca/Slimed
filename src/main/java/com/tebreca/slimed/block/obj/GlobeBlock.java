package com.tebreca.slimed.block.obj;

import com.tebreca.slimed.blockentity.obj.GlobeBlockEntity;
import com.tebreca.slimed.manual.ManualEntry;
import com.tebreca.slimed.manual.SlimedManualEntries;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class GlobeBlock extends Block implements EntityBlock {

    public GlobeBlock() {
        super(Properties.of().dynamicShape().noOcclusion());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new GlobeBlockEntity(blockPos, blockState);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.isEmpty()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof GlobeBlockEntity gbe) {
                gbe.increment();
                return ItemInteractionResult.SUCCESS;
            }
        } else {
            Optional<ManualEntry> match = SlimedManualEntries.match(stack);
            if (match.isPresent()) {
                BlockEntity be = level.getBlockEntity(pos);
                if (be instanceof GlobeBlockEntity gbe) {
                    gbe.setChapter(match.get());
                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        return ItemInteractionResult.CONSUME;
    }
}

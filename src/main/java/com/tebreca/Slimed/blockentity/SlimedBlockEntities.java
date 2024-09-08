package com.tebreca.Slimed.blockentity;

import com.tebreca.Slimed.SlimedMod;
import com.tebreca.Slimed.block.SlimedBlocks;
import com.tebreca.Slimed.blockentity.obj.GlobeBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SlimedBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, SlimedMod.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GlobeBlockEntity>> GLOBE = REGISTRY.register("globe", () -> BlockEntityType.Builder.of(GlobeBlockEntity::new, SlimedBlocks.GLOBE.get()).build(null));

}
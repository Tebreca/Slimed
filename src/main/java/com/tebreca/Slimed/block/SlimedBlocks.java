package com.tebreca.Slimed.block;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tebreca.Slimed.block.obj.GlobeBlock;
import com.tebreca.Slimed.inject.SlimedBlockModule;
import com.tebreca.Slimed.item.SlimedItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.tebreca.Slimed.SlimedMod.MODID;

public class SlimedBlocks {

    public static Injector injector = Guice.createInjector(new SlimedBlockModule());
    public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEM_REGISTRY = SlimedItems.REGISTRY;

    public static final DeferredBlock<GlobeBlock> GLOBE = create("goo_globe", GlobeBlock.class);


    private static <T extends Block> DeferredBlock<T> create(String name, Class<T> type, BlockBehaviour.Properties properties, boolean doItem) {
        DeferredBlock<T> val;
        if (type == Block.class) {
            val = (DeferredBlock<T>) REGISTRY.registerSimpleBlock(name, properties);
        } else {
            val = REGISTRY.register(name, () -> injector.getInstance(type));
        }
        if (doItem) {
            ITEM_REGISTRY.registerSimpleBlockItem(val);
        }
        return val;
    }

    private static <T extends Block> DeferredBlock<T> create(String name, Class<T> type, BlockBehaviour.Properties properties) {
        return create(name, type, properties, true);
    }

    private static <T extends Block> DeferredBlock<T> create(String name, Class<T> type) {
        return create(name, type, BlockBehaviour.Properties.of());
    }

    private static DeferredBlock<Block> create(String name) {
        return REGISTRY.registerSimpleBlock(name, BlockBehaviour.Properties.of());
    }

    private static DeferredBlock<Block> create(String name, BlockBehaviour.Properties properties) {
        return REGISTRY.registerSimpleBlock(name, properties);
    }

}
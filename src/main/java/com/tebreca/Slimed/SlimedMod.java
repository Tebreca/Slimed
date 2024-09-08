package com.tebreca.Slimed;

import com.tebreca.Slimed.block.SlimedBlocks;
import com.tebreca.Slimed.blockentity.SlimedBlockEntities;
import com.tebreca.Slimed.blockentity.render.GlobeBlockEntityRenderer;
import com.tebreca.Slimed.entity.SlimedEntities;
import com.tebreca.Slimed.item.SlimedItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(SlimedMod.MODID)
public class SlimedMod {
    public static final String MODID = "slimed";
    public static final String VERSION = "1.0.0 DEV";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public SlimedMod(IEventBus modEventBus, ModContainer modContainer) {
        SlimedBlocks.REGISTRY.register(modEventBus);
        SlimedBlockEntities.REGISTRY.register(modEventBus);
        SlimedItems.REGISTRY.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        // NeoForge.EVENT_BUS.register(this);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public void onEntityRegistry(RegisterEvent event) {
        event.register(BuiltInRegistries.ENTITY_TYPE.key(), SlimedEntities::register);
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(SlimedBlockEntities.GLOBE.get(), GlobeBlockEntityRenderer::new);
        }
    }
}

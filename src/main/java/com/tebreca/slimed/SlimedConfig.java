package com.tebreca.slimed;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = SlimedMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class SlimedConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue PARTY_POOPER_MODE = BUILDER.comment("Whether to disable 'lore' and other storyline related stuff in the mod. Makes the mod more straight to the point, however a bit bland.").define("partyPooperMode", false);


    static final ModConfigSpec SPEC = BUILDER.build();


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {

    }
}

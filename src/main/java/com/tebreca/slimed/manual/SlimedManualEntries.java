package com.tebreca.slimed.manual;

import com.tebreca.slimed.manual.parser.XmlPageParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static com.tebreca.slimed.SlimedMod.MODID;

public class SlimedManualEntries {

    private static final Map<ResourceLocation, ManualEntry> entries = new HashMap<>();

    public static ManualEntry _default;

    public static Optional<ManualEntry> match(ItemStack stack) {
        return entries.values().stream().filter(manualEntry -> manualEntry.matcher.test(stack)).findAny();
    }

    public static Optional<ManualEntry> find(ResourceLocation location) {
        return Optional.ofNullable(entries.get(location));
    }

    public static void loadAll() {
        _default = register("crystal_ball", () -> XmlPageParser.createFrom("/assets/slimed/manual/CrystalBall.xml", i -> false));
        register("getting_started", () -> XmlPageParser.createFrom("/assets/slimed/manual/GettingStarted.xml", i -> i.getItem().equals(Items.SLIME_BALL)));
    }

    private static ManualEntry register(String name, Supplier<ManualEntry> supplier) {
        ResourceLocation key = ResourceLocation.fromNamespaceAndPath(MODID, name);
        ManualEntry manualEntry = supplier.get();
        entries.put(key, manualEntry);
        return manualEntry;
    }

    public static ResourceLocation getKey(ManualEntry entry) {
        return entries.entrySet().stream().filter(e -> e.getValue().equals(entry)).map(Map.Entry::getKey).findAny().orElse(ResourceLocation.fromNamespaceAndPath(MODID, "crystal_ball"));
    }
}

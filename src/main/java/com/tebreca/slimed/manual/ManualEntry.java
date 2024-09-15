package com.tebreca.slimed.manual;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class ManualEntry {

    public final Page[] pages;

    public final Predicate<ItemStack> matcher;

    public ManualEntry(Predicate<ItemStack> matcher, Page... pages) {
        this.matcher = matcher;
        this.pages = pages;
    }

    public interface Page {

        void draw(PoseStack poseStack, MultiBufferSource bufferSource, int lighting, Font font, float deltaT, BlockEntityRenderDispatcher dispatcher);

    }

}

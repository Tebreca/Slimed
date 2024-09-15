package com.tebreca.slimed.blockentity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tebreca.slimed.blockentity.obj.GlobeBlockEntity;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobeBlockEntityRenderer implements BlockEntityRenderer<GlobeBlockEntity> {

    private static final Logger log = LoggerFactory.getLogger(GlobeBlockEntityRenderer.class);
    private final BlockEntityRenderDispatcher renderDispatcher;
    private final Font font;

    public GlobeBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        renderDispatcher = context.getBlockEntityRenderDispatcher();
        font = context.getFont();

    }

    @Override
    public void render(@NotNull GlobeBlockEntity entity, float deltaTick, @NotNull PoseStack stack, @NotNull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        entity.getPage().draw(stack, buffer, combinedLight, font, deltaTick, renderDispatcher);
    }

}
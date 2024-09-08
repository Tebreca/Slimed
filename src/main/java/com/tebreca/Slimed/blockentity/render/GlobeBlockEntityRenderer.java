package com.tebreca.Slimed.blockentity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tebreca.Slimed.blockentity.obj.GlobeBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import static net.neoforged.api.distmarker.Dist.CLIENT;

@OnlyIn(CLIENT)
public class GlobeBlockEntityRenderer implements BlockEntityRenderer<GlobeBlockEntity> {

    private final BlockEntityRenderDispatcher renderDispatcher;
    private final Font font;

    public GlobeBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        renderDispatcher = context.getBlockEntityRenderDispatcher();
        font = context.getFont();
    }

    @Override
    public void render(GlobeBlockEntity entity, float deltaTick, @NotNull PoseStack stack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        MutableComponent text = Component.literal("Test");
        Vec3 vec3 = Vec3.atLowerCornerOf(entity.getBlockPos());
        vec3.add(0.5, 1, 0.5);
        stack.translate(0.5, 1.5, 0.5);
        stack.mulPose(renderDispatcher.camera.rotation());
        stack.scale(0.025F, -0.025F, 0.025F);
        Matrix4f matrix4f = stack.last().pose();
        float f = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
        int j = (int) (f * 255.0F) << 24;
        float f1 = (-font.width(text) / 2f);
        font.drawInBatch(
                text, f1, 0, -1, false, matrix4f, buffer, Font.DisplayMode.NORMAL, 0, combinedLight);
    }
}

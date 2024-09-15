package com.tebreca.slimed.manual.parser.element;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tebreca.slimed.manual.ManualEntry;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import org.joml.Matrix4f;
import org.joml.Vector2f;

import java.util.Arrays;
import java.util.List;

public class VerticalElementPage implements ManualEntry.Page {

    private final PageElement[] elements;
    private final float width;
    private final int spacing;

    public VerticalElementPage(PageElement[] elements, int spacing) {
        this.elements = List.of(elements).reversed().toArray(PageElement[]::new);
        width = Arrays.stream(elements).map(PageElement::getDimension).map(Vector2f::x).reduce(Float::max).orElse(100f) / 2;
        this.spacing = spacing;
    }

    @Override
    public void draw(PoseStack stack, MultiBufferSource bufferSource, int lighting, Font font, float deltaT, BlockEntityRenderDispatcher dispatcher) {
        stack.translate(0.5, 1.2, 0.5);
        stack.mulPose(dispatcher.camera.rotation());
        stack.scale(0.010F, -0.010F, 0.010F);
        Matrix4f mat = stack.last().pose();
        mat.translate(-width, 0, 0);
        for (PageElement element : elements) {
            element.drawAt(mat, bufferSource, 0, 0, lighting);
            mat.translate(0, -(spacing + element.getDimension().y), 0);
        }
    }
}

package com.tebreca.slimed.manual.parser.element;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import org.joml.Matrix4f;
import org.joml.Vector2f;

import java.util.List;

public class TextBlockElement implements PageElement {

    private final FormattedText text;

    /**
     * Left-side indent (one char = 9 by default)
     */
    private static final int offset = 4;
    private static final int margins = 2;
    private final Font font;
    private final boolean centerText;
    private final int textWidth;
    private final int width;
    private final int height;
    private final List<FormattedCharSequence> split;

    public TextBlockElement(FormattedText text, Font font, boolean centerText) {
        this.text = text;
        this.font = font;
        this.centerText = centerText;
        split = font.split(text, lineWidth()).reversed();
        height = split.size() * font.lineHeight + 2 * margins;
        textWidth = split.stream().map(font::width).reduce(Integer::max).orElse(0);
        width = textWidth + (2 * margins);
    }

    @Override
    public void drawAt(Matrix4f mat, MultiBufferSource buffer, float x, float y, int light) {
        VertexConsumer draw = buffer.getBuffer(RenderType.debugQuads()); //TODO: texture
        mat = new Matrix4f(mat); // make clone
        mat.translate(offset, font.lineHeight, 0);

        draw.addVertex(mat, x, y, -1f).setColor(0, 0, 0, 0.4f);
        draw.addVertex(mat, x, y - height, -1f).setColor(0, 0, 0, 0.4f);
        draw.addVertex(mat, x + width, y - height, -1f).setColor(0, 0, 0, 0.4f);
        draw.addVertex(mat, x + width, y, -1F).setColor(0, 0, 0, 0.4f);
        mat.translate(margins, - margins, 0);
        for (FormattedCharSequence sequence : split) {
            y -= font.lineHeight;
            if (centerText) {
                float center = (textWidth - font.width(sequence)) / 2f;
                font.drawInBatch(sequence, x + center, y, -1, false, mat, buffer, Font.DisplayMode.NORMAL, 0, light);
            } else {
                font.drawInBatch(sequence, x, y, -1, false, mat, buffer, Font.DisplayMode.NORMAL, 0, light);
            }
        }
    }

    @Override
    public int lineWidth() {
        return PageElement.super.lineWidth() - offset;
    }

    @Override
    public Vector2f getDimension() {
        return new Vector2f(width + offset, height);
    }
}

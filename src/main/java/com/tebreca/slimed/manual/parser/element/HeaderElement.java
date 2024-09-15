package com.tebreca.slimed.manual.parser.element;

import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.util.FormattedCharSequence;
import org.joml.Matrix4f;
import org.joml.Vector2f;

public class HeaderElement implements PageElement {

    private final Font font;

    private final float sizeScalar;

    private final FormattedCharSequence text;

    private static final int bottom_margin = 2;

    public HeaderElement(Font font, float sizeScalar, FormattedCharSequence text) {
        this.font = font;
        this.sizeScalar = sizeScalar;
        this.text = text;
    }

    @Override
    public void drawAt(Matrix4f mat, MultiBufferSource buffer, float x, float y, int light) {
        Matrix4f copy = new Matrix4f(mat);
        copy.scale(sizeScalar);
        copy.translate(0, -bottom_margin, 0);
        font.drawInBatch(text, x, y, -1, true, copy, buffer, Font.DisplayMode.NORMAL, 0, light);
    }

    @Override
    public Vector2f getDimension() {
        return new Vector2f(font.width(text), font.lineHeight + bottom_margin).mul(sizeScalar);
    }
}

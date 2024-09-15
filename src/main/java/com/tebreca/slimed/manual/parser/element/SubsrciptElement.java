package com.tebreca.slimed.manual.parser.element;

import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.util.FormattedCharSequence;
import org.joml.Matrix4f;
import org.joml.Vector2f;


public class SubsrciptElement implements PageElement {

    private final FormattedCharSequence text;
    private final Font font;
    private static final float scale = 0.7f;

    public SubsrciptElement(FormattedCharSequence text, Font font) {
        this.text = text;
        this.font = font;
    }

    @Override
    public void drawAt(Matrix4f mat, MultiBufferSource buffer, float x, float y, int light) {
        Matrix4f clone = new Matrix4f(mat);
        clone.scale(scale);
        font.drawInBatch(text, x, y, -1, false, clone, buffer, Font.DisplayMode.NORMAL, 0, light);
    }

    @Override
    public Vector2f getDimension() {
        return new Vector2f(font.width(text) * scale, font.lineHeight * scale);
    }
}

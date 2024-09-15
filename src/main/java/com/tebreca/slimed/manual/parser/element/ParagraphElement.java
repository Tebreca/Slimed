package com.tebreca.slimed.manual.parser.element;

import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import org.joml.Matrix4f;
import org.joml.Vector2f;

import java.util.List;


public class ParagraphElement implements PageElement {

    private final Font font;
    private final List<FormattedCharSequence> sequences;
    private final Vector2f dimensions;

    public ParagraphElement(FormattedText text, Font font) {
        this.font = font;
        sequences = font.split(text, lineWidth()).reversed();
        dimensions = new Vector2f(sequences.stream().map(font::width).reduce(Integer::max).orElse(-1), sequences.size() * font.lineHeight);
    }

    @Override
    public void drawAt(Matrix4f mat, MultiBufferSource buffer, float x, float y, int light) {
        for (FormattedCharSequence sequence : sequences) {
            font.drawInBatch(sequence, x, y, -1, true, mat, buffer, Font.DisplayMode.NORMAL, 0, light);
            y -= font.lineHeight;
        }
    }

    @Override
    public Vector2f getDimension() {
        return dimensions;
    }
}

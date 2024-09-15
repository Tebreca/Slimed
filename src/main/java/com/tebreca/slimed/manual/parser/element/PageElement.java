package com.tebreca.slimed.manual.parser.element;

import net.minecraft.client.renderer.MultiBufferSource;
import org.joml.Matrix4f;
import org.joml.Vector2f;

public interface PageElement {

    void drawAt(Matrix4f mat, MultiBufferSource buffer, float x, float y, int light);

    Vector2f getDimension();

    default int lineWidth(){
        return 200;
    }
}
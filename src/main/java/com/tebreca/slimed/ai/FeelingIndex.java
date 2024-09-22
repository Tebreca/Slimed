package com.tebreca.slimed.ai;

public class FeelingIndex {

    /**
     * Basically a Vector6f but that doesn't actually exist, so a fixed size 6 float array is used.
     * layout
     * 0 - Happy identity
     * 1 - Sad identity
     * 2 - Anger identity
     * 3 - Energetic indentity
     * 4 - Afraid identity
     * 5 - Unknown identity (as in new things etc.)
     */
    private float[] identity;

    public FeelingIndex(float happy, float sad, float anger, float energetic, float afraid, float unknown) {
        this.identity = new float[]{happy, sad, anger, energetic, afraid, unknown};
    }

    /**
     * Simple higher=better score of the supplied {@link FeelingIndex} to this instance
     *
     * @param other Other index, to be mutiplied by this one.
     * @return the dot-product of both {@link FeelingIndex} instances, the value of this product is how well the supplied {@link FeelingIndex}
     * 'scores' relative to this index
     */
    public float score(FeelingIndex other) {
        float score = 0.0f;
        for (int i = 0; i < 6; i++) {
            score += identity[i] * other.identity[i];
        }
        return score;
    }



}

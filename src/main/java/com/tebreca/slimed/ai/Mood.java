package com.tebreca.slimed.ai;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public enum Mood {


    HAPPY(new FeelingIndex(1f, -1f, -1f, 0, 0, 0f)),
    SAD(new FeelingIndex(-1f, 1f, -0.5f, -1f, 0f, 0f)),
    FRUSTRATED(new FeelingIndex(0.5f, 0.3f, 0.5f, 0f, 0f, 0f)),
    CURIOUS(new FeelingIndex(0.5f, 0, 0, 0.2f, 0.2f, 1f)),
    SCARED(new FeelingIndex(0f, 0f, 0f, 0.1f, 1f, 0.5f)),
    ANGRY(new FeelingIndex(0f, 1f, 1f, 1f, 0, 0)),
    BORED(new FeelingIndex(0f, 0f, 0f, 1f, 0, 0)),
    THRILLED(new FeelingIndex(1f, 0f, 0f, 1f, 1f, 0f));
    private final FeelingIndex definition;

    Mood(FeelingIndex definition) {
        this.definition = definition;
    }


    public static Optional<Mood> findFor(FeelingIndex feelingIndex) {
        return Arrays.stream(Mood.values()).max(Comparator.comparingDouble(value -> value.definition.score(feelingIndex)));
    }

}

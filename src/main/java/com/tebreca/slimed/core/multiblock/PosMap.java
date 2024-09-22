package com.tebreca.slimed.core.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class PosMap<T> {

    private BlockPos origin;

    Map<BlockPos, T> map = new HashMap<>();

    public T get(Vec3i relative) {
        return get(origin.offset(relative));
    }

    public T get(BlockPos pos) {
        return map.get(pos);
    }

    public void put(BlockPos pos, T value) {
        map.put(pos, value);
    }

    public T east() {
        return map.get(origin.east());
    }

    public T east(int amount) {
        return map.get(origin.east(amount));
    }

    public T down() {
        return map.get(origin.below());
    }

    public T down(int amount) {
        return map.get(origin.below(amount));
    }

    public T up() {
        return map.get(origin.above());
    }

    public T up(int amount) {
        return map.get(origin.above(amount));
    }

    public T south() {
        return map.get(origin.south());
    }

    public T south(int amount) {
        return map.get(origin.south(amount));
    }

    public T north() {
        return map.get(origin.north());
    }

    public T north(int amount) {
        return map.get(origin.north(amount));
    }

    public T west() {
        return map.get(origin.west());
    }

    public T west(int amount) {
        return map.get(origin.west(amount));
    }

}


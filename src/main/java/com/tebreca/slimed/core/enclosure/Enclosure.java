package com.tebreca.slimed.core.enclosure;

import com.tebreca.slimed.core.multiblock.MultiBlock;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;

public interface Enclosure extends MultiBlock {

    EnclosureAttribute[] getAttributes();

    EnclosureAttribute[] getAttributes(EnclosureAttribute.Type ofType);

    void scanWhole(Level level);

    boolean isEnclosed();

    int getScanLength();

    Tag toNBT();

    void fromNBT(Tag tag, Level level);

}


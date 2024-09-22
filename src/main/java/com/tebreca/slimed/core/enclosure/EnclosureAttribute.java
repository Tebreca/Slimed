package com.tebreca.slimed.core.enclosure;

import com.tebreca.slimed.core.multiblock.MultiBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;

/**
 * An {@link EnclosureAttribute} object is an attribute of a {@link Enclosure}. This can for example be a button that slimes can press.
 * It is meant for abstraction
 */
public interface EnclosureAttribute {


    /**
     * An {@link EnclosureAttribute} is real if and only if it has any block in the world on which it depends.
     *
     * @return whether this {@link EnclosureAttribute} is real
     */
    boolean isReal();

    /**
     * Checks a block within this Attribute's real counterpart, non-real Attributes may just return -1
     *
     * @param level    World in which the Real counterpart is located
     * @param pos      Position to check
     * @param relative relative position within the {@link MultiBlock}. this <u>will</u> be null if {@link EnclosureAttribute#isMultiblock()} returns false!
     * @return an integer incidation status of the Attribute;
     * -1   error
     * 0    all okay
     * 1    requires full rescan (only if {@link EnclosureAttribute#isMultiblock()})
     */
    int checkBlock(Level level, BlockPos pos, Vec3i relative);

    /**
     * An {@link EnclosureAttribute}'s real-world counterpart is a multiblock if it consists of multiple blocks
     *
     * @return true if this {@link EnclosureAttribute} has a real world counterpart, AND this counterpart is a multiblock.
     */
    boolean isMultiblock();

    /**
     * @return null if {@link EnclosureAttribute#isMultiblock()} returns false, else an instance of {@link MultiBlock}
     * that represents this attribute's real-world counterpart
     */
    MultiBlock getMultiblock();

    EnclosureAttribute.Type[] getTypes();

    enum Type {
        INTERACTABLE,
        QOL,
        ITEM_SUPPLIER,
        FOOD,
        REDSTONE_CONTRAPTION,
        SCARY,
        SAD,
        POOL
    }
}

package com.tebreca.slimed.blockentity.obj;

import com.tebreca.slimed.blockentity.SlimedBlockEntities;
import com.tebreca.slimed.manual.ManualEntry;
import com.tebreca.slimed.manual.SlimedManualEntries;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class GlobeBlockEntity extends BlockEntity {

    private static final Logger log = LoggerFactory.getLogger(GlobeBlockEntity.class);
    ManualEntry currentEntry;
    int currentpage;

    public GlobeBlockEntity(BlockPos pos, BlockState blockState) {
        super(SlimedBlockEntities.GLOBE.get(), pos, blockState);
        currentEntry = SlimedManualEntries._default;
        currentpage = 0;
    }

    public ManualEntry.Page getPage() {
        return currentEntry.pages[currentpage];
    }

    public void increment() {
        currentpage++;
        if (currentpage >= currentEntry.pages.length) {
            currentpage = 0;
        }
        setChanged();
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag updateTag = super.getUpdateTag(registries);
        updateTag.putInt("page", currentpage);
        updateTag.putString("chapter", SlimedManualEntries.getKey(currentEntry).toString());
        return updateTag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        Optional<ManualEntry> entry = SlimedManualEntries.find(ResourceLocation.parse(tag.getString("chapter")));
        if (entry.isPresent()) {
            currentEntry = entry.get();
            currentpage = tag.getInt("page");
        }
        super.handleUpdateTag(tag, lookupProvider);
    }

    public void setChapter(ManualEntry chapter) {
        if (chapter != null && chapter.pages.length != 0) {
            this.currentEntry = chapter;
            this.currentpage = 0;
            setChanged();
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        Optional<ManualEntry> entry = SlimedManualEntries.find(ResourceLocation.parse(tag.getString("entry")));
        if (entry.isPresent()) {
            currentEntry = entry.get();
            currentpage = tag.getInt("page");
        }
        super.loadAdditional(tag, registries);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        tag.putString("entry", SlimedManualEntries.getKey(currentEntry).toString());
        tag.putInt("page", currentpage);
        super.saveAdditional(tag, registries);
    }

}

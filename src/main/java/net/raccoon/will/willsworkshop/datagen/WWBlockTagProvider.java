package net.raccoon.will.willsworkshop.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.raccoon.will.willsworkshop.WillsWorkshop;
import net.raccoon.will.willsworkshop.block.WWBlockRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class WWBlockTagProvider extends BlockTagsProvider {
    public WWBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, WillsWorkshop.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(WWBlockRegistry.ENZORITE_BLOCK.get())
                .add(WWBlockRegistry.RAW_ENZORITE_BLOCK.get())
                .add(WWBlockRegistry.DEEPSLATE_ENZORITE_ORE.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(WWBlockRegistry.DEEPSLATE_ENZORITE_ORE.get())
                .add(WWBlockRegistry.ENZORITE_BLOCK.get())
                .add(WWBlockRegistry.RAW_ENZORITE_BLOCK.get());

        tag(BlockTags.WOOL).add(WWBlockRegistry.UNTITLED_BOOK.get());

    }
}

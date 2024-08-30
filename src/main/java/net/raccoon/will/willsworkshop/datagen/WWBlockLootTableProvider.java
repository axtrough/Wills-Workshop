package net.raccoon.will.willsworkshop.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.raccoon.will.willsworkshop.block.WWBlockRegistry;
import net.raccoon.will.willsworkshop.item.WWItemRegistry;

import java.util.Set;



public class WWBlockLootTableProvider extends BlockLootSubProvider {
    protected WWBlockLootTableProvider(HolderLookup.Provider provider){
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);

    }


    @Override
    protected void generate() {
        this.dropSelf(WWBlockRegistry.ENZORITE_BLOCK.get());
        this.dropSelf(WWBlockRegistry.RAW_ENZORITE_BLOCK.get());
        this.dropSelf(WWBlockRegistry.UNTITLED_BOOK.get());

        this.add(WWBlockRegistry.DEEPSLATE_ENZORITE_ORE.get(),
                block -> createOreDrop(WWBlockRegistry.DEEPSLATE_ENZORITE_ORE.get(), WWItemRegistry.RAW_ENZORITE.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return WWBlockRegistry.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}

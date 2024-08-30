package net.raccoon.will.willsworkshop.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.raccoon.will.willsworkshop.WillsWorkshop;
import net.raccoon.will.willsworkshop.block.BlockUntitledBook;
import net.raccoon.will.willsworkshop.block.WWBlockRegistry;

public class WWBlockStateProvider extends BlockStateProvider {
    public WWBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, WillsWorkshop.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(WWBlockRegistry.ENZORITE_BLOCK);
        blockWithItem(WWBlockRegistry.RAW_ENZORITE_BLOCK);

        blockWithItem(WWBlockRegistry.DEEPSLATE_ENZORITE_ORE);

        horizontalBlock(WWBlockRegistry.UNTITLED_BOOK.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/untitled_book")));

    }


    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}


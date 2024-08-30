package net.raccoon.will.willsworkshop.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.raccoon.will.willsworkshop.WillsWorkshop;
import net.raccoon.will.willsworkshop.block.WWBlockRegistry;
import net.raccoon.will.willsworkshop.item.WWItemRegistry;

public class WWItemModelProvider extends ItemModelProvider {
    public WWItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WillsWorkshop.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(WWItemRegistry.ENZORITE.get());
        basicItem(WWItemRegistry.RAW_ENZORITE.get());

        basicItem(WWItemRegistry.TAB_LOGO.get());

        complexBlock(WWBlockRegistry.UNTITLED_BOOK.get());
    }

    public ItemModelBuilder complexBlock(Block block) {
        return withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(),
                ResourceLocation.fromNamespaceAndPath("willsworkshop", "block/untitled_book"));
    }
}



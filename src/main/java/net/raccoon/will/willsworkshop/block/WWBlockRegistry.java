package net.raccoon.will.willsworkshop.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.willsworkshop.WillsWorkshop;
import net.raccoon.will.willsworkshop.item.WWItemRegistry;

import java.util.function.Supplier;

public class WWBlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(WillsWorkshop.MODID);

    public static final DeferredBlock<Block> ENZORITE_BLOCK = registerBlock("enzorite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));

    public static final DeferredBlock<Block> RAW_ENZORITE_BLOCK = registerBlock("raw_enzorite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).sound(SoundType.ANCIENT_DEBRIS)));

    public static final DeferredBlock<Block> DEEPSLATE_ENZORITE_ORE = registerBlock("deepslate_enzorite_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(4.5f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> UNTITLED_BOOK_BLOCK = registerBlock("untitled_book_block", () -> new BlockUntitledBook(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).destroyTime(0.25f).noOcclusion()), false);



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block, boolean registerItem) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        if (registerItem) {
            registerBlockItem(name, toReturn);
        }
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn =BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        WWItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

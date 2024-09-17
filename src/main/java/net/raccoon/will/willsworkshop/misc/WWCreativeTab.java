package net.raccoon.will.willsworkshop.misc;


import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.willsworkshop.WillsWorkshop;
import net.raccoon.will.willsworkshop.block.WWBlockRegistry;
import net.raccoon.will.willsworkshop.item.WWItemRegistry;

import java.util.function.Supplier;

public class WWCreativeTab {
    public static DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WillsWorkshop.MODID);

    public static final Supplier<CreativeModeTab> WORKSHOP_TAB = CREATIVE_MODE_TAB.register("workshop_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(WWItemRegistry.TAB_LOGO.get()))
                    .title(Component.translatable("creativetab.willsworkshop.workshop_tab"))
                    .displayItems((parameters, output) -> {

                        output.accept(WWItemRegistry.ENZORITE);
                        output.accept(WWItemRegistry.RAW_ENZORITE);

                        output.accept(WWBlockRegistry.ENZORITE_BLOCK);
                        output.accept(WWBlockRegistry.RAW_ENZORITE_BLOCK);
                        output.accept(WWBlockRegistry.DEEPSLATE_ENZORITE_ORE);

                        output.accept(WWItemRegistry.UNTITLED_BOOK);


                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
package net.raccoon.will.willsworkshop.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.raccoon.will.willsworkshop.WillsWorkshop;

public class WWItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WillsWorkshop.MODID);


    public static final DeferredItem<Item> TAB_LOGO = ITEMS.register("tab_logo", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ENZORITE = ITEMS.register("enzorite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_ENZORITE = ITEMS.register("raw_enzorite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> UNTITLED_BOOK_ITEM = ITEMS.register("untitled_book_item", () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
    }
}

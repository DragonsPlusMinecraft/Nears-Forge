package net.digitalpear.nears.init;

import net.digitalpear.nears.Nears;
import net.digitalpear.nears.common.datagen.NearsRecipeGen;
import net.digitalpear.nears.common.items.FaarItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class NItems {
    public static Item createItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Nears.MOD_ID, name), item);
    }

    /*
        Fruits
     */
    public static final Item NEAR = createItem("near", new Item(new Item.Settings().food(FoodComponents.APPLE)));
    public static final Item FAAR = createItem("faar", new FaarItem(new Item.Settings().food(FoodComponents.CARROT)));
    public static final Item SOUL_BERRIES = createItem("soul_berries", new Item(new Item.Settings().food(FoodComponents.SWEET_BERRIES)));

    /*
        Foods
     */
    public static final Item SOULLESS_PASTRY = createItem("soulless_pastry", new Item(new Item.Settings().food(FoodComponents.PUMPKIN_PIE)));


    /*
        Seeds
     */
    public static final Item SOUL_BERRY_PIPS = createItem("soul_berry_pips", new AliasedBlockItem(NBlocks.SOUL_BERRY_BUSH, new Item.Settings()));
    public static final Item FAAR_SEEDS = createItem("faar_seeds", new AliasedBlockItem(NBlocks.FAAR_GROWTH, new Item.Settings()));


    public static void init() {
        NearsRecipeGen.COLOR_MELTING_MAP.put(NEAR, Items.ORANGE_DYE);
        NearsRecipeGen.COLOR_MELTING_MAP.put(FAAR, Items.CYAN_DYE);
        NearsRecipeGen.COLOR_MELTING_MAP.put(SOUL_BERRIES, Items.LIGHT_BLUE_DYE);


        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addAfter(Items.APPLE, FAAR);
            entries.addAfter(Items.APPLE, NEAR);
            entries.addAfter(Items.GLOW_BERRIES, SOUL_BERRIES);
            entries.addAfter(Items.PUMPKIN_PIE, SOULLESS_PASTRY);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.MELON_SEEDS, SOUL_BERRY_PIPS);
            entries.addAfter(Items.MELON_SEEDS, FAAR_SEEDS);
        });
    }
}
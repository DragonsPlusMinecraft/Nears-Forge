package net.digitalpear.nears.init;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Rarity;

public class NData {

    public static void registerFuels(){
        FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;
        fuelRegistry.add(NBlocks.FAAR_BUNDLE, 200);
    }
    public static void registerCompostables(){
        CompostingChanceRegistry compostingChanceRegistry = CompostingChanceRegistry.INSTANCE;
        compostingChanceRegistry.add(NItems.FAAR_SEEDS, 0.3f);
        compostingChanceRegistry.add(NItems.SOUL_BERRY_PIPS, 0.3f);
        compostingChanceRegistry.add(NBlocks.FAAR_BUNDLE, 1.0f);
        compostingChanceRegistry.add(NItems.SOUL_BERRIES, 0.5f);
        compostingChanceRegistry.add(NItems.FAAR, 0.2f);
        compostingChanceRegistry.add(NItems.NEAR, 0.4f);
    }
    public static void registerLootTableModifications(){
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (LootTables.BASTION_HOGLIN_STABLE_CHEST.equals(id) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(NItems.NEAR).weight(6).quality(Rarity.COMMON.ordinal() + 1))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F)));
                tableBuilder.pool(pool);
            }
            if (LootTables.BASTION_OTHER_CHEST.equals(id) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(NItems.NEAR).weight(2).quality(Rarity.COMMON.ordinal() + 1))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)));
                tableBuilder.pool(pool);
            }
            if (LootTables.NETHER_BRIDGE_CHEST.equals(id) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder().with(ItemEntry.builder(NItems.SOUL_BERRY_PIPS).weight(5).quality(Rarity.COMMON.ordinal() + 1))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F)));
                tableBuilder.pool(pool);
            }
        });
    }

    public static void init(){
        registerCompostables();
        registerFuels();
        registerLootTableModifications();
    }
}
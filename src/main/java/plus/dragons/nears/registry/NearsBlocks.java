package plus.dragons.nears.registry;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import plus.dragons.nears.Nears;
import plus.dragons.nears.common.block.FaarBushBlock;
import plus.dragons.nears.common.block.NearBushBlock;
import plus.dragons.nears.common.block.SoulBerryBushBlock;
import plus.dragons.nears.data.NearsBlockLoot;
import plus.dragons.nears.data.NearsBlockStates;

public class NearsBlocks {
    private static final NearsRegistrate REGISTRATE = Nears.REGISTRATE;

    public static final BlockEntry<NearBushBlock> NEAR_BUSH = REGISTRATE
        .block("near_bush", NearBushBlock::new)
        .initialProperties(() -> Blocks.SWEET_BERRY_BUSH)
        .properties(prop -> prop.sound(SoundType.ROOTS))
        .loot((loots, block) -> NearsBlockLoot.fruitBush(loots, block, NearsItems.NEAR.get()))
        .addMiscData(ProviderType.LOOT, prov -> prov.addLootAction(LootContextParamSets.BLOCK, loots ->
            loots.accept(Nears.rl("loot_modifiers/near_seeds_from_crimson_roots"), LootTable.lootTable()
                .apply(ApplyExplosionDecay.explosionDecay())
                .withPool(LootPool.lootPool()
                    .add(LootItem.lootTableItem(NearsItems.NEAR_SEEDS.get())
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2)))))))
        .blockstate(NearsBlockStates::fruitBush)
        .register();
        
    public static final BlockEntry<FaarBushBlock> FAAR_BUSH = REGISTRATE
        .block("faar_bush", FaarBushBlock::new)
        .initialProperties(() -> Blocks.SWEET_BERRY_BUSH)
        .properties(prop -> prop.sound(SoundType.ROOTS))
        .tag(BlockTags.HOGLIN_REPELLENTS)
        .loot((loots, block) -> NearsBlockLoot.fruitBush(loots, block, NearsItems.FAAR.get()))
        .addMiscData(ProviderType.LOOT, prov -> prov.addLootAction(LootContextParamSets.BLOCK, loots ->
            loots.accept(Nears.rl("loot_modifiers/faar_seeds_from_warped_roots"), LootTable.lootTable()
                .apply(ApplyExplosionDecay.explosionDecay())
                .withPool(LootPool.lootPool()
                    .add(LootItem.lootTableItem(NearsItems.FAAR_SEEDS.get())
                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2)))))))
        .blockstate(NearsBlockStates::fruitBush)
        .register();
    
    public static final BlockEntry<SoulBerryBushBlock> SOUL_BERRY_BUSH = REGISTRATE
        .block("soul_berry_bush", SoulBerryBushBlock::new)
        .initialProperties(() -> Blocks.SWEET_BERRY_BUSH)
        .properties(prop -> prop
            .sound(SoundType.ROOTS)
            .lightLevel(state -> state.getValue(SoulBerryBushBlock.AGE) * 2))
        .loot((loots, block) -> NearsBlockLoot.fruitBush(loots, block, NearsItems.SOUL_BERRIES.get()))
        .blockstate(NearsBlockStates::fruitBush)
        .register();
    
    public static void register() {
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, Tags::provide);
    }
    
    public static class Tags {
        public static final TagKey<Block> PLANTABLE_NEAR_BUSH = of("plantable/near_bush");
        public static final TagKey<Block> PLANTABLE_FAAR_BUSH = of("plantable/faar_bush");
        public static final TagKey<Block> PLANTABLE_SOUL_BERRY_BUSH = of("plantable/soul_berry_bush");
        
        private static TagKey<Block> of(String id) {
            return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(Nears.ID, id));
        }
        
        private static void provide(RegistrateTagsProvider<Block> prov) {
            prov.tag(PLANTABLE_NEAR_BUSH)
                .add(Blocks.NETHERRACK)
                .addTag(BlockTags.NYLIUM);
            prov.tag(PLANTABLE_FAAR_BUSH)
                .add(Blocks.NETHERRACK)
                .addTag(BlockTags.NYLIUM);
            prov.tag(PLANTABLE_SOUL_BERRY_BUSH)
                .addTag(BlockTags.SOUL_FIRE_BASE_BLOCKS);
        }
        
    }
    
}

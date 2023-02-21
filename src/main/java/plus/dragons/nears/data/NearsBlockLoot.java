package plus.dragons.nears.data;

import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import plus.dragons.nears.common.block.FruitBushBlock;
import plus.dragons.nears.registry.NearsBlocks;

public class NearsBlockLoot {
    
    public static <T extends FruitBushBlock> void fruitBush(RegistrateBlockLootTables loots,
                                                            T blockIn,
                                                            ItemLike fruit)
    {
        loots.add(blockIn, LootTable.lootTable()
                .apply(ApplyExplosionDecay.explosionDecay())
                .withPool(LootPool.lootPool()
                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(NearsBlocks.NEAR_BUSH.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                            .hasProperty(FruitBushBlock.AGE, 3)))
                    .add(LootItem.lootTableItem(fruit))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))).
                    apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))
                .withPool(LootPool.lootPool()
                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(NearsBlocks.NEAR_BUSH.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                            .hasProperty(FruitBushBlock.AGE, 2)))
                    .add(LootItem.lootTableItem(fruit))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
    
}

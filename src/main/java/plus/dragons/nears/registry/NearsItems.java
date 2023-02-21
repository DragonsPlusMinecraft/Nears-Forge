package plus.dragons.nears.registry;

import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.ComposterBlock;
import plus.dragons.nears.Nears;
import plus.dragons.nears.common.item.CurativeDrinkItem;
import plus.dragons.nears.common.item.FoodItem;

public class NearsItems {
    
    private static final NearsRegistrate REGISTRATE = Nears.REGISTRATE
        .creativeModeTab(() -> CreativeModeTab.TAB_FOOD);
    
    public static final ItemEntry<Item> NEAR = REGISTRATE.item("near")
        .properties(prop -> prop.food(NearsFoods.NEAR))
        .onRegister(compostable(0.3))
        .recipe((ctx, prov) -> prov.smelting(DataIngredient.items(ctx), () -> Items.ORANGE_DYE, 0.3F))
        .tag(ItemTags.PIGLIN_FOOD)
        .register();
    
    public static final ItemEntry<Item> FAAR = REGISTRATE.item("faar")
        .properties(prop -> prop.food(NearsFoods.FAAR))
        .onRegister(compostable(0.3))
        .recipe((ctx, prov) -> prov.smelting(DataIngredient.items(ctx), () -> Items.CYAN_DYE, 0.3F))
        .register();
    
    public static final ItemEntry<ItemNameBlockItem> SOUL_BERRIES = REGISTRATE
        .item("soul_berries", prop -> new ItemNameBlockItem(NearsBlocks.SOUL_BERRY_BUSH.get(), prop))
        .properties(prop -> prop.food(NearsFoods.SOUL_BERRIES))
        .tag(ItemTags.FOX_FOOD)
        .onRegister(compostable(0.3))
        .register();
    
    public static final ItemEntry<CurativeDrinkItem> NEARADE = REGISTRATE.item("nearade", prop ->
            new CurativeDrinkItem(() -> MobEffects.WEAKNESS, prop))
        .properties(prop -> prop.food(NearsFoods.NEARADE).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE))
        .recipe((ctx, prov) -> ShapelessRecipeBuilder.shapeless(ctx.get())
            .requires(Items.GLASS_BOTTLE)
            .requires(NEAR.get())
            .requires(NEAR.get())
            .requires(Items.SUGAR)
            .unlockedBy("has_near", RegistrateRecipeProvider.has(NEAR.get()))
            .save(prov))
        .addMiscData(ProviderType.LANG, prov -> prov.add("tooltip.nears.nearade.effect", "Clears Weakness Effect"))
        .register();
    
    public static final ItemEntry<CurativeDrinkItem> FAARADE = REGISTRATE.item("faarade",  prop ->
            new CurativeDrinkItem(() -> MobEffects.MOVEMENT_SLOWDOWN, prop))
        .properties(prop -> prop.food(NearsFoods.FAARADE).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE))
        .recipe((ctx, prov) -> ShapelessRecipeBuilder.shapeless(ctx.get())
            .requires(Items.GLASS_BOTTLE)
            .requires(FAAR.get())
            .requires(FAAR.get())
            .requires(Items.SUGAR)
            .unlockedBy("has_faar", RegistrateRecipeProvider.has(FAAR.get()))
            .save(prov))
        .addMiscData(ProviderType.LANG, prov -> prov.add("tooltip.nears.faarade.effect", "Clears Slowness Effect"))
        .register();
    
    public static final ItemEntry<CurativeDrinkItem> SOULDA = REGISTRATE.item("soulda",  prop ->
            new CurativeDrinkItem(() -> MobEffects.WITHER, prop))
        .properties(prop -> prop.food(NearsFoods.SOULDA).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE))
        .recipe((ctx, prov) -> ShapelessRecipeBuilder.shapeless(ctx.get())
            .requires(Items.GLASS_BOTTLE)
            .requires(SOUL_BERRIES.get())
            .requires(SOUL_BERRIES.get())
            .requires(Items.GHAST_TEAR)
            .unlockedBy("has_soul_berries", RegistrateRecipeProvider.has(FAAR.get()))
            .unlockedBy("has_ghast_tear", RegistrateRecipeProvider.has(Items.GHAST_TEAR))
            .save(prov))
        .addMiscData(ProviderType.LANG, prov -> prov.add("tooltip.nears.soulda.effect", "Clears Wither Effect"))
        .register();
    
    public static final ItemEntry<FoodItem> GLOWY_SALAD = REGISTRATE.foodItem("glowy_salad", NearsFoods.GLOWY_SALAD)
        .properties(prop -> prop.stacksTo(16).craftRemainder(Items.BOWL))
        .recipe((ctx, prov) -> ShapelessRecipeBuilder.shapeless(ctx.get())
            .requires(Items.BOWL)
            .requires(NEAR.get())
            .requires(Items.GLOW_BERRIES)
            .requires(Items.GLOWSTONE_DUST)
            .unlockedBy("has_near", RegistrateRecipeProvider.has(NEAR.get()))
            .unlockedBy("has_glow_berries", RegistrateRecipeProvider.has(Items.GLOW_BERRIES))
            .save(prov))
        .register();
    
    public static final ItemEntry<FoodItem> SOUL_SUNDAE = REGISTRATE.foodItem("soul_sundae", NearsFoods.SOUL_SUNDAE)
        .properties(prop -> prop.stacksTo(16).craftRemainder(Items.GLASS_BOTTLE))
        .recipe((ctx, prov) -> ShapelessRecipeBuilder.shapeless(ctx.get())
            .requires(Items.GLASS_BOTTLE)
            .requires(FAAR.get())
            .requires(SOUL_BERRIES.get())
            .requires(ItemTags.SOUL_FIRE_BASE_BLOCKS)
            .unlockedBy("has_faar", RegistrateRecipeProvider.has(FAAR.get()))
            .unlockedBy("has_soul_berries", RegistrateRecipeProvider.has(SOUL_BERRIES.get()))
            .save(prov))
        .register();
    
    static  {
        REGISTRATE.creativeModeTab(() -> CreativeModeTab.TAB_DECORATIONS);
    }
    
    public static final ItemEntry<ItemNameBlockItem> NEAR_SEEDS = REGISTRATE
        .item("near_seeds", prop -> new ItemNameBlockItem(NearsBlocks.NEAR_BUSH.get(), prop))
        .onRegister(compostable(0.3))
        .recipe((ctx, prov) -> prov.singleItem(DataIngredient.items(NEAR), ctx, 1, 1))
        .register();
    
    public static final ItemEntry<ItemNameBlockItem> FAAR_SEEDS = REGISTRATE
        .item("faar_seeds", prop -> new ItemNameBlockItem(NearsBlocks.FAAR_BUSH.get(), prop))
        .onRegister(compostable(0.3))
        .recipe((ctx, prov) -> prov.singleItem(DataIngredient.items(FAAR), ctx, 1, 1))
        .register();
    
    public static void register() {}
    
    private static NonNullConsumer<? super Item> compostable(double chance) {
        return item -> ComposterBlock.COMPOSTABLES.put(item, (float) chance);
    }

}

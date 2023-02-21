package plus.dragons.nears.common.loot.modifier;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ReplaceItemLootModifier extends LootModifier {
    public static final Supplier<Codec<ReplaceItemLootModifier>> CODEC = Suppliers.memoize(() ->
        RecordCodecBuilder.create(instance -> LootModifier.codecStart(instance).and(instance.group(
            ForgeRegistries.ITEMS.getCodec().fieldOf("item")
                .forGetter(x -> x.item),
            ResourceLocation.CODEC.fieldOf("loot_table")
                .forGetter(x -> x.lootTable)
        )).apply(instance, ReplaceItemLootModifier::new)));
        
    private final Item item;
    private final ResourceLocation lootTable;
    
    public ReplaceItemLootModifier(LootItemCondition[] conditionsIn, Item item, ResourceLocation lootTable) {
        super(conditionsIn);
        this.item = item;
        this.lootTable = lootTable;
    }
    
    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ObjectArrayList<ItemStack> replaced = new ObjectArrayList<>();
        for (var stack : generatedLoot) {
            if (stack.is(item))
                replaced.addAll(context.getLootTable(lootTable).getRandomItems(context));
            else
                replaced.add(stack);
        }
        return replaced;
    }
    
    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
    
}

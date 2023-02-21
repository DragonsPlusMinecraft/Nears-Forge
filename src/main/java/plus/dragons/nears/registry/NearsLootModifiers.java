package plus.dragons.nears.registry;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import plus.dragons.nears.Nears;
import plus.dragons.nears.common.loot.modifier.ReplaceItemLootModifier;

public class NearsLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTER =
        DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Nears.ID);

    public static final RegistryObject<Codec<ReplaceItemLootModifier>> REPLACE_ITEM =
        REGISTER.register("replace_item", ReplaceItemLootModifier.CODEC);
    
}

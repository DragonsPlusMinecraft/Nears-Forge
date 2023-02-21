package plus.dragons.nears.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import plus.dragons.nears.Nears;
import plus.dragons.nears.common.block.SoulBerryBushBlock;

import java.util.List;

public class NearsFeatures {
    
    public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> FEATURE_SOUL_BERRY_BUSHES;
    
    public static Holder<PlacedFeature> SOUL_BERRY_BUSHES;
    
    public static void register() {
        FEATURE_SOUL_BERRY_BUSHES = register(Nears.rl("patch_soul_berry_bushes"),
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(NearsBlocks.SOUL_BERRY_BUSH
                    .getDefaultState()
                    .setValue(SoulBerryBushBlock.AGE, 3))
                )
            )
        );
        SOUL_BERRY_BUSHES = registerPlacement(Nears.rl("patch_soul_berry_bushes"),
            FEATURE_SOUL_BERRY_BUSHES,
            PlacementUtils.FULL_RANGE,
            BiomeFilter.biome()
        );
    }
    
    private static Holder<PlacedFeature> registerPlacement(ResourceLocation id, Holder<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
        return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, id, new PlacedFeature(Holder.hackyErase(feature), List.of(modifiers)));
    }
    
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(ResourceLocation id, F feature, FC featureConfig) {
        return register(BuiltinRegistries.CONFIGURED_FEATURE, id, new ConfiguredFeature<>(feature, featureConfig));
    }
    
    @SuppressWarnings("unchecked")
    private static <V extends T, T> Holder<V> register(Registry<T> registry, ResourceLocation id, V value) {
        return (Holder<V>) BuiltinRegistries.register(registry, id, value);
    }
    
}

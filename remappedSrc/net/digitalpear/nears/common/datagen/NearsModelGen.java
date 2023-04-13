package net.digitalpear.nears.common.datagen;

import net.digitalpear.nears.init.NBlocks;
import net.digitalpear.nears.init.NItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;

public class NearsModelGen extends FabricModelProvider {
    public NearsModelGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        registerSoulBerryBush(blockStateModelGenerator);
        registerFaarPlants(blockStateModelGenerator);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(NItems.NEAR, Models.GENERATED);
        itemModelGenerator.register(NItems.SOULLESS_PASTRY, Models.GENERATED);
    }

    private void registerFaarPlants(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerItemModel(NItems.FAAR_SEEDS);
        blockStateModelGenerator.registerItemModel(NItems.FAAR);
        blockStateModelGenerator.registerSimpleCubeAll(NBlocks.FAAR_BUNDLE);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(NBlocks.FAAR_GROWTH)
                .coordinate(BlockStateVariantMap.create(Properties.AGE_3).register((stage) -> BlockStateVariant.create().put(VariantSettings.MODEL,
                        blockStateModelGenerator.createSubModel(NBlocks.FAAR_GROWTH, "_stage" + stage, Models.CROSS, TextureMap::cross)))));
    }

    private void registerSoulBerryBush(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerItemModel(NItems.SOUL_BERRIES);
        blockStateModelGenerator.registerItemModel(NItems.SOUL_BERRY_PIPS);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(NBlocks.SOUL_BERRY_BUSH)
                .coordinate(BlockStateVariantMap.create(Properties.AGE_3).register((stage) -> BlockStateVariant.create().put(VariantSettings.MODEL,
                        blockStateModelGenerator.createSubModel(NBlocks.SOUL_BERRY_BUSH, "_stage" + stage, Models.CROSS, TextureMap::cross)))));
    }
}
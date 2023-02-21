package plus.dragons.nears.data;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import plus.dragons.nears.common.block.FruitBushBlock;

public class NearsBlockStates {
    
    public static void fruitBush(DataGenContext<Block, ? extends FruitBushBlock> ctx,
                                 RegistrateBlockstateProvider prov)
    {
        prov.getVariantBuilder(ctx.get()).forAllStates(state -> {
            String name = ctx.getName() + "_stage" + state.getValue(FruitBushBlock.AGE);
            return ConfiguredModel.builder().modelFile(prov.models()
                .cross(name, prov.modLoc("block/" + name))
                .renderType("cutout")
            ).build();
        });
    }
    
}

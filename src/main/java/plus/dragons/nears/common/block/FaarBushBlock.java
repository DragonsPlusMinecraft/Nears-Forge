package plus.dragons.nears.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import plus.dragons.nears.registry.NearsBlocks;
import plus.dragons.nears.registry.NearsItems;

public class FaarBushBlock extends FruitBushBlock {
    
    public FaarBushBlock(Properties pProperties) {
        super(pProperties);
    }
    
    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(NearsBlocks.Tags.PLANTABLE_FAAR_BUSH);
    }
    
    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(NearsItems.FAAR_SEEDS.get());
    }
    
    @Override
    public ItemStack getFruitItemStack(boolean ripe, BlockState state, Level level, BlockPos pos) {
        int baseCount = 1 + level.random.nextInt(2);
        return new ItemStack(NearsItems.FAAR.get(), baseCount + (ripe ? 1 : 0));
    }
    
}

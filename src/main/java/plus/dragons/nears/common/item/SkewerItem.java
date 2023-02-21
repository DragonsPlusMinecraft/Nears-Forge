package plus.dragons.nears.common.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SkewerItem extends RemainingFoodItem {
    
    public SkewerItem(Properties settings) {
        super(settings);
    }
    
    @Override
    protected ItemStack getEatingRemainingItem(ItemStack stack) {
        return new ItemStack(Items.STICK);
    }
    
}

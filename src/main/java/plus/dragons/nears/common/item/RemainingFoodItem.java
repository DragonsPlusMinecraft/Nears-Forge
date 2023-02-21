package plus.dragons.nears.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RemainingFoodItem extends FoodItem {
    
    public RemainingFoodItem(Properties properties) {
        super(properties);
    }
    
    protected ItemStack getEatingRemainingItem(ItemStack stack) {
        return stack.getCraftingRemainingItem();
    }
    
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        ItemStack container = this.getEatingRemainingItem(stack);
        
        super.finishUsingItem(stack, world, user);
        
        if (stack.isEmpty()) {
            return container;
        } else {
            if (user instanceof Player playerEntity && !((Player)user).getAbilities().instabuild) {
                if (!playerEntity.getInventory().add(container)) {
                    playerEntity.drop(container, false);
                }
            }
            return stack;
        }
    }
    
}

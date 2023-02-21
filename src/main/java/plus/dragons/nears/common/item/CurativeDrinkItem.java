package plus.dragons.nears.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class CurativeDrinkItem extends RemainingFoodItem {
    private static final int USE_DURATION = 40;
    private final Supplier<MobEffect> effect;

    public CurativeDrinkItem(Supplier<MobEffect> effect, Properties settings) {
        super(settings);
        this.effect = effect;
    }
    
    @Override
    protected boolean hasEffectTooltip(ItemStack stack, @Nullable Level level) {
        return true;
    }
    
    @Override
    protected void appendEffectTooltip(ItemStack stack, @Nullable Level level, List<Component> tooltips, TooltipFlag advanced) {
        super.appendEffectTooltip(stack, level, tooltips, advanced);
        tooltips.add(Component
            .translatable(Util.makeDescriptionId("tooltip", ForgeRegistries.ITEMS.getKey(this)) + ".effect")
            .withStyle(ChatFormatting.BLUE));
    }
    
    @Override
    protected void applySpecialEffect(ItemStack stack, Level world, LivingEntity user) {
        var effectInstance = user.getEffect(effect.get());
        if (effectInstance != null && !MinecraftForge.EVENT_BUS.post(new MobEffectEvent.Remove(user, effectInstance))) {
            user.removeEffect(effectInstance.getEffect());
        }
    }
    
    public int getUseDuration(ItemStack stack) {
        return USE_DURATION;
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }
    
}

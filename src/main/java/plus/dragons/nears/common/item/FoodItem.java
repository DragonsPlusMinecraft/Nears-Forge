package plus.dragons.nears.common.item;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import plus.dragons.nears.mixin.FoodPropertiesAccessor;

import java.util.List;
import java.util.Map;

public class FoodItem extends Item {
    protected boolean hasEffects;
    
    @SuppressWarnings("deprecation")
    public FoodItem(Properties properties) {
        super(properties);
        var food = this.getFoodProperties();
        if (food == null)
            throw new IllegalArgumentException("FoodItem must be edible");
        this.hasEffects = !((FoodPropertiesAccessor)food).getRawEffects().isEmpty();
    }
    
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }
    
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(world, user, hand);
    }
    
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        this.applySpecialEffect(stack, world, user);
        return super.finishUsingItem(stack, world, user);
    }
    
    protected void applySpecialEffect(ItemStack stack, Level world, LivingEntity user) {
    
    }
    
    protected boolean hasEffectTooltip(ItemStack stack, @Nullable Level level) {
        return hasEffects;
    }
    
    protected void appendEffectTooltip(ItemStack stack, @Nullable Level level, List<Component> tooltips, TooltipFlag advanced) {
        if (level == null || !level.isClientSide)
            return;
        
        if (!stack.isEdible())
            return;
        
        var food = stack.getFoodProperties(Minecraft.getInstance().player);
        assert food != null;
        
        List<MobEffectInstance> effects = food.getEffects().stream().map(Pair::getFirst).toList();
        List<Pair<Attribute, AttributeModifier>> attributes = Lists.newArrayList();
        
        for (MobEffectInstance effectInstance : effects) {
            var effectName = Component.translatable(effectInstance.getDescriptionId());
            MobEffect effect = effectInstance.getEffect();
            Map<Attribute, AttributeModifier> map = effect.getAttributeModifiers();
            if (!map.isEmpty()) {
                for(Map.Entry<Attribute, AttributeModifier> entry : map.entrySet()) {
                    AttributeModifier rawModifier = entry.getValue();
                    AttributeModifier modifier = new AttributeModifier(
                        rawModifier.getName(),
                        effect.getAttributeModifierValue(effectInstance.getAmplifier(), rawModifier),
                        rawModifier.getOperation());
                    attributes.add(new Pair<>(entry.getKey(), modifier));
                }
            }
        
            if (effectInstance.getAmplifier() > 0) {
                effectName = Component.translatable("potion.withAmplifier",
                    effectName,
                    Component.translatable("potion.potency." + effectInstance.getAmplifier()));
            }
        
            if (effectInstance.getDuration() > 20) {
                effectName = Component.translatable("potion.withDuration",
                    effectName,
                    MobEffectUtil.formatDuration(effectInstance, 1F));
            }
        
            tooltips.add(effectName.withStyle(effect.getCategory().getTooltipFormatting()));
        }
        
        if (!attributes.isEmpty()) {
            tooltips.add(CommonComponents.EMPTY);
            var whenUse = (this.getUseAnimation(stack) == UseAnim.DRINK
                ? Component.translatable("potion.whenDrank")
                : Component.translatable("food.whenAte")).withStyle(ChatFormatting.DARK_PURPLE);
            tooltips.add(whenUse);
        
            for (Pair<Attribute, AttributeModifier> pair : attributes) {
                AttributeModifier modifier = pair.getSecond();
                double amount = modifier.getAmount();
                double displayAmount;
                if (modifier.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE &&
                    modifier.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL)
                    displayAmount = amount;
                else
                    displayAmount = amount * 100.0D;
            
                if (amount > 0.0D) {
                    tooltips.add(Component.translatable("attribute.modifier.plus." + modifier.getOperation().toValue(),
                        ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(displayAmount),
                        Component.translatable(pair.getFirst().getDescriptionId())
                    ).withStyle(ChatFormatting.BLUE));
                } else if (amount < 0.0D) {
                    displayAmount *= -1.0D;
                    tooltips.add(Component.translatable("attribute.modifier.take." + modifier.getOperation().toValue(),
                        ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(displayAmount),
                        Component.translatable(pair.getFirst().getDescriptionId())
                    ).withStyle(ChatFormatting.RED));
                }
            }
        }
    }
    
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltips, TooltipFlag advanced) {
        super.appendHoverText(stack, level, tooltips, advanced);
        if (this.hasEffectTooltip(stack, level))
            this.appendEffectTooltip(stack, level, tooltips, advanced);
    }
    
}

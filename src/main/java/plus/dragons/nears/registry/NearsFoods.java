package plus.dragons.nears.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class NearsFoods {
    
    public static final FoodProperties NEAR = food(2, 0.1).build();
    public static final FoodProperties FAAR = food(2, 0.1).build();
    public static final FoodProperties SOUL_BERRIES = food(1, 0.4).build();
    
    public static final FoodProperties NEARADE = drink()
        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600), 1.0F).build();
    
    public static final FoodProperties FAARADE = drink()
        .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600), 1.0F).build();
    
    public static final FoodProperties SOULDA = drink()
        .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0F).build();
    
    public static final FoodProperties GLOWY_SALAD = food(4, 0.4)
        .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 1200), 1.0F)
        .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 600), 0.8F).build();
    
    public static final FoodProperties SOUL_SUNDAE = food(4, 0.4)
        .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200), 1.0F)
        .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 600), 0.8F).build();
    
    private static FoodProperties.Builder food(int nutrition, double saturationMod) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationMod((float) saturationMod);
    }
    
    private static FoodProperties.Builder drink() {
        return new FoodProperties.Builder().alwaysEat();
    }
    
}

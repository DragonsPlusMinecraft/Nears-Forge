package plus.dragons.nears.registry;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.ItemBuilder;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;

public class NearsRegistrate extends AbstractRegistrate<NearsRegistrate> {
    
    protected NearsRegistrate(String modid) {
        super(modid);
    }
    
    public static NearsRegistrate create(String modid) {
        return new NearsRegistrate(modid);
    }
    
    @Override
    public NearsRegistrate registerEventListeners(IEventBus bus) {
        return super.registerEventListeners(bus);
    }
    
    public ItemBuilder<Item, NearsRegistrate> item(String name) {
        return this.item(name, Item::new);
    }
    
    public ItemBuilder<Item, NearsRegistrate> foodItem(String name, FoodProperties food) {
        return this.item(name, Item::new).properties(prop -> prop.food(food));
    }
    
}

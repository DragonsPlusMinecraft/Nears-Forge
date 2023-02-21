package plus.dragons.nears;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import plus.dragons.nears.registry.*;

@Mod(Nears.ID)
public class Nears {
	public static final String ID = "nears";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final NearsRegistrate REGISTRATE = NearsRegistrate.create(ID);
	
	public Nears() {
		NearsBlocks.register();
		NearsItems.register();
		
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		REGISTRATE.registerEventListeners(modBus);
		NearsLootModifiers.REGISTER.register(modBus);
		
		modBus.addListener(this::setup);
	}
	
	public void setup(final FMLCommonSetupEvent event) {
		event.enqueueWork(NearsFeatures::register);
	}
	
	public static ResourceLocation rl(String path) {
		return new ResourceLocation(ID, path);
	}
	
}

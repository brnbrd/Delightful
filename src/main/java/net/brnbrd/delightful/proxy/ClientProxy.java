package net.brnbrd.delightful.proxy;

import net.brnbrd.delightful.compat.AppleSkinEventHandler;
import net.brnbrd.delightful.compat.Modid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy extends CommonProxy {
	@Override
	public void start() {
		super.start();
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void setupClient(FMLClientSetupEvent e) {
		if (Modid.AS.loaded()) {
			MinecraftForge.EVENT_BUS.register(new AppleSkinEventHandler());
		}
	}
}
package net.brdle.delightful.proxy;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy extends CommonProxy {

    @Override
    public void start() {
        super.start();
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        //final IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        modBus.register(this);
    }

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent e){
        //ItemBlockRenderTypes.setRenderLayer(DelightfulBlocks.PIZZA_STONE.get(), RenderType.cutout());
        //ItemBlockRenderTypes.setRenderLayer(DelightfulBlocks.PIZZA_PEEL.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers e) {
        //e.registerBlockEntityRenderer(DelightfulTiles.PIZZA_STONE.get(), PizzaStoneRenderer::new);
        //e.registerBlockEntityRenderer(DelightfulTiles.PIZZA_PEEL.get(), PizzaPeelRenderer::new);
    }
}

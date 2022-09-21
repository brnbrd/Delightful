package net.brdle.delightful.proxy;

import net.brdle.delightful.common.block.DelightfulBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
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
    public void setupClient(FMLClientSetupEvent e){
        ItemBlockRenderTypes.setRenderLayer(DelightfulBlocks.WILD_SALMONBERRIES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(DelightfulBlocks.SALMONBERRY_BUSH.get(), RenderType.cutout());
    }
}

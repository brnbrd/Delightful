package net.brdle.delightful.proxy;

import net.brdle.delightful.common.Events;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.loot.DelightfulLootItemConditions;
import net.brdle.delightful.common.loot.DelightfulLootModifiers;
import net.brdle.delightful.data.Generators;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CommonProxy {

    public void start() {
        final var modBus = FMLJavaModLoadingContext.get().getModEventBus();
        //final IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        //DelightfulTiles.create(modBus);
        modBus.register(Events.class);
        modBus.register(Generators.class);
        DelightfulBlocks.create(modBus);
        DelightfulItems.create(modBus);
        DelightfulLootItemConditions.create(modBus);
        DelightfulLootModifiers.create(modBus);
    }

    /*@SubscribeEvent
    public void setup(FMLCommonSetupEvent e) {
        e.enqueueWork(() -> {
            //Flammables
        });
    }*/
}
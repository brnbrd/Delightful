package net.brdle.delightful.proxy;

import net.brdle.delightful.common.Events;
import net.brdle.delightful.common.ForgeEvents;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.loot.DelightfulLootItemConditions;
import net.brdle.delightful.common.loot.DelightfulLootModifiers;
import net.brdle.delightful.data.Generators;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CommonProxy {

    public void start() {
        final var modBus = FMLJavaModLoadingContext.get().getModEventBus();
        final var forgeBus = MinecraftForge.EVENT_BUS;
        //DelightfulTiles.create(modBus);
        forgeBus.register(ForgeEvents.class);
        modBus.register(Events.class);
        modBus.register(Generators.class);
        DelightfulBlocks.create(modBus);
        DelightfulItems.create(modBus);
        DelightfulLootItemConditions.create(modBus);
        DelightfulLootModifiers.create(modBus);
    }
}
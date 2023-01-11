package net.brnbrd.delightful.proxy;

import net.brnbrd.delightful.common.Events;
import net.brnbrd.delightful.common.ForgeEvents;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.common.loot.DelightfulLootItemConditions;
import net.brnbrd.delightful.common.loot.DelightfulLootModifiers;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.compat.RootsCompat;
import net.brnbrd.delightful.data.gen.Generators;
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
        Knives.create();
        DelightfulItems.create(modBus);
        DelightfulLootItemConditions.create(modBus);
        DelightfulLootModifiers.create(modBus);
        if (Mods.loaded(Mods.RC)) {
            (new RootsCompat()).init();
        }
    }
}
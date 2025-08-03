package net.brnbrd.delightful.proxy;

import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.common.events.ForgeEvents;
import net.brnbrd.delightful.common.events.KnifeEvents;
import net.brnbrd.delightful.common.events.ModEvents;
import net.brnbrd.delightful.common.events.PieEvents;
import net.brnbrd.delightful.common.fluid.DelightfulFluids;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.common.loot.DelightfulLootItemConditions;
import net.brnbrd.delightful.common.loot.DelightfulLootModifiers;
import net.brnbrd.delightful.common.world.DelightfulPlacementModifiers;
import net.brnbrd.delightful.data.gen.Generators;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CommonProxy {
	public void start() {
		final var modBus = FMLJavaModLoadingContext.get().getModEventBus();
		final var forgeBus = MinecraftForge.EVENT_BUS;
		forgeBus.register(new ForgeEvents());
		forgeBus.register(new KnifeEvents());
		forgeBus.register(new PieEvents());
		modBus.register(new ModEvents());
		modBus.register(Generators.class);
		DelightfulBlocks.create(modBus);
		DelightfulFluids.create(modBus);
		Knives.create();
		DelightfulItems.create(modBus);
		DelightfulLootItemConditions.create(modBus);
		DelightfulLootModifiers.create(modBus);
		DelightfulPlacementModifiers.create(modBus);
	}
}
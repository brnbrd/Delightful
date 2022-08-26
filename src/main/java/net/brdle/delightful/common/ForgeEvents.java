package net.brdle.delightful.common;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.FurnaceFuelItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;
import java.util.List;

@Mod.EventBusSubscriber(modid= Delightful.MODID)
public class ForgeEvents {

	private static final List<String> portedMods = List.of("coppersdelight", "steelsdelight", "enderitesdelight");

	// Remaps any block or item ids to use "delightful" namespace
	@SubscribeEvent
	public static void onMissingBlockMappings(MissingMappingsEvent e) {
		for (var mapping : e.getAllMappings(ForgeRegistries.BLOCKS.getRegistryKey())) {
			if (portedMods.contains(mapping.getKey().getNamespace())) {
				var remap = new ResourceLocation(Delightful.MODID, mapping.getKey().getPath());
				if (ForgeRegistries.BLOCKS.containsKey(remap)) {
					mapping.remap(ForgeRegistries.BLOCKS.getValue(remap));
				} else {
					mapping.warn();
				}
			}
		}
		for (var mapping : e.getAllMappings(ForgeRegistries.ITEMS.getRegistryKey())) {
			if (portedMods.contains(mapping.getKey().getNamespace())) {
				var remap = new ResourceLocation(Delightful.MODID, mapping.getKey().getPath());
				if (ForgeRegistries.ITEMS.containsKey(remap)) {
					mapping.remap(ForgeRegistries.ITEMS.getValue(remap));
				} else {
					mapping.warn();
				}
			} else if (mapping.getKey().getNamespace().equals(Delightful.MODID) &&
				mapping.getKey().getPath().equals("salmonberry")) {
				mapping.remap(DelightfulItems.SALMONBERRIES.get());
			}
		}
	}

	@SubscribeEvent
	public static void burnTime(FurnaceFuelBurnTimeEvent e) {
		if (e.getItemStack().getItem() instanceof FurnaceFuelItem fuel) {
			e.setBurnTime(fuel.getFuelTime());
		}
	}
}

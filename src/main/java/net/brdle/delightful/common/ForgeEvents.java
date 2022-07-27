package net.brdle.delightful.common;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.brdle.delightful.common.item.FurnaceFuelItem;
import net.brdle.delightful.common.world.DelightfulWildCropGeneration;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= Delightful.MODID)
public class ForgeEvents {

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent e) {
		if (e.getName() != null &&
			e.getCategory() == Biome.BiomeCategory.FOREST &&
				DelightfulConfig.CHANCE_WILD_SALMONBERRIES.get() > 0) {
					e.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, DelightfulWildCropGeneration.PATCH_WILD_SALMONBERRIES);
		}
	}

	@SubscribeEvent
	public static void burnTime(FurnaceFuelBurnTimeEvent e) {
		if (e.getItemStack().getItem() instanceof FurnaceFuelItem fuel) {
			e.setBurnTime(fuel.getFuelTime());
		}
	}
}

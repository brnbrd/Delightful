package net.brdle.delightful.common;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.brdle.delightful.common.item.FurnaceFuelItem;
import net.brdle.delightful.common.world.DelightfulWildCropGeneration;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= Delightful.MODID)
public class ForgeEvents {

	private static void veg(BiomeGenerationSettingsBuilder b, Holder<PlacedFeature> feature) {
		b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, feature);
	}

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent e) {
		switch (e.getCategory()) {
			case FOREST -> {
				if (DelightfulConfig.CHANCE_WILD_SALMONBERRIES.get() > 0) {
					veg(e.getGeneration(), DelightfulWildCropGeneration.PATCH_WILD_SALMONBERRIES);
				}
			}
			case PLAINS -> {
				if (DelightfulConfig.CHANCE_MINI_MELON.get() > 0) {
					veg(e.getGeneration(), DelightfulWildCropGeneration.PATCH_MINI_MELON);
				}
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

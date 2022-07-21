package net.brdle.delightful.common;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.brdle.delightful.common.world.DelightfulWildCropGeneration;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= Delightful.MODID)
public class ForgeEvents {

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent e) {
		Delightful.getLogger().error("A");
		if (e.getName() != null) {
			Delightful.getLogger().error("B");
			if (e.getName().getPath().toLowerCase().contains("forest")) {
				Delightful.getLogger().error("C");
				if (DelightfulConfig.CHANCE_WILD_SALMONBERRIES.get() > 0) {
					Delightful.getLogger().error("D");
					e.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, DelightfulWildCropGeneration.PATCH_WILD_SALMONBERRIES);
				}
			}
		}
	}
}

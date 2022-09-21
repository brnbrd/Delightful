package net.brdle.delightful.common;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.FurnaceFuelItem;
import net.brdle.delightful.common.world.DelightfulWildCropGeneration;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.List;

@Mod.EventBusSubscriber(modid= Delightful.MODID)
public class ForgeEvents {

	private static final List<String> portedMods = List.of("coppersdelight", "steelsdelight", "enderitesdelight");

	// Remaps any blocks ids to use "delightful" namespace
	@SubscribeEvent
	public static void onMissingBlockMappings(RegistryEvent.MissingMappings<Block> e) {
		for (var mapping : e.getAllMappings()) {
			if (portedMods.contains(mapping.key.getNamespace())) {
				var remap = new ResourceLocation(Delightful.MODID, mapping.key.getPath());
				if (ForgeRegistries.BLOCKS.containsKey(remap)) {
					mapping.remap(ForgeRegistries.BLOCKS.getValue(remap));
				} else {
					mapping.warn();
				}
			}
		}
	}

	// Remaps any item ids to use "delightful" namespace
	@SubscribeEvent
	public static void onMissingItemMappings(RegistryEvent.MissingMappings<Item> e) {
		for (var mapping : e.getAllMappings()) {
			if (portedMods.contains(mapping.key.getNamespace())) {
				var remap = new ResourceLocation(Delightful.MODID, mapping.key.getPath());
				if (ForgeRegistries.ITEMS.containsKey(remap)) {
					mapping.remap(ForgeRegistries.ITEMS.getValue(remap));
				} else {
					mapping.warn();
				}
			} else if (mapping.key.getNamespace().equals(Delightful.MODID) &&
				mapping.key.getPath().equals("salmonberry")) {
				mapping.remap(DelightfulItems.SALMONBERRIES.get());
			}
		}
	}

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

	@SubscribeEvent
	public static void fieryToolSetFire(LivingAttackEvent e) {
		if (e.getSource().getEntity() instanceof LivingEntity living &&
			living.getMainHandItem().is(DelightfulItems.FIERY_KNIFE.get()) &&
			!e.getEntity().fireImmune()) {
			e.getEntity().setSecondsOnFire(1);
		}
	}

	@SubscribeEvent
	public static void onKnightmetalToolDamage(LivingHurtEvent e) {
		LivingEntity target = e.getEntityLiving();
		if (!target.getLevel().isClientSide() && e.getSource().getDirectEntity() instanceof LivingEntity living) {
			ItemStack weapon = living.getMainHandItem();
			if (!weapon.isEmpty()) {
				if (target.getArmorValue() > 0 && weapon.is(DelightfulItems.KNIGHTMETAL_KNIFE.get())) {
					if (target.getArmorCoverPercentage() > 0) {
						int moreBonus = (int) (2 * target.getArmorCoverPercentage());
						e.setAmount(e.getAmount() + moreBonus);
					} else {
						e.setAmount(e.getAmount() + 2);
					}
					((ServerLevel) target.getLevel()).getChunkSource().broadcastAndSend(target, new ClientboundAnimatePacket(target, 5));
				}
			}
		}
	}
}
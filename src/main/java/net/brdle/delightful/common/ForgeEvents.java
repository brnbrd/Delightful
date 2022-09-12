package net.brdle.delightful.common;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.FurnaceFuelItem;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
		LivingEntity target = e.getEntity();
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
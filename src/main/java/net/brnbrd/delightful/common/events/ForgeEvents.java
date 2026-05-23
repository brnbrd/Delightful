package net.brnbrd.delightful.common.events;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.common.block.ISliceable;
import net.brnbrd.delightful.common.fluid.DelightfulFluids;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.compat.SOBCompat;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;
import java.util.List;
import java.util.Objects;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;

public class ForgeEvents {
	@SubscribeEvent
	void onMissingMappings(MissingMappingsEvent e) {
		for (var map : e.getMappings(Registries.ITEM, Delightful.MODID)) {
			final ResourceLocation crystalline = Util.rl(Modid.EP, "crystalline_knife");
			if (checkRoeMap(map)) {
				map.remap(DelightfulItems.AGED_ROE.get());
			} else if (map.getKey().getPath().equals("crystalline_knife") && Util.itemExists(crystalline)) {
				map.remap(Util.item(crystalline));
			}
		}
		for (var map : e.getMappings(Registries.BLOCK, Delightful.MODID)) {
			if (map.getKey().getPath().equals("pumpkin_pie")) {
				map.remap(ModBlocks.PUMPKIN_PIE.get());
			}
		}
		e.getMappings(Registries.FLUID, Delightful.MODID).stream()
			.filter(this::checkRoeMap)
			.forEach(map -> map.remap(DelightfulFluids.AGED_ROE.get()));
		for (var map : e.getMappings(Registries.ITEM, Delightful.MODID)) {
			if (map.getKey().getPath().equals("pumpkin_pie_slice")) {
				map.remap(ModItems.PUMPKIN_PIE_SLICE.get());
			}
		}
	}

	private boolean checkRoeMap(final MissingMappingsEvent.Mapping<?> mapping) {
		final String path = mapping.getKey().getPath();
		return path.equals("aged_fish_roe") || path.equals("aged_prawn_roe");
	}

	@SubscribeEvent
	void onEatEffectProvider(LivingEntityUseItemEvent.Finish e) {
		if (e.getResult() != Event.Result.DENY) {
			final ItemStack stack = e.getItem();
			final LivingEntity entity = e.getEntity();
			if (
				Modid.CAD.loaded() &&
				stack.is(DelightfulItemTags.ROTTEN) &&
				entity.getRandom().nextBoolean() // 50% chance
			) {
				int duration = 160;
				if (stack.is(Modid.RL.item("rotten_chunk"))) {
					duration = 1800;
				}
				Util.addEffect(entity, Modid.CAD.effect("rotten"), duration, 0);
			} else if (
				Modid.SOB.loaded() &&
				stack.is(DelightfulItemTags.SPITE)
			) {
				int duration = 200;
				if (stack.is(Modid.ECO.item("cooked_prickly_pear"))) {
					duration = 400;
				}
				Util.addEffect(entity, SOBCompat.getSpite(), duration, 0);
			}
		}
	}

	@SubscribeEvent
	void onWanderingTrader(WandererTradesEvent e) {
		final List<VillagerTrades.ItemListing> trades = e.getGenericTrades();
		if (Util.enabled(DelightfulItems.SALMONBERRIES) && Util.enabled(DelightfulItems.SALMONBERRY_PIPS)) {
			trades.add((ent, r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), Util.getStack(DelightfulItems.SALMONBERRY_PIPS), 5, 1, 1));
		}
		if (Util.enabled(DelightfulItems.CANTALOUPE) && Util.enabled(DelightfulItems.CANTALOUPE_SLICE)) {
			trades.add((ent, r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3), Util.getStack(DelightfulItems.CANTALOUPE_SLICE, 8), 5, 1, 1));
		}
		if (
			Util.enabled(DelightfulItems.CANTALOUPE) &&
			Util.enabled(DelightfulItems.STUFFED_CANTALOUPE_BLOCK) &&
			Util.enabled(DelightfulItems.STUFFED_CANTALOUPE)
		) {
			trades.add((ent, r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), Util.getStack(DelightfulItems.STUFFED_CANTALOUPE_BLOCK, 1), 3, 1, 1));
		}
	}

	// Crouch right-click slicing
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	void onInteract(PlayerInteractEvent.RightClickBlock e) {
		Level level = e.getLevel();
		BlockPos pos = e.getPos();
		if (e.getItemStack().is(ModTags.Items.KNIVES) && !e.getEntity().isCrouching()) {
			Block current = level.getBlockState(pos).getBlock();
			Block newBlock;
			if (current == Blocks.MELON) {
				newBlock = DelightfulBlocks.SLICED_MELON.get();
			} else if (current == Blocks.PUMPKIN) {
				newBlock = DelightfulBlocks.SLICED_PUMPKIN.get();
			} else if (
				Objects.equals(ForgeRegistries.BLOCKS.getKey(current), Modid.UG.rl("carved_gloomgourd"))
			) {
				newBlock = DelightfulBlocks.SLICED_GLOOMGOURD.get();
			} else {
				return;
			}
			if (newBlock instanceof ISliceable slice) {
				boolean client = level.isClientSide();
				e.setCancellationResult(InteractionResult.sidedSuccess(client));
				e.setCanceled(true);
				if (!client) {
					slice(newBlock.defaultBlockState(), slice.getSliceItem(), level, pos, e.getEntity(), e.getHand());
				}
			}
		}
	}

	// Replaces Block in world, drops Item, cancels interaction event
	void slice(BlockState block, ItemStack slice, Level world, BlockPos pos, Player player, InteractionHand hand) {
		world.setBlock(pos, block, 2);
		Util.dropOrGive(slice, world, pos, player);
		world.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1F, 1F);
		player.getItemInHand(hand).hurtAndBreak(1, player, onBroken -> {});
	}
}
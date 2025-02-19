package net.brnbrd.delightful.common.events;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.common.block.ISliceable;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.compat.BrewinChewinCompat;
import net.brnbrd.delightful.compat.CasualnessDelightCompat;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
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
import vectorwing.farmersdelight.common.tag.ForgeTags;
import java.util.List;
import java.util.Objects;

public class ForgeEvents {
	@SubscribeEvent
	void onEatEffectProvider(LivingEntityUseItemEvent.Finish e) {
		if (e.getResult() != Event.Result.DENY) {
			if (
				Modid.BG.loaded() && // Berry Good loaded
				Util.itemStackIs(e.getItem(), BrewinChewinCompat.glowMarmalade) // Brewin' & Chewin' item exists
			) {
				Util.addEffect(e.getEntity(), MobEffects.GLOWING, 3000, 0);
			} else if (
				Modid.CAD.loaded() &&
				e.getItem().is(DelightfulItemTags.ROTTEN) &&
				e.getEntity().getRandom().nextBoolean() // 50% chance
			) {
				int duration = 160;
				if (e.getItem().is(Modid.RL.item("rotten_chunk"))) {
					duration = 1800;
				}
				Util.addEffect(e.getEntity(), CasualnessDelightCompat.getRotten(), duration, 0);
			}
		}
	}

	@SubscribeEvent
	void onWanderingTrader(WandererTradesEvent e) {
		List<VillagerTrades.ItemListing> trades = e.getGenericTrades();
		if (Util.enabled(DelightfulItems.SALMONBERRIES) && Util.enabled(DelightfulItems.SALMONBERRY_PIPS)) {
			trades.add((ent, r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), Util.gs(DelightfulItems.SALMONBERRY_PIPS), 5, 1, 1));
		}
		if (Util.enabled(DelightfulItems.CANTALOUPE) && Util.enabled(DelightfulItems.CANTALOUPE_SLICE)) {
			trades.add((ent, r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3), Util.gs(DelightfulItems.CANTALOUPE_SLICE, 8), 5, 1, 1));
		}
		if (
			Util.enabled(DelightfulItems.CANTALOUPE) &&
			Util.enabled(DelightfulItems.STUFFED_CANTALOUPE_BLOCK) &&
			Util.enabled(DelightfulItems.STUFFED_CANTALOUPE)
		) {
			trades.add((ent, r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 5), Util.gs(DelightfulItems.STUFFED_CANTALOUPE_BLOCK, 1), 3, 1, 1));
		}
	}

	// Crouch right-click slicing
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	void onInteract(PlayerInteractEvent.RightClickBlock e) {
		Level level = e.getLevel();
		BlockPos pos = e.getPos();
		if (e.getItemStack().is(ForgeTags.TOOLS_KNIVES) && !e.getEntity().isCrouching()) {
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
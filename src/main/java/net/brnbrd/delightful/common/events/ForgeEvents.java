package net.brnbrd.delightful.common.events;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.common.block.SlicedGourdBlock;
import net.brnbrd.delightful.common.block.SlicedMelonBlock;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import java.util.List;
import java.util.Objects;

public class ForgeEvents {

	@SubscribeEvent
	void onWanderingTrader(WandererTradesEvent e) {
		List<VillagerTrades.ItemListing> trades = e.getGenericTrades();
		if (Util.enabled(DelightfulItems.SALMONBERRIES) && Util.enabled(DelightfulItems.SALMONBERRY_PIPS)) {
			trades.add((ent, r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 1), Util.gs(DelightfulItems.SALMONBERRY_PIPS), 5, 1, 1));
		}
		if (Util.enabled(DelightfulItems.CANTALOUPE) && Util.enabled(DelightfulItems.CANTALOUPE_SLICE)) {
			trades.add((ent, r) -> new MerchantOffer(new ItemStack(Items.EMERALD, 2), new ItemStack(DelightfulItems.CANTALOUPE_SLICE.get(), 8), 5, 1, 1));
		}
	}

	// Right slick slicing an Item from a Block
	@SubscribeEvent (priority = EventPriority.HIGHEST)
	void onInteract(PlayerInteractEvent.RightClickBlock e) {
		Level world = e.getLevel();
		BlockPos pos = e.getPos();
		if (e.getItemStack().is(ForgeTags.TOOLS_KNIVES)) {
			BlockState current = world.getBlockState(pos);
			boolean client = world.isClientSide();
			if (current.getBlock() == Blocks.MELON) {
				SlicedMelonBlock sliced = (SlicedMelonBlock) DelightfulBlocks.SLICED_MELON.get();
				slice(sliced.defaultBlockState(), sliced.getSliceItem(), world, pos, SoundEvents.BAMBOO_BREAK, e, client);
			} else if (current.getBlock() == Blocks.PUMPKIN && !e.getEntity().isCrouching()) {
				SlicedGourdBlock sliced = (SlicedGourdBlock) DelightfulBlocks.SLICED_PUMPKIN.get();
				slice(sliced.defaultBlockState(), sliced.getSliceItem(), world, pos, SoundEvents.BAMBOO_BREAK, e, client);
			} else if (
				Mods.loaded("undergardendelight") &&
				Objects.equals(ForgeRegistries.BLOCKS.getKey(current.getBlock()), Util.rl(Mods.UG, "gloomgourd"))
			) {
				SlicedGourdBlock sliced = (SlicedGourdBlock) DelightfulBlocks.SLICED_GLOOMGOURD.get();
				slice(sliced.defaultBlockState(), sliced.getSliceItem(), world, pos, SoundEvents.BAMBOO_BREAK, e, client);
			} else if (Mods.loaded(Mods.FU, Mods.FUD) &&
				Util.name(current.getBlock()).equals("truffle_cake")) {
				int currentBites = current.getValue(BlockStateProperties.BITES);
				ItemStack slice = Objects.requireNonNull(Util.item("frozen_delight", "truffle_cake_slice")).getDefaultInstance();
				if (currentBites >= 3) {
					world.removeBlock(pos, false);
					world.gameEvent(e.getEntity(), GameEvent.BLOCK_DESTROY, pos);
					Util.dropOrGive(slice, world, pos, e.getEntity());
					world.playSound(null, pos, SoundEvents.WOOL_PLACE, SoundSource.PLAYERS, 0.8F, 0.8F);
					e.getEntity().getItemInHand(e.getHand()).hurtAndBreak(1, e.getEntity(), onBroken -> {});
					e.setCancellationResult(InteractionResult.sidedSuccess(client));
					e.setCanceled(true);
					return;
				}
				slice(current.setValue(BlockStateProperties.BITES, currentBites + 1), slice, world, pos, SoundEvents.WOOL_PLACE, e, client);
			}
		}
	}

	// Replaces Block in world, drops Item, cancels interaction event
	void slice(BlockState block, ItemStack slice, Level world, BlockPos pos, SoundEvent sound, PlayerInteractEvent.RightClickBlock e, boolean client) {
		if (!client) {
			world.setBlock(pos, block, 2);
			Util.dropOrGive(slice, world, pos, e.getEntity());
			world.playSound(null, pos, sound, SoundSource.PLAYERS, 0.8F, 0.8F);
			e.getEntity().getItemInHand(e.getHand()).hurtAndBreak(1, e.getEntity(), onBroken -> {});
		}
		e.setCancellationResult(InteractionResult.sidedSuccess(client));
		e.setCanceled(true);
	}
}
package net.brnbrd.delightful.common;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.common.block.SlicedMelonBlock;
import net.brnbrd.delightful.common.block.SlicedPumpkinBlock;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.FurnaceFuelItem;
import net.brnbrd.delightful.compat.ArsNouveauCompat;
import net.brnbrd.delightful.compat.BYGCompat;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import vectorwing.farmersdelight.common.block.PieBlock;
import java.util.List;
import java.util.Objects;

public class ForgeEvents {

	@SubscribeEvent
	void burnTime(FurnaceFuelBurnTimeEvent e) {
		if (e.getItemStack().getItem() instanceof FurnaceFuelItem fuel) {
			e.setBurnTime(fuel.getFuelTime());
		}
	}

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
		if (e.getItemStack().is(DelightfulItemTags.SCAVENGING_TOOLS)) {
			BlockState current = world.getBlockState(pos);
			boolean client = world.isClientSide();
			if (current.getBlock() == Blocks.MELON) {
				SlicedMelonBlock sliced = (SlicedMelonBlock) DelightfulBlocks.SLICED_MELON.get();
				slice(sliced.defaultBlockState(), sliced.getSliceItem(), world, pos, SoundEvents.BAMBOO_BREAK, e, client);
			} else if (current.getBlock() == Blocks.PUMPKIN && !e.getEntity().isCrouching()) {
				SlicedPumpkinBlock sliced = (SlicedPumpkinBlock) DelightfulBlocks.SLICED_PUMPKIN.get();
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

	// Right click placing a pie Block using Item
	@SubscribeEvent
	void onPieOverhaul(PlayerInteractEvent.RightClickBlock e) {
		ItemStack stack = e.getItemStack();
		if (!stack.isEmpty()) {
			if (DelightfulConfig.PUMPKIN_PIE_OVERHAUL.get() && stack.is(Items.PUMPKIN_PIE)) {
				tryPlacePie((PieBlock) DelightfulBlocks.PUMPKIN_PIE.get(), e);
				return;
			}
			ifTryPlace(stack, Mods.AN, ArsNouveauCompat.pie, ArsNouveauCompat.slice, (PieBlock) DelightfulBlocks.SOURCE_BERRY_PIE.get(), e);
			ifTryPlace(stack, Mods.BYG, BYGCompat.blueberry_pie, BYGCompat.blueberry_pie_slice, (PieBlock) DelightfulBlocks.BLUEBERRY_PIE.get(), e);
			ifTryPlace(stack, Mods.BYG, BYGCompat.crimson_berry_pie, BYGCompat.crimson_berry_pie_slice, (PieBlock) DelightfulBlocks.CRIMSON_BERRY_PIE.get(), e);
			ifTryPlace(stack, Mods.BYG, BYGCompat.green_apple_pie, BYGCompat.green_apple_pie_slice, (PieBlock) DelightfulBlocks.GREEN_APPLE_PIE.get(), e);
			ifTryPlace(stack, Mods.BYG, BYGCompat.nightshade_berry_pie, BYGCompat.nightshade_berry_pie_slice, (PieBlock) DelightfulBlocks.NIGHTSHADE_BERRY_PIE.get(), e);
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

	// Tries placement of pie, may cancel interaction event
	void tryPlacePie(PieBlock pie, PlayerInteractEvent.RightClickBlock e) {
		e.setUseItem(Event.Result.DENY);
		e.setUseBlock(Event.Result.DENY);
		if (!e.isCanceled() && placePie(pie, new BlockPlaceContext(e.getEntity(), e.getHand(), e.getEntity().getItemInHand(e.getHand()), e.getHitVec()))) {
			e.setCanceled(true);
		}
	}

	// Places pie Block in world using Item
	boolean placePie(PieBlock pie, BlockPlaceContext context) {
		if (context.canPlace() &&
			context.getLevel().getBlockState(context.getClickedPos().below()).getMaterial().isSolid() &&
			pie.getStateForPlacement(context) != null) {
			BlockState piestate = pie.getStateForPlacement(context);
			if (!context.getPlayer().getAbilities().instabuild) {
				context.getItemInHand().shrink(1);
			}
			context.getLevel().setBlock(context.getClickedPos(), piestate, 2);
			piestate.getBlock().setPlacedBy(context.getLevel(), context.getClickedPos(), piestate, context.getPlayer(), context.getItemInHand());
			context.getLevel().gameEvent(GameEvent.BLOCK_PLACE, context.getClickedPos(), GameEvent.Context.of(context.getPlayer(), piestate));
			context.getLevel().playSound(null, context.getClickedPos(), SoundEvents.WOOL_PLACE, SoundSource.PLAYERS, 0.8F, 0.8F);
			return true;
		}
		return false;
	}

	// Cancels right-clicking overhauled pie items to eat them normally
	@SubscribeEvent (priority = EventPriority.HIGHEST)
	void onPieOverhaul(PlayerInteractEvent.RightClickItem e) {
		if (isCompatPie(e.getItemStack())) {
			e.setCancellationResult(InteractionResult.FAIL);
			e.setCanceled(true);
		}
	}

	// Adds "placeable" tooltip to compat pies
	@SubscribeEvent (priority = EventPriority.HIGHEST)
	void onPieTooltip(ItemTooltipEvent e) {
		ItemStack stack = e.getItemStack();
		if ((stack.getItem() instanceof BlockItem b && b.getBlock() instanceof PieBlock) ||
			isCompatPie(stack) ||
			isBerryPieOrMuffin(stack)) {
			MutableComponent desc = Component.translatable(Delightful.MODID + ".placeable.desc")
				.withStyle(ChatFormatting.DARK_GRAY)
				.withStyle(ChatFormatting.ITALIC);
			e.getToolTip().add(desc);
		}
	}

	boolean isPie(ItemStack stack, String modid, String pie, String slice) {
		return Mods.loaded(modid) && Util.enabled(slice) && stack.is(Util.item(modid, pie));
	}

	void ifTryPlace(ItemStack stack, String modid, String pie, String slice, PieBlock pieBlock, PlayerInteractEvent.RightClickBlock e) {
		if (isPie(stack, modid, pie, slice)) {
			tryPlacePie(pieBlock, e);
		}
	}

	boolean isCompatPie(ItemStack stack) {
		return (stack.is(Items.PUMPKIN_PIE) && DelightfulConfig.PUMPKIN_PIE_OVERHAUL.get()) ||
			isPie(stack, Mods.AN, ArsNouveauCompat.pie, ArsNouveauCompat.slice) ||
			isPie(stack, Mods.BYG, BYGCompat.blueberry_pie, BYGCompat.blueberry_pie_slice) ||
			isPie(stack, Mods.BYG, BYGCompat.crimson_berry_pie, BYGCompat.crimson_berry_pie_slice) ||
			isPie(stack, Mods.BYG, BYGCompat.green_apple_pie, BYGCompat.green_apple_pie_slice) ||
			isPie(stack, Mods.BYG, BYGCompat.nightshade_berry_pie, BYGCompat.nightshade_berry_pie_slice);
	}

	boolean isBerryPieOrMuffin(ItemStack stack) {
		return Mods.loaded(Mods.WB) && ((stack.is(Util.it(Mods.WB, "berry_pies"))) || (stack.is(Util.it(Mods.WB, "berry_muffins"))));
	}
}
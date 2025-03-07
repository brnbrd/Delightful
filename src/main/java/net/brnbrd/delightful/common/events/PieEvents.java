package net.brnbrd.delightful.common.events;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class PieEvents {
	// Checks if pie slice is enabled in config
	public static boolean enabled(ItemStack stack) {
		return (
			stack.is(DelightfulItemTags.COMPAT_PIES) &&
			!(stack.is(Items.PUMPKIN_PIE) && Modid.CCK.loaded()) &&
			Util.enabled(Util.name(stack) + "_slice")
		);
	}

	// Adds "Placeable" tooltip to compat pies
	@SubscribeEvent(priority = EventPriority.NORMAL)
	void onPieTooltip(ItemTooltipEvent e) {
		ItemStack stack = e.getItemStack();
		if (
			(stack.getItem() instanceof BlockItem b && b.getBlock() instanceof PieBlock) ||
			enabled(stack)
		) {
			e.getToolTip().add(Util.tooltip("placeable")
				.withStyle(ChatFormatting.DARK_GRAY)
				.withStyle(ChatFormatting.ITALIC)
			);
		}
	}

	// Cancels pies' vanilla right-click eating
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	void onCancelDefault(PlayerInteractEvent.RightClickItem e) {
		if (enabled(e.getItemStack())) {
			e.setCancellationResult(InteractionResult.FAIL);
			e.setCanceled(true);
		}
	}

	// Right click placing a pie Block using Item
	@SubscribeEvent
	void onPieOverhaul(PlayerInteractEvent.RightClickBlock e) {
		BlockState clicked = e.getLevel().getBlockState(e.getHitVec().getBlockPos());
		if (
			enabled(e.getItemStack()) &&
			!(clicked.getBlock() instanceof PieBlock) &&
			!clicked.is(ModBlocks.CUTTING_BOARD.get()) &&
			!e.isCanceled() &&
			Util.block(Delightful.MODID, Util.name(e.getItemStack())) instanceof PieBlock pie
		) {
			InteractionResult place = placePie(pie, new BlockPlaceContext(
				e.getEntity(),
				e.getHand(),
				e.getItemStack(),
				e.getHitVec()
			));
			if (place.consumesAction()) {
				e.setUseItem(Event.Result.DENY);
				e.setUseBlock(Event.Result.DENY);
			}
			e.setCancellationResult(place);
			e.setCanceled(place.consumesAction());
		}
	}

	// Places pie Block in world using Item
	private InteractionResult placePie(PieBlock pie, BlockPlaceContext context) {
		BlockPos pos = context.getClickedPos();
		Level level = context.getLevel();
		if (context.canPlace()) {
			Player player = context.getPlayer();
			BlockState pieState = pie.getStateForPlacement(context);
			if (
				player != null &&
				pieState != null &&
				canPlace(context, pieState) &&
				level.setBlock(pos, pieState, 11)
			) {
				BlockState placedState = level.getBlockState(pos);
				if (placedState.is(pieState.getBlock())) {
					placedState.getBlock().setPlacedBy(level, pos, placedState, player, context.getItemInHand());
				}
				level.gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(player, pieState));
				SoundType soundtype = placedState.getSoundType(level, pos, player);
				level.playSound(
					player,
					pos,
					placedState.getSoundType(level, pos, player).getPlaceSound(),
					SoundSource.BLOCKS,
					(soundtype.getVolume() + 1F) / 2F,
					soundtype.getPitch() * 0.8F
				);
				if (!player.getAbilities().instabuild) context.getItemInHand().shrink(1);
				return InteractionResult.sidedSuccess(level.isClientSide());
			}
		}
		return InteractionResult.FAIL;
	}

	private boolean canPlace(BlockPlaceContext context, BlockState state) {
		return (
			state.canSurvive(context.getLevel(), context.getClickedPos()) &&
			context.getLevel().isUnobstructed(state, context.getClickedPos(),
			context.getPlayer() == null ? CollisionContext.empty() : CollisionContext.of(context.getPlayer())
		));
	}
}
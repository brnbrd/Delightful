package net.brnbrd.delightful.common.events.pie;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
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

	// Adds "Placeable" tooltip to compat pies
	@SubscribeEvent (priority = EventPriority.NORMAL)
	void onPieTooltip(ItemTooltipEvent e) {
		ItemStack stack = e.getItemStack();
		if (
			(stack.getItem() instanceof BlockItem b && b.getBlock() instanceof PieBlock) ||
				Pies.enabled(stack) ||
				isBerryPieOrMuffin(stack)
		) {
			e.getToolTip().add(
				Component.translatable(Delightful.MODID + ".placeable.desc")
					.withStyle(ChatFormatting.DARK_GRAY)
					.withStyle(ChatFormatting.ITALIC)
			);
		}
	}

	// Cancels pies' vanilla right-click eating
	@SubscribeEvent (priority = EventPriority.HIGHEST)
	void onCancelDefault(PlayerInteractEvent.RightClickItem e) {
		if (Pies.enabled(e.getItemStack())) {
			e.setCancellationResult(InteractionResult.FAIL);
			e.setCanceled(true);
		}
	}

	// Right click placing a pie Block using Item
	@SubscribeEvent
	void onPieOverhaul(PlayerInteractEvent.RightClickBlock e) {
		if (
			Pies.enabled(e.getItemStack()) &&
			!e.getLevel().getBlockState(e.getHitVec().getBlockPos()).is(ModBlocks.CUTTING_BOARD.get()) &&
			!e.isCanceled()
		) {
			PieBlock pie = Pies.get(e.getItemStack());
			if (pie != null) {
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
	}

	// Places pie Block in world using Item
	private InteractionResult placePie(PieBlock pie, BlockPlaceContext context) {
		BlockPos pos = context.getClickedPos();
		Level level = context.getLevel();
		if (context.canPlace()) {
			Player player = context.getPlayer();
			BlockState pieState = pie.getStateForPlacement(context);
			if (pieState != null && canPlace(context, pieState) && level.setBlock(pos, pieState, 11)) {
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
					(soundtype.getVolume() + 1.0F) / 2.0F,
					soundtype.getPitch() * 0.8F
				);
				if (player != null && !player.getAbilities().instabuild) {
					context.getItemInHand().shrink(1);
				}
				return InteractionResult.sidedSuccess(level.isClientSide());
			}
		}
		return InteractionResult.FAIL;
	}

	private boolean canPlace(BlockPlaceContext pContext, BlockState pState) {
		Player player = pContext.getPlayer();
		CollisionContext collisioncontext = player == null ? CollisionContext.empty() : CollisionContext.of(player);
		return (
			pState.canSurvive(pContext.getLevel(), pContext.getClickedPos()) &&
			pContext.getLevel().isUnobstructed(pState, pContext.getClickedPos(), collisioncontext)
		);
	}

	// Wild Berries compat
	boolean isBerryPieOrMuffin(ItemStack stack) {
		return (
			Mods.loaded(Mods.WB) &&
			(
				stack.is(Util.it(Mods.WB, "berry_pies")) ||
				stack.is(Util.it(Mods.WB, "berry_muffins"))
			)
		);
	}
}

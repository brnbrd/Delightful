package net.brnbrd.delightful.common.events;

import com.google.common.collect.ImmutableMap;
import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DPieBlock;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.compat.BWGCompat;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.compat.UnusualEndCompat;
import net.brnbrd.delightful.compat.abnormals.AquaticCompat;
import net.brnbrd.delightful.compat.abnormals.AtmosphericCompat;
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
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

public class PieEvents {
	@NotNull private static final ImmutableMap<String, Supplier<? extends DPieBlock>> PIE_BLOCKS =
		ImmutableMap.<String, Supplier<? extends DPieBlock>>builder()
			.put(Util.id(Items.PUMPKIN_PIE), DelightfulBlocks.PUMPKIN_PIE)
			.put(Modid.AN.id("source_berry_pie"), DelightfulBlocks.SOURCE_BERRY_PIE)
			.put(Modid.UG.id("gloomgourd_pie"), DelightfulBlocks.GLOOMGOURD_PIE)
			.put(Modid.BWG.id(BWGCompat.blueberry_pie), DelightfulBlocks.BLUEBERRY_PIE)
			.put(Modid.BWG.id(BWGCompat.green_apple_pie), DelightfulBlocks.GREEN_APPLE_PIE)
			.put(Modid.UE.id(UnusualEndCompat.chorus_pie), DelightfulBlocks.CHORUS_PIE)
			.put(Modid.UA.id(AquaticCompat.mulberry_pie), DelightfulBlocks.MULBERRY_PIE)
			.put(Modid.AT.id(AtmosphericCompat.passion_fruit_tart), DelightfulBlocks.PASSION_FRUIT_TART)
			.build();

	// Adds "Placeable" tooltip to compat pies
	@SubscribeEvent(priority = EventPriority.NORMAL)
	void onPieTooltip(ItemTooltipEvent e) {
		ItemStack stack = e.getItemStack();
		if (
			(stack.getItem() instanceof BlockItem b && b.getBlock() instanceof PieBlock) ||
			enabled(stack)
		) {
			e.getToolTip().add(Util.delightfulTooltip("placeable")
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

	// Checks if pie slice is enabled in config
	public static boolean enabled(ItemStack stack) {
		String id = Util.id(stack.getItem());
		if (PIE_BLOCKS.containsKey(id)) {
			DPieBlock pie = PIE_BLOCKS.get(id).get();
			if (pie != null) return pie.enabled();
		}
		return false;
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
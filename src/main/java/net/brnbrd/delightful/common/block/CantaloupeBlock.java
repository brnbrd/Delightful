package net.brnbrd.delightful.common.block;

import net.brnbrd.delightful.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import org.jetbrains.annotations.NotNull;

public class CantaloupeBlock extends MiniBlock {
	public CantaloupeBlock(Properties pProperties) {
		super(pProperties);
	}

	@SuppressWarnings("deprecation")
	@Override
	public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
		if (player.getItemInHand(hand).is(ForgeTags.TOOLS_KNIVES)) {
			if (!level.isClientSide()) {
				SlicedMiniMelonBlock sliced = (SlicedMiniMelonBlock) DelightfulBlocks.SLICED_CANTALOUPE.get();
				level.setBlock(pos, sliced.defaultBlockState(), 2);
				Direction direction = hit.getDirection();
				Util.dropOrGive(
					sliced.getSliceItem(),
					level,
					pos,
					direction.getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : direction,
					player
				);
				level.playSound(null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1F, 1F);
				player.getItemInHand(hand).hurtAndBreak(1, player, onBroken -> onBroken.broadcastBreakEvent(hand));
			}
			return InteractionResult.sidedSuccess(level.isClientSide());
		}
		return InteractionResult.FAIL;
	}
}
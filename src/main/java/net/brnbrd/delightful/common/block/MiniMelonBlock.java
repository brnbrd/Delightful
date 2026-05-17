package net.brnbrd.delightful.common.block;

import net.brnbrd.delightful.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeHooks;
import vectorwing.farmersdelight.common.tag.ModTags;
import org.jetbrains.annotations.NotNull;

public class MiniMelonBlock extends MiniBlock implements BonemealableBlock {
	public MiniMelonBlock(Properties properties) {
		super(properties);
	}

	public void grow(Level level, BlockPos pos) {
		BlockState newState = Blocks.MELON.defaultBlockState();
		level.setBlock(pos, newState, 2);
		ForgeHooks.onCropsGrowPost(level, pos, newState);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void randomTick(@NotNull BlockState state, ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
		if (
			level.getRawBrightness(pos, 0) >= 5 &&
			level.getBlockState(pos.below()).is(Blocks.DIRT) &&
			ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt(10) == 0)
		) {
			grow(level, pos);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void performBonemeal(ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
		if (!level.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
		if (ForgeHooks.onCropsGrowPre(level, pos, state, true)) grow(level, pos);
	}

	@Override
	public boolean isValidBonemealTarget(@NotNull LevelReader pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, boolean pIsClient) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
		if (player.getItemInHand(hand).is(ModTags.Items.KNIVES)) {
			if (!level.isClientSide()) {
				SlicedMiniMelonBlock sliced = (SlicedMiniMelonBlock) DelightfulBlocks.SLICED_MINI_MELON.get();
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
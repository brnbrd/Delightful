package net.brdle.delightful.common.block;

import net.brdle.delightful.Util;
import net.brdle.delightful.data.DelightfulItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class CantaloupeBlock extends MiniBlock {
	public CantaloupeBlock(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public InteractionResult use(BlockState pState, Level world, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
		if (pPlayer.getItemInHand(pHand).is(DelightfulItemTags.SCAVENGING_TOOLS)) {
			if (!world.isClientSide()) {
				SlicedMiniMelonBlock sliced = (SlicedMiniMelonBlock) DelightfulBlocks.SLICED_CANTALOUPE.get();
				world.setBlock(pPos, sliced.defaultBlockState(), 2);
				Util.dropOrGive(sliced.getSliceItem(), world, pPos, pPlayer);
				world.playSound(null, pPos, SoundEvents.WOOD_HIT, SoundSource.PLAYERS, 0.8F, 0.8F);
				pPlayer.getItemInHand(pHand).hurtAndBreak(1, pPlayer, onBroken -> onBroken.broadcastBreakEvent(pHand));
			}
			return InteractionResult.sidedSuccess(world.isClientSide());
		}
		return InteractionResult.FAIL;
	}
}

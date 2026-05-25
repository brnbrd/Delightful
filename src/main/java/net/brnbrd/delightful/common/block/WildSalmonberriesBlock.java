package net.brnbrd.delightful.common.block;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.block.WildCropBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class WildSalmonberriesBlock extends WildCropBlock {
	public WildSalmonberriesBlock() {
		super(MobEffects.REGENERATION, 6, BlockBehaviour.Properties.copy(ModBlocks.WILD_BEETROOTS.get()));
	}

	@SuppressWarnings("deprecation")
	@Override
	public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
		final ItemStack itemInHand = player.getItemInHand(hand);
		final boolean isShears = itemInHand.is(Tags.Items.SHEARS);
		popResource(level, pos, Util.getStack(DelightfulItems.SALMONBERRIES, isShears ? 2 : 1));
		level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1F, 0.8F + level.random.nextFloat() * 0.4F);
		if (isShears) itemInHand.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
		final BlockState newState = this.getPickedBushState();
		level.setBlock(pos, newState, 2);
		level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
		return InteractionResult.sidedSuccess(level.isClientSide());
	}

	private BlockState getPickedBushState() {
		return DelightfulBlocks.SALMONBERRY_BUSH.get()
			.defaultBlockState()
			.setValue(SalmonberryBushBlock.AGE, 1);
	}
}
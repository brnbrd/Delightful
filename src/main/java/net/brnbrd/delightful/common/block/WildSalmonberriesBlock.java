package net.brnbrd.delightful.common.block;

import net.brnbrd.delightful.common.item.DelightfulItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import vectorwing.farmersdelight.common.block.WildCropBlock;

public class WildSalmonberriesBlock extends WildCropBlock {
	public WildSalmonberriesBlock() {
		super(MobEffects.REGENERATION, 6, BlockBehaviour.Properties.copy(Blocks.TALL_GRASS));
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
		return new ItemStack(DelightfulItems.SALMONBERRIES.get());
	}
}

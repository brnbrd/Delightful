package net.brdle.delightful.common.block;

import net.brdle.delightful.common.item.DelightfulItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.block.WildCropBlock;

public class WildSalmonberriesBlock extends WildCropBlock {
	public WildSalmonberriesBlock() {
		super(MobEffects.REGENERATION, 6, BlockBehaviour.Properties.copy(Blocks.TALL_GRASS));
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
		return new ItemStack(DelightfulItems.SALMONBERRY_PIPS.get());
	}
}

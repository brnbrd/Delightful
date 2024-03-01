package net.brnbrd.delightful.common.block;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.common.block.WildCropBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class WildSalmonberriesBlock extends WildCropBlock {
	public WildSalmonberriesBlock() {
		super(MobEffects.REGENERATION, 6, BlockBehaviour.Properties.copy(ModBlocks.WILD_BEETROOTS.get()));
	}
}

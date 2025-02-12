package net.brnbrd.delightful.compat.jade;

import net.brnbrd.delightful.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;

public enum DMushroomColonyAge implements IBlockComponentProvider {
	INSTANCE;

	@Override
	public void appendTooltip(ITooltip tooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
		BlockState state = blockAccessor.getBlockState();
		if (state.getBlock() instanceof MushroomColonyBlock mush) {
			int age = state.getValue(mush.getAgeProperty());
			int maxAge = mush.getMaxAge();
			tooltip.add(Component.translatable("tooltip.jade.age", Component.literal(age + "/" + maxAge)
					.withStyle(age == maxAge ? ChatFormatting.GREEN : ChatFormatting.WHITE)
			));
		}
	}

	@Override
	public ResourceLocation getUid() {
		return Util.delight("mushroom_colony");
	}
}
package net.brnbrd.delightful.compat.jade;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DPieBlock;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.impl.ui.ItemStackElement;

public enum DPieIcons implements IBlockComponentProvider {
	INSTANCE;

	@Override
	public @Nullable IElement getIcon(BlockAccessor accessor, IPluginConfig config, IElement currentIcon) {
		if (accessor.getBlock() instanceof DPieBlock pie) {
			return ItemStackElement.of(pie.getStack());
		}
		return currentIcon;
	}

	@Override
	public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
	}

	@Override
	public ResourceLocation getUid() {
		return Util.rl(Delightful.MODID, "pie_icons");
	}
}

package net.brnbrd.delightful.common.item;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class CompatBlockItem extends BlockItem implements IConfigured {
	private final String[] modid;

	public CompatBlockItem(Block block, Item.Properties props, String... modid) {
		super(block, props);
		this.modid = modid;
	}

	public CompatBlockItem(Block block, Item.Properties props, String modid) {
		super(block, props);
		this.modid = new String[]{ modid };
	}

	public boolean isLoaded() {
		return Mods.orLoaded(modid);
	}

	@Override
	public boolean isEnabled() {
		return this.isLoaded() && Util.enabled(this);
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
		if (!this.isLoaded()) {
			comps.add(Component.translatable("tooltip.requires_modid"));
			StringBuilder ids = new StringBuilder();
			for (String s : modid) {
				ids.append(s).append(", ");
			}
			comps.add(Component.literal(ids.substring(0, ids.length() - 2)).withStyle(ChatFormatting.UNDERLINE));
			return;
		} else if (!this.isEnabled()) {
			comps.add(Component.translatable("tooltip.config_disabled").withStyle(ChatFormatting.UNDERLINE));
			return;
		}
		super.appendHoverText(stack, level, comps, pIsAdvanced);
	}
}

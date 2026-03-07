package net.brnbrd.delightful.common.item;

import net.brnbrd.delightful.compat.Modid;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CompatItem extends Item implements IConfigured {
	private final Modid[] modid;

	public CompatItem(Properties properties, Modid... modid) {
		super(properties);
		this.modid = modid;
	}

	public Modid[] getModid() {
		return this.modid;
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag isAdvanced) {
		if (this.enabledText(comps)) {
			super.appendHoverText(stack, level, comps, isAdvanced);
		}
	}
}
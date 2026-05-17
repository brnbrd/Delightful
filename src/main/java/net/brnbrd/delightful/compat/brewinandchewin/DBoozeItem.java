package net.brnbrd.delightful.compat.brewinandchewin;

import net.brnbrd.delightful.common.item.IConfigured;
import net.brnbrd.delightful.compat.Modid;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import umpaz.brewinandchewin.common.item.BoozeItem;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DBoozeItem extends BoozeItem implements IConfigured {
	public DBoozeItem(Fluid fluid, Item.Properties properties) {
		super(fluid, properties);
	}

	@Override
	public @NotNull Modid[] getModid() {
		return new Modid[]{Modid.BC};
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
		this.enabledText(comps);

		super.appendHoverText(stack, level, comps, pIsAdvanced);
	}
}
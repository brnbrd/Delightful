package net.brnbrd.delightful.common.events.pie;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.PieBlock;

public class Pies {
	public static boolean enabled(ItemStack stack) {
		return (
			stack.is(DelightfulItemTags.COMPAT_PIES) &&
			!(stack.is(Items.PUMPKIN_PIE) && Mods.loaded(Mods.CCK)) &&
			Util.enabled(Util.name(stack) + "_slice")
		);
	}

	public @Nullable static PieBlock get(ItemStack stack) {
		return Util.block(Delightful.MODID, Util.name(stack)) instanceof PieBlock pie ? pie : null;
	}
}
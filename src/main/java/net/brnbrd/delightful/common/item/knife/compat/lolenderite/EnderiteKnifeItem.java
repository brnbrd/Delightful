package net.brnbrd.delightful.common.item.knife.compat.lolenderite;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.registry.ModItems;
import java.util.function.Supplier;

public class EnderiteKnifeItem extends CompatKnifeItem {
	public EnderiteKnifeItem(Properties properties) {
		super(Mods.LE, DelightfulItems.ingot("enderite"), DelightfulTiers.ENDERITE, properties);
	}

	@Override
	public Supplier<Ingredient> getSmithingBase() {
		return Util.ing(ModItems.NETHERITE_KNIFE);
	}
}

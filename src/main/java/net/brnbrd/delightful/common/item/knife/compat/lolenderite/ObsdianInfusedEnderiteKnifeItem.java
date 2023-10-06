package net.brnbrd.delightful.common.item.knife.compat.lolenderite;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.function.Supplier;

public class ObsdianInfusedEnderiteKnifeItem extends CompatKnifeItem {
	public ObsdianInfusedEnderiteKnifeItem(Properties properties) {
		super(Mods.LE, DelightfulItems.ingot("obsidian_infused_enderite"), DelightfulTiers.OBSIDIAN_INFUSED_ENDERITE, properties);
	}

	@Override
	public Supplier<Ingredient> getSmithingBase() {
		return Util.ing(Knives.ENDERITE);
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}
}

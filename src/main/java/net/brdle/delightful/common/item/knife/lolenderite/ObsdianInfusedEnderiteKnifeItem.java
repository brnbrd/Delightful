package net.brdle.delightful.common.item.knife.lolenderite;

import net.brdle.delightful.Util;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.DelightfulTiers;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.Optional;
import java.util.function.Supplier;

public class ObsdianInfusedEnderiteKnifeItem extends CompatKnifeItem {
	public ObsdianInfusedEnderiteKnifeItem(Properties properties) {
		super("lolenderite", DelightfulItems.ingot("obsidian_infused_enderite"), DelightfulTiers.OBSIDIAN_INFUSED_ENDERITE, 0.5F, -2.0F, properties, Optional.of(Util.ing(DelightfulItems.ENDERITE_KNIFE)));
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}
}

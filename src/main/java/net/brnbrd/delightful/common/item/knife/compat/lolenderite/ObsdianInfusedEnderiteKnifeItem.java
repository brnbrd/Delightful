package net.brnbrd.delightful.common.item.knife.compat.lolenderite;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class ObsdianInfusedEnderiteKnifeItem extends DKnifeItem {
	public ObsdianInfusedEnderiteKnifeItem(Properties properties) {
		super(DelightfulItemTags.ingot("obsidian_infused_enderite"), DelightfulTiers.OBSIDIAN_INFUSED_ENDERITE, properties, Mods.LE);
	}

	@Override
	public String getTranslation() {
		return "Obsidian-Infused Enderite Knife";
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return RecipeType.SMITHING;
	}
}
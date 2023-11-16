package net.brnbrd.delightful.common.item.knife.compat.deep_aether;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.common.item.knife.compat.aether.AetherKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jetbrains.annotations.Nullable;

public class DummyStratusKnifeItem extends AetherKnifeItem {

	public DummyStratusKnifeItem(Properties props, Tier tier) {
		super("deep_aether", DelightfulItemTags.INGOTS_STRATUS, tier, props);
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return RecipeType.SMITHING;
	}

	@Override
	public ImmutablePair<Ingredient, Ingredient> getSmithing() {
		return new ImmutablePair<>(
			Ingredient.of(DelightfulItemTags.STRATUS_UPGRADE),
			Util.ing(Knives.GRAVITITE)
		);
	}
}

package net.brnbrd.delightful.common.item.knife.compat;

import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class DummyKnifeItem extends CompatKnifeItem {
	private final Ingredient stick;
	private final boolean genRecipe;

	public DummyKnifeItem(String[] modid, TagKey<Item> material, Ingredient stick) {
		super(modid, material, Tiers.IRON, (new Item.Properties()));
		this.stick = stick;
		this.genRecipe = true;
	}

	public DummyKnifeItem(String[] modid, TagKey<Item> material, Ingredient stick, boolean genRecipe) {
		super(modid, material, Tiers.IRON, (new Item.Properties()));
		this.stick = stick;
		this.genRecipe = genRecipe;
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return this.genRecipe ? super.getRecipeType() : null;
	}

	@Override
	public Ingredient getRod() {
		return this.stick;
	}
}
package net.brnbrd.delightful.common.item.knife.compat;

import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class DummyKnifeItem extends DKnifeItem {
	private final Ingredient stick;
	private final boolean genRecipe;

	public DummyKnifeItem(TagKey<Item> material, Ingredient stick, String... modid) {
		super(material, Tiers.IRON, (new Item.Properties()), modid);
		this.stick = stick;
		this.genRecipe = true;
	}

	public DummyKnifeItem(TagKey<Item> material, Ingredient stick, boolean genRecipe, String... modid) {
		super(material, Tiers.IRON, (new Item.Properties()), modid);
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
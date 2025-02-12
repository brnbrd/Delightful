package net.brnbrd.delightful.common.item.food;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TeaItem extends DrinkItem {
	@NotNull private final TagKey<Item> ingredient;

	public TeaItem(Properties properties, @NotNull TagKey<Item> ingredient, boolean hasCustomTooltip) {
		super(
			properties.craftRemainder(Items.GLASS_BOTTLE).stacksTo(16),
			true, // Has potion effect tooltip by default
			hasCustomTooltip
		);
		this.ingredient = ingredient;
	}

	public @NotNull TagKey<Item> getIngredient() {
		return this.ingredient;
	}

	@Override
	public @Nullable TagKey<Item> getDependencyTag() {
		return this.getIngredient();
	}
}
package net.brdle.delightful.common.item;

import net.minecraft.world.item.crafting.Ingredient;
import java.util.function.Supplier;

public interface ISingleIngredient {
    Supplier<Ingredient> getIngredient();
}
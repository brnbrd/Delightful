package net.brdle.delightful.common;

import com.google.common.base.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

public class DelightfulIngredients {

    public static final ResourceLocation ingot(String name) {
        return new ResourceLocation("forge", "ingots/" + name);
    }

    public static final ResourceLocation gem(String name) {
        return new ResourceLocation("forge", "gems/" + name);
    }

    public static final Supplier<Ingredient> getIngot(String name) {
        return () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), ingot(name)));
    }

    public static final Supplier<Ingredient> getGem(String name) {
        return () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), gem(name)));
    }

}

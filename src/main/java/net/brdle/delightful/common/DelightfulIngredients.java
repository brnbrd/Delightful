package net.brdle.delightful.common;

import com.google.common.base.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

public class DelightfulIngredients {
    public static final Supplier<Ingredient> steel = () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "ingots/steel")));
    public static final Supplier<Ingredient> enderite = () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "ingots/enderite")));
    public static final Supplier<Ingredient> obsidianInfusedEnderite = () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "ingots/obsidian_infused_enderite")));
    public static final Supplier<Ingredient> lapis = () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "gems/lapis")));
}
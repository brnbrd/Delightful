package net.brdle.delightful.common;

import com.google.common.base.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTagHandler;
import net.minecraftforge.registries.ForgeRegistries;

public class DelightfulIngredients {
    public static final Supplier<Ingredient> steel = () -> Ingredient.of(ForgeTagHandler.makeWrapperTag(ForgeRegistries.ITEMS, new ResourceLocation("forge", "ingots/steel")));
    public static final Supplier<Ingredient> enderite = () -> Ingredient.of(ForgeTagHandler.makeWrapperTag(ForgeRegistries.ITEMS, new ResourceLocation("forge", "ingots/enderite")));
    public static final Supplier<Ingredient> obsidianInfusedEnderite = () -> Ingredient.of(ForgeTagHandler.makeWrapperTag(ForgeRegistries.ITEMS, new ResourceLocation("forge", "ingots/obsidian_infused_enderite")));
}
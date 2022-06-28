package net.brdle.delightful.common;

import com.google.common.base.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

public class DelightfulIngredients {
    public static final Supplier<Ingredient> tin = () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "ingots/tin")));
    public static final Supplier<Ingredient> steel = () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "ingots/steel")));
    public static final Supplier<Ingredient> enderite = () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "ingots/enderite")));
    public static final Supplier<Ingredient> obsidianInfusedEnderite = () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "ingots/obsidian_infused_enderite")));
    public static final Supplier<Ingredient> bronze = () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "ingots/bronze")));
    public static final Supplier<Ingredient> osmium = () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "ingots/osmium")));
    public static final Supplier<Ingredient> refinedGlowstone = () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "ingots/refined_glowstone")));
    public static final Supplier<Ingredient> refinedObsidian = () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "ingots/refined_obsidian")));
    public static final Supplier<Ingredient> lapis = () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "gems/lapis")));
    public static final Supplier<Ingredient> largeAmethyst = () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "gems/large_amethyst")));
    public static final Supplier<Ingredient> blackOpal = () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "gems/black_opal")));

}

package net.brdle.delightful.common;

import com.google.common.base.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

public class DelightfulIngredients {
    public static final Supplier<Ingredient> tin = getIngot("tin");
    public static final Supplier<Ingredient> steel = getIngot("steel");
    public static final Supplier<Ingredient> silver = getIngot("silver");
    public static final Supplier<Ingredient> brass = getIngot("brass");
    public static final Supplier<Ingredient> enderite = getIngot("enderite");
    public static final Supplier<Ingredient> obsidianInfusedEnderite = getIngot("obsidian_infused_enderite");
    public static final Supplier<Ingredient> bronze = getIngot("bronze");
    public static final Supplier<Ingredient> osmium = getIngot("osmium");
    public static final Supplier<Ingredient> refinedGlowstone = getIngot("refined_glowstone");
    public static final Supplier<Ingredient> refinedObsidian = getIngot("refined_obsidian");
    public static final Supplier<Ingredient> lapis = getGem("lapis");
    public static final Supplier<Ingredient> largeAmethyst = getGem("large_amethyst");
    public static final Supplier<Ingredient> blackOpal = getGem("black_opal");
    public static final Supplier<Ingredient> constantan = getIngot("constantan");
    public static final Supplier<Ingredient> electrum = getIngot("electrum");
    public static final Supplier<Ingredient> invar = getIngot("invar");
    public static final Supplier<Ingredient> lead = getIngot("lead");
    public static final Supplier<Ingredient> nickel = getIngot("nickel");

    private static final Supplier<Ingredient> getIngot(String name) {
        return () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "ingots/" + name)));
    }

    private static final Supplier<Ingredient> getGem(String name) {
        return () -> Ingredient.of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("forge", "gems/" + name)));
    }

}

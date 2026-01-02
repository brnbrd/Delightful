package net.brnbrd.delightful.data.gen;

import com.google.common.collect.Lists;
import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.common.block.DelightfulCabinetBlock;
import net.brnbrd.delightful.common.crafting.EnabledCondition;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.IConfigured;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.ArrayUtils;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.crafting.ingredient.ToolActionIngredient;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;
import vectorwing.farmersdelight.data.recipe.CookingRecipes;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public class DelightfulRecipeProvider extends RecipeProvider implements IConditionBuilder {
	public DelightfulRecipeProvider(PackOutput output) {
		super(output);
	}

	@Override
	protected void buildRecipes(@NotNull Consumer<FinishedRecipe> finished) {
		// Cabinets
		cabinet(DelightfulBlocks.QUARTZ_CABINET.get(), ModBlocks.WARPED_CABINET.get(), Blocks.POLISHED_BLACKSTONE, finished);
		cabinet(DelightfulBlocks.BASALT_CABINET.get(), ModBlocks.CRIMSON_CABINET.get(), Blocks.POLISHED_BLACKSTONE, finished);

		// Knives
		DelightfulItems.ITEMS.getEntries().stream()
			.map(RegistryObject::get)
			.filter(item -> item instanceof DKnifeItem)
			.map(item -> (DKnifeItem) item)
			.filter(knife -> knife.getDependencyTag() != null && knife.getRecipeType() == RecipeType.CRAFTING)
			.forEach(k -> knife(k, finished));
		knifeSmeltAndBlast((DKnifeItem) Knives.BONE.get(), "bone/knife", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(Items.BONE_MEAL)), finished);

		// Smelting
		foodSmeltingRecipes("cactus_steak", DelightfulItems.CACTUS_FLESH.get(), DelightfulItems.CACTUS_STEAK.get(), 0.35F, finished);
		foodSmeltingRecipes("cooked_venison_chops", DelightfulItems.VENISON_CHOPS.get(), DelightfulItems.COOKED_VENISON_CHOPS.get(), 0.35F, finished);
		foodSmeltingRecipes("cooked_goat", DelightfulItems.RAW_GOAT.get(), DelightfulItems.COOKED_GOAT.get(), 0.35F, finished);

		// Foods
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.NUT_BUTTER_AND_JAM_SANDWICH.get())
			.requires(ForgeTags.BREAD)
			.requires(DelightfulItemTags.NUT_BUTTER)
			.requires(DelightfulItemTags.JAMS)
			.unlockedBy("has_nut_butter", has(DelightfulItems.NUT_BUTTER_BOTTLE.get())),
			"food/nut_butter_and_jam_sandwich", finished, enabled(DelightfulItems.NUT_BUTTER_AND_JAM_SANDWICH), not(tagEmpty(DelightfulItemTags.NUTS)), not(modLoaded(Modid.CT.get())));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.ROE_BLINI.get())
			.requires(DelightfulItemTags.PANCAKES)
			.requires(DelightfulItemTags.CHEESE_MILD_CREAM)
			.requires(DelightfulItemTags.AGED_ROES)
			.requires(Items.DRIED_KELP)
			.unlockedBy("has_aged_roe", has(DelightfulItemTags.AGED_ROES)),
			"food/roe_blini", finished, enabled(DelightfulItems.ROE_BLINI), not(tagEmpty(DelightfulItemTags.PANCAKES)), not(tagEmpty(DelightfulItemTags.CHEESE_MILD_CREAM)), not(tagEmpty(DelightfulItemTags.ROE)), modLoaded(Modid.BC.get()), modLoaded(Modid.LFL.get()));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.ROE_BLINI.get())
			.requires(DelightfulItemTags.PANCAKES)
			.requires(ForgeTags.MILK)
			.requires(DelightfulItemTags.AGED_ROES)
			.requires(Items.DRIED_KELP)
			.unlockedBy("has_aged_roe", has(DelightfulItemTags.AGED_ROES)),
			"food/roe_blini_from_milk", finished, enabled(DelightfulItems.ROE_BLINI), not(tagEmpty(DelightfulItemTags.PANCAKES)), tagEmpty(DelightfulItemTags.CHEESE_MILD_CREAM), not(tagEmpty(DelightfulItemTags.ROE)), modLoaded(Modid.BC.get()), modLoaded(Modid.LFL.get()));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.ROE_BLINI.get())
			.requires(ForgeTags.BREAD)
			.requires(DelightfulItemTags.CHEESE_MILD_CREAM)
			.requires(DelightfulItemTags.AGED_ROES)
			.requires(Items.DRIED_KELP)
			.unlockedBy("has_aged_roe", has(DelightfulItemTags.AGED_ROES)),
			"food/roe_blini_from_bread", finished, enabled(DelightfulItems.ROE_BLINI), tagEmpty(DelightfulItemTags.PANCAKES), not(tagEmpty(DelightfulItemTags.CHEESE_MILD_CREAM)), not(tagEmpty(DelightfulItemTags.ROE)), modLoaded(Modid.BC.get()), modLoaded(Modid.LFL.get()));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.ROE_BLINI.get())
			.requires(ForgeTags.BREAD)
			.requires(ForgeTags.MILK)
			.requires(DelightfulItemTags.AGED_ROES)
			.requires(Items.DRIED_KELP)
			.unlockedBy("has_aged_roe", has(DelightfulItemTags.AGED_ROES)),
			"food/roe_blini_from_bread_and_milk", finished, enabled(DelightfulItems.ROE_BLINI), tagEmpty(DelightfulItemTags.PANCAKES), tagEmpty(DelightfulItemTags.CHEESE_MILD_CREAM), not(tagEmpty(DelightfulItemTags.ROE)), modLoaded(Modid.BC.get()), modLoaded(Modid.LFL.get()));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SALMON_AND_ROE_BLINI.get())
			.requires(DelightfulItemTags.PANCAKES)
			.requires(DelightfulItemTags.CHEESE_MILD_CREAM)
			.requires(DelightfulItemTags.AGED_ROES)
			.requires(ModItems.SALMON_SLICE.get())
			.requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
			.unlockedBy("has_aged_roe", has(DelightfulItemTags.AGED_ROES)),
			"food/salmon_and_roe_blini", finished, enabled(DelightfulItems.SALMON_AND_ROE_BLINI), not(tagEmpty(DelightfulItemTags.PANCAKES)), not(tagEmpty(DelightfulItemTags.CHEESE_MILD_CREAM)), not(tagEmpty(DelightfulItemTags.ROE)), modLoaded(Modid.BC.get()), modLoaded(Modid.LFL.get()));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SALMON_AND_ROE_BLINI.get())
			.requires(DelightfulItemTags.PANCAKES)
			.requires(ForgeTags.MILK)
			.requires(DelightfulItemTags.AGED_ROES)
			.requires(ModItems.SALMON_SLICE.get())
			.requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
			.unlockedBy("has_aged_roe", has(DelightfulItemTags.AGED_ROES)),
			"food/salmon_and_roe_blini_from_milk", finished, enabled(DelightfulItems.SALMON_AND_ROE_BLINI), not(tagEmpty(DelightfulItemTags.PANCAKES)), tagEmpty(DelightfulItemTags.CHEESE_MILD_CREAM), not(tagEmpty(DelightfulItemTags.ROE)), modLoaded(Modid.BC.get()), modLoaded(Modid.LFL.get()));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SALMON_AND_ROE_BLINI.get())
			.requires(ForgeTags.BREAD)
			.requires(DelightfulItemTags.CHEESE_MILD_CREAM)
			.requires(DelightfulItemTags.AGED_ROES)
			.requires(ModItems.SALMON_SLICE.get())
			.requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
			.unlockedBy("has_aged_roe", has(DelightfulItemTags.AGED_ROES)),
			"food/salmon_and_roe_blini_from_bread", finished, enabled(DelightfulItems.SALMON_AND_ROE_BLINI), tagEmpty(DelightfulItemTags.PANCAKES), not(tagEmpty(DelightfulItemTags.CHEESE_MILD_CREAM)), not(tagEmpty(DelightfulItemTags.ROE)), modLoaded(Modid.BC.get()), modLoaded(Modid.LFL.get()));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SALMON_AND_ROE_BLINI.get())
			.requires(ForgeTags.BREAD)
			.requires(ForgeTags.MILK)
			.requires(DelightfulItemTags.AGED_ROES)
			.requires(ModItems.SALMON_SLICE.get())
			.requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
			.unlockedBy("has_aged_roe", has(DelightfulItemTags.AGED_ROES)),
			"food/salmon_and_roe_blini_from_bread_and_milk", finished, enabled(DelightfulItems.SALMON_AND_ROE_BLINI), tagEmpty(DelightfulItemTags.PANCAKES), tagEmpty(DelightfulItemTags.CHEESE_MILD_CREAM), not(tagEmpty(DelightfulItemTags.ROE)), modLoaded(Modid.BC.get()), modLoaded(Modid.LFL.get()));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SALMON_AND_ROE_BLINI.get())
			.requires(DelightfulItems.ROE_BLINI.get())
			.requires(ModItems.SALMON_SLICE.get())
			.requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
			.unlockedBy("has_roe_blini", has(DelightfulItems.ROE_BLINI.get())),
			"food/salmon_and_roe_blini_from_roe_blini", finished, enabled(DelightfulItems.SALMON_AND_ROE_BLINI), enabled(DelightfulItems.ROE_BLINI), not(tagEmpty(DelightfulItemTags.ROE)), modLoaded(Modid.BC.get()), modLoaded(Modid.LFL.get()));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CHEESEBURGER.get())
			.requires(ForgeTags.BREAD)
			.requires(ModItems.BEEF_PATTY.get())
			.requires(DelightfulItemTags.CHEESE)
			.requires(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
			.requires(ForgeTags.VEGETABLES_TOMATO)
			.requires(ForgeTags.VEGETABLES_ONION)
			.unlockedBy("has_cheese", has(DelightfulItemTags.CHEESE)),
			"food/cheeseburger", finished, enabled(DelightfulItems.CHEESEBURGER), tagEmpty(DelightfulItemTags.BURGER_BUN), not(tagEmpty(DelightfulItemTags.CHEESE)), not(modLoaded(Modid.VD.get())));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CHEESEBURGER.get())
			.requires(ForgeTags.BREAD)
			.requires(ModItems.BEEF_PATTY.get())
			.requires(ForgeTags.MILK)
			.requires(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
			.requires(ForgeTags.VEGETABLES_TOMATO)
			.requires(ForgeTags.VEGETABLES_ONION)
			.unlockedBy("has_beef_patty", has(ModItems.BEEF_PATTY.get())),
			"food/cheeseburger_from_milk", finished, enabled(DelightfulItems.CHEESEBURGER), tagEmpty(DelightfulItemTags.CHEESE));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CHEESEBURGER.get())
			.requires(DelightfulItemTags.BURGER_BUN)
			.requires(ModItems.BEEF_PATTY.get())
			.requires(DelightfulItemTags.CHEESE)
			.requires(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
			.requires(ForgeTags.VEGETABLES_TOMATO)
			.requires(ForgeTags.VEGETABLES_ONION)
			.unlockedBy("has_beef_patty", has(ModItems.BEEF_PATTY.get())),
			"food/cheeseburger_from_bun", finished, enabled(DelightfulItems.CHEESEBURGER), not(tagEmpty(DelightfulItemTags.BURGER_BUN)), not(tagEmpty(DelightfulItemTags.CHEESE)), not(modLoaded(Modid.VD.get())));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CHEESEBURGER.get())
			.requires(ModItems.HAMBURGER.get())
			.requires(DelightfulItemTags.CHEESE)
			.unlockedBy("has_hamburger", has(ModItems.HAMBURGER.get())),
			"food/cheeseburger_from_hamburger", finished, enabled(DelightfulItems.CHEESEBURGER), not(tagEmpty(DelightfulItemTags.CHEESE)));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.DELUXE_CHEESEBURGER.get())
			.requires(ForgeTags.BREAD)
			.requires(ModItems.BEEF_PATTY.get())
			.requires(DelightfulItemTags.CHEESE)
			.requires(ModItems.BEEF_PATTY.get())
			.requires(DelightfulItemTags.CHEESE)
			.requires(ModItems.COOKED_BACON.get())
			.requires(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
			.requires(ForgeTags.VEGETABLES_TOMATO)
			.requires(ForgeTags.VEGETABLES_ONION)
			.unlockedBy("has_bacon", has(ModItems.COOKED_BACON.get())),
			"food/deluxe_cheeseburger", finished, enabled("deluxe_cheeseburger"), tagEmpty(DelightfulItemTags.BURGER_BUN), not(tagEmpty(DelightfulItemTags.CHEESE)));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.DELUXE_CHEESEBURGER.get())
			.requires(ForgeTags.BREAD)
			.requires(ModItems.BEEF_PATTY.get())
			.requires(ForgeTags.MILK)
			.requires(ModItems.BEEF_PATTY.get())
			.requires(ForgeTags.MILK)
			.requires(ModItems.COOKED_BACON.get())
			.requires(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
			.requires(ForgeTags.VEGETABLES_TOMATO)
			.requires(ForgeTags.VEGETABLES_ONION)
			.unlockedBy("has_bacon", has(ModItems.COOKED_BACON.get())),
			"food/deluxe_cheeseburger_from_milk", finished, enabled("deluxe_cheeseburger"), tagEmpty(DelightfulItemTags.CHEESE));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.DELUXE_CHEESEBURGER.get())
			.requires(DelightfulItemTags.BURGER_BUN)
			.requires(ModItems.BEEF_PATTY.get())
			.requires(DelightfulItemTags.CHEESE)
			.requires(ModItems.BEEF_PATTY.get())
			.requires(DelightfulItemTags.CHEESE)
			.requires(ModItems.COOKED_BACON.get())
			.requires(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
			.requires(ForgeTags.VEGETABLES_TOMATO)
			.requires(ForgeTags.VEGETABLES_ONION)
			.unlockedBy("has_bacon", has(ModItems.COOKED_BACON.get())),
			"food/deluxe_cheeseburger_from_bun", finished, enabled("deluxe_cheeseburger"), not(tagEmpty(DelightfulItemTags.BURGER_BUN)), not(tagEmpty(DelightfulItemTags.CHEESE)));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.DELUXE_CHEESEBURGER.get())
			.requires(DelightfulItems.CHEESEBURGER.get())
			.requires(ModItems.BEEF_PATTY.get())
			.requires(DelightfulItemTags.CHEESE)
			.requires(ModItems.COOKED_BACON.get())
			.unlockedBy("has_cheeseburger", has(DelightfulItems.CHEESEBURGER.get())),
			"food/deluxe_cheeseburger_from_cheeseburger", finished, enabled(DelightfulItems.DELUXE_CHEESEBURGER), enabled(DelightfulItems.CHEESEBURGER));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.MARSHMALLOW_STICK.get())
			.requires(DelightfulItemTags.MARSHMALLOW)
			.requires(Tags.Items.RODS_WOODEN)
			.unlockedBy("has_marshmallow", has(DelightfulItemTags.MARSHMALLOW)),
			"food/marshmallow_stick_from_marshmallow", finished, enabled(DelightfulItems.MARSHMALLOW_STICK), not(tagEmpty(DelightfulItemTags.MARSHMALLOW)), not(modLoaded(Modid.HH.get())));
		wrap(SimpleCookingRecipeBuilder.campfireCooking(
			Ingredient.of(DelightfulItems.MARSHMALLOW_STICK.get()),
			RecipeCategory.FOOD,
			DelightfulItems.COOKED_MARSHMALLOW_STICK.get(),
			0.5F,
			600
			).unlockedBy("has_marshmallow_stick", has(DelightfulItems.MARSHMALLOW_STICK.get())),
			"campfire/marshmallow_stick", finished,
			enabled(DelightfulItems.COOKED_MARSHMALLOW_STICK), not(modLoaded(Modid.HH.get())));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SMORE.get())
			.requires(ModItems.HONEY_COOKIE.get())
			.requires(Items.COCOA_BEANS)
			.requires(ForgeTags.MILK)
			.requires(Items.SUGAR)
			.requires(DelightfulItemTags.ROASTED_MARSHMALLOWS)
			.requires(ModItems.HONEY_COOKIE.get())
			.unlockedBy("has_cooked_marshmallow_stick", has(DelightfulItemTags.ROASTED_MARSHMALLOWS)),
			"food/smore", finished, enabled(DelightfulItems.SMORE), tagEmpty(DelightfulItemTags.CHOCOLATE));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SMORE.get())
			.requires(ModItems.HONEY_COOKIE.get())
			.requires(DelightfulItemTags.CHOCOLATE)
			.requires(DelightfulItemTags.ROASTED_MARSHMALLOWS)
			.requires(ModItems.HONEY_COOKIE.get())
			.unlockedBy("has_cooked_marshmallow_stick", has(DelightfulItemTags.ROASTED_MARSHMALLOWS)),
			"food/smore_from_chocolate", finished, enabled(DelightfulItems.SMORE), not(tagEmpty(DelightfulItemTags.CHOCOLATE)));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.GLOW_JAM_COOKIE.get(), 8)
			.requires(DelightfulItemTags.JAMS_GLOW)
			.requires(Ingredient.of(ForgeTags.GRAIN_WHEAT), 2)
			.unlockedBy("has_glow_jam", has(DelightfulItemTags.JAMS_GLOW)),
			"food/glow_jam_cookie", finished, enabled(DelightfulItems.GLOW_JAM_COOKIE));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.HONEY_GLAZED_WALNUT.get(), 2)
			.requires(Ingredient.of(DelightfulItemTags.NUTS_WALNUT), 2)
			.requires(Items.HONEY_BOTTLE)
			.unlockedBy("has_walnut", has(DelightfulItemTags.NUTS_WALNUT)),
			"food/honey_glazed_walnut", finished, enabled(DelightfulItems.HONEY_GLAZED_WALNUT), not(tagEmpty(DelightfulItemTags.NUTS_WALNUT)));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.MATCHA_LATTE.get(), 1, CookingRecipes.FAST_COOKING, 0.35F, Items.GLASS_BOTTLE)
			.addIngredient(ForgeTags.MILK)
			.addIngredient(Items.HONEY_BOTTLE)
			.addIngredient(DelightfulItemTags.MATCHA)
			.unlockedBy("has_matcha", has(DelightfulItemTags.MATCHA)),
			"food/cooking/matcha_latte", finished, enabled(DelightfulItems.MATCHA_LATTE), not(modLoaded(Modid.FR.get())));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.ENDER_NECTAR.get(), 1, CookingRecipes.SLOW_COOKING, 0.35F, Items.GLASS_BOTTLE)
			.addIngredient(DelightfulItems.MATCHA_LATTE.get())
			.addIngredient(Items.ENDER_EYE)
			.addIngredient(Ingredient.of(Items.CHORUS_FRUIT, Items.ENDER_EYE))
			.addIngredient(Ingredient.of(Items.CHORUS_FRUIT, Items.ENDER_EYE))
			.unlockedBy("has_ender_eye", has(Items.ENDER_EYE)),
			"food/cooking/ender_nectar", finished, enabled(DelightfulItems.MATCHA_LATTE), enabled(DelightfulItems.ENDER_NECTAR), not(modLoaded(Modid.FR.get())), not(modLoaded(Modid.BC.get())));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.BERRY_MATCHA_LATTE.get(), 1)
			.requires(Items.GLASS_BOTTLE)
			.requires(DelightfulItems.MATCHA_LATTE.get())
			.requires(ForgeTags.BERRIES)
			.requires(Items.ICE)
			.unlockedBy("has_matcha_latte", has(DelightfulItems.MATCHA_LATTE.get())),
			"food/berry_matcha_latte", finished, enabled(DelightfulItems.MATCHA_LATTE), enabled(DelightfulItems.BERRY_MATCHA_LATTE), tagEmpty(DelightfulItemTags.ICE_CUBES));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.BERRY_MATCHA_LATTE.get(), 1)
			.requires(Items.GLASS_BOTTLE)
			.requires(DelightfulItems.MATCHA_LATTE.get())
			.requires(ForgeTags.BERRIES)
			.requires(DelightfulItemTags.ICE_CUBES)
			.unlockedBy("has_matcha_latte", has(DelightfulItems.MATCHA_LATTE.get())),
			"food/berry_matcha_latte_neapolitan", finished, enabled(DelightfulItems.MATCHA_LATTE), enabled(DelightfulItems.BERRY_MATCHA_LATTE), not(tagEmpty(DelightfulItemTags.ICE_CUBES)));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.FIELD_SALAD.get(), 1)
			.requires(Items.BOWL)
			.requires(Ingredient.of(ForgeTags.SALAD_INGREDIENTS), 2)
			.requires(DelightfulItems.CACTUS_STEAK.get())
			.requires(ForgeTags.VEGETABLES_CARROT)
			.requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
			.requires(DelightfulItemTags.COOKED_NUTS)
			.unlockedBy("has_cactus_steak", has(DelightfulItems.CACTUS_STEAK.get())),
			"food/field_salad", finished, enabled(DelightfulItems.CACTUS_STEAK), enabled(DelightfulItems.FIELD_SALAD));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.NUT_DOUGH.get(), 3)
			.requires(DelightfulItemTags.NUT_BUTTER)
			.requires(Tags.Items.EGGS)
			.unlockedBy("has_nut_butter", has(DelightfulItemTags.NUT_BUTTER)),
			"food/nut_dough", finished, enabled(DelightfulItems.NUT_DOUGH));
	wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.NUT_DOUGH.get(), 3)
			.requires(Ingredient.of(DelightfulItemTags.DUSTS_FLOUR_NUT), 3)
			.requires(Tags.Items.EGGS)
			.requires(Items.SUGAR)
			.unlockedBy("has_nut_flour", has(DelightfulItemTags.DUSTS_FLOUR_NUT)),
			"food/nut_dough_from_flour_and_eggs", finished, enabled(DelightfulItems.NUT_DOUGH), not(tagEmpty(DelightfulItemTags.DUSTS_FLOUR_NUT)));
		sack(DelightfulItems.ACORN_SACK, DelightfulItems.ACORN.get(), "acorn", finished);
		sack(DelightfulItems.SALMONBERRY_SACK, DelightfulItems.SALMONBERRIES.get(), "salmonberry", finished);
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SALMONBERRY_ICE_CREAM.get(), 1)
			.requires(Items.BOWL)
			.requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
			.requires(ForgeTags.MILK)
			.requires(DelightfulItemTags.ICE_CUBES)
			.requires(Items.SUGAR)
			.unlockedBy("has_ice_cubes", has(DelightfulItemTags.ICE_CUBES)),
			"food/salmonberry_ice_cream", finished, enabled("salmonberry_ice_cream"), not(tagEmpty(DelightfulItemTags.ICE_CUBES)));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SALMONBERRY_ICE_CREAM.get(), 1)
			.requires(Items.BOWL)
			.requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
			.requires(ForgeTags.MILK)
			.requires(Items.ICE)
			.requires(Items.SUGAR)
			.unlockedBy("has_ice", has(Items.ICE)),
			"food/salmonberry_ice_cream_no_neapolitan", finished, enabled("salmonberry_ice_cream"), tagEmpty(DelightfulItemTags.ICE_CUBES));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SALMONBERRY_MILKSHAKE.get(), 3)
			.requires(Items.GLASS_BOTTLE, 3)
			.requires(DelightfulItems.SALMONBERRY_ICE_CREAM.get())
			.requires(ForgeTags.MILK)
			.unlockedBy("has_salmonberry_ice_cream", has(DelightfulItems.SALMONBERRY_ICE_CREAM.get())),
			"food/salmonberry_milkshake", finished, enabled(DelightfulItems.SALMONBERRY_MILKSHAKE), enabled(DelightfulItems.SALMONBERRY_ICE_CREAM));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.MATCHA_ICE_CREAM.get(), 1)
			.requires(Items.BOWL)
			.requires(DelightfulItemTags.MATCHA)
			.requires(ForgeTags.MILK)
			.requires(DelightfulItemTags.ICE_CUBES)
			.requires(Items.SUGAR)
			.unlockedBy("has_ice_cubes", has(DelightfulItemTags.ICE_CUBES)),
			"food/matcha_ice_cream", finished, enabled(DelightfulItems.MATCHA_ICE_CREAM), not(tagEmpty(DelightfulItemTags.ICE_CUBES)));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.MATCHA_ICE_CREAM.get(), 1)
			.requires(Items.BOWL)
			.requires(DelightfulItemTags.MATCHA)
			.requires(ForgeTags.MILK)
			.requires(Items.ICE)
			.requires(Items.SUGAR)
			.unlockedBy("has_ice", has(Items.ICE)),
			"food/matcha_ice_cream_no_neapolitan", finished, enabled(DelightfulItems.MATCHA_ICE_CREAM), tagEmpty(DelightfulItemTags.ICE_CUBES));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.MATCHA_MILKSHAKE.get(), 3)
			.requires(Items.GLASS_BOTTLE, 3)
			.requires(DelightfulItems.MATCHA_ICE_CREAM.get())
			.requires(ForgeTags.MILK)
			.unlockedBy("has_matcha_ice_cream", has(DelightfulItems.MATCHA_ICE_CREAM.get())),
			"food/matcha_milkshake", finished, enabled(DelightfulItems.MATCHA_MILKSHAKE), enabled(DelightfulItems.MATCHA_ICE_CREAM));
		wrap(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DelightfulItems.BAKLAVA.get(), 1)
			.pattern(" h ")
			.pattern("nnn")
			.pattern("sdg")
			.define('h', Items.HONEY_BOTTLE)
			.define('n', DelightfulItemTags.COOKED_NUTS)
			.define('d', ForgeTags.DOUGH)
			.define('s', DelightfulItemTags.FRUITS_CITRUS)
			.define('g', DelightfulItemTags.HOT_SPICES)
			.unlockedBy("has_nuts", has(DelightfulItemTags.COOKED_NUTS)),
			"food/baklava", finished, enabled(DelightfulItems.BAKLAVA), not(tagEmpty(DelightfulItemTags.FRUITS_CITRUS)));
		wrap(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DelightfulItems.BAKLAVA.get(), 1)
			.pattern(" h ")
			.pattern("nnn")
			.pattern("sdg")
			.define('h', Items.HONEY_BOTTLE)
			.define('n', DelightfulItemTags.COOKED_NUTS)
			.define('d', ForgeTags.DOUGH)
			.define('s', Items.SUGAR)
			.define('g', DelightfulItemTags.HOT_SPICES)
			.unlockedBy("has_nuts", has(DelightfulItemTags.COOKED_NUTS)),
			"food/baklava_no_citrus", finished, enabled(DelightfulItems.BAKLAVA), tagEmpty(DelightfulItemTags.FRUITS_CITRUS));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.BAKLAVA.get(), 1)
			.requires(DelightfulItems.BAKLAVA_SLICE.get(), 4)
			.unlockedBy("has_baklava_slice", has(DelightfulItems.BAKLAVA_SLICE.get())),
			"food/baklava_from_slices", finished, enabled(DelightfulItems.BAKLAVA));
		wrap(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DelightfulItems.SALMONBERRY_PIE.get(), 1)
			.pattern("###")
			.pattern("aaa")
			.pattern("xOx")
			.define('#', ForgeTags.GRAIN_WHEAT)
			.define('a', DelightfulItemTags.FRUITS_SALMONBERRIES)
			.define('x', Items.SUGAR)
			.define('O', ModItems.PIE_CRUST.get())
			.unlockedBy("has_pie_crust", has(ModItems.PIE_CRUST.get())),
			"food/salmonberry_pie", finished, enabled(DelightfulItems.SALMONBERRY_PIE));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SALMONBERRY_PIE.get(), 1)
			.requires(DelightfulItems.SALMONBERRY_PIE_SLICE.get(), 4)
			.unlockedBy("has_salmonberry_pie_slice", has(DelightfulItems.SALMONBERRY_PIE_SLICE.get())),
			"food/salmonberry_pie_from_slices", finished, enabled(DelightfulItems.SALMONBERRY_PIE));
		wrap(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.PUMPKIN_PIE, 1)
			.pattern("ppp")
			.pattern("pep")
			.pattern("scs")
			.define('p', ModItems.PUMPKIN_SLICE.get())
			.define('e', Tags.Items.EGGS)
			.define('s', Items.SUGAR)
			.define('c', ModItems.PIE_CRUST.get())
			.unlockedBy("has_pie_crust", has(ModItems.PIE_CRUST.get())),
			"food/pumpkin_pie", finished, enabled(DelightfulItems.PUMPKIN_PIE_SLICE), not(modLoaded("create_central_kitchen")));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.PUMPKIN_PIE, 1)
			.requires(DelightfulItems.PUMPKIN_PIE_SLICE.get(), 4)
			.unlockedBy("has_pumpkin_pie_slice", has(DelightfulItems.PUMPKIN_PIE_SLICE.get())),
			"food/pumpkin_pie_from_slices", finished, enabled(DelightfulItems.PUMPKIN_PIE_SLICE), not(modLoaded("create_central_kitchen")));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DelightfulItems.SALMONBERRY_PIPS.get())
			.requires(DelightfulItems.SALMONBERRIES.get())
			.unlockedBy("has_salmonberries", has(DelightfulItemTags.FRUITS_SALMONBERRIES)),
			"salmonberry_pips", finished, enabled("salmonberry_pips"));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CANTALOUPE_SLICE.get(), 2)
			.requires(DelightfulItems.CANTALOUPE.get())
			.unlockedBy("has_cantaloupe", has(DelightfulItems.CANTALOUPE.get())),
			"cantaloupe_slice", finished, enabled(DelightfulItems.CANTALOUPE), enabled(DelightfulItems.CANTALOUPE_SLICE));
		wrap(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DelightfulItems.CANTALOUPE.get(), 1)
			.pattern("cc")
			.pattern("cc")
			.define('c', DelightfulItems.CANTALOUPE_SLICE.get())
			.unlockedBy("has_cantaloupe_slice", has(DelightfulItems.CANTALOUPE_SLICE.get())),
			"cantaloupe_from_slices", finished, enabled(DelightfulItems.CANTALOUPE), enabled(DelightfulItems.CANTALOUPE_SLICE));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
			Ingredient.of(DelightfulItems.CANTALOUPE.get()),
			Ingredient.of(ForgeTags.TOOLS_KNIVES),
			DelightfulItems.CANTALOUPE_SLICE.get(), 4),
			"cutting/cantaloupe", finished, enabled(DelightfulItems.CANTALOUPE), enabled(DelightfulItems.CANTALOUPE_SLICE));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CANTALOUPE_SEEDS.get())
			.requires(DelightfulItems.CANTALOUPE_SLICE.get())
			.unlockedBy("has_cantaloupe", has(DelightfulItemTags.FRUITS_CANTALOUPE)),
			"cantaloupe_seeds", finished, enabled(DelightfulItems.CANTALOUPE), enabled(DelightfulItems.CANTALOUPE_SEEDS));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.STUFFED_CANTALOUPE_BLOCK.get(), 1, CookingRecipes.SLOW_COOKING, 0.35F, DelightfulItems.CANTALOUPE.get())
			.addIngredient(ForgeTags.GRAIN_RICE)
			.addIngredient(ForgeTags.COOKED_MUTTON)
			.addIngredient(ForgeTags.VEGETABLES_ONION)
			.addIngredient(ForgeTags.BERRIES)
			.addIngredient(ForgeTags.SEEDS)
			.unlockedBy("has_cantaloupe", has(DelightfulItems.CANTALOUPE.get())),
			"food/cooking/stuffed_cantaloupe", finished, enabled(DelightfulItems.STUFFED_CANTALOUPE), enabled(DelightfulItems.CANTALOUPE));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.WRAPPED_CANTALOUPE.get(), 1)
			.requires(DelightfulItemTags.FRUITS_CANTALOUPE)
			.requires(ForgeTags.COOKED_BACON)
			.unlockedBy("has_cantaloupe_slice", has(DelightfulItemTags.FRUITS_CANTALOUPE)),
			"food/wrapped_cantaloupe", finished, enabled(DelightfulItems.WRAPPED_CANTALOUPE), enabled(DelightfulItems.CANTALOUPE));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CANTALOUPE_BREAD.get(), 1)
			.requires(ForgeTags.DOUGH)
			.requires(DelightfulItemTags.FRUITS_CANTALOUPE)
			.requires(Items.SUGAR)
			.unlockedBy("has_cantaloupe_slice", has(DelightfulItemTags.FRUITS_CANTALOUPE)),
			"food/cantaloupe_bread", finished, enabled(DelightfulItems.CANTALOUPE_BREAD), enabled(DelightfulItems.CANTALOUPE));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(DelightfulItems.ROCK_CANDY.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F, Items.STICK)
			.addIngredient(DelightfulItemTags.GEMS_ROSE_QUARTZ)
			.addIngredient(Items.SUGAR)
			.addIngredient(ForgeTags.BERRIES)
			.unlockedBy("has_rose_quartz", has(DelightfulItemTags.GEMS_ROSE_QUARTZ)),
			"food/cooking/rock_candy", finished, enabled(DelightfulItems.ROCK_CANDY), not(tagEmpty(DelightfulItemTags.GEMS_ROSE_QUARTZ)), not(modLoaded(Modid.DUNG.get())));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.ANIMAL_OIL_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
			.addIngredient(DelightfulItems.ANIMAL_FAT.get(), 3)
			.unlockedBy("has_animal_fat", has(DelightfulItems.ANIMAL_FAT.get())),
			"cooking/animal_oil_bottle", finished, enabled(DelightfulItems.ANIMAL_OIL_BOTTLE));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.JAM_JAR.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F, Items.GLASS_BOTTLE)
			.addIngredient(Ingredient.of(DelightfulItemTags.FRUITS), 3)
			.addIngredient(Items.SUGAR, 2)
			.unlockedBy("has_fruit", has(DelightfulItemTags.FRUITS)),
			"food/cooking/jam_jar", finished, enabled(DelightfulItems.JAM_JAR), not(modLoaded(Modid.BC.get())), not(modLoaded(Modid.HH.get())), not(modLoaded(Modid.FRD.get())));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.GLOW_JAM_JAR.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F, Items.GLASS_BOTTLE)
			.addIngredient(Ingredient.of(DelightfulItemTags.FRUITS_GLOW_BERRIES), 3)
			.addIngredient(Items.SUGAR, 3)
			.unlockedBy("has_glow_berries", has(DelightfulItemTags.FRUITS_GLOW_BERRIES)),
			"food/cooking/glow_jam_jar", finished, enabled(DelightfulItems.GLOW_JAM_JAR), not(modLoaded(Modid.BC.get())), not(modLoaded(Modid.HH.get())), not(modLoaded(Modid.FRD.get())));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.NUT_BUTTER_BOTTLE.get(),
			1,
			CookingRecipes.NORMAL_COOKING,
			0.35F,
			Items.GLASS_BOTTLE
			)
			.addIngredient(Ingredient.of(DelightfulItemTags.NUTS), 3)
			.addIngredient(Items.KELP)
			.addIngredient(Items.SUGAR)
			.unlockedBy("has_nuts", has(DelightfulItemTags.NUTS)),
			"food/cooking/nut_butter_bottle", finished, enabled(DelightfulItems.NUT_BUTTER_BOTTLE), not(tagEmpty(DelightfulItemTags.NUTS)), tagEmpty(DelightfulItemTags.SALT), not(modLoaded(Modid.VD.get())));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.NUT_BUTTER_BOTTLE.get(),
			1,
			CookingRecipes.NORMAL_COOKING,
			0.35F,
			Items.GLASS_BOTTLE
			)
			.addIngredient(Ingredient.of(DelightfulItemTags.NUTS), 3)
			.addIngredient(Items.KELP)
			.addIngredient(DelightfulItemTags.SALT)
			.addIngredient(Items.SUGAR)
			.unlockedBy("has_nuts", has(DelightfulItemTags.NUTS)),
			"food/cooking/nut_butter_bottle_from_salt", finished, enabled(DelightfulItems.NUT_BUTTER_BOTTLE), not(tagEmpty(DelightfulItemTags.NUTS)), not(tagEmpty(DelightfulItemTags.SALT)), not(modLoaded(Modid.VD.get())));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.CACTUS_CHILI.get(), 1, CookingRecipes.NORMAL_COOKING, 1F, Items.BOWL)
			.addIngredient(ForgeTags.RAW_PORK)
			.addIngredient(DelightfulItemTags.VEGETABLES_CACTUS)
			.addIngredient(ModItems.TOMATO_SAUCE.get())
			.addIngredient(DelightfulItemTags.HOT_SPICES)
			.addIngredient(DelightfulItemTags.VEGETABLES_CORN)
			.unlockedBy("has_cactus", has(DelightfulItemTags.VEGETABLES_CACTUS))
			.setRecipeBookTab(CookingPotRecipeBookTab.MEALS),
			"food/cooking/cactus_chili_from_corn", finished, enabled(DelightfulItems.CACTUS_CHILI), not(tagEmpty(DelightfulItemTags.VEGETABLES_CORN)));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.CACTUS_CHILI.get(), 1, CookingRecipes.NORMAL_COOKING, 1F, Items.BOWL)
			.addIngredient(ForgeTags.RAW_PORK)
			.addIngredient(DelightfulItemTags.VEGETABLES_CACTUS)
			.addIngredient(ModItems.TOMATO_SAUCE.get())
			.addIngredient(DelightfulItemTags.HOT_SPICES)
			.addIngredient(ForgeTags.VEGETABLES)
			.unlockedBy("has_cactus", has(DelightfulItemTags.VEGETABLES_CACTUS))
			.setRecipeBookTab(CookingPotRecipeBookTab.MEALS),
			"food/cooking/cactus_chili", finished, enabled(DelightfulItems.CACTUS_CHILI), tagEmpty(DelightfulItemTags.VEGETABLES_CORN));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.CACTUS_SOUP.get(), 1, CookingRecipes.NORMAL_COOKING, 1F, Items.BOWL)
			.addIngredient(DelightfulItemTags.VEGETABLES_CACTUS)
			.addIngredient(ForgeTags.GRAIN_RICE)
			.addIngredient(ForgeTags.VEGETABLES_TOMATO)
			.addIngredient(ForgeTags.VEGETABLES_ONION)
			.unlockedBy("has_cactus_chunk", has(DelightfulItemTags.VEGETABLES_CACTUS))
			.setRecipeBookTab(CookingPotRecipeBookTab.MEALS),
			"food/cooking/cactus_soup", finished, enabled(DelightfulItems.CACTUS_SOUP));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.VENISON_STEW.get(), 1, CookingRecipes.NORMAL_COOKING, 1F, Items.BOWL)
			.addIngredient(DelightfulItemTags.RAW_VENISON)
			.addIngredient(ForgeTags.VEGETABLES_CARROT)
			.addIngredient(ForgeTags.VEGETABLES_POTATO)
			.addIngredient(DelightfulItems.ANIMAL_FAT.get())
			.unlockedBy("has_raw_venison", has(DelightfulItemTags.RAW_VENISON))
			.setRecipeBookTab(CookingPotRecipeBookTab.MEALS),
			"food/cooking/venison_stew", finished, enabled(DelightfulItems.VENISON_STEW), not(tagEmpty(DelightfulItemTags.RAW_VENISON_COMPAT)), enabled(DelightfulItems.ANIMAL_FAT));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.VENISON_STEW.get(), 1, CookingRecipes.NORMAL_COOKING, 1F, Items.BOWL)
			.addIngredient(DelightfulItemTags.RAW_VENISON)
			.addIngredient(ForgeTags.VEGETABLES_CARROT)
			.addIngredient(ForgeTags.VEGETABLES_POTATO)
			.addIngredient(ForgeTags.VEGETABLES_POTATO)
			.unlockedBy("has_raw_venison", has(DelightfulItemTags.RAW_VENISON))
			.setRecipeBookTab(CookingPotRecipeBookTab.MEALS),
			"food/cooking/venison_stew_no_animal_fat", finished, enabled(DelightfulItems.VENISON_STEW), not(tagEmpty(DelightfulItemTags.RAW_VENISON_COMPAT)), not(enabled(DelightfulItems.ANIMAL_FAT)));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.COCONUT_CURRY.get(), 1, CookingRecipes.NORMAL_COOKING, 1F, Items.BOWL)
			.addIngredient(DelightfulItemTags.COCONUT)
			.addIngredient(ForgeTags.MILK)
			.addIngredient(ForgeTags.RAW_CHICKEN)
			.addIngredient(ModItems.TOMATO_SAUCE.get())
			.addIngredient(ForgeTags.VEGETABLES_ONION)
			.addIngredient(DelightfulItemTags.TEA_LEAVES)
			.unlockedBy("has_coconut", has(DelightfulItemTags.COCONUT))
			.setRecipeBookTab(CookingPotRecipeBookTab.MEALS),
			"food/cooking/coconut_curry_no_ginger", finished, enabled(DelightfulItems.COCONUT_CURRY), not(tagEmpty(DelightfulItemTags.COCONUT)), tagEmpty(DelightfulItemTags.VEGETABLES_GINGER));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.COCONUT_CURRY.get(), 1, CookingRecipes.NORMAL_COOKING, 1F, Items.BOWL)
			.addIngredient(DelightfulItemTags.COCONUT)
			.addIngredient(ForgeTags.MILK)
			.addIngredient(ForgeTags.RAW_CHICKEN)
			.addIngredient(ModItems.TOMATO_SAUCE.get())
			.addIngredient(DelightfulItemTags.VEGETABLES_GINGER)
			.addIngredient(DelightfulItemTags.TEA_LEAVES)
			.unlockedBy("has_coconut", has(DelightfulItemTags.COCONUT))
			.setRecipeBookTab(CookingPotRecipeBookTab.MEALS),
			"food/cooking/coconut_curry", finished, enabled(DelightfulItems.COCONUT_CURRY), not(tagEmpty(DelightfulItemTags.COCONUT)), not(tagEmpty(DelightfulItemTags.VEGETABLES_GINGER)));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.CRAB_RANGOON.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
			.addIngredient(ForgeTags.DOUGH)
			.addIngredient(DelightfulItemTags.CHEESE)
			.addIngredient(DelightfulItemTags.COOKED_CRAB_MEAT)
			.unlockedBy("has_cooked_crab", has(DelightfulItemTags.COOKED_CRAB_MEAT)),
			"food/cooking/crab_rangoon", finished, enabled(DelightfulItems.CRAB_RANGOON), not(tagEmpty(DelightfulItemTags.COOKED_CRAB_MEAT)), not(tagEmpty(DelightfulItemTags.CHEESE)), tagEmpty(DelightfulItemTags.CHEESE_MILD_CREAM));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.CRAB_RANGOON.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
			.addIngredient(ForgeTags.DOUGH)
			.addIngredient(DelightfulItemTags.CHEESE_MILD_CREAM)
			.addIngredient(DelightfulItemTags.COOKED_CRAB_MEAT)
			.unlockedBy("has_cooked_crab", has(DelightfulItemTags.COOKED_CRAB_MEAT)),
			"food/cooking/crab_rangoon_from_cream_cheese", finished, enabled(DelightfulItems.CRAB_RANGOON), not(tagEmpty(DelightfulItemTags.COOKED_CRAB_MEAT)), not(tagEmpty(DelightfulItemTags.CHEESE_MILD_CREAM)));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			DelightfulItems.CRAB_RANGOON.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
			.addIngredient(ForgeTags.DOUGH)
			.addIngredient(ForgeTags.MILK)
			.addIngredient(DelightfulItemTags.COOKED_CRAB_MEAT)
			.unlockedBy("has_cooked_crab", has(DelightfulItemTags.COOKED_CRAB_MEAT)),
			"food/cooking/crab_rangoon_from_milk", finished, enabled(DelightfulItems.CRAB_RANGOON), not(tagEmpty(DelightfulItemTags.COOKED_CRAB_MEAT)), tagEmpty(DelightfulItemTags.CHEESE));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
			Items.HONEY_BOTTLE, 3, CookingRecipes.NORMAL_COOKING, 0.35F)
			.addIngredient(Items.HONEY_BOTTLE, 2)
			.addIngredient(DelightfulItems.CHOPPED_CLOVER.get(), 4),
			"food/clover_honey", finished, enabled("clover_honey"), enabled(DelightfulItems.CHOPPED_CLOVER), or(modLoaded(Modid.BB.get()), not(tagEmpty(DelightfulItemTags.CLOVER))));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.WHEAT_DOUGH.get(), 3)
			.requires(Ingredient.of(DelightfulItemTags.DUSTS_FLOUR_WHEAT), 3)
			.requires(Tags.Items.EGGS)
			.unlockedBy("has_wheat_flour", has(DelightfulItemTags.DUSTS_FLOUR_WHEAT)),
			"food/wheat_dough_from_flour_and_eggs", finished, not(tagEmpty(DelightfulItemTags.DUSTS_FLOUR_WHEAT)));
		wrap(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PIE_CRUST.get(), 1)
			.pattern("fAf")
			.pattern(" f ")
			.define('f', ForgeTags.GRAIN_WHEAT)
			.define('A', DelightfulItems.ANIMAL_FAT.get())
			.unlockedBy("has_fat", has(DelightfulItems.ANIMAL_FAT.get())),
			"food/pie_crust_from_fat", finished, enabled(DelightfulItems.ANIMAL_FAT));
		wrap(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PIE_CRUST.get(), 1)
			.pattern("fMf")
			.pattern(" f ")
			.define('f', DelightfulItemTags.FLOUR)
			.define('M', ForgeTags.MILK)
			.unlockedBy("has_flour", has(DelightfulItemTags.FLOUR)),
			"food/pie_crust_from_flour", finished, not(tagEmpty(DelightfulItemTags.FLOUR)));
		wrap(ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PIE_CRUST.get(), 1)
			.pattern("fAf")
			.pattern(" f ")
			.define('f', DelightfulItemTags.FLOUR)
			.define('A', DelightfulItems.ANIMAL_FAT.get())
			.unlockedBy("has_flour", has(DelightfulItemTags.FLOUR)),
			"food/pie_crust_from_flour_and_fat", finished, enabled(DelightfulItems.ANIMAL_FAT), not(tagEmpty(DelightfulItemTags.FLOUR)));
		wrap(ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.TORCH, 8)
			.define('o', DelightfulItems.ANIMAL_OIL_BOTTLE.get())
			.define('s', Tags.Items.RODS_WOODEN)
			.pattern("o")
			.pattern("s")
			.unlockedBy("has_oil_bottle", has(DelightfulItems.ANIMAL_OIL_BOTTLE.get())),
			"torch_from_animal_oil_bottle", finished, enabled(DelightfulItems.ANIMAL_OIL_BOTTLE));
		wrap(ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.CANDLE, 1)
			.define('f', DelightfulItems.ANIMAL_FAT.get())
			.define('s', Tags.Items.STRING)
			.pattern("s")
			.pattern("f")
			.pattern("f")
			.unlockedBy("has_fat", has(DelightfulItems.ANIMAL_FAT.get())),
			"candle_from_animal_fat", finished, enabled(DelightfulItems.ANIMAL_FAT));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
			Ingredient.of(Items.OAK_SAPLING),
			new ToolActionIngredient(ToolActions.AXE_DIG),
			Items.STICK, 1)
			.addResultWithChance(DelightfulItems.ACORN.get(), 0.2F, 1),
			"cutting/oak_sapling", finished, enabled(DelightfulItems.ACORN));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
			Ingredient.of(Items.DARK_OAK_SAPLING),
			new ToolActionIngredient(ToolActions.AXE_DIG),
			Items.STICK, 1)
			.addResultWithChance(DelightfulItems.ACORN.get(), 0.2F, 1),
			"cutting/dark_oak_sapling", finished, enabled(DelightfulItems.ACORN));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
			Ingredient.of(DelightfulItemTags.TEA_LEAVES_GREEN),
			new ToolActionIngredient(ToolActions.SHOVEL_DIG),
			Items.GREEN_DYE, 1)
			.addResultWithChance(DelightfulItems.MATCHA.get(), 0.5F, 1),
			"cutting/green_tea_leaves", finished, enabled(DelightfulItems.MATCHA), not(tagEmpty(DelightfulItemTags.TEA_LEAVES_GREEN)), not(modLoaded(Modid.YH.get())));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
			Ingredient.of(DelightfulItemTags.CLOVER),
			Ingredient.of(ForgeTags.TOOLS_KNIVES),
			DelightfulItems.CHOPPED_CLOVER.get(), 2),
			"cutting/clover", finished, enabled(DelightfulItems.CHOPPED_CLOVER), not(tagEmpty(DelightfulItemTags.CLOVER)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
			Ingredient.of(DelightfulItems.BAKLAVA.get()),
			Ingredient.of(ForgeTags.TOOLS_KNIVES),
			DelightfulItems.BAKLAVA_SLICE.get(), 4),
			"cutting/baklava", finished, enabled(DelightfulItems.BAKLAVA), enabled(DelightfulItems.BAKLAVA_SLICE));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
			Ingredient.of(DelightfulItems.SALMONBERRY_PIE.get()),
			Ingredient.of(ForgeTags.TOOLS_KNIVES),
			DelightfulItems.SALMONBERRY_PIE_SLICE.get(), 4),
			"cutting/salmonberry_pie", finished, enabled("salmonberry_pie"), enabled("salmonberry_pie_slice"));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
			Ingredient.of(Items.PUMPKIN_PIE),
			Ingredient.of(ForgeTags.TOOLS_KNIVES),
			DelightfulItems.PUMPKIN_PIE_SLICE.get(), 4),
			"cutting/pumpkin_pie", finished, enabled("pumpkin_pie_slice"));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
			Ingredient.of(DelightfulItems.MINI_MELON.get()),
			Ingredient.of(ForgeTags.TOOLS_KNIVES),
			Items.MELON_SLICE, 4),
			"cutting/mini_melon", finished, enabled(DelightfulItems.MINI_MELON));
		wrap(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DelightfulItems.MINI_MELON.get(), 1)
			.pattern("mm")
			.pattern("mm")
			.define('m', Items.MELON_SLICE)
			.unlockedBy("has_melon_slice", has(Items.MELON_SLICE)),
			"mini_melon_from_slices", finished, enabled(DelightfulItems.MINI_MELON));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
			Ingredient.of(DelightfulItemTags.RAW_VENISON_COMPAT),
			Ingredient.of(ForgeTags.TOOLS_KNIVES),
			DelightfulItems.VENISON_CHOPS.get(), 2),
			"cutting/raw_venison", finished, enabled(DelightfulItems.VENISON_CHOPS), not(tagEmpty(DelightfulItemTags.RAW_VENISON_COMPAT)), tagEmpty(DelightfulItemTags.RAW_VENISON_CHOP_COMPAT));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
			Ingredient.of(DelightfulItemTags.COOKED_VENISON_COMPAT),
			Ingredient.of(ForgeTags.TOOLS_KNIVES),
			DelightfulItems.COOKED_VENISON_CHOPS.get(), 2),
			"cutting/cooked_venison", finished, enabled(DelightfulItems.COOKED_VENISON_CHOPS), not(tagEmpty(DelightfulItemTags.COOKED_VENISON_COMPAT)), tagEmpty(DelightfulItemTags.COOKED_VENISON_CHOP_COMPAT));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
			Ingredient.of(DelightfulItems.CACTUS_STEAK.get()),
			Ingredient.of(ForgeTags.TOOLS_KNIVES),
			Items.GREEN_DYE, 1),
			"cutting/cactus_steak", finished, enabled(DelightfulItems.CACTUS_STEAK));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.MELON_SLICE, 2)
			.requires(DelightfulItems.MINI_MELON.get())
			.unlockedBy("has_mini_melon", has(DelightfulItems.MINI_MELON.get())),
			"melon_slice", finished, enabled(DelightfulItems.MINI_MELON));
		wrap(shaped(RecipeCategory.BUILDING_BLOCKS, DelightfulItems.SALMONBERRY_ICE_CREAM_BLOCK, 8)
			.pattern("sss")
			.pattern("sis")
			.pattern("sss")
			.define('s', Blocks.SNOW_BLOCK)
			.define('i', DelightfulItems.SALMONBERRY_ICE_CREAM.get())
			.unlockedBy("has_salmonberry_ice_cream", has(DelightfulItems.SALMONBERRY_ICE_CREAM.get())),
			"salmonberry_ice_cream_block", finished, enabled(DelightfulItems.SALMONBERRY_ICE_CREAM_BLOCK), enabled(DelightfulItems.SALMONBERRY_ICE_CREAM), modLoaded(Modid.N.get()));
		wrap(shaped(RecipeCategory.BUILDING_BLOCKS, DelightfulItems.MATCHA_ICE_CREAM_BLOCK, 8)
			.pattern("sss")
			.pattern("sis")
			.pattern("sss")
			.define('s', Blocks.SNOW_BLOCK)
			.define('i', DelightfulItems.MATCHA_ICE_CREAM.get())
			.unlockedBy("has_matcha_ice_cream", has(DelightfulItems.MATCHA_ICE_CREAM.get())),
			"matcha_ice_cream_block", finished, enabled(DelightfulItems.MATCHA_ICE_CREAM_BLOCK), enabled(DelightfulItems.MATCHA_ICE_CREAM), enabled(DelightfulItems.MATCHA), modLoaded(Modid.N.get()));
		wrap(shaped(RecipeCategory.BUILDING_BLOCKS, DelightfulItems.GLOW_JAM_COOKIE_TILES, 4)
			.pattern("cc")
			.pattern("cc")
			.define('c', DelightfulItems.GLOW_JAM_COOKIE.get())
			.unlockedBy("has_glow_jam_cookie", has(DelightfulItems.GLOW_JAM_COOKIE.get())),
			"integration/cookielicious/glow_jam_cookie_tiles", finished,
			enabled(DelightfulItems.GLOW_JAM_COOKIE_TILES),
			enabled(DelightfulItems.GLOW_JAM_COOKIE),
			modLoaded(Modid.COOK.get()));
		wrap(shaped(RecipeCategory.BUILDING_BLOCKS, DelightfulItems.GLOW_JAM_COOKIE_TILE_SLAB, 6)
			.pattern("ttt")
			.define('t', DelightfulItems.GLOW_JAM_COOKIE_TILES.get())
			.unlockedBy("has_glow_jam_cookie_tiles", has(DelightfulItems.GLOW_JAM_COOKIE_TILES.get())),
			"integration/cookielicious/glow_jam_cookie_tile_slab", finished,
			enabled(DelightfulItems.GLOW_JAM_COOKIE_TILE_SLAB),
			enabled(DelightfulItems.GLOW_JAM_COOKIE_TILES),
			enabled(DelightfulItems.GLOW_JAM_COOKIE),
			modLoaded(Modid.COOK.get()));
		wrap(shaped(RecipeCategory.BUILDING_BLOCKS, DelightfulItems.GLOW_JAM_COOKIE_TILE_STAIRS, 4)
			.pattern("t  ")
			.pattern("tt ")
			.pattern("ttt")
			.define('t', DelightfulItems.GLOW_JAM_COOKIE_TILES.get())
			.unlockedBy("has_glow_jam_cookie_tiles", has(DelightfulItems.GLOW_JAM_COOKIE_TILES.get())),
			"integration/cookielicious/glow_jam_cookie_tile_stairs", finished,
			enabled(DelightfulItems.GLOW_JAM_COOKIE_TILE_STAIRS),
			enabled(DelightfulItems.GLOW_JAM_COOKIE_TILES),
			enabled(DelightfulItems.GLOW_JAM_COOKIE),
			modLoaded(Modid.COOK.get()));
		wrap(shaped(RecipeCategory.BUILDING_BLOCKS, DelightfulItems.GLOW_JAM_COOKIE_TILE_WALL, 6)
			.pattern("ttt")
			.pattern("ttt")
			.define('t', DelightfulItems.GLOW_JAM_COOKIE_TILES.get())
			.unlockedBy("has_glow_jam_cookie_tiles", has(DelightfulItems.GLOW_JAM_COOKIE_TILES.get())),
			"integration/cookielicious/glow_jam_cookie_tile_wall", finished,
			enabled(DelightfulItems.GLOW_JAM_COOKIE_TILE_WALL),
			enabled(DelightfulItems.GLOW_JAM_COOKIE_TILES),
			enabled(DelightfulItems.GLOW_JAM_COOKIE),
			modLoaded(Modid.COOK.get()));
		wrap(shaped(RecipeCategory.BUILDING_BLOCKS, DelightfulItems.SOURCE_BERRY_COOKIE_TILES, 4)
			.pattern("cc")
			.pattern("cc")
			.define('c', DelightfulItemTags.COOKIES_SOURCE_BERRY)
			.unlockedBy("has_source_berry_cookie", has(DelightfulItemTags.COOKIES_SOURCE_BERRY)),
			"integration/cookielicious/source_berry_cookie_tiles", finished,
			enabled(DelightfulItems.SOURCE_BERRY_COOKIE_TILES),
			modLoaded(Modid.COOK.get()),
			modLoaded(Modid.AN.get()));
		wrap(shaped(RecipeCategory.BUILDING_BLOCKS, DelightfulItems.SOURCE_BERRY_COOKIE_TILE_SLAB, 6)
			.pattern("ttt")
			.define('t', DelightfulItems.SOURCE_BERRY_COOKIE_TILES.get())
			.unlockedBy("has_source_berry_cookie_tiles", has(DelightfulItems.SOURCE_BERRY_COOKIE_TILES.get())),
			"integration/cookielicious/source_berry_cookie_tile_slab", finished,
			enabled(DelightfulItems.SOURCE_BERRY_COOKIE_TILE_SLAB),
			enabled(DelightfulItems.SOURCE_BERRY_COOKIE_TILES),
			modLoaded(Modid.COOK.get()),
			modLoaded(Modid.AN.get()));
		wrap(shaped(RecipeCategory.BUILDING_BLOCKS, DelightfulItems.SOURCE_BERRY_COOKIE_TILE_STAIRS, 4)
			.pattern("t  ")
			.pattern("tt ")
			.pattern("ttt")
			.define('t', DelightfulItems.SOURCE_BERRY_COOKIE_TILES.get())
			.unlockedBy("has_source_berry_cookie_tiles", has(DelightfulItems.SOURCE_BERRY_COOKIE_TILES.get())),
			"integration/cookielicious/source_berry_cookie_tile_stairs", finished,
			enabled(DelightfulItems.SOURCE_BERRY_COOKIE_TILE_STAIRS),
			enabled(DelightfulItems.SOURCE_BERRY_COOKIE_TILES),
			modLoaded(Modid.COOK.get()),
			modLoaded(Modid.AN.get()));
		wrap(shaped(RecipeCategory.BUILDING_BLOCKS, DelightfulItems.SOURCE_BERRY_COOKIE_TILE_WALL, 6)
			.pattern("ttt")
			.pattern("ttt")
			.define('t', DelightfulItems.SOURCE_BERRY_COOKIE_TILES.get())
			.unlockedBy("has_source_berry_cookie_tiles", has(DelightfulItems.SOURCE_BERRY_COOKIE_TILES.get())),
			"integration/cookielicious/source_berry_cookie_tile_wall", finished,
			enabled(DelightfulItems.SOURCE_BERRY_COOKIE_TILE_WALL),
			enabled(DelightfulItems.SOURCE_BERRY_COOKIE_TILES),
			modLoaded(Modid.COOK.get()),
			modLoaded(Modid.AN.get()));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(DelightfulItems.SALMONBERRY_GUMMY.get(), 1, 200, 1F)
			.addIngredient(DelightfulItemTags.FRUITS_SALMONBERRIES)
			.addIngredient(Items.SUGAR)
			.addIngredient(Items.HONEY_BOTTLE)
			.addIngredient(Items.KELP)
			.unlockedBy("has_salmonberries", has(DelightfulItemTags.FRUITS_SALMONBERRIES)),
			"gummy/salmonberries", finished, modLoaded(Modid.CR.get()), enabled(DelightfulItems.SALMONBERRIES), enabled(DelightfulItems.SALMONBERRY_GUMMY));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(DelightfulItems.MATCHA_GUMMY.get(), 1, 200, 1F)
			.addIngredient(DelightfulItemTags.MATCHA)
			.addIngredient(Items.SUGAR)
			.addIngredient(Items.HONEY_BOTTLE)
			.addIngredient(Items.KELP)
			.unlockedBy("has_matcha", has(DelightfulItemTags.MATCHA)),
			"gummy/matcha", finished, modLoaded(Modid.CR.get()), enabled(DelightfulItems.MATCHA), enabled(DelightfulItems.MATCHA_GUMMY));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(DelightfulItems.CANTALOUPE_GUMMY.get(), 1, 200, 1F)
			.addIngredient(DelightfulItemTags.FRUITS_CANTALOUPE)
			.addIngredient(Items.SUGAR)
			.addIngredient(Items.HONEY_BOTTLE)
			.addIngredient(Items.KELP)
			.unlockedBy("has_cantaloupe", has(DelightfulItemTags.FRUITS_CANTALOUPE)),
			"gummy/cantaloupe", finished, modLoaded(Modid.CR.get()), enabled(DelightfulItems.CANTALOUPE), enabled(DelightfulItems.CANTALOUPE_GUMMY));

		// Unwrappables
		ConditionalRecipe.builder()
			.addCondition(not(tagEmpty(DelightfulItemTags.CHOCOLATE)))
			.addRecipe(f -> ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CHOCOLATE_PIE.get(), 1)
			.pattern("ccc")
			.pattern("mmm")
			.pattern("xOx")
			.define('c', DelightfulItemTags.CHOCOLATE)
			.define('m', ForgeTags.MILK)
			.define('x', Items.SUGAR)
			.define('O', ModItems.PIE_CRUST.get())
			.unlockedBy("has_pie_crust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PIE_CRUST.get()))
			.save(f))
			.addCondition(tagEmpty(DelightfulItemTags.CHOCOLATE))
			.addRecipe(f -> ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CHOCOLATE_PIE.get(), 1)
			.pattern("ccc")
			.pattern("mmm")
			.pattern("xOx")
			.define('c', Items.COCOA_BEANS)
			.define('m', ForgeTags.MILK)
			.define('x', Items.SUGAR)
			.define('O', ModItems.PIE_CRUST.get())
			.unlockedBy("has_pie_crust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PIE_CRUST.get()))
			.save(f))
			.generateAdvancement()
			.build(finished, ModItems.CHOCOLATE_PIE.getId());
		CuttingBoardRecipeBuilder.cuttingRecipe(
			Ingredient.of(DelightfulItems.WILD_SALMONBERRIES.get()),
			Ingredient.of(ForgeTags.TOOLS_KNIVES),
			DelightfulItems.SALMONBERRIES.get(), 1
			).addResult(Items.ORANGE_DYE, 1)
			.build(finished, Util.delight("cutting/wild_salmonberries"));
		CuttingBoardRecipeBuilder.cuttingRecipe(
			Ingredient.of(Items.DEAD_BUSH),
			new ToolActionIngredient(ToolActions.AXE_DIG),
			Items.STICK, 2)
			.build(finished, Util.delight("cutting/dead_bush"));
		CuttingBoardRecipeBuilder.cuttingRecipe(
			Ingredient.of(Items.SUGAR_CANE),
			Ingredient.of(ForgeTags.TOOLS_KNIVES),
			Items.SUGAR, 1)
			.addResultWithChance(Items.SUGAR, 0.5F, 1)
			.build(finished, Util.delight("cutting/sugar_cane"));
	}

	private InventoryChangeTrigger.TriggerInstance has(ItemLike... items) {
		return InventoryChangeTrigger.TriggerInstance.hasItems(items);
	}

	private void wrap(RecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
		wrap(builder, Delightful.MODID, name, consumer, conds);
	}

	private void wrap(RecipeBuilder builder, String modid, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
		ResourceLocation loc = Util.rl(modid, name);
		FinishedRecipe[] recipe = new FinishedRecipe[1];
		builder.save(f -> recipe[0] = f, loc);
		ConditionalRecipe.Builder cond = ConditionalRecipe.builder();
		if (conds.length >= 1) {
			for (ICondition currentCond : conds) {
			cond.addCondition(currentCond);
			}
		}
		if (Modid.D.item(name) instanceof IConfigured conf) {
			for (Modid conflict : conf.getConflicts()) {
			cond.addCondition(new NotCondition(new ModLoadedCondition(conflict.get())));
			}
		}
		cond.addRecipe(recipe[0])
			.generateAdvancement()
			.build(consumer, loc);
	}

	private void wrap(SmithingTransformRecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
		ResourceLocation loc = Util.delight(name);
		ConditionalRecipe.Builder cond = ConditionalRecipe.builder();
		if (conds.length >= 1) {
			for (ICondition currentCond : conds) {
			cond.addCondition(currentCond);
			}
		}
		FinishedRecipe[] recipe = new FinishedRecipe[1];
		builder.save(f -> recipe[0] = f, loc);
		cond.addRecipe(recipe[0])
			.generateAdvancement()
			.build(consumer, loc);
	}

	private void wrap(CuttingBoardRecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
		wrap(builder, Delightful.MODID, name, consumer, conds);
	}

	private void wrap(CuttingBoardRecipeBuilder builder, String modid, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
		ResourceLocation loc = Util.rl(modid, name);
		ConditionalRecipe.Builder cond = ConditionalRecipe.builder();
		if (conds.length >= 1) {
			for (ICondition currentCond : conds) {
			cond.addCondition(currentCond);
			}
		}
		FinishedRecipe[] recipe = new FinishedRecipe[1];
		builder.build(f -> recipe[0] = f, loc);
		cond.addRecipe(recipe[0])
			.build(consumer, loc);
	}

	private void wrap(CookingPotRecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
		wrap(builder, Delightful.MODID, name, consumer, conds);
	}

	private void wrap(CookingPotRecipeBuilder builder, String modid, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
		ResourceLocation loc = Util.rl(modid, name);
		ConditionalRecipe.Builder cond = ConditionalRecipe.builder();
		if (conds.length >= 1) {
			for (ICondition currentCond : conds) {
			cond.addCondition(currentCond);
			}
		}
		FinishedRecipe[] recipe = new FinishedRecipe[1];
		builder.build(f -> recipe[0] = f, loc);
		cond.addRecipe(recipe[0])
			.generateAdvancement()
			.build(consumer, loc);
	}

	private void sack(RegistryObject<Item> sack, ItemLike ingredient, String name, Consumer<FinishedRecipe> finished) {
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, sack.get(), 1)
			.requires(ingredient, 9)
			.unlockedBy("has_" + name, has(ingredient)),
			"storage/" + name + "_storage_block", finished, enabled(sack));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingredient, 9)
			.requires(sack.get(), 1)
			.unlockedBy("has_" + name + "_storage_block", has(sack.get())),
			"storage/unpack_" + name + "_storage_block", finished, enabled(sack));
	}

	private void cabinet(Block block, Block wood, Block counter, Consumer<FinishedRecipe> finished) {
		String path = Util.name(block);
		ConditionalRecipe.builder()
			.addCondition(enabled(path))
			.addRecipe(f -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block, 1)
			.define('b', ((DelightfulCabinetBlock) block).getIngredient())
			.define('c', wood)
			.define('t', counter)
			.pattern("ttt")
			.pattern("bcb")
			.pattern("bbb")
			.unlockedBy("has_cabinet", has(wood))
			.save(f))
			.generateAdvancement()
			.build(finished, Delightful.MODID, "cabinets/" + path);
	}

	private void knife(DKnifeItem knife, Consumer<FinishedRecipe> finished) {
		TagKey<Item> tag = knife.getDependencyTag();
		if (tag != null) {
			String path = Util.name(knife);
			ICondition[] conds = new ICondition[]{
				enabled(path),
				not(tagEmpty(tag))
			};
			Modid[] conflicts = knife.getConflicts();
			if (conflicts.length > 0) {
				conds = ArrayUtils.addAll(conds, Arrays.stream(conflicts)
					.map(conf -> not(modLoaded(conf.get())))
					.toList()
					.toArray(new ICondition[0])
				);
			}
			Modid[] knifeMods = knife.getModid();
			if (knifeMods.length > 0) {
				final List<String> dependencies = Lists.newArrayList(Arrays.stream(knifeMods).map(Modid::get).toList());
				dependencies.remove(FarmersDelight.MODID);
				if (!dependencies.isEmpty()) {
					conds = ArrayUtils.addAll(conds, dependencies.stream()
						.map(this::modLoaded)
						.toList()
						.toArray(new ICondition[0])
					);
				}
			}
			if (knife.getRecipeType() == RecipeType.CRAFTING) {
				wrap(ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, knife)
					.define('m', Ingredient.of(tag))
					.define('s', knife.getRod())
					.pattern("m")
					.pattern("s")
					.unlockedBy("has_" + tag.location().getPath(), has(tag)),
				"knives/" + path, finished, conds);
			}
		}
	}

	private void knifeSmeltAndBlast(DKnifeItem knife, String metal, ResourceLocation nugget, Consumer<FinishedRecipe> finished) {
		ConditionalRecipe.builder()
			.addCondition(and(enabled(Util.name(knife)), itemExists(nugget.getNamespace(), nugget.getPath())))
			.addRecipe(f -> SimpleCookingRecipeBuilder.smelting(Ingredient.of(knife), RecipeCategory.COMBAT, Objects.requireNonNull(Util.item(nugget)), 0.1F, 200)
			.unlockedBy("has_" + metal + "_knife", InventoryChangeTrigger.TriggerInstance.hasItems(knife))
			.save(f, Util.delight("knives/smelting/" + metal + "_" + nugget.getNamespace())))
			.generateAdvancement()
			.build(finished, Delightful.MODID, "knives/smelting/" + metal + "_" + nugget.getNamespace());
		ConditionalRecipe.builder()
			.addCondition(and(enabled(Util.name(knife)), itemExists(nugget.getNamespace(), nugget.getPath())))
			.addRecipe(f -> SimpleCookingRecipeBuilder.blasting(Ingredient.of(knife), RecipeCategory.COMBAT, Objects.requireNonNull(Util.item(nugget)), 0.1F, 100)
			.unlockedBy("has_" + metal + "_knife", InventoryChangeTrigger.TriggerInstance.hasItems(knife))
			.save(f, Util.delight("knives/blasting/" + metal + "_" + nugget.getNamespace())))
			.generateAdvancement()
			.build(finished, Delightful.MODID, "knives/blasting/" + metal + "_" + nugget.getNamespace());
	}

	private EnabledCondition enabled(RegistryObject<Item> item) {
		return new EnabledCondition(Util.name(item));
	}

	private EnabledCondition enabled(String name) {
		return new EnabledCondition(name);
	}

	private void foodSmeltingRecipes(String name, ItemLike ingredient, ItemLike result, float experience, Consumer<FinishedRecipe> consumer) {
		wrap(SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 200).unlockedBy(name, has(ingredient)), "smelting/" + name, consumer, enabled(name));
		wrap(SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 600).unlockedBy(name, has(ingredient)), "campfire/" + name, consumer, enabled(name));
		wrap(SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 100).unlockedBy(name, has(ingredient)), "smoking/" + name, consumer, enabled(name));
	}

	private ShapedRecipeBuilder shaped(RecipeCategory category, RegistryObject<Item> returns, int count) {
		return ShapedRecipeBuilder.shaped(category, returns.get(), count);
	}

	private ShapedRecipeBuilder shaped(RecipeCategory category, RegistryObject<Item> returns) {
		return shaped(category, returns, 1);
	}
}
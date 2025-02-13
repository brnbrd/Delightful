package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.common.block.DelightfulCabinetBlock;
import net.brnbrd.delightful.common.crafting.EnabledCondition;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.IConfigured;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.compat.Mods;
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
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;
import vectorwing.farmersdelight.data.recipe.CookingRecipes;
import java.util.ArrayList;
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
		foodSmeltingRecipes("roasted_acorn", DelightfulItems.ACORN.get(), DelightfulItems.ROASTED_ACORN.get(), 0.35F, finished);

		// Foods
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.NUT_BUTTER_AND_JAM_SANDWICH.get())
						.requires(ForgeTags.BREAD)
						.requires(DelightfulItemTags.NUT_BUTTER)
						.requires(DelightfulItemTags.JAMS)
						.unlockedBy("has_nut_butter", has(DelightfulItems.NUT_BUTTER_BOTTLE.get())),
				"food/nut_butter_and_jam_sandwich", finished, enabled(DelightfulItems.NUT_BUTTER_AND_JAM_SANDWICH), not(tagEmpty(DelightfulItemTags.NUTS)), not(modLoaded(Mods.CT)));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CHEESEBURGER.get())
						.requires(ForgeTags.BREAD)
						.requires(ModItems.BEEF_PATTY.get())
						.requires(DelightfulItemTags.CHEESE)
						.requires(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
						.requires(ForgeTags.VEGETABLES_TOMATO)
						.requires(ForgeTags.VEGETABLES_ONION)
						.unlockedBy("has_cheese", has(DelightfulItemTags.CHEESE)),
				"food/cheeseburger", finished, enabled(DelightfulItems.CHEESEBURGER), tagEmpty(DelightfulItemTags.BURGER_BUN), not(tagEmpty(DelightfulItemTags.CHEESE)), not(modLoaded(Mods.VD)));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CHEESEBURGER.get())
						.requires(ForgeTags.BREAD)
						.requires(ModItems.BEEF_PATTY.get())
						.requires(ForgeTags.MILK)
						.requires(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
						.requires(ForgeTags.VEGETABLES_TOMATO)
						.requires(ForgeTags.VEGETABLES_ONION)
						.unlockedBy("has_beef_patty_and_cheese", has(ModItems.BEEF_PATTY.get(), Items.MILK_BUCKET)),
				"food/cheeseburger_from_milk", finished, enabled("cheeseburger"), tagEmpty(DelightfulItemTags.CHEESE));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CHEESEBURGER.get())
						.requires(DelightfulItemTags.BURGER_BUN)
						.requires(ModItems.BEEF_PATTY.get())
						.requires(DelightfulItemTags.CHEESE)
						.requires(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
						.requires(ForgeTags.VEGETABLES_TOMATO)
						.requires(ForgeTags.VEGETABLES_ONION)
						.unlockedBy("has_beef_patty_and_cheese", has(ModItems.BEEF_PATTY.get(), Items.MILK_BUCKET)),
				"food/cheeseburger_from_bun", finished, enabled("cheeseburger"), not(tagEmpty(DelightfulItemTags.BURGER_BUN)), not(tagEmpty(DelightfulItemTags.CHEESE)), not(modLoaded(Mods.VD)));
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
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.MARSHMALLOW_STICK.get(), 2)
						.requires(DelightfulItemTags.SUGAR)
						.requires(DelightfulItemTags.WATER)
						.requires(Ingredient.of(Tags.Items.RODS_WOODEN), 2)
						.unlockedBy("has_sugar", has(DelightfulItemTags.SUGAR)),
				"food/marshmallow_stick", finished, enabled("marshmallow_stick"), not(modLoaded("create_confectionery")));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SMORE.get())
						.requires(ModItems.HONEY_COOKIE.get())
						.requires(Items.COCOA_BEANS)
						.requires(ForgeTags.MILK)
						.requires(DelightfulItemTags.SUGAR)
						.requires(DelightfulItems.COOKED_MARSHMALLOW_STICK.get())
						.requires(ModItems.HONEY_COOKIE.get())
						.unlockedBy("has_cooked_marshmallow_stick", has(DelightfulItems.COOKED_MARSHMALLOW_STICK.get())),
				"food/smore", finished, enabled("smore"), tagEmpty(DelightfulItemTags.CHOCOLATE));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SMORE.get())
						.requires(ModItems.HONEY_COOKIE.get())
						.requires(DelightfulItemTags.CHOCOLATE)
						.requires(DelightfulItems.COOKED_MARSHMALLOW_STICK.get())
						.requires(ModItems.HONEY_COOKIE.get())
						.unlockedBy("has_cooked_marshmallow_stick", has(DelightfulItems.COOKED_MARSHMALLOW_STICK.get())),
				"food/smore_from_chocolate", finished, enabled("smore"), not(tagEmpty(DelightfulItemTags.CHOCOLATE)));
		wrap(SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(DelightfulItems.MARSHMALLOW_STICK.get()), RecipeCategory.FOOD,
								DelightfulItems.COOKED_MARSHMALLOW_STICK.get(), 0.5F, 600)
						.unlockedBy("has_marshmallow_stick", has(DelightfulItems.MARSHMALLOW_STICK.get())),
				"campfire/marshmallow_stick", finished, enabled("cooked_marshmallow_stick"));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.HONEY_GLAZED_WALNUT.get(), 2)
						.requires(Ingredient.of(DelightfulItemTags.NUTS_WALNUT), 2)
						.requires(Items.HONEY_BOTTLE)
						.unlockedBy("has_walnut", has(DelightfulItemTags.NUTS_WALNUT)),
				"food/honey_glazed_walnut", finished, enabled(DelightfulItems.HONEY_GLAZED_WALNUT), not(tagEmpty(DelightfulItemTags.NUTS_WALNUT)));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								DelightfulItems.MATCHA_LATTE.get(), 1, CookingRecipes.FAST_COOKING, 0.35F, Items.HONEY_BOTTLE)
						.addIngredient(Ingredient.of(ForgeTags.MILK))
						.addIngredient(Ingredient.of(DelightfulItemTags.MATCHA))
						.unlockedBy("has_matcha", has(DelightfulItemTags.MATCHA)),
				"cooking/matcha_latte", finished, enabled("matcha_latte"));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.BERRY_MATCHA_LATTE.get(), 1)
						.requires(Items.GLASS_BOTTLE)
						.requires(DelightfulItems.MATCHA_LATTE.get())
						.requires(ForgeTags.BERRIES)
						.requires(Items.ICE)
						.unlockedBy("has_matcha_latte", has(DelightfulItems.MATCHA_LATTE.get())),
				"food/berry_matcha_latte", finished, enabled(DelightfulItems.BERRY_MATCHA_LATTE), tagEmpty(DelightfulItemTags.ICE_CUBES));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.BERRY_MATCHA_LATTE.get(), 1)
						.requires(Items.GLASS_BOTTLE)
						.requires(DelightfulItems.MATCHA_LATTE.get())
						.requires(ForgeTags.BERRIES)
						.requires(DelightfulItemTags.ICE_CUBES)
						.unlockedBy("has_matcha_latte", has(DelightfulItems.MATCHA_LATTE.get())),
				"food/berry_matcha_latte_neapolitan", finished, enabled(DelightfulItems.BERRY_MATCHA_LATTE), not(tagEmpty(DelightfulItemTags.ICE_CUBES)));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.FIELD_SALAD.get(), 1)
						.requires(Items.BOWL)
						.requires(Ingredient.of(ForgeTags.SALAD_INGREDIENTS), 2)
						.requires(DelightfulItems.CACTUS_STEAK.get())
						.requires(ForgeTags.VEGETABLES_CARROT)
						.requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
						.requires(DelightfulItems.ROASTED_ACORN.get())
						.unlockedBy("has_cactus_steak", has(DelightfulItems.CACTUS_STEAK.get())),
				"food/field_salad", finished, enabled(DelightfulItems.CACTUS_STEAK), enabled(DelightfulItems.FIELD_SALAD));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.NUT_DOUGH.get(), 2)
						.requires(Tags.Items.EGGS)
						.requires(Items.BONE_MEAL)
						.requires(DelightfulItemTags.NUT_BUTTER)
						.requires(DelightfulItemTags.NUT_BUTTER)
						.unlockedBy("has_nut_butter", has(DelightfulItemTags.NUT_BUTTER)),
				"food/nut_dough", finished, enabled(DelightfulItems.NUT_DOUGH));

		sack(DelightfulItems.ACORN_SACK, DelightfulItems.ACORN.get(), "acorn", finished);
		sack(DelightfulItems.SALMONBERRY_SACK, DelightfulItems.SALMONBERRIES.get(), "salmonberry", finished);
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SALMONBERRY_ICE_CREAM.get(), 1)
						.requires(Items.BOWL)
						.requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
						.requires(ForgeTags.MILK)
						.requires(DelightfulItemTags.ICE_CUBES)
						.requires(DelightfulItemTags.SUGAR)
						.unlockedBy("has_ice_cubes", has(DelightfulItemTags.ICE_CUBES)),
				"food/salmonberry_ice_cream", finished, enabled("salmonberry_ice_cream"), not(tagEmpty(DelightfulItemTags.ICE_CUBES)));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SALMONBERRY_ICE_CREAM.get(), 1)
						.requires(Items.BOWL)
						.requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
						.requires(ForgeTags.MILK)
						.requires(Items.ICE)
						.requires(DelightfulItemTags.SUGAR)
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
						.requires(DelightfulItemTags.SUGAR)
						.unlockedBy("has_ice_cubes", has(DelightfulItemTags.ICE_CUBES)),
				"food/matcha_ice_cream", finished, enabled(DelightfulItems.MATCHA_ICE_CREAM), not(tagEmpty(DelightfulItemTags.ICE_CUBES)));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.MATCHA_ICE_CREAM.get(), 1)
						.requires(Items.BOWL)
						.requires(DelightfulItemTags.MATCHA)
						.requires(ForgeTags.MILK)
						.requires(Items.ICE)
						.requires(DelightfulItemTags.SUGAR)
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
						.define('n', DelightfulItemTags.NUTS)
						.define('d', ForgeTags.DOUGH)
						.define('s', DelightfulItemTags.FRUITS_CITRUS)
						.define('g', DelightfulItemTags.HOT_SPICE)
						.unlockedBy("has_nuts", has(DelightfulItemTags.NUTS)),
				"food/baklava", finished, enabled(DelightfulItems.BAKLAVA), not(tagEmpty(DelightfulItemTags.FRUITS_CITRUS)));
		wrap(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DelightfulItems.BAKLAVA.get(), 1)
						.pattern(" h ")
						.pattern("nnn")
						.pattern("sdg")
						.define('h', Items.HONEY_BOTTLE)
						.define('n', DelightfulItemTags.NUTS)
						.define('d', ForgeTags.DOUGH)
						.define('s', DelightfulItemTags.SUGAR)
						.define('g', DelightfulItemTags.HOT_SPICE)
						.unlockedBy("has_nuts", has(DelightfulItemTags.NUTS)),
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
						.define('x', DelightfulItemTags.SUGAR)
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
						.define('s', DelightfulItemTags.SUGAR)
						.define('c', ModItems.PIE_CRUST.get())
						.unlockedBy("has_pie_crust", has(ModItems.PIE_CRUST.get())),
				"food/pumpkin_pie", finished, not(modLoaded("create_central_kitchen")));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.PUMPKIN_PIE, 1)
						.requires(DelightfulItems.PUMPKIN_PIE_SLICE.get(), 4)
						.unlockedBy("has_pumpkin_pie_slice", has(DelightfulItems.PUMPKIN_PIE_SLICE.get())),
				"food/pumpkin_pie_from_slices", finished, enabled("pumpkin_pie_slice"), not(modLoaded("create_central_kitchen")));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DelightfulItems.SALMONBERRY_PIPS.get())
						.requires(DelightfulItems.SALMONBERRIES.get())
						.unlockedBy("has_salmonberries", has(DelightfulItemTags.FRUITS_SALMONBERRIES)),
				"salmonberry_pips", finished, enabled("salmonberry_pips"));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CANTALOUPE_SLICE.get(), 2)
						.requires(DelightfulItems.CANTALOUPE.get())
						.unlockedBy("has_cantaloupe", has(DelightfulItems.CANTALOUPE.get())),
				"cantaloupe_slice", finished, enabled(DelightfulItems.CANTALOUPE_SLICE));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItems.CANTALOUPE.get()),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						DelightfulItems.CANTALOUPE_SLICE.get(), 4),
				"cutting/cantaloupe", finished, enabled(DelightfulItems.CANTALOUPE_SLICE));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CANTALOUPE_SEEDS.get())
						.requires(DelightfulItems.CANTALOUPE_SLICE.get())
						.unlockedBy("has_cantaloupe", has(DelightfulItemTags.FRUITS_CANTALOUPE)),
				"cantaloupe_seeds", finished, enabled(DelightfulItems.CANTALOUPE_SEEDS));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(DelightfulItems.STUFFED_CANTALOUPE_BLOCK.get(), 1,
								CookingRecipes.SLOW_COOKING, 0.35F, DelightfulItems.CANTALOUPE.get())
						.addIngredient(ForgeTags.GRAIN_RICE)
						.addIngredient(ForgeTags.COOKED_MUTTON)
						.addIngredient(ForgeTags.VEGETABLES_ONION)
						.addIngredient(ForgeTags.BERRIES)
						.addIngredient(ForgeTags.SEEDS)
						.unlockedBy("has_cantaloupe", has(DelightfulItems.CANTALOUPE.get())),
				"food/cooking/stuffed_cantaloupe", finished, enabled(DelightfulItems.STUFFED_CANTALOUPE));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.WRAPPED_CANTALOUPE.get(), 1)
						.requires(DelightfulItemTags.FRUITS_CANTALOUPE)
						.requires(ForgeTags.COOKED_BACON)
						.unlockedBy("has_cantaloupe_slice", has(DelightfulItemTags.FRUITS_CANTALOUPE)),
				"food/wrapped_cantaloupe", finished, enabled(DelightfulItems.WRAPPED_CANTALOUPE));
		wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CANTALOUPE_BREAD.get(), 1)
						.requires(ForgeTags.DOUGH)
						.requires(DelightfulItemTags.FRUITS_CANTALOUPE)
						.unlockedBy("has_cantaloupe_slice", has(DelightfulItemTags.FRUITS_CANTALOUPE)),
				"food/cantaloupe_bread", finished, enabled(DelightfulItems.CANTALOUPE_BREAD));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								DelightfulItems.ENDER_NECTAR.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F, Items.HONEY_BOTTLE)
						.addIngredient(ForgeTags.MILK)
						.addIngredient(Items.ENDER_EYE)
						.unlockedBy("has_ender_eye", has(Items.ENDER_EYE)),
				"food/cooking/ender_nectar", finished, enabled(DelightfulItems.ENDER_NECTAR));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(DelightfulItems.ROCK_CANDY.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F, Items.STICK)
						.addIngredient(Ingredient.of(DelightfulItemTags.GEMS_ROSE_QUARTZ), 2)
						.addIngredient(DelightfulItemTags.SUGAR)
						.unlockedBy("has_rose_quartz", has(DelightfulItemTags.GEMS_ROSE_QUARTZ)),
				"food/cooking/rock_candy", finished, enabled(DelightfulItems.ROCK_CANDY), not(tagEmpty(DelightfulItemTags.GEMS_ROSE_QUARTZ)));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								DelightfulItems.ANIMAL_OIL_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
						.addIngredient(DelightfulItems.ANIMAL_FAT.get(), 3)
						.unlockedBy("has_animal_fat", has(DelightfulItems.ANIMAL_FAT.get())),
				"cooking/animal_oil_bottle", finished, enabled(DelightfulItems.ANIMAL_OIL_BOTTLE));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								DelightfulItems.JAM_JAR.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F, Items.GLASS_BOTTLE)
						.addIngredient(Ingredient.of(DelightfulItemTags.FRUITS_SWEET), 2)
						.addIngredient(DelightfulItemTags.SUGAR)
						.unlockedBy("has_sweet_fruit", has(DelightfulItemTags.FRUITS_SWEET)),
				"food/cooking/jam_jar", finished, enabled(DelightfulItems.JAM_JAR), not(modLoaded(Mods.BC)), not(modLoaded(Mods.FRD)));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								DelightfulItems.GLOW_JAM_JAR.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F, Items.GLASS_BOTTLE)
						.addIngredient(Ingredient.of(DelightfulItemTags.FRUITS_GLOW_BERRIES), 2)
						.addIngredient(DelightfulItemTags.SUGAR)
						.unlockedBy("has_glow_berries", has(DelightfulItemTags.FRUITS_GLOW_BERRIES)),
				"food/cooking/glow_jam_jar", finished, enabled(DelightfulItems.GLOW_JAM_JAR), not(modLoaded(Mods.BC)), not(modLoaded(Mods.FRD)));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								DelightfulItems.NUT_BUTTER_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F, Items.GLASS_BOTTLE)
						.addIngredient(DelightfulItemTags.NUTS)
						.addIngredient(DelightfulItemTags.SUGAR)
						.unlockedBy("has_nuts", has(DelightfulItemTags.NUTS)),
				"food/cooking/nut_butter_bottle", finished, enabled(DelightfulItems.NUT_BUTTER_BOTTLE), not(tagEmpty(DelightfulItemTags.NUTS)), not(modLoaded(Mods.VD)));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								DelightfulItems.CACTUS_CHILI.get(), 1, CookingRecipes.NORMAL_COOKING, 1.0F, Items.BOWL)
						.addIngredient(ForgeTags.RAW_PORK)
						.addIngredient(ModItems.TOMATO_SAUCE.get())
						.addIngredient(DelightfulItems.CACTUS_FLESH.get())
						.addIngredient(ForgeTags.VEGETABLES_ONION)
						.unlockedBy("has_cactus_chunk", has(DelightfulItems.CACTUS_FLESH.get()))
						.setRecipeBookTab(CookingPotRecipeBookTab.MEALS),
				"food/cooking/cactus_chili", finished, enabled(DelightfulItems.CACTUS_CHILI), enabled(DelightfulItems.CACTUS_FLESH));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								DelightfulItems.CACTUS_SOUP.get(), 1, CookingRecipes.NORMAL_COOKING, 1.0F, Items.BOWL)
						.addIngredient(DelightfulItems.CACTUS_FLESH.get())
						.addIngredient(ForgeTags.VEGETABLES_TOMATO)
						.addIngredient(DelightfulItemTags.HOT_SPICE)
						.addIngredient(ForgeTags.GRAIN_RICE)
						.unlockedBy("has_cactus_chunk", has(DelightfulItems.CACTUS_FLESH.get()))
						.setRecipeBookTab(CookingPotRecipeBookTab.MEALS),
				"food/cooking/cactus_soup", finished, enabled(DelightfulItems.CACTUS_SOUP), enabled(DelightfulItems.CACTUS_FLESH));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								DelightfulItems.VENISON_STEW.get(), 1, CookingRecipes.NORMAL_COOKING, 1.0F, Items.BOWL)
						.addIngredient(DelightfulItemTags.RAW_VENISON)
						.addIngredient(ForgeTags.VEGETABLES_CARROT)
						.addIngredient(ForgeTags.VEGETABLES_POTATO)
						.addIngredient(DelightfulItems.ANIMAL_FAT.get())
						.unlockedBy("has_raw_venison", has(DelightfulItemTags.RAW_VENISON))
						.setRecipeBookTab(CookingPotRecipeBookTab.MEALS),
				"food/cooking/venison_stew", finished, enabled(DelightfulItems.VENISON_STEW), not(tagEmpty(DelightfulItemTags.RAW_VENISON_COMPAT)), enabled(DelightfulItems.ANIMAL_FAT));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								DelightfulItems.VENISON_STEW.get(), 1, CookingRecipes.NORMAL_COOKING, 1.0F, Items.BOWL)
						.addIngredient(DelightfulItemTags.RAW_VENISON)
						.addIngredient(ForgeTags.VEGETABLES_CARROT)
						.addIngredient(ForgeTags.VEGETABLES_POTATO)
						.addIngredient(ForgeTags.VEGETABLES_POTATO)
						.unlockedBy("has_raw_venison", has(DelightfulItemTags.RAW_VENISON))
						.setRecipeBookTab(CookingPotRecipeBookTab.MEALS),
				"food/cooking/venison_stew_no_animal_fat", finished, enabled(DelightfulItems.VENISON_STEW), not(tagEmpty(DelightfulItemTags.RAW_VENISON_COMPAT)), not(enabled(DelightfulItems.ANIMAL_FAT)));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								DelightfulItems.COCONUT_CURRY.get(), 1, CookingRecipes.NORMAL_COOKING, 1.0F, Items.BOWL)
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
								DelightfulItems.COCONUT_CURRY.get(), 1, CookingRecipes.NORMAL_COOKING, 1.0F, Items.BOWL)
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
								DelightfulItems.SINIGANG.get(), 1, CookingRecipes.NORMAL_COOKING, 1.0F, Items.BOWL)
						.addIngredient(ForgeTags.RAW_FISHES)
						.addIngredient(ModItems.TOMATO_SAUCE.get())
						.addIngredient(ForgeTags.VEGETABLES_ONION)
						.addIngredient(ForgeTags.RAW_PORK)
						.addIngredient(DelightfulItemTags.VEGETABLES_GINGER)
						.addIngredient(DelightfulItemTags.FRUITS_CITRUS)
						.unlockedBy("has_ginger", has(DelightfulItemTags.VEGETABLES_GINGER))
						.setRecipeBookTab(CookingPotRecipeBookTab.MEALS),
				"food/cooking/sinigang", finished, enabled("sinigang"), not(tagEmpty(DelightfulItemTags.VEGETABLES_GINGER)), not(tagEmpty(DelightfulItemTags.FRUITS_CITRUS)));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								DelightfulItems.SINIGANG.get(), 1, CookingRecipes.NORMAL_COOKING, 1.0F, Items.BOWL)
						.addIngredient(ForgeTags.RAW_PORK)
						.addIngredient(ForgeTags.RAW_FISHES)
						.addIngredient(ForgeTags.GRAIN_RICE)
						.addIngredient(ForgeTags.VEGETABLES_TOMATO)
						.addIngredient(DelightfulItemTags.FRUITS)
						.addIngredient(ForgeTags.VEGETABLES_ONION)
						.unlockedBy("has_ginger", has(DelightfulItemTags.FRUITS))
						.setRecipeBookTab(CookingPotRecipeBookTab.MEALS),
				"food/cooking/sinigang_no_ginger_citrus", finished, enabled("sinigang"), or(tagEmpty(DelightfulItemTags.VEGETABLES_GINGER), tagEmpty(DelightfulItemTags.FRUITS_CITRUS)));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								DelightfulItems.CRAB_RANGOON.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
						.addIngredient(ForgeTags.DOUGH)
						.addIngredient(DelightfulItemTags.CHEESE)
						.addIngredient(DelightfulItemTags.CRAB_MEAT)
						.unlockedBy("has_cooked_crab", has(DelightfulItemTags.CRAB_MEAT)),
				"food/cooking/crab_rangoon", finished, enabled("crab_rangoon"), not(tagEmpty(DelightfulItemTags.CRAB_MEAT)), not(tagEmpty(DelightfulItemTags.CHEESE)));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								DelightfulItems.CRAB_RANGOON.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
						.addIngredient(ForgeTags.DOUGH)
						.addIngredient(ForgeTags.MILK)
						.addIngredient(DelightfulItemTags.CRAB_MEAT)
						.unlockedBy("has_cooked_crab", has(DelightfulItemTags.CRAB_MEAT)),
				"food/cooking/crab_rangoon_from_milk", finished, enabled("crab_rangoon"), not(tagEmpty(DelightfulItemTags.CRAB_MEAT)), tagEmpty(DelightfulItemTags.CHEESE));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								Items.MILK_BUCKET, 1, CookingRecipes.NORMAL_COOKING, 0.35F, Items.WATER_BUCKET)
						.addIngredient(DelightfulItemTags.NUT_BUTTER)
						.addIngredient(DelightfulItemTags.NUT_BUTTER)
						.unlockedBy("has_nut_butter", has(DelightfulItemTags.NUT_BUTTER)),
				"food/cooking/nut_milk", finished, enabled("nut_milk"), not(modLoaded(Mods.VD)));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(
								Items.HONEY_BOTTLE, 3, CookingRecipes.NORMAL_COOKING, 0.35F)
						.addIngredient(Items.HONEY_BOTTLE, 2)
						.addIngredient(DelightfulItems.CHOPPED_CLOVER.get(), 4),
				"food/clover_honey", finished, enabled("clover_honey"), enabled(DelightfulItems.CHOPPED_CLOVER), not(tagEmpty(DelightfulItemTags.CLOVER)));
		wrap(ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.TORCH, 8)
						.define('o', DelightfulItems.ANIMAL_OIL_BOTTLE.get())
						.define('s', Tags.Items.RODS_WOODEN)
						.pattern("o")
						.pattern("s")
						.unlockedBy("has_oil_bottle", has(DelightfulItems.ANIMAL_OIL_BOTTLE.get())),
				"torch_from_animal_oil_bottle", finished, enabled("animal_oil_bottle"));
		wrap(ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, Items.CANDLE, 1)
						.define('f', DelightfulItems.ANIMAL_FAT.get())
						.define('s', Tags.Items.STRING)
						.pattern("s")
						.pattern("f")
						.pattern("f")
						.unlockedBy("has_fat", has(DelightfulItems.ANIMAL_FAT.get())),
				"candle_from_animal_fat", finished, enabled("animal_fat"));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.TEA_LEAVES_GREEN),
						Ingredient.of(ForgeTags.TOOLS_SHOVELS),
						Items.GREEN_DYE, 1).addResultWithChance(DelightfulItems.MATCHA.get(), 0.4f),
				"cutting/green_tea_leaves", finished, enabled("matcha"), not(tagEmpty(DelightfulItemTags.TEA_LEAVES_GREEN)), not(modLoaded(Mods.YH)));
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
						Ingredient.of(DelightfulItemTags.CACTI),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						DelightfulItems.CACTUS_FLESH.get(), 2),
				"cutting/cactus", finished, enabled("cactus_flesh"));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.SMALL_CACTI),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						DelightfulItems.CACTUS_FLESH.get(), 1),
				"cutting/small_cactus", finished, enabled("cactus_flesh"), not(tagEmpty(DelightfulItemTags.SMALL_CACTI)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItems.MINI_MELON.get()),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.MELON_SLICE, 4),
				"cutting/mini_melon", finished, enabled(DelightfulItems.MINI_MELON));
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
				"salmonberry_ice_cream_block", finished, enabled(DelightfulItems.SALMONBERRY_ICE_CREAM_BLOCK), enabled(DelightfulItems.SALMONBERRY_ICE_CREAM), modLoaded(Mods.N));
		wrap(shaped(RecipeCategory.BUILDING_BLOCKS, DelightfulItems.MATCHA_ICE_CREAM_BLOCK, 8)
						.pattern("sss")
						.pattern("sis")
						.pattern("sss")
						.define('s', Blocks.SNOW_BLOCK)
						.define('i', DelightfulItems.MATCHA_ICE_CREAM.get())
						.unlockedBy("has_matcha_ice_cream", has(DelightfulItems.MATCHA_ICE_CREAM.get())),
				"matcha_ice_cream_block", finished, enabled(DelightfulItems.MATCHA_ICE_CREAM_BLOCK), enabled(DelightfulItems.MATCHA_ICE_CREAM), enabled(DelightfulItems.MATCHA), modLoaded(Mods.N));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(DelightfulItems.SALMONBERRY_GUMMY.get(), 1, 200, 1.0F)
						.addIngredient(DelightfulItemTags.FRUITS_SALMONBERRIES)
						.addIngredient(Items.SUGAR)
						.addIngredient(Items.HONEY_BOTTLE)
						.addIngredient(Items.KELP)
						.unlockedBy("has_salmonberries", has(DelightfulItemTags.FRUITS_SALMONBERRIES)),
				"gummy/salmonberries", finished, enabled(DelightfulItems.SALMONBERRIES), enabled(DelightfulItems.SALMONBERRY_GUMMY));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(DelightfulItems.MATCHA_GUMMY.get(), 1, 200, 1.0F)
						.addIngredient(DelightfulItemTags.MATCHA)
						.addIngredient(Items.SUGAR)
						.addIngredient(Items.HONEY_BOTTLE)
						.addIngredient(Items.KELP)
						.unlockedBy("has_matcha", has(DelightfulItemTags.MATCHA)),
				"gummy/matcha", finished, enabled(DelightfulItems.MATCHA), enabled(DelightfulItems.MATCHA_GUMMY));
		wrap(CookingPotRecipeBuilder.cookingPotRecipe(DelightfulItems.CANTALOUPE_GUMMY.get(), 1, 200, 1.0F)
						.addIngredient(DelightfulItemTags.FRUITS_CANTALOUPE)
						.addIngredient(Items.SUGAR)
						.addIngredient(Items.HONEY_BOTTLE)
						.addIngredient(Items.KELP)
						.unlockedBy("has_cantaloupe", has(DelightfulItemTags.FRUITS_CANTALOUPE)),
				"gummy/cantaloupe", finished, enabled(DelightfulItems.CANTALOUPE), enabled(DelightfulItems.CANTALOUPE_GUMMY));

		// Dye Cutting
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.MAKES_DYE_RED),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.RED_DYE, 2),
				"cutting/makes_dye/red", finished, not(tagEmpty(DelightfulItemTags.MAKES_DYE_RED)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.MAKES_DYE_YELLOW),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.YELLOW_DYE, 2),
				"cutting/makes_dye/yellow", finished, not(tagEmpty(DelightfulItemTags.MAKES_DYE_YELLOW)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.MAKES_DYE_PURPLE),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.PURPLE_DYE, 2),
				"cutting/makes_dye/purple", finished, not(tagEmpty(DelightfulItemTags.MAKES_DYE_PURPLE)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.MAKES_DYE_MAGENTA),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.MAGENTA_DYE, 2),
				"cutting/makes_dye/magenta", finished, not(tagEmpty(DelightfulItemTags.MAKES_DYE_MAGENTA)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.MAKES_DYE_WHITE),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.WHITE_DYE, 2),
				"cutting/makes_dye/white", finished, not(tagEmpty(DelightfulItemTags.MAKES_DYE_WHITE)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.MAKES_DYE_BLUE),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.BLUE_DYE, 2),
				"cutting/makes_dye/blue", finished, not(tagEmpty(DelightfulItemTags.MAKES_DYE_BLUE)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.MAKES_DYE_BLACK),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.BLACK_DYE, 2),
				"cutting/makes_dye/black", finished, not(tagEmpty(DelightfulItemTags.MAKES_DYE_BLACK)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.MAKES_DYE_ORANGE),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.ORANGE_DYE, 2),
				"cutting/makes_dye/orange", finished, not(tagEmpty(DelightfulItemTags.MAKES_DYE_ORANGE)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.MAKES_DYE_PINK),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.PINK_DYE, 2),
				"cutting/makes_dye/pink", finished, not(tagEmpty(DelightfulItemTags.MAKES_DYE_PINK)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.MAKES_DYE_GREEN),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.GREEN_DYE, 2),
				"cutting/makes_dye/green", finished, not(tagEmpty(DelightfulItemTags.MAKES_DYE_GREEN)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.MAKES_DYE_LIME),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.LIME_DYE, 2),
				"cutting/makes_dye/lime", finished, not(tagEmpty(DelightfulItemTags.MAKES_DYE_LIME)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.MAKES_DYE_CYAN),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.CYAN_DYE, 2),
				"cutting/makes_dye/cyan", finished, not(tagEmpty(DelightfulItemTags.MAKES_DYE_CYAN)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.MAKES_DYE_LIGHT_GRAY),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.LIGHT_GRAY_DYE, 2),
				"cutting/makes_dye/light_gray", finished, not(tagEmpty(DelightfulItemTags.MAKES_DYE_LIGHT_GRAY)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItemTags.MAKES_DYE_LIGHT_BLUE),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.LIGHT_BLUE_DYE, 2),
				"cutting/makes_dye/light_blue", finished, not(tagEmpty(DelightfulItemTags.MAKES_DYE_LIGHT_BLUE)));
		wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
						Ingredient.of(DelightfulItems.CACTUS_STEAK.get()),
						Ingredient.of(ForgeTags.TOOLS_KNIVES),
						Items.GREEN_DYE, 1),
				"cutting/cactus_steak", finished, enabled(DelightfulItems.CACTUS_STEAK));

		// Unwrappables
		ConditionalRecipe.builder()
				.addCondition(not(tagEmpty(DelightfulItemTags.CHOCOLATE)))
				.addRecipe(f -> ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CHOCOLATE_PIE.get(), 1)
						.pattern("ccc")
						.pattern("mmm")
						.pattern("xOx")
						.define('c', DelightfulItemTags.CHOCOLATE)
						.define('m', ForgeTags.MILK)
						.define('x', DelightfulItemTags.SUGAR)
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
						.define('x', DelightfulItemTags.SUGAR)
						.define('O', ModItems.PIE_CRUST.get())
						.unlockedBy("has_pie_crust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PIE_CRUST.get()))
						.save(f))
				.generateAdvancement()
				.build(finished, ModItems.CHOCOLATE_PIE.getId());
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DelightfulItems.WILD_SALMONBERRIES.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), DelightfulItems.SALMONBERRIES.get(), 1)
				.addResult(Items.ORANGE_DYE, 1)
				.build(finished, Util.delight("cutting/wild_salmonberries"));
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.SUGAR_CANE),
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
		if (
			Util.item(Delightful.MODID, name) instanceof IConfigured conf &&
			conf.getConflicts().length > 0
		) {
			for (String conflict : conf.getConflicts()) {
				cond.addCondition(new NotCondition(new ModLoadedCondition(conflict)));
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
			String[] conflicts = knife.getConflicts();
			if (conflicts.length > 0) {
				conds = ArrayUtils.addAll(conds, Arrays.stream(conflicts)
					.map(conf -> not(modLoaded(conf)))
					.toList()
					.toArray(new ICondition[0])
				);
			}
			String[] knifeMods = knife.getModid();
			if (knifeMods.length > 0) {
				List<String> dependencies = new ArrayList<>(Arrays.asList(knifeMods));
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
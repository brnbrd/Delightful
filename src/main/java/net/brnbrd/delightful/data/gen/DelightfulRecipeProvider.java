package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.common.block.DelightfulCabinetBlock;
import net.brnbrd.delightful.common.crafting.EnabledCondition;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.ICompat;
import net.brnbrd.delightful.common.item.knife.DelightfulKnifeItem;
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
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;
import vectorwing.farmersdelight.data.recipe.CookingRecipes;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
            .filter(item -> item instanceof DelightfulKnifeItem)
            .map(item -> (DelightfulKnifeItem) item)
            .filter(knife -> knife.getTag() != null && knife.getRecipeType() != null)
            .forEach(k -> knife(k, finished));
        knifeSmeltAndBlast((DelightfulKnifeItem) Knives.BONE.get(), "bone/knife", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(Items.BONE_MEAL)), finished);

        // Smelting
        foodSmeltingRecipes("cactus_steak", DelightfulItems.CACTUS_FLESH.get(), DelightfulItems.CACTUS_STEAK.get(), 0.35F, finished);
        foodSmeltingRecipes("cooked_venison_chops", DelightfulItems.VENISON_CHOPS.get(), DelightfulItems.COOKED_VENISON_CHOPS.get(), 0.35F, finished);
        foodSmeltingRecipes("cooked_goat", DelightfulItems.RAW_GOAT.get(), DelightfulItems.COOKED_GOAT.get(), 0.35F, finished);

        // Foods
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.NUT_BUTTER_AND_JELLY_SANDWICH.get())
                .requires(ForgeTags.BREAD)
                .requires(DelightfulItems.NUT_BUTTER_BOTTLE.get())
                .requires(DelightfulItemTags.JAMS)
                .unlockedBy("has_nut_butter", has(DelightfulItems.NUT_BUTTER_BOTTLE.get())),
            "food/nut_butter_and_jelly_sandwich", finished, enabled("nut_butter_and_jelly_sandwich"), not(tagEmpty(DelightfulItemTags.NUTS)), tagEmpty(DelightfulItemTags.BREAD_SLICE));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.NUT_BUTTER_AND_JELLY_SANDWICH.get())
                .requires(DelightfulItemTags.BREAD_SLICE)
                .requires(DelightfulItems.NUT_BUTTER_BOTTLE.get())
                .requires(DelightfulItemTags.JAMS)
                .requires(DelightfulItemTags.BREAD_SLICE)
                .unlockedBy("has_nut_butter", has(DelightfulItems.NUT_BUTTER_BOTTLE.get())),
            "food/nut_butter_and_jelly_sandwich_from_bread_slice", finished, enabled("nut_butter_and_jelly_sandwich"), not(tagEmpty(DelightfulItemTags.NUTS)), not(tagEmpty(DelightfulItemTags.BREAD_SLICE)));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CHEESEBURGER.get())
                .requires(ForgeTags.BREAD)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESES)
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(ForgeTags.CROPS_TOMATO)
                .requires(ForgeTags.CROPS_ONION)
                .unlockedBy("has_cheese", has(DelightfulItemTags.CHEESES)),
            "food/cheeseburger", finished, enabled("cheeseburger"), tagEmpty(DelightfulItemTags.BURGER_BUN), not(tagEmpty(DelightfulItemTags.CHEESES)), not(enabled("use_milk_tag")));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CHEESEBURGER.get())
                .requires(ForgeTags.BREAD)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(ForgeTags.MILK)
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(ForgeTags.CROPS_TOMATO)
                .requires(ForgeTags.CROPS_ONION)
                .unlockedBy("has_beef_patty_and_cheese", has(ModItems.BEEF_PATTY.get(), Items.MILK_BUCKET)),
            "food/cheeseburger_from_milk", finished, enabled("cheeseburger"), or(tagEmpty(DelightfulItemTags.BURGER_BUN), tagEmpty(DelightfulItemTags.CHEESES), enabled("use_milk_tag")));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CHEESEBURGER.get())
                .requires(DelightfulItemTags.BURGER_BUN)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESES)
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(ForgeTags.CROPS_TOMATO)
                .requires(ForgeTags.CROPS_ONION)
                .unlockedBy("has_beef_patty_and_cheese", has(ModItems.BEEF_PATTY.get(), Items.MILK_BUCKET)),
            "food/cheeseburger_from_bun", finished, enabled("cheeseburger"), not(tagEmpty(DelightfulItemTags.BURGER_BUN)), not(tagEmpty(DelightfulItemTags.CHEESES)), not(enabled("use_milk_tag")));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.DELUXE_CHEESEBURGER.get())
                .requires(ForgeTags.BREAD)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESES)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESES)
                .requires(ModItems.COOKED_BACON.get())
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(ForgeTags.CROPS_TOMATO)
                .requires(ForgeTags.CROPS_ONION)
                .unlockedBy("has_bacon", has(ModItems.COOKED_BACON.get())),
            "food/deluxe_cheeseburger", finished, enabled("deluxe_cheeseburger"), tagEmpty(DelightfulItemTags.BURGER_BUN), not(tagEmpty(DelightfulItemTags.CHEESES)), not(enabled("use_milk_tag")));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.DELUXE_CHEESEBURGER.get())
                .requires(ForgeTags.BREAD)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(ForgeTags.MILK)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(ForgeTags.MILK)
                .requires(ModItems.COOKED_BACON.get())
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(ForgeTags.CROPS_TOMATO)
                .requires(ForgeTags.CROPS_ONION)
                .unlockedBy("has_bacon", has(ModItems.COOKED_BACON.get())),
            "food/deluxe_cheeseburger_from_milk", finished, enabled("deluxe_cheeseburger"), or(tagEmpty(DelightfulItemTags.BURGER_BUN), tagEmpty(DelightfulItemTags.CHEESES), enabled("use_milk_tag")));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.DELUXE_CHEESEBURGER.get())
                .requires(DelightfulItemTags.BURGER_BUN)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESES)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESES)
                .requires(ModItems.COOKED_BACON.get())
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(ForgeTags.CROPS_TOMATO)
                .requires(ForgeTags.CROPS_ONION)
                .unlockedBy("has_bacon", has(ModItems.COOKED_BACON.get())),
            "food/deluxe_cheeseburger_from_bun", finished, enabled("deluxe_cheeseburger"), not(tagEmpty(DelightfulItemTags.BURGER_BUN)), not(tagEmpty(DelightfulItemTags.CHEESES)), not(enabled("use_milk_tag")));
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
            "food/smore", finished, enabled("smore"), tagEmpty(DelightfulItemTags.BARS_CHOCOLATE));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SMORE.get())
                .requires(ModItems.HONEY_COOKIE.get())
                .requires(DelightfulItemTags.BARS_CHOCOLATE)
                .requires(DelightfulItems.COOKED_MARSHMALLOW_STICK.get())
                .requires(ModItems.HONEY_COOKIE.get())
                .unlockedBy("has_cooked_marshmallow_stick", has(DelightfulItems.COOKED_MARSHMALLOW_STICK.get())),
            "food/smore_from_chocolate", finished, enabled("smore"), not(tagEmpty(DelightfulItemTags.BARS_CHOCOLATE)));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CRAB_RANGOON.get())
                .requires(ForgeTags.DOUGH_WHEAT)
                .requires(DelightfulItemTags.CHEESES)
                .requires(DelightfulItemTags.COOKED_CRAB)
                .unlockedBy("has_cooked_crab", has(DelightfulItemTags.COOKED_CRAB)),
            "food/crab_rangoon", finished, enabled("crab_rangoon"), not(tagEmpty(DelightfulItemTags.COOKED_CRAB)), not(tagEmpty(DelightfulItemTags.CHEESES)), not(enabled("use_milk_tag")));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.CRAB_RANGOON.get())
                .requires(ForgeTags.DOUGH_WHEAT)
                .requires(ForgeTags.MILK)
                .requires(DelightfulItemTags.COOKED_CRAB)
                .unlockedBy("has_cooked_crab", has(DelightfulItemTags.COOKED_CRAB)),
            "food/crab_rangoon_from_milk", finished, enabled("crab_rangoon"), not(tagEmpty(DelightfulItemTags.COOKED_CRAB)), or(tagEmpty(DelightfulItemTags.CHEESES), enabled("use_milk_tag")));
        wrap(SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(DelightfulItems.MARSHMALLOW_STICK.get()), RecipeCategory.FOOD,
                    DelightfulItems.COOKED_MARSHMALLOW_STICK.get(), 0.5F, 600)
                .unlockedBy("has_marshmallow_stick", has(DelightfulItems.MARSHMALLOW_STICK.get())),
            "campfire/marshmallow_stick", finished, enabled("cooked_marshmallow_stick"));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.HONEY_GLAZED_WALNUT.get(), 3)
                .requires(Ingredient.of(DelightfulItemTags.NUTS_WALNUT), 3)
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy("has_walnut", has(DelightfulItemTags.NUTS_WALNUT)),
            "food/honey_glazed_walnut", finished, enabled("honey_glazed_walnut"), not(tagEmpty(DelightfulItemTags.NUTS_WALNUT)));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.MATCHA.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(Ingredient.of(DelightfulItemTags.TEA_LEAVES_GREEN), 2)
                .unlockedBy("has_green_tea_leaves", has(DelightfulItemTags.TEA_LEAVES_GREEN)),
            "cooking/green_tea_leaves", finished, enabled("matcha"), not(tagEmpty(DelightfulItemTags.TEA_LEAVES_GREEN)));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.MATCHA_LATTE.get(), 1)
                .requires(Items.GLASS_BOTTLE)
                .requires(ForgeTags.MILK)
                .requires(Items.HONEY_BOTTLE)
                .requires(DelightfulItems.MATCHA.get())
                .unlockedBy("has_matcha", has(DelightfulItems.MATCHA.get())),
            "food/matcha_latte", finished, enabled("matcha_latte"), enabled("matcha"), not(modLoaded(Mods.FR)));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.BERRY_MATCHA_LATTE.get(), 1)
                .requires(Items.GLASS_BOTTLE)
                .requires(DelightfulItemTags.FRUITS_BERRIES)
                .requires(DelightfulItemTags.ICE_CUBES)
                .requires(ForgeTags.MILK)
                .requires(Items.HONEY_BOTTLE)
                .requires(DelightfulItems.MATCHA.get())
                .unlockedBy("has_matcha_latte", has(DelightfulItems.MATCHA_LATTE.get())),
            "food/berry_matcha_latte_neapolitan", finished, not(modLoaded(Mods.FR)), not(tagEmpty(DelightfulItemTags.ICE_CUBES)), enabled("berry_matcha_latte"), enabled("matcha_latte"), enabled("matcha"));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.BERRY_MATCHA_LATTE.get(), 1)
                .requires(Items.GLASS_BOTTLE)
                .requires(DelightfulItemTags.FRUITS_BERRIES)
                .requires(Items.ICE)
                .requires(ForgeTags.MILK)
                .requires(Items.HONEY_BOTTLE)
                .requires(DelightfulItems.MATCHA.get())
                .unlockedBy("has_matcha_latte", has(DelightfulItems.MATCHA_LATTE.get())),
            "food/berry_matcha_latte", finished, not(modLoaded(Mods.FR)), enabled("berry_matcha_latte"), enabled("matcha_latte"), enabled("matcha"));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.BERRY_MATCHA_LATTE.get(), 1)
                .requires(Items.GLASS_BOTTLE)
                .requires(DelightfulItems.MATCHA_LATTE.get())
                .requires(DelightfulItemTags.FRUITS_BERRIES)
                .requires(DelightfulItemTags.ICE_CUBES)
                .unlockedBy("has_matcha_latte", has(DelightfulItems.MATCHA_LATTE.get())),
            "food/berry_matcha_latte_from_matcha_latte_neapolitan", finished, not(tagEmpty(DelightfulItemTags.ICE_CUBES)), enabled("berry_matcha_latte"), enabled("matcha_latte"), enabled("matcha"));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.BERRY_MATCHA_LATTE.get(), 1)
                .requires(Items.GLASS_BOTTLE)
                .requires(DelightfulItems.MATCHA_LATTE.get())
                .requires(DelightfulItemTags.FRUITS_BERRIES)
                .requires(Items.ICE)
                .unlockedBy("has_matcha_latte", has(DelightfulItems.MATCHA_LATTE.get())),
            "food/berry_matcha_latte_from_matcha_latte", finished, tagEmpty(DelightfulItemTags.ICE_CUBES), enabled("berry_matcha_latte"), enabled("matcha_latte"), enabled("matcha"));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.FIELD_SALAD.get(), 1)
                .requires(Items.BOWL)
                .requires(Ingredient.of(ForgeTags.SALAD_INGREDIENTS), 2)
                .requires(DelightfulItems.CACTUS_STEAK.get())
                .requires(Items.CARROT)
                .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
                .requires(DelightfulItemTags.NUTS_ACORN)
                .unlockedBy("has_cactus_steak", has(DelightfulItems.CACTUS_STEAK.get())),
            "food/field_salad", finished, enabled("field_salad"));
        sack(DelightfulItems.ACORN_SACK, DelightfulItems.ACORN, "acorn", finished);
        sack(DelightfulItems.SALMONBERRY_SACK, DelightfulItems.SALMONBERRIES, "salmonberry", finished);
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
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.MATCHA_ICE_CREAM.get(), 1)
                .requires(Items.BOWL)
                .requires(DelightfulItems.MATCHA.get())
                .requires(ForgeTags.MILK)
                .requires(DelightfulItemTags.ICE_CUBES)
                .requires(DelightfulItemTags.SUGAR)
                .unlockedBy("has_ice_cubes", has(DelightfulItemTags.ICE_CUBES)),
            "food/matcha_ice_cream", finished, enabled("matcha_ice_cream"), not(tagEmpty(DelightfulItemTags.ICE_CUBES)));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.MATCHA_ICE_CREAM.get(), 1)
                .requires(Items.BOWL)
                .requires(DelightfulItems.MATCHA.get())
                .requires(ForgeTags.MILK)
                .requires(Items.ICE)
                .requires(DelightfulItemTags.SUGAR)
                .unlockedBy("has_ice", has(Items.ICE)),
            "food/matcha_ice_cream_no_neapolitan", finished, enabled("matcha_ice_cream"), tagEmpty(DelightfulItemTags.ICE_CUBES));
        wrap(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, DelightfulItems.SALMONBERRY_PIE.get(), 1)
                .pattern("###")
                .pattern("aaa")
                .pattern("xOx")
                .define('#', Items.WHEAT)
                .define('a', DelightfulItemTags.FRUITS_SALMONBERRIES)
                .define('x', DelightfulItemTags.SUGAR)
                .define('O', ModItems.PIE_CRUST.get())
                .unlockedBy("has_pie_crust", has(ModItems.PIE_CRUST.get())),
            "food/salmonberry_pie", finished, enabled("salmonberry_pie"));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, DelightfulItems.SALMONBERRY_PIE.get(), 1)
                .requires(DelightfulItems.SALMONBERRY_PIE_SLICE.get(), 4)
                .unlockedBy("has_salmonberry_pie_slice", has(DelightfulItems.SALMONBERRY_PIE_SLICE.get())),
            "food/salmonberry_pie_from_slices", finished, enabled("salmonberry_pie"));
        wrap(ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.PUMPKIN_PIE, 1)
                .pattern("###")
                .pattern("aaa")
                .pattern("xOe")
                .define('#', Items.WHEAT)
                .define('a', Items.PUMPKIN)
                .define('x', DelightfulItemTags.SUGAR)
                .define('e', ForgeTags.EGGS)
                .define('O', ModItems.PIE_CRUST.get())
                .unlockedBy("has_pie_crust", has(ModItems.PIE_CRUST.get())),
            "food/pumpkin_pie", finished, enabled("pumpkin_pie_slice"));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.PUMPKIN_PIE, 1)
                .requires(DelightfulItems.PUMPKIN_PIE_SLICE.get(), 4)
                .unlockedBy("has_pumpkin_pie_slice", has(DelightfulItems.PUMPKIN_PIE_SLICE.get())),
            "food/pumpkin_pie_from_slices", finished, enabled("pumpkin_pie_slice"));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DelightfulItems.SALMONBERRY_PIPS.get())
                .requires(DelightfulItems.SALMONBERRIES.get())
                .unlockedBy("has_salmonberries", has(DelightfulItemTags.FRUITS_SALMONBERRIES)),
            "salmonberry_pips", finished, enabled("salmonberry_pips"));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DelightfulItems.CANTALOUPE_SLICE.get(), 3)
                .requires(DelightfulItems.CANTALOUPE.get())
                .unlockedBy("has_cantaloupe", has(DelightfulItems.CANTALOUPE.get())),
            "cantaloupe_slice", finished, enabled("cantaloupe_slice"));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(DelightfulItems.CANTALOUPE.get()),
                Ingredient.of(ForgeTags.TOOLS_KNIVES),
                DelightfulItems.CANTALOUPE_SLICE.get(), 6),
            "cutting/cantaloupe", finished, enabled("cantaloupe_slice"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
            DelightfulItems.ENDER_NECTAR.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(Items.ENDER_EYE)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(DelightfulItemTags.SUGAR)
                .unlockedBy("has_ender_eye", has(Items.ENDER_EYE)),
            "food/cooking/ender_nectar", finished, enabled("ender_nectar"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(DelightfulItems.ROCK_CANDY.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F, Items.STICK)
                .addIngredient(Ingredient.of(DelightfulItemTags.GEMS_ROSE_QUARTZ), 2)
                .addIngredient(DelightfulItemTags.SUGAR)
                .unlockedBy("has_rose_quartz", has(DelightfulItemTags.GEMS_ROSE_QUARTZ)),
            "food/cooking/rock_candy", finished, enabled(DelightfulItems.ROCK_CANDY), not(tagEmpty(DelightfulItemTags.GEMS_ROSE_QUARTZ)));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
            DelightfulItems.ANIMAL_OIL_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItems.ANIMAL_FAT.get(), 2)
                .unlockedBy("has_animal_fat", has(DelightfulItems.ANIMAL_FAT.get())),
            "cooking/animal_oil_bottle", finished, enabled("animal_oil_bottle"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
            DelightfulItems.JELLY_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(Ingredient.of(DelightfulItemTags.FRUITS_SWEET), 2)
                .addIngredient(Ingredient.of(DelightfulItemTags.SUGAR), 2)
                .unlockedBy("has_sweet_fruit", has(DelightfulItemTags.FRUITS_SWEET)),
            "food/cooking/jelly_bottle", finished, enabled("jelly_bottle"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
            DelightfulItems.GLOW_JELLY_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItemTags.FRUITS_GLOW_BERRIES)
                .addIngredient(DelightfulItemTags.FRUITS_GLOW_BERRIES)
                .addIngredient(Tags.Items.DUSTS_GLOWSTONE)
                .addIngredient(Ingredient.of(DelightfulItemTags.SUGAR), 2)
                .unlockedBy("has_glow_berries", has(DelightfulItemTags.FRUITS_GLOW_BERRIES)),
            "food/cooking/glow_jelly_bottle", finished, enabled("glow_jelly_bottle"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
            DelightfulItems.NUT_BUTTER_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItemTags.NUTS)
                .addIngredient(DelightfulItemTags.SUGAR)
                .unlockedBy("has_nuts", has(DelightfulItemTags.NUTS)),
            "food/cooking/nut_butter_bottle", finished, enabled("nut_butter_bottle"), not(tagEmpty(DelightfulItemTags.NUTS)));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.COCONUT_CURRY.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItemTags.COCONUT)
                .addIngredient(ForgeTags.RAW_CHICKEN)
                .addIngredient(ForgeTags.CROPS_TOMATO)
                .addIngredient(DelightfulItemTags.TEA_LEAVES_GREEN)
                .addIngredient(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
                .addIngredient(ForgeTags.CROPS_ONION)
                .unlockedBy("has_tea", has(DelightfulItemTags.TEA_LEAVES_GREEN)),
            "food/cooking/coconut_curry_no_ginger", finished, enabled("coconut_curry"), not(tagEmpty(DelightfulItemTags.COCONUT)), tagEmpty(DelightfulItemTags.CROPS_GINGER));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.COCONUT_CURRY.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItemTags.COCONUT)
                .addIngredient(ForgeTags.RAW_CHICKEN)
                .addIngredient(ForgeTags.CROPS_TOMATO)
                .addIngredient(DelightfulItemTags.TEA_LEAVES_GREEN)
                .addIngredient(DelightfulItemTags.CROPS_GINGER)
                .addIngredient(ForgeTags.CROPS_ONION)
                .unlockedBy("has_coconut", has(DelightfulItemTags.COCONUT)),
            "food/cooking/coconut_curry", finished, enabled("coconut_curry"), not(tagEmpty(DelightfulItemTags.COCONUT)), not(tagEmpty(DelightfulItemTags.CROPS_GINGER)));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.SINIGANG.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(ForgeTags.RAW_FISHES)
                .addIngredient(ModItems.TOMATO_SAUCE.get())
                .addIngredient(ForgeTags.CROPS_ONION)
                .addIngredient(ForgeTags.RAW_PORK)
                .addIngredient(DelightfulItemTags.CROPS_GINGER)
                .addIngredient(DelightfulItemTags.FRUITS_CITRUS)
                .unlockedBy("has_ginger", has(DelightfulItemTags.CROPS_GINGER)),
            "food/cooking/sinigang", finished, enabled("sinigang"), not(tagEmpty(DelightfulItemTags.CROPS_GINGER)), not(tagEmpty(DelightfulItemTags.FRUITS_CITRUS)));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.SINIGANG.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(ForgeTags.RAW_PORK)
                .addIngredient(ForgeTags.RAW_FISHES)
                .addIngredient(ForgeTags.CROPS_RICE)
                .addIngredient(ForgeTags.CROPS_TOMATO)
                .addIngredient(DelightfulItemTags.FRUITS)
                .addIngredient(ForgeTags.CROPS_ONION)
                .unlockedBy("has_ginger", has(DelightfulItemTags.FRUITS)),
            "food/cooking/sinigang_no_ginger_citrus", finished, enabled("sinigang"), or(tagEmpty(DelightfulItemTags.CROPS_GINGER), tagEmpty(DelightfulItemTags.FRUITS_CITRUS)));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
            Items.MILK_BUCKET, 1, CookingRecipes.NORMAL_COOKING, 0.35F, Items.BUCKET)
                .addIngredient(DelightfulItemTags.WATER)
                .addIngredient(DelightfulItemTags.NUTS)
                .addIngredient(DelightfulItemTags.NUTS)
                .addIngredient(DelightfulItemTags.SUGAR)
                .unlockedBy("has_nuts", has(DelightfulItemTags.NUTS)),
            "food/cooking/nut_milk", finished, enabled("nut_milk"));
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
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
                    Items.HONEY_BOTTLE, 3, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(Items.HONEY_BOTTLE, 2)
                .addIngredient(DelightfulItems.CHOPPED_CLOVER.get(), 4),
            "food/clover_honey", finished, enabled("clover_honey"), modLoaded("biomesoplenty"));
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
                Ingredient.of(Items.CACTUS),
                Ingredient.of(ForgeTags.TOOLS_KNIVES),
                DelightfulItems.CACTUS_FLESH.get(), 2),
            "cutting/cactus", finished, enabled("cactus_flesh"));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(DelightfulItems.MINI_MELON.get()),
                Ingredient.of(ForgeTags.TOOLS_KNIVES),
                Items.MELON_SLICE, 6),
            "cutting/mini_melon", finished, enabled("mini_melon"));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.MELON_SLICE, 3)
                .requires(DelightfulItems.MINI_MELON.get())
                .unlockedBy("has_mini_melon", has(DelightfulItems.MINI_MELON.get())),
            "melon_slice", finished, enabled("mini_melon"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(DelightfulItems.SALMONBERRY_GUMMY.get(), 1, 200, 1.0F)
                .addIngredient(DelightfulItemTags.FRUITS_SALMONBERRIES)
                .addIngredient(Items.SUGAR)
                .addIngredient(Items.HONEY_BOTTLE)
                .addIngredient(Items.KELP)
                .unlockedBy("has_salmonberries", has(DelightfulItemTags.FRUITS_SALMONBERRIES)),
            "gummy/salmonberries", finished, enabled(DelightfulItems.SALMONBERRIES), enabled(DelightfulItems.SALMONBERRY_GUMMY));
        wrap(shaped(RecipeCategory.BUILDING_BLOCKS, DelightfulItems.SALMONBERRY_ICE_CREAM_BLOCK, 8)
                .pattern("sss")
                .pattern("sis")
                .pattern("sss")
                .define('s', Blocks.SNOW_BLOCK)
                .define('i', DelightfulItems.SALMONBERRY_ICE_CREAM.get())
                .unlockedBy("has_salmonberry_ice_cream", has(DelightfulItems.SALMONBERRY_ICE_CREAM.get())),
            "salmonberry_ice_cream_block", finished, enabled(DelightfulItems.SALMONBERRY_ICE_CREAM_BLOCK), enabled(DelightfulItems.SALMONBERRY_ICE_CREAM), modLoaded("neapolitan"));
        wrap(shaped(RecipeCategory.BUILDING_BLOCKS, DelightfulItems.MATCHA_ICE_CREAM_BLOCK, 8)
                .pattern("sss")
                .pattern("sis")
                .pattern("sss")
                .define('s', Blocks.SNOW_BLOCK)
                .define('i', DelightfulItems.MATCHA_ICE_CREAM.get())
                .unlockedBy("has_matcha_ice_cream", has(DelightfulItems.MATCHA_ICE_CREAM.get())),
            "matcha_ice_cream_block", finished, enabled(DelightfulItems.MATCHA_ICE_CREAM_BLOCK), enabled(DelightfulItems.MATCHA_ICE_CREAM), enabled("matcha"), modLoaded("neapolitan"));

        // Unwrappables
        ConditionalRecipe.builder()
          .addCondition(not(tagEmpty(DelightfulItemTags.BARS_CHOCOLATE)))
          .addRecipe(f -> ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CHOCOLATE_PIE.get(), 1)
            .pattern("ccc")
            .pattern("mmm")
            .pattern("xOx")
            .define('c', DelightfulItemTags.BARS_CHOCOLATE)
            .define('m', ForgeTags.MILK)
            .define('x', Items.SUGAR)
            .define('O', ModItems.PIE_CRUST.get())
            .unlockedBy("has_pie_crust", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PIE_CRUST.get()))
            .save(f))
          .addCondition(tagEmpty(DelightfulItemTags.BARS_CHOCOLATE))
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.FRUIT_SALAD.get(), 1)
            .requires(Items.APPLE)
            .requires(Items.MELON_SLICE)
            .requires(ModItems.PUMPKIN_SLICE.get())
            .requires(DelightfulItemTags.FRUITS)
            .requires(ForgeTags.BERRIES)
            .requires(ForgeTags.BERRIES)
            .requires(Items.BOWL)
            .unlockedBy("has_fruits", has(Items.MELON_SLICE, Items.SWEET_BERRIES, Items.APPLE, ModItems.PUMPKIN_SLICE.get()))
            .save(finished, ModItems.FRUIT_SALAD.getId());
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.SUGAR_CANE), Ingredient.of(ForgeTags.TOOLS_KNIVES), Items.SUGAR, 1)
            .addResultWithChance(Items.SUGAR, 0.5F, 1)
            .build(finished, Util.rl(Delightful.MODID, "cutting/sugar_cane"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(DelightfulItems.WILD_SALMONBERRIES.get()), Ingredient.of(ForgeTags.TOOLS_KNIVES), DelightfulItems.SALMONBERRY_PIPS.get(), 1)
            .addResultWithChance(Items.ORANGE_DYE, 0.5F, 2)
            .build(finished, Util.rl(Delightful.MODID, "cutting/wild_salmonberries"));
        }

    private InventoryChangeTrigger.TriggerInstance has(ItemLike... items) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(items);
    }

    private void wrap(RecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        wrap(builder, Delightful.MODID, name, consumer, conds);
    }

    private void wrap(RecipeBuilder builder, String modid, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        ResourceLocation loc = Util.rl(modid, name);
        ConditionalRecipe.Builder cond = ConditionalRecipe.builder();
        if (conds.length > 1) {
            cond.addCondition(and(conds));
        } else if (conds.length == 1) {
            cond.addCondition(conds[0]);
        }
        FinishedRecipe[] recipe = new FinishedRecipe[1];
        builder.save(f -> recipe[0] = f, loc);
        cond.addRecipe(recipe[0])
          .generateAdvancement()
          .build(consumer, loc);
    }

    private void wrap(SmithingTransformRecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        ResourceLocation loc = Util.rl(Delightful.MODID, name);
        ConditionalRecipe.Builder cond = ConditionalRecipe.builder();
        if (conds.length > 1) {
            cond.addCondition(and(conds));
        } else if (conds.length == 1) {
            cond.addCondition(conds[0]);
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
        ConditionalRecipe.Builder cond;
        if (conds.length > 1) {
            cond = ConditionalRecipe.builder().addCondition(and(conds));
        } else if (conds.length == 1) {
            cond = ConditionalRecipe.builder().addCondition(conds[0]);
        } else {
            cond = ConditionalRecipe.builder();
        }
        FinishedRecipe[] recipe = new FinishedRecipe[1];
        builder.build(f -> recipe[0] = f, loc);
        cond.addRecipe(recipe[0])
            .generateAdvancement()
            .build(consumer, loc);
    }

    private void wrap(CookingPotRecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        wrap(builder, Delightful.MODID, name, consumer, conds);
    }

    private void wrap(CookingPotRecipeBuilder builder, String modid, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        ResourceLocation loc = Util.rl(modid, name);
        ConditionalRecipe.Builder cond;
        if (conds.length > 1) {
            cond = ConditionalRecipe.builder().addCondition(and(conds));
        } else if (conds.length == 1) {
            cond = ConditionalRecipe.builder().addCondition(conds[0]);
        } else {
            cond = ConditionalRecipe.builder();
        }
        FinishedRecipe[] recipe = new FinishedRecipe[1];
        builder.build(f -> recipe[0] = f, loc);
        cond.addRecipe(recipe[0])
            .generateAdvancement()
            .build(consumer, loc);
    }

    private void sack(RegistryObject<Item> sack, Supplier<Item> ingredient, String name, Consumer<FinishedRecipe> finished) {
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, sack.get(), 1)
                .requires(ingredient.get(), 9)
                .unlockedBy("has_" + name, has(ingredient.get())),
            "storage/" + name + "_storage_block", finished, enabled(sack));
        wrap(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingredient.get(), 9)
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

    private void knife(DelightfulKnifeItem knife, Consumer<FinishedRecipe> finished) {
        TagKey<Item> tag = knife.getTag();
        if (tag != null) {
            String path = Util.name(knife);
            ICondition[] conds = new ICondition[] { enabled(path), not(tagEmpty(tag)) };
            if (knife instanceof ICompat compat) {
                conds = ArrayUtils.addAll(conds, Arrays.stream(compat.getModid()).map(this::modLoaded).toList().toArray(new ICondition[0]));
            }
            if (knife.getRecipeType() == RecipeType.SMITHING) {
                wrap(SmithingTransformRecipeBuilder.smithing(knife.getSmithing().getLeft(), knife.getSmithing().getRight(), Ingredient.of(tag), RecipeCategory.COMBAT, knife)
                    .unlocks("has_" + tag.location().getPath(), has(tag)),
                    "knives/smithing/" + path, finished, conds);
            } else {
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

    private void knifeSmeltAndBlast(DelightfulKnifeItem knife, String metal, ResourceLocation nugget, Consumer<FinishedRecipe> finished) {
        ConditionalRecipe.builder()
            .addCondition(and(enabled(Util.name(knife)), itemExists(nugget.getNamespace(), nugget.getPath())))
            .addRecipe(f -> SimpleCookingRecipeBuilder.smelting(Ingredient.of(knife), RecipeCategory.COMBAT, Objects.requireNonNull(Util.item(nugget)), 0.1F, 200)
                .unlockedBy("has_" + metal + "_knife", InventoryChangeTrigger.TriggerInstance.hasItems(knife))
                .save(f, Util.rl(Delightful.MODID, "knives/smelting/" + metal + "_" + nugget.getNamespace())))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "knives/smelting/" + metal + "_" + nugget.getNamespace());
        ConditionalRecipe.builder()
            .addCondition(and(enabled(Util.name(knife)), itemExists(nugget.getNamespace(), nugget.getPath())))
            .addRecipe(f -> SimpleCookingRecipeBuilder.blasting(Ingredient.of(knife), RecipeCategory.COMBAT, Objects.requireNonNull(Util.item(nugget)), 0.1F, 100)
                .unlockedBy("has_" + metal + "_knife", InventoryChangeTrigger.TriggerInstance.hasItems(knife))
                .save(f, Util.rl(Delightful.MODID, "knives/blasting/" + metal + "_" + nugget.getNamespace())))
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
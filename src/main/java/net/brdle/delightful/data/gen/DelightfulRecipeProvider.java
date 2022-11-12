package net.brdle.delightful.data.gen;

import com.hollingsworth.arsnouveau.setup.ItemsRegistry;
import net.brdle.delightful.Delightful;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.block.DelightfulCabinetBlock;
import net.brdle.delightful.common.crafting.EnabledCondition;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import net.brdle.delightful.common.item.knife.DelightfulKnifeItem;
import net.brdle.delightful.compat.ArsNouveauCompat;
import net.brdle.delightful.data.DelightfulItemTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.StrictNBTIngredient;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;
import vectorwing.farmersdelight.data.recipe.CookingRecipes;
import java.util.Objects;
import java.util.function.Consumer;

public class DelightfulRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public DelightfulRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> finished) {
        // Cabinets
        DelightfulBlocks.BLOCKS.getEntries().stream()
            .map(RegistryObject::get)
            .filter(block -> block instanceof DelightfulCabinetBlock)
            .forEach(block -> cabinet((DelightfulCabinetBlock) block, finished));

        // Knives
        DelightfulItems.ITEMS.getEntries().stream()
            .map(RegistryObject::get)
            .filter(item -> item instanceof DelightfulKnifeItem)
            .map(item -> (DelightfulKnifeItem) item)
            .forEach(k -> knife(k, finished));
        knifeSmeltAndBlast((DelightfulKnifeItem) DelightfulItems.BONE_KNIFE.get(), "bone/knife", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(Items.BONE_MEAL)), finished);

        // Foods
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.NUT_BUTTER_AND_JELLY_SANDWICH.get())
                .requires(ForgeTags.BREAD)
                .requires(DelightfulItems.NUT_BUTTER_BOTTLE.get())
                .requires(DelightfulItemTags.JELLY)
                .unlockedBy("has_nut_butter", has(DelightfulItems.NUT_BUTTER_BOTTLE.get())),
            "food/nut_butter_and_jelly_sandwich", finished, enabled("nut_butter_and_jelly_sandwich"), not(tagEmpty(DelightfulItemTags.NUTS)), tagEmpty(DelightfulItemTags.BREAD_SLICE));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.NUT_BUTTER_AND_JELLY_SANDWICH.get())
                .requires(DelightfulItemTags.BREAD_SLICE)
                .requires(DelightfulItems.NUT_BUTTER_BOTTLE.get())
                .requires(DelightfulItemTags.JELLY)
                .requires(DelightfulItemTags.BREAD_SLICE)
                .unlockedBy("has_nut_butter", has(DelightfulItems.NUT_BUTTER_BOTTLE.get())),
            "food/nut_butter_and_jelly_sandwich_from_bread_slice", finished, enabled("nut_butter_and_jelly_sandwich"), not(tagEmpty(DelightfulItemTags.NUTS)), not(tagEmpty(DelightfulItemTags.BREAD_SLICE)));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.CHEESEBURGER.get())
                .requires(ForgeTags.BREAD)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(ForgeTags.CROPS_TOMATO)
                .requires(ForgeTags.CROPS_ONION)
                .unlockedBy("has_beef_patty_and_cheese", has(ModItems.BEEF_PATTY.get(), Items.MILK_BUCKET)),
            "food/cheeseburger", finished, enabled("cheeseburger"), tagEmpty(DelightfulItemTags.BURGER_BUN));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.CHEESEBURGER.get())
                .requires(DelightfulItemTags.BURGER_BUN)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(ForgeTags.CROPS_TOMATO)
                .requires(ForgeTags.CROPS_ONION)
                .unlockedBy("has_beef_patty_and_cheese", has(ModItems.BEEF_PATTY.get(), Items.MILK_BUCKET)),
            "food/cheeseburger_from_bun", finished, enabled("cheeseburger"), not(tagEmpty(DelightfulItemTags.BURGER_BUN)));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.CHEESEBURGER.get())
                .requires(ModItems.HAMBURGER.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .unlockedBy("has_hamburger", has(ModItems.HAMBURGER.get())),
            "food/cheeseburger_from_hamburger", finished, enabled("cheeseburger"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.DELUXE_CHEESEBURGER.get())
                .requires(ForgeTags.BREAD)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .requires(ModItems.COOKED_BACON.get())
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(ForgeTags.CROPS_TOMATO)
                .requires(ForgeTags.CROPS_ONION)
                .unlockedBy("has_bacon", has(ModItems.COOKED_BACON.get())),
            "food/deluxe_cheeseburger", finished, enabled("deluxe_cheeseburger"), tagEmpty(DelightfulItemTags.BURGER_BUN));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.DELUXE_CHEESEBURGER.get())
                .requires(DelightfulItemTags.BURGER_BUN)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .requires(ModItems.COOKED_BACON.get())
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(ForgeTags.CROPS_TOMATO)
                .requires(ForgeTags.CROPS_ONION)
                .unlockedBy("has_bacon", has(ModItems.COOKED_BACON.get())),
            "food/deluxe_cheeseburger_from_bun", finished, enabled("deluxe_cheeseburger"), not(tagEmpty(DelightfulItemTags.BURGER_BUN)));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.DELUXE_CHEESEBURGER.get())
                .requires(DelightfulItems.CHEESEBURGER.get())
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .requires(ModItems.COOKED_BACON.get())
                .unlockedBy("has_cheeseburger", has(DelightfulItems.CHEESEBURGER.get())),
            "food/deluxe_cheeseburger_from_cheeseburger", finished, enabled("deluxe_cheeseburger"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.MARSHMALLOW_STICK.get(), 2)
                .requires(DelightfulItemTags.SUGAR)
                .requires(DelightfulItemTags.WATER)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(Tags.Items.RODS_WOODEN)
                .unlockedBy("has_sugar", has(DelightfulItemTags.SUGAR)),
            "food/marshmallow_stick", finished, enabled("marshmallow_stick"), not(modLoaded("create_confectionery")));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.MARSHMALLOW_STICK.get(), 2)
                .requires(DelightfulItemTags.SUGAR)
                .requires(StrictNBTIngredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)))
                .requires(Tags.Items.RODS_WOODEN)
                .requires(Tags.Items.RODS_WOODEN)
                .unlockedBy("has_sugar", has(DelightfulItemTags.SUGAR)),
            "food/marshmallow_stick_from_water_bottle", finished, enabled("marshmallow_stick"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.SMORE.get())
                .requires(ModItems.HONEY_COOKIE.get())
                .requires(Items.COCOA_BEANS)
                .requires(ForgeTags.MILK)
                .requires(DelightfulItemTags.SUGAR)
                .requires(DelightfulItems.COOKED_MARSHMALLOW_STICK.get())
                .requires(ModItems.HONEY_COOKIE.get())
                .unlockedBy("has_cooked_marshmallow_stick", has(DelightfulItems.COOKED_MARSHMALLOW_STICK.get())),
            "food/smore", finished, enabled("smore"), tagEmpty(DelightfulItemTags.CHOCOLATE));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.SMORE.get())
                .requires(ModItems.HONEY_COOKIE.get())
                .requires(DelightfulItemTags.CHOCOLATE)
                .requires(DelightfulItems.COOKED_MARSHMALLOW_STICK.get())
                .requires(ModItems.HONEY_COOKIE.get())
                .unlockedBy("has_cooked_marshmallow_stick", has(DelightfulItems.COOKED_MARSHMALLOW_STICK.get())),
            "food/smore_from_chocolate", finished, enabled("smore"), not(tagEmpty(DelightfulItemTags.CHOCOLATE)));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.PRICKLY_PEAR_JUICE.get())
                .requires(DelightfulItemTags.COOKED_PRICKLY_PEAR)
                .requires(DelightfulItemTags.COOKED_PRICKLY_PEAR)
                .requires(DelightfulItemTags.SUGAR)
                .requires(DelightfulItemTags.COOKED_PRICKLY_PEAR)
                .requires(DelightfulItemTags.COOKED_PRICKLY_PEAR)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_cooked_prickly_pear", has(DelightfulItemTags.COOKED_PRICKLY_PEAR)),
            "food/prickly_pear_juice", finished, enabled("prickly_pear_juice"), not(tagEmpty(DelightfulItemTags.COOKED_PRICKLY_PEAR)));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.CRAB_RANGOON.get())
                .requires(ForgeTags.DOUGH_WHEAT)
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .requires(DelightfulItemTags.COOKED_CRAB)
                .unlockedBy("has_cooked_crab", has(DelightfulItemTags.COOKED_CRAB)),
            "food/crab_rangoon", finished, enabled("crab_rangoon"), not(tagEmpty(DelightfulItemTags.COOKED_CRAB)));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.CHUNKWICH.get())
                .requires(ForgeTags.BREAD)
                .requires(DelightfulItemTags.SWEETENED_CHUNK)
                .unlockedBy("has_sweetened_chunk", has(DelightfulItemTags.SWEETENED_CHUNK)),
            "food/chunkwich", finished, enabled("chunkwich"), not(tagEmpty(DelightfulItemTags.SWEETENED_CHUNK)), tagEmpty(DelightfulItemTags.BURGER_BUN));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.CHUNKWICH.get())
                .requires(DelightfulItemTags.BURGER_BUN)
                .requires(DelightfulItemTags.SWEETENED_CHUNK)
                .unlockedBy("has_sweetened_chunk", has(DelightfulItemTags.SWEETENED_CHUNK)),
            "food/chunkwich_from_bun", finished, enabled("chunkwich"), not(tagEmpty(DelightfulItemTags.SWEETENED_CHUNK)), not(tagEmpty(DelightfulItemTags.BURGER_BUN)));
        wrap(SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(DelightfulItems.MARSHMALLOW_STICK.get()),
                DelightfulItems.COOKED_MARSHMALLOW_STICK.get(), 0.5F, 600)
                .unlockedBy("has_marshmallow_stick", has(DelightfulItems.MARSHMALLOW_STICK.get())),
            "food/cooked_marshmallow_stick", finished, enabled("cooked_marshmallow_stick"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.HONEY_GLAZED_WALNUT.get(), 3)
                .requires(DelightfulItemTags.NUTS_WALNUT)
                .requires(DelightfulItemTags.NUTS_WALNUT)
                .requires(DelightfulItemTags.NUTS_WALNUT)
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy("has_walnut", has(DelightfulItemTags.NUTS_WALNUT)),
            "food/honey_glazed_walnut", finished, enabled("honey_glazed_walnut"), not(tagEmpty(DelightfulItemTags.NUTS_WALNUT)));
        wrap(SimpleCookingRecipeBuilder.smelting(Ingredient.of(DelightfulItemTags.TEA_LEAVES_GREEN),
                DelightfulItems.MATCHA.get(), 0.1F, 200)
                .unlockedBy("has_green_tea_leaves", has(DelightfulItemTags.TEA_LEAVES_GREEN)),
            "smelting/green_tea_leaves", finished, enabled("matcha"), not(tagEmpty(DelightfulItemTags.TEA_LEAVES_GREEN)));
        wrap(SimpleCookingRecipeBuilder.blasting(Ingredient.of(DelightfulItemTags.TEA_LEAVES_GREEN),
                DelightfulItems.MATCHA.get(), 0.1F, 100)
                .unlockedBy("has_green_tea_leaves", has(DelightfulItemTags.TEA_LEAVES_GREEN)),
            "blasting/green_tea_leaves", finished, enabled("matcha"), not(tagEmpty(DelightfulItemTags.TEA_LEAVES_GREEN)));
        wrap(SimpleCookingRecipeBuilder.smelting(Ingredient.of(DelightfulItems.GREEN_TEA_LEAF.get()),
                    DelightfulItems.MATCHA.get(), 0.1F, 200)
                .unlockedBy("has_green_tea_leaf", has(DelightfulItems.GREEN_TEA_LEAF.get())),
            "smelting/green_tea_leaf", finished, enabled("matcha"), tagEmpty(DelightfulItemTags.TEA_LEAVES_GREEN));
        wrap(SimpleCookingRecipeBuilder.blasting(Ingredient.of(DelightfulItems.GREEN_TEA_LEAF.get()),
                    DelightfulItems.MATCHA.get(), 0.1F, 100)
                .unlockedBy("has_green_tea_leaf", has(DelightfulItems.GREEN_TEA_LEAF.get())),
            "blasting/green_tea_leaf", finished, enabled("matcha"), tagEmpty(DelightfulItemTags.TEA_LEAVES_GREEN));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.MATCHA_LATTE.get(), 1)
                .requires(Items.GLASS_BOTTLE)
                .requires(ForgeTags.MILK)
                .requires(Items.HONEY_BOTTLE)
                .requires(DelightfulItems.MATCHA.get())
                .unlockedBy("has_matcha", has(DelightfulItems.MATCHA.get())),
            "food/matcha_latte", finished, enabled("matcha_latte"), enabled("matcha"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.BERRY_MATCHA_LATTE.get(), 1)
                .requires(Items.GLASS_BOTTLE)
                .requires(DelightfulItemTags.FRUITS_BERRIES)
                .requires(ForgeTags.MILK)
                .requires(Items.HONEY_BOTTLE)
                .requires(DelightfulItems.MATCHA.get())
                .unlockedBy("has_matcha_latte", has(DelightfulItems.MATCHA_LATTE.get())),
            "food/berry_matcha_latte", finished, enabled("berry_matcha_latte"), enabled("matcha_latte"), enabled("matcha"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.BERRY_MATCHA_LATTE.get(), 1)
                .requires(DelightfulItems.MATCHA_LATTE.get())
                .requires(DelightfulItemTags.FRUITS_BERRIES)
                .unlockedBy("has_matcha_latte", has(DelightfulItems.MATCHA_LATTE.get())),
            "food/berry_matcha_latte_from_matcha_latte", finished, enabled("berry_matcha_latte"), enabled("matcha_latte"), enabled("matcha"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.ROCK_CANDY.get(), 1)
                .requires(DelightfulItemTags.GEMS_ROSE_QUARTZ)
                .requires(DelightfulItemTags.GEMS_ROSE_QUARTZ)
                .requires(DelightfulItemTags.SUGAR)
                .requires(Tags.Items.RODS_WOODEN)
                .unlockedBy("has_rose_quartz", has(DelightfulItemTags.GEMS_ROSE_QUARTZ)),
            "food/rock_candy", finished, enabled("rock_candy"), not(tagEmpty(DelightfulItemTags.GEMS_ROSE_QUARTZ)));
        wrap(SimpleCookingRecipeBuilder.smelting(Ingredient.of(DelightfulItems.CACTUS_FLESH.get()),
                DelightfulItems.CACTUS_STEAK.get(), 0.1F, 200)
                .unlockedBy("has_cactus_flesh", has(DelightfulItems.CACTUS_FLESH.get())),
            "smelting/cactus_flesh", finished, enabled("cactus_steak"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.FIELD_SALAD.get(), 1)
                .requires(Items.BOWL)
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(DelightfulItems.CACTUS_STEAK.get())
                .requires(Items.CARROT)
                .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
                .unlockedBy("has_cactus_steak", has(DelightfulItems.CACTUS_STEAK.get())),
            "food/field_salad", finished, enabled("field_salad"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.SALMONBERRY_SACK.get(), 1)
                .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
                .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
                .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
                .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
                .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
                .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
                .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
                .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
                .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
                .unlockedBy("has_salmonberries", has(DelightfulItems.SALMONBERRIES.get())),
            "storage/salmonberry_sack", finished, enabled("salmonberry_sack"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.SALMONBERRY_ICE_CREAM.get(), 1)
                .requires(Items.BOWL)
                .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
                .requires(ForgeTags.MILK)
                .requires(DelightfulItemTags.ICE_CUBES)
                .requires(DelightfulItemTags.SUGAR)
                .unlockedBy("has_ice_cubes", has(DelightfulItemTags.ICE_CUBES)),
            "food/salmonberry_ice_cream", finished, enabled("salmonberry_ice_cream"), not(tagEmpty(DelightfulItemTags.ICE_CUBES)));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.SALMONBERRY_ICE_CREAM.get(), 1)
                .requires(Items.BOWL)
                .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
                .requires(ForgeTags.MILK)
                .requires(Items.ICE)
                .requires(DelightfulItemTags.SUGAR)
                .unlockedBy("has_ice", has(Items.ICE)),
            "food/salmonberry_ice_cream_no_neapolitan", finished, enabled("salmonberry_ice_cream"), tagEmpty(DelightfulItemTags.ICE_CUBES));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.MATCHA_ICE_CREAM.get(), 1)
                .requires(Items.BOWL)
                .requires(DelightfulItems.MATCHA.get())
                .requires(ForgeTags.MILK)
                .requires(DelightfulItemTags.ICE_CUBES)
                .requires(DelightfulItemTags.SUGAR)
                .unlockedBy("has_ice_cubes", has(DelightfulItemTags.ICE_CUBES)),
            "food/matcha_ice_cream", finished, enabled("matcha_ice_cream"), not(tagEmpty(DelightfulItemTags.ICE_CUBES)));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.SALMONBERRY_ICE_CREAM.get(), 1)
                .requires(Items.BOWL)
                .requires(DelightfulItems.MATCHA.get())
                .requires(ForgeTags.MILK)
                .requires(Items.ICE)
                .requires(DelightfulItemTags.SUGAR)
                .unlockedBy("has_ice", has(Items.ICE)),
            "food/matcha_ice_cream_no_neapolitan", finished, enabled("matcha_ice_cream"), tagEmpty(DelightfulItemTags.ICE_CUBES));
        wrap(ShapedRecipeBuilder.shaped(DelightfulItems.SALMONBERRY_PIE.get(), 1)
                .pattern("###")
                .pattern("aaa")
                .pattern("xOx")
                .define('#', Items.WHEAT)
                .define('a', DelightfulItemTags.FRUITS_SALMONBERRIES)
                .define('x', DelightfulItemTags.SUGAR)
                .define('O', ModItems.PIE_CRUST.get())
                .unlockedBy("has_pie_crust", has(ModItems.PIE_CRUST.get())),
            "food/salmonberry_pie", finished, enabled("salmonberry_pie"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.SALMONBERRY_PIE.get(), 1)
                .requires(DelightfulItems.SALMONBERRY_PIE_SLICE.get(), 4)
                .unlockedBy("has_salmonberry_pie_slice", has(DelightfulItems.SALMONBERRY_PIE_SLICE.get())),
            "food/salmonberry_pie_from_slices", finished, enabled("salmonberry_pie"));
        /*wrap(ShapedRecipeBuilder.shaped(ItemsRegistry.SOURCE_BERRY_PIE.get(), 1)
                .pattern("#m#")
                .pattern("aaa")
                .pattern("xOe")
                .define('#', Items.WHEAT)
                .define('m', ItemsRegistry.MAGE_BLOOM.get())
                .define('a', DelightfulItemTags.FRUITS_SOURCEBERRY)
                .define('x', DelightfulItemTags.SUGAR)
                .define('e', ForgeTags.EGGS)
                .define('O', ModItems.PIE_CRUST.get())
                .unlockedBy("has_pie_crust", has(ModItems.PIE_CRUST.get())),
            "food/source_berry_pie", finished, enabled("source_berry_pie_slice"), itemExists(ArsNouveauCompat.modid, "source_berry_pie"));*/
        wrap(ShapelessRecipeBuilder.shapeless(ItemsRegistry.SOURCE_BERRY_PIE.get(), 1)
                .requires(DelightfulItems.SOURCE_BERRY_PIE_SLICE.get(), 4)
                .unlockedBy("has_source_berry_pie_slice", has(DelightfulItems.SOURCE_BERRY_PIE_SLICE.get())),
            "food/source_berry_pie_from_slices", finished, enabled("source_berry_pie_slice"), itemExists(ArsNouveauCompat.modid, "source_berry_pie"));
        wrap(ShapedRecipeBuilder.shaped(Items.PUMPKIN_PIE, 1)
                .pattern("###")
                .pattern("aaa")
                .pattern("xOe")
                .define('#', Items.WHEAT)
                .define('a', Items.PUMPKIN)
                .define('x', DelightfulItemTags.SUGAR)
                .define('e', ForgeTags.EGGS)
                .define('O', ModItems.PIE_CRUST.get())
                .unlockedBy("has_pie_crust", has(ModItems.PIE_CRUST.get())),
            "food/pumpkin_pie", finished, enabled("pumpkin_pie_overhaul"));
        wrap(ShapelessRecipeBuilder.shapeless(Items.PUMPKIN_PIE, 1)
                .requires(DelightfulItems.PUMPKIN_PIE_SLICE.get(), 4)
                .unlockedBy("has_pumpkin_pie_slice", has(DelightfulItems.PUMPKIN_PIE_SLICE.get())),
            "food/pumpkin_pie_from_slices", finished, enabled("pumpkin_pie_overhaul"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.SALMONBERRIES.get(), 9)
                .requires(DelightfulItems.SALMONBERRY_SACK.get())
                .unlockedBy("has_salmonberry_sack", has(DelightfulItems.SALMONBERRY_SACK.get())),
            "storage/unpack_salmonberry_sack", finished, enabled("salmonberry_sack"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.SALMONBERRY_PIPS.get())
                .requires(DelightfulItems.SALMONBERRIES.get())
                .unlockedBy("has_salmonberries", has(DelightfulItemTags.FRUITS_SALMONBERRIES)),
            "salmonberry_pips", finished, enabled("salmonberry_pips"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.ACORN_SACK.get(), 1)
                .requires(DelightfulItemTags.NUTS_ACORN)
                .requires(DelightfulItemTags.NUTS_ACORN)
                .requires(DelightfulItemTags.NUTS_ACORN)
                .requires(DelightfulItemTags.NUTS_ACORN)
                .requires(DelightfulItemTags.NUTS_ACORN)
                .requires(DelightfulItemTags.NUTS_ACORN)
                .requires(DelightfulItemTags.NUTS_ACORN)
                .requires(DelightfulItemTags.NUTS_ACORN)
                .requires(DelightfulItemTags.NUTS_ACORN)
                .unlockedBy("has_acorn", has(DelightfulItemTags.NUTS_ACORN)),
            "storage/acorn_sack", finished, enabled("acorn_sack"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.ACORN.get(), 9)
                .requires(DelightfulItems.ACORN_SACK.get())
                .unlockedBy("has_acorn_sack", has(DelightfulItems.ACORN_SACK.get())),
            "storage/unpack_acorn_sack", finished, enabled("acorn_sack"));
        wrap(SimpleCookingRecipeBuilder.smelting(Ingredient.of(DelightfulItems.VENISON_CHOPS.get()),
                DelightfulItems.COOKED_VENISON_CHOPS.get(), 0.35F, 200)
            .unlockedBy("has_venison_chops", has(DelightfulItems.VENISON_CHOPS.get())),
            "cooking/venison_chops", finished, enabled("cooked_venison_chops"), enabled("venison_chops"), not(tagEmpty(DelightfulItemTags.RAW_VENISON)));
        wrap(SimpleCookingRecipeBuilder.smoking(Ingredient.of(DelightfulItems.VENISON_CHOPS.get()),
            DelightfulItems.COOKED_VENISON_CHOPS.get(), 0.35F, 100)
                .unlockedBy("has_venison_chops", has(DelightfulItems.VENISON_CHOPS.get())),
            "cooking/venison_chops_from_smoking", finished, enabled("cooked_venison_chops"), enabled("venison_chops"), not(tagEmpty(DelightfulItemTags.RAW_VENISON)));
        wrap(SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(DelightfulItems.VENISON_CHOPS.get()),
            DelightfulItems.COOKED_VENISON_CHOPS.get(), 0.35F, 600)
                .unlockedBy("has_venison_chops", has(DelightfulItems.VENISON_CHOPS.get())),
            "cooking/venison_chops_from_campfire_cooking", finished, enabled("cooked_venison_chops"), enabled("venison_chops"), not(tagEmpty(DelightfulItemTags.RAW_VENISON)));
        wrap(SimpleCookingRecipeBuilder.smelting(Ingredient.of(DelightfulItems.RAW_GOAT.get()),
            DelightfulItems.COOKED_GOAT.get(), 0.35F, 200)
                .unlockedBy("has_raw_goat", has(DelightfulItems.RAW_GOAT.get())),
            "cooking/raw_goat", finished, enabled("cooked_goat"));
        wrap(SimpleCookingRecipeBuilder.smoking(Ingredient.of(DelightfulItems.RAW_GOAT.get()),
            DelightfulItems.COOKED_GOAT.get(), 0.35F, 100)
                .unlockedBy("has_raw_goat", has(DelightfulItems.RAW_GOAT.get())),
            "cooking/raw_goat_from_smoking", finished, enabled("cooked_goat"));
        wrap(SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(DelightfulItems.RAW_GOAT.get()),
            DelightfulItems.COOKED_GOAT.get(), 0.35F, 600)
                .unlockedBy("has_raw_goat", has(DelightfulItems.RAW_GOAT.get())),
            "cooking/raw_goat_from_campfire_cooking", finished, enabled("cooked_goat"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.CANTALOUPE_SLICE.get(), 3)
                .requires(DelightfulItems.CANTALOUPE.get())
                .unlockedBy("has_cantaloupe", has(DelightfulItems.CANTALOUPE.get())),
            "cantaloupe_slice", finished, enabled("cantaloupe_slice"));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(DelightfulItems.CANTALOUPE.get()),
                Ingredient.of(DelightfulItemTags.SCAVENGING_TOOLS),
                DelightfulItems.CANTALOUPE_SLICE.get(), 6),
            "cutting/cantaloupe", finished, enabled("cantaloupe_slice"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
            DelightfulItems.ENDER_NECTAR.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(Items.ENDER_EYE)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(DelightfulItemTags.SUGAR)
                .unlockedBy("has_ender_eye", has(Items.ENDER_EYE)),
            "food/cooking/ender_nectar", finished, enabled("ender_nectar"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
            DelightfulItems.ANIMAL_OIL_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItems.ANIMAL_FAT.get())
                .addIngredient(DelightfulItems.ANIMAL_FAT.get())
                .unlockedBy("has_animal_fat", has(DelightfulItems.ANIMAL_FAT.get())),
            "cooking/animal_oil_bottle", finished, enabled("animal_oil_bottle"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
            DelightfulItems.JELLY_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItemTags.FRUITS_SWEET)
                .addIngredient(DelightfulItemTags.FRUITS_SWEET)
                .addIngredient(DelightfulItemTags.SUGAR)
                .addIngredient(DelightfulItemTags.SUGAR)
                .unlockedBy("has_sweet_fruit", has(DelightfulItemTags.FRUITS_SWEET)),
            "food/cooking/jelly_bottle", finished, enabled("jelly_bottle"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
            DelightfulItems.GLOW_JELLY_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItemTags.FRUITS_GLOW_BERRIES)
                .addIngredient(Tags.Items.DUSTS_GLOWSTONE)
                .addIngredient(DelightfulItemTags.SUGAR)
                .addIngredient(DelightfulItemTags.SUGAR)
                .unlockedBy("has_glow_berries", has(DelightfulItemTags.FRUITS_GLOW_BERRIES)),
            "food/cooking/glow_jelly_bottle", finished, enabled("glow_jelly_bottle"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
            DelightfulItems.NUT_BUTTER_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItemTags.NUTS)
                .addIngredient(DelightfulItemTags.SUGAR)
                .unlockedBy("has_nuts", has(DelightfulItemTags.NUTS)),
            "food/cooking/nut_butter_bottle", finished, enabled("nut_butter_bottle"), not(tagEmpty(DelightfulItemTags.NUTS)));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
            DelightfulItems.CHUNK_NUGGET.get(), 6, CookingRecipes.FAST_COOKING, 0.25F)
                .addIngredient(ForgeTags.BREAD)
                .addIngredient(DelightfulItemTags.SWEETENED_CHUNK)
                .addIngredient(DelightfulItemTags.SWEETENED_CHUNK)
                .unlockedBy("has_sweetened_chunk", has(DelightfulItemTags.SWEETENED_CHUNK)),
            "food/cooking/chunk_nugget", finished, enabled("chunk_nugget"), not(tagEmpty(DelightfulItemTags.SWEETENED_CHUNK)));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.AZALEA_TEA.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(StrictNBTIngredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)))
                .addIngredient(DelightfulItems.GREEN_TEA_LEAF.get())
                .addIngredient(Objects.requireNonNull(Util.item("ecologics", "azalea_flower")))
                .unlockedBy("has_azalea_flower", has(Objects.requireNonNull(Util.item("ecologics", "azalea_flower")))),
            "food/cooking/azalea_tea", finished, enabled("azalea_tea"), itemExists("ecologics", "azalea_flower"), not(modLoaded("farmersrespite")));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.LAVENDER_TEA.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(StrictNBTIngredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)))
                .addIngredient(DelightfulItems.GREEN_TEA_LEAF.get())
                .addIngredient(Objects.requireNonNull(Util.item("biomesoplenty", "lavender")))
                .unlockedBy("has_lavender_flower", has(Objects.requireNonNull(Util.item("biomesoplenty", "lavender")))),
            "food/cooking/lavender_tea", finished, enabled("lavender_tea"), itemExists("biomesoplenty", "lavender"), not(modLoaded("farmersrespite")));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
            Items.MILK_BUCKET, 1, CookingRecipes.NORMAL_COOKING, 0.35F, Items.BUCKET)
                .addIngredient(DelightfulItemTags.WATER)
                .addIngredient(DelightfulItemTags.NUTS)
                .addIngredient(DelightfulItemTags.NUTS)
                .addIngredient(DelightfulItemTags.SUGAR)
                .unlockedBy("has_nuts", has(DelightfulItemTags.NUTS)),
            "food/nut_milk", finished, enabled("nut_milk"));
        wrap(ShapedRecipeBuilder.shaped(Items.TORCH, 8)
                .define('o', DelightfulItems.ANIMAL_OIL_BOTTLE.get())
                .define('s', Tags.Items.RODS_WOODEN)
                .pattern("o")
                .pattern("s")
                .unlockedBy("has_oil_bottle", has(DelightfulItems.ANIMAL_OIL_BOTTLE.get())),
            "torch_from_animal_oil_bottle", finished, enabled("animal_oil_bottle"));
        wrap(ShapedRecipeBuilder.shaped(Items.CANDLE, 1)
                .define('f', DelightfulItems.ANIMAL_FAT.get())
                .define('s', Tags.Items.STRING)
                .pattern("s")
                .pattern("f")
                .pattern("f")
                .unlockedBy("has_fat", has(DelightfulItems.ANIMAL_FAT.get())),
            "candle_from_animal_fat", finished, enabled("animal_fat"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
                    Objects.requireNonNull(Util.item("ecologics", "tropical_stew")), 1, CookingRecipes.NORMAL_COOKING, 0.35F, Util.item("ecologics", "coconut_slice"))
                .addIngredient(DelightfulItemTags.COOKED_CRAB)
                .addIngredient(ForgeTags.CROPS_RICE)
                .addIngredient(ForgeTags.CROPS_ONION)
                .unlockedBy("has_crab", has(DelightfulItemTags.COOKED_CRAB)),
            "ecologics", "tropical_stew", finished, itemExists("ecologics", "tropical_stew"), itemExists("ecologics", "coconut_slice"), not(tagEmpty(DelightfulItemTags.COOKED_CRAB)));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(Util.item("biomesoplenty", "clover")),
                Ingredient.of(DelightfulItemTags.SCAVENGING_TOOLS),
                DelightfulItems.CHOPPED_CLOVER.get(), 2),
            "cutting/clover", finished, enabled("chopped_clover"), itemExists("biomesoplenty", "clover"));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(Util.item("biomesoplenty", "huge_clover_petal")),
                Ingredient.of(DelightfulItemTags.SCAVENGING_TOOLS),
                DelightfulItems.CHOPPED_CLOVER.get(), 4),
            "cutting/huge_clover_petal", finished, enabled("chopped_clover"), itemExists("biomesoplenty", "huge_clover_petal"));
        wrap(CookingPotRecipeBuilder.cookingPotRecipe(
                    Items.HONEY_BOTTLE, 3, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(Items.HONEY_BOTTLE, 2)
                .addIngredient(DelightfulItems.CHOPPED_CLOVER.get(), 4),
            "food/clover_honey", finished, enabled("clover_honey"), modLoaded("biomesoplenty"));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(DelightfulItemTags.RAW_VENISON),
                Ingredient.of(DelightfulItemTags.SCAVENGING_TOOLS),
                DelightfulItems.VENISON_CHOPS.get(), 2),
            "cutting/venison", finished, enabled("venison_chops"), not(tagEmpty(DelightfulItemTags.RAW_VENISON)));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(DelightfulItemTags.COOKED_VENISON),
                Ingredient.of(DelightfulItemTags.SCAVENGING_TOOLS),
                DelightfulItems.COOKED_VENISON_CHOPS.get(), 2),
            "cutting/cooked_venison", finished, enabled("cooked_venison_chops"), enabled("venison_chops"), not(tagEmpty(DelightfulItemTags.COOKED_VENISON)));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(DelightfulItems.SALMONBERRY_PIE.get()),
                Ingredient.of(DelightfulItemTags.SCAVENGING_TOOLS),
                DelightfulItems.SALMONBERRY_PIE_SLICE.get(), 4),
            "cutting/salmonberry_pie", finished, enabled("salmonberry_pie"), enabled("salmonberry_pie_slice"));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(ItemsRegistry.SOURCE_BERRY_PIE.get()),
                Ingredient.of(DelightfulItemTags.SCAVENGING_TOOLS),
                DelightfulItems.SOURCE_BERRY_PIE_SLICE.get(), 4),
            "cutting/source_berry_pie", finished, enabled(ArsNouveauCompat.slice), itemExists(ArsNouveauCompat.modid, ArsNouveauCompat.pie));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(Items.PUMPKIN_PIE),
                Ingredient.of(DelightfulItemTags.SCAVENGING_TOOLS),
                DelightfulItems.PUMPKIN_PIE_SLICE.get(), 4),
            "cutting/pumpkin_pie", finished, enabled("pumpkin_pie_overhaul"), enabled("pumpkin_pie_slice"));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(Items.CACTUS),
                Ingredient.of(DelightfulItemTags.SCAVENGING_TOOLS),
                DelightfulItems.CACTUS_FLESH.get(), 2),
            "cutting/cactus", finished, enabled("cactus_flesh"));
        wrap(CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(DelightfulItems.MINI_MELON.get()),
                Ingredient.of(DelightfulItemTags.SCAVENGING_TOOLS),
                Items.MELON_SLICE, 6),
            "cutting/mini_melon", finished, enabled("mini_melon"));
        wrap(ShapelessRecipeBuilder.shapeless(Items.MELON_SLICE, 3)
                .requires(DelightfulItems.MINI_MELON.get())
                .unlockedBy("has_mini_melon", has(DelightfulItems.MINI_MELON.get())),
            "melon_slice", finished, enabled("mini_melon"));

        // Unwrappables
        ConditionalRecipe.builder()
          .addCondition(not(tagEmpty(DelightfulItemTags.CHOCOLATE)))
          .addRecipe(f -> ShapedRecipeBuilder.shaped(ModItems.CHOCOLATE_PIE.get(), 1)
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
          .addRecipe(f -> ShapedRecipeBuilder.shaped(ModItems.CHOCOLATE_PIE.get(), 1)
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
        ShapelessRecipeBuilder.shapeless(ModItems.FRUIT_SALAD.get(), 1)
            .requires(Items.APPLE)
            .requires(Items.MELON_SLICE)
            .requires(ModItems.PUMPKIN_SLICE.get())
            .requires(DelightfulItemTags.FRUITS)
            .requires(ForgeTags.BERRIES)
            .requires(ForgeTags.BERRIES)
            .requires(Items.BOWL)
            .unlockedBy("has_fruits", has(Items.MELON_SLICE, Items.SWEET_BERRIES, Items.APPLE, ModItems.PUMPKIN_SLICE.get()))
            .save(finished, ModItems.FRUIT_SALAD.getId());
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.SUGAR_CANE), Ingredient.of(DelightfulItemTags.SCAVENGING_TOOLS), Items.SUGAR, 1)
            .addResultWithChance(Items.SUGAR, 0.5F, 1)
            .build(finished);
        }

    private InventoryChangeTrigger.TriggerInstance has(ItemLike... items) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(items);
    }

    private void wrap(RecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        wrap(builder, Delightful.MODID, name, consumer, conds);
    }

    private void wrap(RecipeBuilder builder, String modid, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
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
        builder.save(f -> recipe[0] = f, loc);
        cond.addRecipe(recipe[0])
          .generateAdvancement()
          .build(consumer, loc);
    }

    private void wrap(UpgradeRecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        ResourceLocation loc = Util.rl(Delightful.MODID, name);
        ConditionalRecipe.Builder cond;
        if (conds.length > 1) {
            cond = ConditionalRecipe.builder().addCondition(and(conds));
        } else if (conds.length == 1) {
            cond = ConditionalRecipe.builder().addCondition(conds[0]);
        } else {
            cond = ConditionalRecipe.builder();
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

    private void cabinet(DelightfulCabinetBlock block, Consumer<FinishedRecipe> finished) {
        String path = Util.name(block);
        ConditionalRecipe.builder()
            .addCondition(enabled(path))
            .addRecipe(f -> ShapedRecipeBuilder.shaped(block)
                .define('b', block.getIngredient().get())
                .define('c', ModTags.WOODEN_CABINETS)
                .pattern("bbb")
                .pattern("bcb")
                .pattern("bbb")
                .unlockedBy("has_cabinet", inventoryTrigger(ItemPredicate.Builder.item()
                    .of(ModTags.CABINETS).build()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "cabinets/" + path);
    }

    private void knife(DelightfulKnifeItem knife, Consumer<FinishedRecipe> finished) {
        if (knife.getTag() == null || !knife.genRecipe()) {
            return;
        }
        if (knife.isSmithing()) {
            knifeSmith(knife, finished);
            return;
        }
        /*Arrays.stream(Nugget.values()).forEach(mod -> {
            String metal = knife.getTag().getPath().replace("_knife", "").replace("ingots/", "").trim();
            if (mod.getMetals().contains(metal)) {
                knifeSmeltAndBlast(knife, metal, Util.rl(mod.getModid(), mod.formatMetal(metal)), finished);
            }
        });*/
        String path = Util.name(knife);
        TagKey<Item> tag = knife.getTag();
        ICondition cond = (knife instanceof CompatKnifeItem cki) ?
            and(enabled(path), modLoaded(cki.getModid()), not(tagEmpty(tag))) :
            and(enabled(path), not(tagEmpty(tag)));
        wrap(ShapedRecipeBuilder.shaped(knife)
                .define('m', Ingredient.of(tag))
                .define('s', knife.getRod().get())
                .pattern("m")
                .pattern("s")
                .unlockedBy("has_" + path.replace("_knife", ""), has(tag)),
            "knives/" + path, finished, cond);
    }

    private void knifeSmeltAndBlast(DelightfulKnifeItem knife, String metal, ResourceLocation nugget, Consumer<FinishedRecipe> finished) {
        ConditionalRecipe.builder()
            .addCondition(and(enabled(Util.name(knife)), itemExists(nugget.getNamespace(), nugget.getPath())))
            .addRecipe(f -> SimpleCookingRecipeBuilder.smelting(Ingredient.of(knife), Objects.requireNonNull(Util.item(nugget)), 0.1F, 200)
                .unlockedBy("has_" + metal + "_knife", InventoryChangeTrigger.TriggerInstance.hasItems(knife))
                .save(f, Util.rl(Delightful.MODID, "knives/smelting/" + metal + "_" + nugget.getNamespace())))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "knives/smelting/" + metal + "_" + nugget.getNamespace());
        ConditionalRecipe.builder()
            .addCondition(and(enabled(Util.name(knife)), itemExists(nugget.getNamespace(), nugget.getPath())))
            .addRecipe(f -> SimpleCookingRecipeBuilder.blasting(Ingredient.of(knife), Objects.requireNonNull(Util.item(nugget)), 0.1F, 100)
                .unlockedBy("has_" + metal + "_knife", InventoryChangeTrigger.TriggerInstance.hasItems(knife))
                .save(f, Util.rl(Delightful.MODID, "knives/blasting/" + metal + "_" + nugget.getNamespace())))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "knives/blasting/" + metal + "_" + nugget.getNamespace());
    }

    private void knifeSmith(DelightfulKnifeItem knife, Consumer<FinishedRecipe> finished) {
        String path = Util.name(knife);
        TagKey<Item> tag = knife.getTag();
        wrap(UpgradeRecipeBuilder.smithing(Objects.requireNonNull(knife.getSmithingBase()), Ingredient.of(tag), knife)
                .unlocks("has_" + tag.location().getPath(), has(tag)),
            "knives/" + path + "_smithing", finished, enabled(path), not(tagEmpty(tag)));
    }

    private EnabledCondition enabled(String name) {
        return new EnabledCondition(name);
    }
}
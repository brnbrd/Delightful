package net.brdle.delightful.data.gen;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.block.DelightfulCabinetBlock;
import net.brdle.delightful.common.config.EnabledCondition;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import net.brdle.delightful.common.item.knife.DelightfulKnifeItem;
import net.brdle.delightful.common.item.knife.TaggedKnifeItem;
import net.brdle.delightful.common.item.knife.twilightforest.IronwoodKnifeItem;
import net.brdle.delightful.common.item.knife.twilightforest.SteeleafKnifeItem;
import net.brdle.delightful.data.DelightfulItemTags;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.NBTIngredient;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.onvoid.rottenleather.common.RottenLeatherItems;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;
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
            .filter(k -> !(k instanceof IronwoodKnifeItem) && !(k instanceof SteeleafKnifeItem))
            .forEach(k -> knife(k, finished));
        knifeSmeltAndBlast((DelightfulKnifeItem) DelightfulItems.BONE_KNIFE.get(), "bone/knife", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(Items.BONE_MEAL)), finished);

        // Foods
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.NUT_BUTTER_AND_JELLY_SANDWICH.get())
            .requires(ForgeTags.BREAD)
            .requires(DelightfulItems.NUT_BUTTER_BOTTLE.get())
            .requires(DelightfulItemTags.JELLY)
            .unlockedBy("has_nut_butter", has(DelightfulItems.NUT_BUTTER_BOTTLE.get())),
            "food/nut_butter_and_jelly_sandwich", finished, enabled("nut_butter_and_jelly_sandwich"), not(tagEmpty(DelightfulItemTags.NUTS)));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.CHEESEBURGER.get())
            .requires(ForgeTags.BREAD)
            .requires(ModItems.BEEF_PATTY.get())
            .requires(DelightfulItemTags.CHEESE_OR_MILK)
            .requires(ForgeTags.SALAD_INGREDIENTS)
            .requires(ForgeTags.CROPS_TOMATO)
            .requires(ForgeTags.CROPS_ONION)
            .unlockedBy("has_beef_patty_and_cheese", has(ModItems.BEEF_PATTY.get(), Items.MILK_BUCKET)),
            "food/cheeseburger", finished, enabled("cheeseburger"), not(itemExists("some_assembly_required", "burger_bun")));
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
            "food/deluxe_cheeseburger", finished, enabled("deluxe_cheeseburger"), not(itemExists("some_assembly_required", "burger_bun")));
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
            "food/marshmallow_stick", finished, enabled("marshmallow_stick"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.MARSHMALLOW_STICK.get(), 2)
            .requires(DelightfulItemTags.SUGAR)
            .requires(NBTIngredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)))
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
            .requires(ModItems.WHEAT_DOUGH.get())
            .requires(DelightfulItemTags.CHEESE_OR_MILK)
            .requires(DelightfulItemTags.COOKED_CRAB)
            .unlockedBy("has_cooked_crab", has(DelightfulItemTags.COOKED_CRAB)),
            "food/crab_rangoon", finished, enabled("crab_rangoon"), not(tagEmpty(DelightfulItemTags.COOKED_CRAB)));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.CHUNKWICH.get())
            .requires(ForgeTags.BREAD)
            .requires(RottenLeatherItems.SWEETENED_CHUNK.get())
            .unlockedBy("has_sweetened_chunk", has(RottenLeatherItems.SWEETENED_CHUNK.get())),
            "food/chunkwich", finished, enabled("chunkwich"), itemExists("rottenleather", "sweetened_chunk"), not(itemExists("some_assembly_required", "burger_bun")));
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
            .unlockedBy("has_green_tea_leaf", has(DelightfulItems.GREEN_TEA_LEAF.get())),
            "smelting/green_tea_leaf", finished, enabled("matcha"));
        wrap(SimpleCookingRecipeBuilder.blasting(Ingredient.of(DelightfulItemTags.TEA_LEAVES_GREEN),
            DelightfulItems.MATCHA.get(), 0.1F, 100)
            .unlockedBy("has_green_tea_leaf", has(DelightfulItems.GREEN_TEA_LEAF.get())),
            "blasting/green_tea_leaf", finished, enabled("matcha"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.MATCHA_LATTE.get(), 1)
            .requires(Items.GLASS_BOTTLE)
            .requires(ForgeTags.MILK)
            .requires(Items.HONEY_BOTTLE)
            .requires(DelightfulItems.MATCHA.get())
            .unlockedBy("has_matcha", has(DelightfulItems.MATCHA.get())),
            "food/matcha_latte", finished, enabled("matcha_latte"));
        wrap(ShapelessRecipeBuilder.shapeless(DelightfulItems.BERRY_MATCHA_LATTE.get(), 1)
            .requires(Items.GLASS_BOTTLE)
            .requires(DelightfulItemTags.FRUITS_BERRIES)
            .requires(ForgeTags.MILK)
            .requires(Items.HONEY_BOTTLE)
            .requires(DelightfulItems.MATCHA.get())
            .unlockedBy("has_matcha_latte", has(DelightfulItems.MATCHA_LATTE.get())),
            "food/berry_matcha_latte", finished, enabled("berry_matcha_latte"));
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
            "cooking/venison_chops", finished, enabled("cooked_venison_chops"));
        wrap(SimpleCookingRecipeBuilder.smoking(Ingredient.of(DelightfulItems.VENISON_CHOPS.get()),
                    DelightfulItems.COOKED_VENISON_CHOPS.get(), 0.35F, 100)
                .unlockedBy("has_venison_chops", has(DelightfulItems.VENISON_CHOPS.get())),
            "cooking/venison_chops_from_smoking", finished, enabled("cooked_venison_chops"));
        wrap(SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(DelightfulItems.VENISON_CHOPS.get()),
                    DelightfulItems.COOKED_VENISON_CHOPS.get(), 0.35F, 600)
                .unlockedBy("has_venison_chops", has(DelightfulItems.VENISON_CHOPS.get())),
            "cooking/venison_chops_from_campfire_cooking", finished, enabled("cooked_venison_chops"));
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

        // Unwrappables (FinishedRecipe)
        ConditionalRecipe.builder()
            .addCondition(enabled("ender_nectar"))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.ENDER_NECTAR.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(Items.ENDER_EYE)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(DelightfulItemTags.SUGAR)
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/cooking/ender_nectar");
        ConditionalRecipe.builder()
            .addCondition(enabled("animal_oil_bottle"))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.ANIMAL_OIL_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItems.ANIMAL_FAT.get())
                .addIngredient(DelightfulItems.ANIMAL_FAT.get())
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "cooking/animal_oil_bottle");
        ConditionalRecipe.builder()
            .addCondition(enabled("jelly_bottle"))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.JELLY_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItemTags.FRUITS_SWEET)
                .addIngredient(DelightfulItemTags.FRUITS_SWEET)
                .addIngredient(DelightfulItemTags.SUGAR)
                .addIngredient(DelightfulItemTags.SUGAR)
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/cooking/jelly_bottle");
        ConditionalRecipe.builder()
            .addCondition(enabled("glow_jelly_bottle"))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.GLOW_JELLY_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItemTags.FRUITS_GLOW_BERRIES)
                .addIngredient(Tags.Items.DUSTS_GLOWSTONE)
                .addIngredient(DelightfulItemTags.SUGAR)
                .addIngredient(DelightfulItemTags.SUGAR)
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/cooking/glow_jelly_bottle");
        ConditionalRecipe.builder()
            .addCondition(and(enabled("nut_butter_bottle"), not(tagEmpty(DelightfulItemTags.NUTS))))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.NUT_BUTTER_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItemTags.NUTS)
                .addIngredient(DelightfulItemTags.SUGAR)
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/cooking/nut_butter_bottle");
        ConditionalRecipe.builder()
            .addCondition(and(enabled("chunk_nugget"), itemExists("rottenleather", "sweetened_chunk")))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.CHUNK_NUGGET.get(), 6, CookingRecipes.FAST_COOKING, 0.25F)
                .addIngredient(ForgeTags.BREAD)
                .addIngredient(RottenLeatherItems.SWEETENED_CHUNK.get())
                .addIngredient(RottenLeatherItems.SWEETENED_CHUNK.get())
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/chunk_nugget");
        ConditionalRecipe.builder()
            .addCondition(and(enabled("azalea_tea"), itemExists("ecologics", "azalea_flower")))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.AZALEA_TEA.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(NBTIngredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)))
                .addIngredient(DelightfulItemTags.TEA_LEAVES_GREEN)
                .addIngredient(modItem("ecologics", "azalea_flower"))
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/azalea_tea");
        ConditionalRecipe.builder()
            .addCondition(and(enabled("lavender_tea"), itemExists("biomesoplenty", "lavender")))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.LAVENDER_TEA.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(NBTIngredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)))
                .addIngredient(DelightfulItemTags.TEA_LEAVES_GREEN)
                .addIngredient(modItem("biomesoplenty", "lavender"))
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/lavender_tea");
        ConditionalRecipe.builder()
            .addCondition(enabled("nut_milk"))
            .addRecipe(f ->
                CookingPotRecipeBuilder.cookingPotRecipe(
                        Items.MILK_BUCKET, 1, CookingRecipes.NORMAL_COOKING, 0.35F, Items.BUCKET)
                    .addIngredient(DelightfulItemTags.WATER)
                    .addIngredient(DelightfulItemTags.NUTS)
                    .addIngredient(DelightfulItemTags.NUTS)
                    .addIngredient(DelightfulItemTags.SUGAR)
                    .build(f))
            .build(finished, Delightful.MODID, "nut_milk");
        ShapedRecipeBuilder.shaped(Items.TORCH, 8)
            .define('o', DelightfulItems.ANIMAL_OIL_BOTTLE.get())
            .define('s', Tags.Items.RODS_WOODEN)
            .pattern("o")
            .pattern("s")
            .unlockedBy("has_oil_bottle", inventoryTrigger(ItemPredicate.Builder.item()
                .of(DelightfulItems.ANIMAL_OIL_BOTTLE.get()).build()))
            .save(finished, "torch_from_animal_oil_bottle");
        ShapedRecipeBuilder.shaped(Items.CANDLE, 1)
            .define('f', DelightfulItems.ANIMAL_FAT.get())
            .define('s', Tags.Items.STRING)
            .pattern("s")
            .pattern("f")
            .pattern("f")
            .unlockedBy("has_fat", inventoryTrigger(ItemPredicate.Builder.item()
                .of(DelightfulItems.ANIMAL_FAT.get()).build()))
            .save(finished, "candle_from_animal_fat");
        ConditionalRecipe.builder()
            .addCondition(itemExists("ecologics", "tropical_stew"))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                    modItem("ecologics", "tropical_stew"), 1, CookingRecipes.NORMAL_COOKING, 0.35F, modItem("ecologics", "coconut_slice"))
                .addIngredient(DelightfulItemTags.COOKED_CRAB)
                .addIngredient(ForgeTags.CROPS_RICE)
                .addIngredient(ForgeTags.CROPS_ONION)
                .build(f))
            .generateAdvancement()
            .build(finished, "ecologics", "tropical_stew");
        ConditionalRecipe.builder()
            .addCondition(and(enabled("chopped_clover"), itemExists("biomesoplenty", "clover")))
            .addRecipe(f -> CuttingBoardRecipeBuilder.cuttingRecipe(
                    Ingredient.of(modItem("biomesoplenty", "clover")),
                    Ingredient.of(ForgeTags.TOOLS_KNIVES),
                    DelightfulItems.CHOPPED_CLOVER.get(), 2)
                .build(f))
            .build(finished, Delightful.MODID, "chopped_clover");
        ConditionalRecipe.builder()
            .addCondition(and(enabled("chopped_clover"), itemExists("biomesoplenty", "huge_clover_petal")))
            .addRecipe(f -> CuttingBoardRecipeBuilder.cuttingRecipe(
                    Ingredient.of(modItem("biomesoplenty", "huge_clover_petal")),
                    Ingredient.of(ForgeTags.TOOLS_KNIVES),
                    DelightfulItems.CHOPPED_CLOVER.get(), 4)
                .build(f))
            .build(finished, Delightful.MODID, "chopped_clover_from_huge_clover_petal");
        ConditionalRecipe.builder()
            .addCondition(and(enabled("clover_honey"), modLoaded("biomesoplenty")))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                    Items.HONEY_BOTTLE, 3, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(Items.HONEY_BOTTLE, 2)
                .addIngredient(DelightfulItems.CHOPPED_CLOVER.get(), 4)
                .build(f))
            .build(finished, Delightful.MODID, "clover_honey");
        ConditionalRecipe.builder()
            .addCondition(and(enabled("venison_chops"), not(tagEmpty(DelightfulItemTags.RAW_VENISON))))
            .addRecipe(f -> CuttingBoardRecipeBuilder.cuttingRecipe(
                    Ingredient.of(DelightfulItemTags.RAW_VENISON),
                    Ingredient.of(ForgeTags.TOOLS_KNIVES),
                    DelightfulItems.VENISON_CHOPS.get(), 2)
                .build(f))
            .build(finished, Delightful.MODID, "venison");
        ConditionalRecipe.builder()
            .addCondition(and(enabled("cooked_venison_chops"), not(tagEmpty(DelightfulItemTags.COOKED_VENISON))))
            .addRecipe(f -> CuttingBoardRecipeBuilder.cuttingRecipe(
                    Ingredient.of(DelightfulItemTags.COOKED_VENISON),
                    Ingredient.of(ForgeTags.TOOLS_KNIVES),
                    DelightfulItems.COOKED_VENISON_CHOPS.get(), 2)
                .build(f))
            .build(finished, Delightful.MODID, "cooked_venison");
        ConditionalRecipe.builder()
            .addCondition(enabled("cactus_flesh"))
            .addRecipe(f ->
                CuttingBoardRecipeBuilder.cuttingRecipe(
                        Ingredient.of(Items.CACTUS),
                        Ingredient.of(ForgeTags.TOOLS_KNIVES),
                        DelightfulItems.CACTUS_FLESH.get(), 2)
                    .build(f))
            .build(finished, Delightful.MODID, "cactus_flesh");
        CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(DelightfulItems.MINI_MELON.get()),
                Ingredient.of(ForgeTags.TOOLS_KNIVES),
                Items.MELON_SLICE, 6)
            .build(finished);
        ShapelessRecipeBuilder.shapeless(Items.MELON_SLICE, 3)
            .requires(DelightfulItems.MINI_MELON.get())
            .unlockedBy("has_mini_melon", has(DelightfulItems.MINI_MELON.get()))
            .save(finished);
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
          .build(finished, FarmersDelight.MODID, "chocolate_pie");
        }

    private InventoryChangeTrigger.TriggerInstance has(ItemLike... items) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(items);
    }

    private void wrap(RecipeBuilder builder, String name, Consumer<FinishedRecipe> consumer, ICondition... conds) {
        ResourceLocation loc = new ResourceLocation(Delightful.MODID, name);
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

    private Item modItem(String modid, String path) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(modid, path));
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
        if (knife instanceof TaggedKnifeItem tki) {
            taggedKnife(tki, finished);
            return;
        }
        String path = Util.name(knife);
        ConditionalRecipe.builder()
            .addCondition(and(enabled(path)))
            .addRecipe(f -> ShapedRecipeBuilder.shaped(knife)
                .define('m', knife.getIngredient().get())
                .define('s', knife.getRod().get())
                .pattern("m")
                .pattern("s")
                .unlockedBy("has_" + path.replace("_knife", ""), inventoryTrigger(ItemPredicate.Builder.item()
                    .of(knife.getIngredient().get().getItems()[0].getItem()).build()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "knives/" + path);
    }

    private void taggedKnife(TaggedKnifeItem knife, Consumer<FinishedRecipe> finished) {
        if (knife.isSmithing()) {
            knifeSmith(knife, finished);
            return;
        }
        if (knife.getTag() == null) {
            return;
        }
        /*Arrays.stream(Nugget.values()).forEach(mod -> {
            String metal = knife.getTag().getPath().replace("_knife", "").replace("ingots/", "").trim();
            if (mod.getMetals().contains(metal)) {
                knifeSmeltAndBlast(knife, metal, new ResourceLocation(mod.getModid(), mod.formatMetal(metal)), finished);
            }
        });*/
        String path = Util.name(knife);
        var add = ItemTags.create(knife.getTag());
        var cond = (knife instanceof CompatKnifeItem cki) ?
            and(enabled(path), modLoaded(cki.getModid()), not(tagEmpty(add))) :
            and(enabled(path), not(tagEmpty(add)));
        ConditionalRecipe.builder()
            .addCondition(cond)
            .addRecipe(f -> ShapedRecipeBuilder.shaped(knife)
                .define('m', knife.getIngredient().get())
                .define('s', knife.getRod().get())
                .pattern("m")
                .pattern("s")
                .unlockedBy("has_" + path.replace("_knife", ""), inventoryTrigger(ItemPredicate.Builder.item()
                    .of(ItemTags.create(knife.getTag())).build()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "knives/" + path);
    }

    private void knifeSmeltAndBlast(DelightfulKnifeItem knife, String metal, ResourceLocation nugget, Consumer<FinishedRecipe> finished) {
        ConditionalRecipe.builder()
            .addCondition(and(enabled(Util.name(knife)), itemExists(nugget.getNamespace(), nugget.getPath())))
            .addRecipe(f -> SimpleCookingRecipeBuilder.smelting(Ingredient.of(knife), Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(nugget)), 0.1F, 200)
                .unlockedBy("has_" + metal + "_knife", InventoryChangeTrigger.TriggerInstance.hasItems(knife))
                .save(f, new ResourceLocation(Delightful.MODID, "knives/smelting/" + metal + "_" + nugget.getNamespace())))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "knives/smelting/" + metal + "_" + nugget.getNamespace());
        ConditionalRecipe.builder()
            .addCondition(and(enabled(Util.name(knife)), itemExists(nugget.getNamespace(), nugget.getPath())))
            .addRecipe(f -> SimpleCookingRecipeBuilder.blasting(Ingredient.of(knife), Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(nugget)), 0.1F, 100)
                .unlockedBy("has_" + metal + "_knife", InventoryChangeTrigger.TriggerInstance.hasItems(knife))
                .save(f, new ResourceLocation(Delightful.MODID, "knives/blasting/" + metal + "_" + nugget.getNamespace())))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "knives/blasting/" + metal + "_" + nugget.getNamespace());
    }

    private void knifeSmith(TaggedKnifeItem knife, Consumer<FinishedRecipe> finished) {
        String path = Util.name(knife);
        var add = ItemTags.create(knife.getTag());
        ConditionalRecipe.builder()
            .addCondition(and(enabled(path), not(tagEmpty(add))))
            .addRecipe(f -> UpgradeRecipeBuilder.smithing(knife.getIngredient().get(), Ingredient.of(add), knife)
                .unlocks("has_metal", inventoryTrigger(ItemPredicate.Builder.item().of(add).build()))
                .save(f, new ResourceLocation(Delightful.MODID, path + "_smithing")))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "knives/" + path + "_smithing");
    }

    private EnabledCondition enabled(String name) {
        return new EnabledCondition(name);
    }
}
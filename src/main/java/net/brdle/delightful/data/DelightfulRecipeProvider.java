package net.brdle.delightful.data;

import com.farmersrespite.core.registry.FRItems;
import com.farmersrespite.data.builder.KettleRecipeBuilder;
import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.block.DelightfulCabinetBlock;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.brdle.delightful.common.config.EnabledCondition;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import net.brdle.delightful.common.item.knife.DelightfulKnifeItem;
import net.brdle.delightful.common.item.knife.TaggedKnifeItem;
import net.brdle.delightful.common.tag.DelightfulItemTags;
import net.brdle.delightful.compat.nuggets.Nugget;
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
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.NBTIngredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.onvoid.rottenleather.common.RottenLeatherItems;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;
import vectorwing.farmersdelight.data.recipe.CookingRecipes;
import java.util.Arrays;
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
        knifeSmeltAndBlast((DelightfulKnifeItem) DelightfulItems.BONE_KNIFE.get(), "bone/knife", Items.BONE_MEAL.getRegistryName(), finished);

        // Foods
        // Cooking
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
            .addCondition(and(enabled("nut_butter_and_jelly_sandwich"), not(tagEmpty(DelightfulItemTags.NUTS))))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.NUT_BUTTER_AND_JELLY_SANDWICH.get())
                .requires(ForgeTags.BREAD)
                .requires(DelightfulItems.NUT_BUTTER_BOTTLE.get())
                .requires(DelightfulItemTags.JELLY)
                .unlockedBy("has_nut_butter", has(DelightfulItems.NUT_BUTTER_BOTTLE.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/nut_butter_and_jelly_sandwich");
        ConditionalRecipe.builder()
            .addCondition(enabled("cheeseburger"))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.CHEESEBURGER.get())
                .requires(ForgeTags.BREAD)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(ForgeTags.CROPS_TOMATO)
                .requires(ForgeTags.CROPS_ONION)
                .unlockedBy("has_beef_patty_and_cheese", has(ModItems.BEEF_PATTY.get(), Items.MILK_BUCKET))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/cheeseburger");
        ConditionalRecipe.builder()
            .addCondition(enabled("cheeseburger"))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.CHEESEBURGER.get())
                .requires(ModItems.HAMBURGER.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .unlockedBy("has_hamburger", has(ModItems.HAMBURGER.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/cheeseburger_from_hamburger");
        ConditionalRecipe.builder()
            .addCondition(enabled("deluxe_cheeseburger"))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.DELUXE_CHEESEBURGER.get())
                .requires(ForgeTags.BREAD)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .requires(ModItems.COOKED_BACON.get())
                .requires(ForgeTags.SALAD_INGREDIENTS)
                .requires(ForgeTags.CROPS_TOMATO)
                .requires(ForgeTags.CROPS_ONION)
                .unlockedBy("has_bacon", has(ModItems.COOKED_BACON.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/deluxe_cheeseburger");
        ConditionalRecipe.builder()
            .addCondition(enabled("deluxe_cheeseburger"))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.DELUXE_CHEESEBURGER.get())
                .requires(DelightfulItems.CHEESEBURGER.get())
                .requires(ModItems.BEEF_PATTY.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .requires(ModItems.COOKED_BACON.get())
                .unlockedBy("has_cheeseburger", has(DelightfulItems.CHEESEBURGER.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/deluxe_cheeseburger_from_cheeseburger");
        ConditionalRecipe.builder()
            .addCondition(enabled("marshmallow_stick"))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.MARSHMALLOW_STICK.get(), 2)
                .requires(DelightfulItemTags.SUGAR)
                .requires(DelightfulItemTags.WATER)
                .requires(Tags.Items.RODS_WOODEN)
                .requires(Tags.Items.RODS_WOODEN)
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/marshmallow_stick");
        ConditionalRecipe.builder()
            .addCondition(enabled("marshmallow_stick"))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.MARSHMALLOW_STICK.get(), 2)
                .requires(DelightfulItemTags.SUGAR)
                .requires(NBTIngredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)))
                .requires(Tags.Items.RODS_WOODEN)
                .requires(Tags.Items.RODS_WOODEN)
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/marshmallow_stick_from_water_bottle");
        ConditionalRecipe.builder()
            .addCondition(enabled("smore"))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.SMORE.get())
                .requires(ModItems.HONEY_COOKIE.get())
                .requires(Items.COCOA_BEANS)
                .requires(ForgeTags.MILK)
                .requires(DelightfulItemTags.SUGAR)
                .requires(DelightfulItems.COOKED_MARSHMALLOW_STICK.get())
                .requires(ModItems.HONEY_COOKIE.get())
                .unlockedBy("has_cooked_marshmallow_stick", has(DelightfulItems.COOKED_MARSHMALLOW_STICK.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/smore");
        ConditionalRecipe.builder()
            .addCondition(and(enabled("prickly_pear_juice"), itemExists("ecologics", "cooked_prickly_pear")))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.PRICKLY_PEAR_JUICE.get())
                .requires(DelightfulItemTags.COOKED_PRICKLY_PEAR)
                .requires(DelightfulItemTags.COOKED_PRICKLY_PEAR)
                .requires(DelightfulItemTags.SUGAR)
                .requires(DelightfulItemTags.COOKED_PRICKLY_PEAR)
                .requires(DelightfulItemTags.COOKED_PRICKLY_PEAR)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_cooked_prickly_pear", has(modItem("ecologics", "cooked_prickly_pear")))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/prickly_pear_juice");
        ConditionalRecipe.builder()
            .addCondition(and(enabled("crab_rangoon"), not(tagEmpty(DelightfulItemTags.COOKED_CRAB))))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.CRAB_RANGOON.get())
                .requires(ModItems.WHEAT_DOUGH.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .requires(DelightfulItemTags.COOKED_CRAB)
                .unlockedBy("has_wheat_dough", has(ModItems.WHEAT_DOUGH.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/crab_rangoon");
        ConditionalRecipe.builder()
            .addCondition(and(enabled("chunkwich"), itemExists("rottenleather", "sweetened_chunk")))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.CHUNKWICH.get())
                .requires(ForgeTags.BREAD)
                .requires(RottenLeatherItems.SWEETENED_CHUNK.get())
                .unlockedBy("has_sweetened_chunk", has(RottenLeatherItems.SWEETENED_CHUNK.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/chunkwich");
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
            .addCondition(enabled("cooked_marshmallow_stick"))
            .addRecipe(f -> SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(DelightfulItems.MARSHMALLOW_STICK.get()),
                DelightfulItems.COOKED_MARSHMALLOW_STICK.get(), 0.5F, 600)
                .unlockedBy("has_marshmallow_stick", has(DelightfulItems.MARSHMALLOW_STICK.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/cooked_marshmallow_stick");
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
            .addCondition(and(enabled("azalea_tea"), itemExists("ecologics", "azalea_flower"), modLoaded("farmersrespite")))
            .addRecipe(f -> KettleRecipeBuilder.kettleRecipe(DelightfulItems.AZALEA_TEA.get(), 1, 2400, 0.35F, false, Items.GLASS_BOTTLE)
                .addIngredient(DelightfulItemTags.TEA_LEAVES_GREEN)
                .addIngredient(modItem("ecologics", "azalea_flower"))
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/kettle/azalea_tea");
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
          .addCondition(and(enabled("lavender_tea"), itemExists("biomesoplenty", "lavender"), modLoaded("farmersrespite")))
          .addRecipe(f -> KettleRecipeBuilder.kettleRecipe(DelightfulItems.LAVENDER_TEA.get(), 1, 2400, 0.35F, false, Items.GLASS_BOTTLE)
            .addIngredient(DelightfulItemTags.TEA_LEAVES_GREEN)
            .addIngredient(modItem("biomesoplenty", "lavender"))
            .build(f))
          .generateAdvancement()
          .build(finished, Delightful.MODID, "food/kettle/lavender_tea");
        ConditionalRecipe.builder()
            .addCondition(and(enabled("honey_glazed_walnut"), not(tagEmpty(DelightfulItemTags.NUTS_WALNUT))))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.HONEY_GLAZED_WALNUT.get(), 3)
                .requires(DelightfulItemTags.NUTS_WALNUT)
                .requires(DelightfulItemTags.NUTS_WALNUT)
                .requires(DelightfulItemTags.NUTS_WALNUT)
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy("has_honey", has(Items.HONEY_BOTTLE))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/honey_glazed_walnut");
        ConditionalRecipe.builder()
            .addCondition(enabled("matcha_latte"))
            .addRecipe(f ->  ShapelessRecipeBuilder.shapeless(DelightfulItems.MATCHA_LATTE.get(), 1)
                .requires(Items.GLASS_BOTTLE)
                .requires(ForgeTags.MILK)
                .requires(Items.HONEY_BOTTLE)
                .requires(DelightfulItems.MATCHA.get())
                .unlockedBy("has_matcha", has(DelightfulItems.MATCHA.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/matcha_latte");
        ConditionalRecipe.builder()
            .addCondition(enabled("berry_matcha_latte"))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.BERRY_MATCHA_LATTE.get(), 1)
                .requires(Items.GLASS_BOTTLE)
                .requires(DelightfulItemTags.FRUITS_BERRIES)
                .requires(ForgeTags.MILK)
                .requires(Items.HONEY_BOTTLE)
                .requires(DelightfulItems.MATCHA.get())
                .unlockedBy("has_matcha_latte", has(DelightfulItems.MATCHA_LATTE.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/berry_matcha_latte");
        ConditionalRecipe.builder()
            .addCondition(enabled("matcha"))
            .addRecipe(f -> SimpleCookingRecipeBuilder.smelting(Ingredient.of(DelightfulItemTags.TEA_LEAVES_GREEN),
                DelightfulItems.MATCHA.get(), 0.1F, 200)
                .unlockedBy("has_green_tea_leaf", has(DelightfulItems.GREEN_TEA_LEAF.get()))
                .save(f, new ResourceLocation(Delightful.MODID, "smelting/green_tea_leaf")))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "smelting/green_tea_leaf");
        ConditionalRecipe.builder()
            .addCondition(itemExists("farmersrespite", "green_tea_leaves"))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(FRItems.GREEN_TEA_LEAVES.get(), 2)
                .requires(DelightfulItems.GREEN_TEA_LEAF.get())
                .requires(DelightfulItems.GREEN_TEA_LEAF.get())
                .unlockedBy("has_green_tea_leaf", has(DelightfulItems.GREEN_TEA_LEAF.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "green_tea_leaves_from_green_tea_leaf");
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
          .addCondition(and(enabled("rock_candy"), itemExists("biomesoplenty", "rose_quartz_shard")))
          .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.ROCK_CANDY.get(), 1)
            .requires(modItem("biomesoplenty", "rose_quartz_shard"))
            .requires(modItem("biomesoplenty", "rose_quartz_shard"))
            .requires(DelightfulItemTags.SUGAR)
            .requires(Tags.Items.RODS_WOODEN)
            .unlockedBy("has_sugar", has(Items.SUGAR))
            .save(f))
          .generateAdvancement()
          .build(finished, Delightful.MODID, "food/rock_candy");
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
          .addCondition(itemExists("biomemakeover", "glowshroom_stew"))
          .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
            modItem("biomemakeover", "glowshroom_stew"), 1, CookingRecipes.NORMAL_COOKING, 0.35F, Items.BOWL)
            .addIngredient(modItem("biomemakeover", "purple_glowshroom"))
            .addIngredient(modItem("biomemakeover", "green_glowshroom"))
            .addIngredient(modItem("biomemakeover", "orange_glowshroom"))
            .build(f))
          .generateAdvancement()
          .build(finished, "biomemakeover", "glowshroom_stew");
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
          .addCondition(enabled("cactus_flesh"))
          .addRecipe(f ->
            CuttingBoardRecipeBuilder.cuttingRecipe(
              Ingredient.of(Items.CACTUS),
              Ingredient.of(ForgeTags.TOOLS_KNIVES),
              DelightfulItems.CACTUS_FLESH.get(), 2)
          .build(f))
        .build(finished, Delightful.MODID, "cactus_flesh");
        ConditionalRecipe.builder()
          .addCondition(and(enabled("cactus_flesh"), itemExists("quark", "cactus_block")))
          .addRecipe(f -> CuttingBoardRecipeBuilder.cuttingRecipe(
              Ingredient.of(modItem("quark", "cactus_block")),
              Ingredient.of(ForgeTags.TOOLS_KNIVES),
              DelightfulItems.CACTUS_FLESH.get(), 18)
            .build(f))
          .build(finished, Delightful.MODID, "cactus_flesh_from_cactus_block");
        ConditionalRecipe.builder()
          .addCondition(and(enabled("cactus_flesh"), itemExists("biomemakeover", "barrel_cactus")))
          .addRecipe(f -> CuttingBoardRecipeBuilder.cuttingRecipe(
              Ingredient.of(modItem("biomemakeover", "barrel_cactus")),
              Ingredient.of(ForgeTags.TOOLS_KNIVES),
              DelightfulItems.CACTUS_FLESH.get(), 1)
            .build(f))
          .build(finished, Delightful.MODID, "cactus_flesh_from_barrel_cactus");
        ConditionalRecipe.builder()
          .addCondition(and(enabled("cactus_flesh"), itemExists("biomemakeover", "barrel_cactus_flowered")))
          .addRecipe(f -> CuttingBoardRecipeBuilder.cuttingRecipe(
              Ingredient.of(modItem("biomemakeover", "barrel_cactus_flowered")),
              Ingredient.of(ForgeTags.TOOLS_KNIVES),
              DelightfulItems.CACTUS_FLESH.get(), 1)
            .build(f))
          .build(finished, Delightful.MODID, "cactus_flesh_from_barrel_cactus_flowered");
        ConditionalRecipe.builder()
          .addCondition(and(enabled("cactus_flesh"), itemExists("biomemakeover", "saguaro_cactus")))
          .addRecipe(f -> CuttingBoardRecipeBuilder.cuttingRecipe(
              Ingredient.of(modItem("biomemakeover", "saguaro_cactus")),
              Ingredient.of(ForgeTags.TOOLS_KNIVES),
              DelightfulItems.CACTUS_FLESH.get(), 2)
            .build(f))
          .build(finished, Delightful.MODID, "cactus_flesh_from_saguaro_cactus");
        ConditionalRecipe.builder()
          .addCondition(enabled("cactus_steak"))
          .addRecipe(f -> SimpleCookingRecipeBuilder.smelting(Ingredient.of(DelightfulItems.CACTUS_FLESH.get()),
              DelightfulItems.CACTUS_STEAK.get(), 0.1F, 200)
            .unlockedBy("has_cactus_flesh", has(DelightfulItems.CACTUS_FLESH.get()))
            .save(f, new ResourceLocation(Delightful.MODID, "smelting/cactus_flesh")))
          .generateAdvancement()
          .build(finished, Delightful.MODID, "smelting/cactus_flesh");
        ConditionalRecipe.builder()
          .addCondition(and(enabled("field_salad"), modLoaded("biomesoplenty")))
          .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.FIELD_SALAD.get(), 1)
            .requires(DelightfulItems.CHOPPED_CLOVER.get())
            .requires(DelightfulItems.CHOPPED_CLOVER.get())
            .requires(DelightfulItems.CACTUS_STEAK.get())
            .requires(Items.CARROT)
            .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
            .unlockedBy("has_chopped_clover", has(DelightfulItems.CHOPPED_CLOVER.get()))
            .save(f))
          .generateAdvancement()
          .build(finished, Delightful.MODID, "food/field_salad");
        ConditionalRecipe.builder()
          .addCondition(and(enabled("field_salad"), not(modLoaded("biomesoplenty"))))
          .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.FIELD_SALAD.get(), 1)
            .requires(Items.BOWL)
            .requires(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
            .requires(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
            .requires(DelightfulItems.CACTUS_STEAK.get())
            .requires(Items.CARROT)
            .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
            .unlockedBy("has_cactus_steak", has(DelightfulItems.CACTUS_STEAK.get()))
            .save(f))
          .generateAdvancement()
          .build(finished, Delightful.MODID, "food/field_salad_no_biomesoplenty");
        CuttingBoardRecipeBuilder.cuttingRecipe(
              Ingredient.of(DelightfulItems.MINI_MELON.get()),
              Ingredient.of(ForgeTags.TOOLS_KNIVES),
              Items.MELON_SLICE, 6)
          .build(finished);
        ConditionalRecipe.builder()
          .addCondition(enabled("salmonberry_sack"))
          .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.SALMONBERRY_SACK.get(), 1)
            .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
            .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
            .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
            .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
            .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
            .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
            .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
            .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
            .requires(DelightfulItemTags.FRUITS_SALMONBERRIES)
            .unlockedBy("has_salmonberries", has(DelightfulItems.SALMONBERRIES.get()))
            .save(f))
          .generateAdvancement()
          .build(finished, Delightful.MODID, "storage/salmonberries");
        ConditionalRecipe.builder()
          .addCondition(enabled("salmonberry_sack"))
          .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.SALMONBERRIES.get(), 9)
            .requires(DelightfulItems.SALMONBERRY_SACK.get())
            .unlockedBy("has_salmonberry_sack", has(DelightfulItems.SALMONBERRY_SACK.get()))
            .save(f))
          .generateAdvancement()
          .build(finished, Delightful.MODID, "storage/unpack_salmonberries");
        }

    private InventoryChangeTrigger.TriggerInstance has(ItemLike... items) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(items);
    }

    private Item modItem(String modid, String path) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(modid, path));
    }

    private void cabinet(DelightfulCabinetBlock block, Consumer<FinishedRecipe> finished) {
        String path = block.getRegistryName().getPath();
        ConditionalRecipe.builder()
            .addCondition(enabled(path))
            .addRecipe(f -> ShapedRecipeBuilder.shaped(block)
                .define('b', block.getIngredient().get())
                .define('c', ItemTags.create(new ResourceLocation("farmersdelight", "cabinets/wooden")))
                .pattern("bbb")
                .pattern("bcb")
                .pattern("bbb")
                .unlockedBy("has_cabinet", inventoryTrigger(ItemPredicate.Builder.item()
                    .of(ItemTags.create(new ResourceLocation("farmersdelight", "cabinets"))).build()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "cabinets/" + path);
    }

    private void knife(DelightfulKnifeItem knife, Consumer<FinishedRecipe> finished) {
        if (knife instanceof TaggedKnifeItem tki) {
            taggedKnife(tki, finished);
            return;
        }
        String path = knife.getRegistryName().getPath();
        ConditionalRecipe.builder()
            .addCondition(and(enabled(path)))
            .addRecipe(f -> ShapedRecipeBuilder.shaped(knife)
                .define('m', knife.getIngredient().get())
                .define('s', Tags.Items.RODS_WOODEN)
                .pattern(" m")
                .pattern("s ")
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
        Arrays.stream(Nugget.values()).forEach(mod -> {
            String metal = knife.getTag().getPath().replace("_knife", "").replace("ingots/", "").trim();
            if (mod.getMetals().contains(metal)) {
                knifeSmeltAndBlast(knife, metal, new ResourceLocation(mod.getModid(), mod.formatMetal(metal)), finished);
            }
        });
        String path = knife.getRegistryName().getPath();
        var add = ItemTags.create(knife.getTag());
        var cond = (knife instanceof CompatKnifeItem cki) ?
                and(enabled(path), modLoaded(cki.getModid()), not(tagEmpty(add))) :
                and(enabled(path), not(tagEmpty(add)));
        ConditionalRecipe.builder()
            .addCondition(cond)
            .addRecipe(f -> ShapedRecipeBuilder.shaped(knife)
                .define('m', knife.getIngredient().get())
                .define('s', Tags.Items.RODS_WOODEN)
                .pattern(" m")
                .pattern("s ")
                .unlockedBy("has_" + path.replace("_knife", ""), inventoryTrigger(ItemPredicate.Builder.item()
                    .of(ItemTags.create(knife.getTag())).build()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "knives/" + path);
    }

    private void knifeSmeltAndBlast(DelightfulKnifeItem knife, String metal, ResourceLocation nugget, Consumer<FinishedRecipe> finished) {
        ConditionalRecipe.builder()
            .addCondition(and(enabled(knife.getRegistryName().getPath()), itemExists(nugget.getNamespace(), nugget.getPath())))
            .addRecipe(f -> SimpleCookingRecipeBuilder.smelting(Ingredient.of(knife), Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(nugget)), 0.1F, 200)
                .unlockedBy("has_" + metal + "_knife", InventoryChangeTrigger.TriggerInstance.hasItems(knife))
                .save(f, new ResourceLocation(Delightful.MODID, "knives/smelting/" + metal + "_" + nugget.getNamespace())))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "knives/smelting/" + metal + "_" + nugget.getNamespace());
        ConditionalRecipe.builder()
            .addCondition(and(enabled(knife.getRegistryName().getPath()), itemExists(nugget.getNamespace(), nugget.getPath())))
            .addRecipe(f -> SimpleCookingRecipeBuilder.blasting(Ingredient.of(knife), Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(nugget)), 0.1F, 100)
                .unlockedBy("has_" + metal + "_knife", InventoryChangeTrigger.TriggerInstance.hasItems(knife))
                .save(f, new ResourceLocation(Delightful.MODID, "knives/blasting/" + metal + "_" + nugget.getNamespace())))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "knives/blasting/" + metal + "_" + nugget.getNamespace());
    }

    private void knifeSmith(TaggedKnifeItem knife, Consumer<FinishedRecipe> finished) {
        String path = knife.getRegistryName().getPath();
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
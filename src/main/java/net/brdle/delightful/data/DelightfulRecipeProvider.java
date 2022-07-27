package net.brdle.delightful.data;

import com.farmersrespite.core.registry.FRItems;
import com.farmersrespite.data.builder.KettleRecipeBuilder;
import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.block.DelightfulCabinetBlock;
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
            .addCondition(new EnabledCondition("ender_nectar"))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                DelightfulItems.ENDER_NECTAR.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(Items.ENDER_EYE)
                .addIngredient(ForgeTags.MILK)
                .addIngredient(DelightfulItemTags.SUGAR)
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/cooking/ender_nectar");
        ConditionalRecipe.builder()
            .addCondition(new EnabledCondition("animal_oil_bottle"))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.ANIMAL_OIL_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItems.ANIMAL_FAT.get())
                .addIngredient(DelightfulItems.ANIMAL_FAT.get())
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "cooking/animal_oil_bottle");
        ConditionalRecipe.builder()
            .addCondition(new EnabledCondition("jelly_bottle"))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.JELLY_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItemTags.FRUITS_SWEET)
                .addIngredient(DelightfulItemTags.FRUITS_SWEET)
                .addIngredient(DelightfulItemTags.SUGARS)
                .addIngredient(DelightfulItemTags.SUGARS)
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/cooking/jelly_bottle");
        ConditionalRecipe.builder()
            .addCondition(and(new EnabledCondition("nut_butter_bottle"), not(tagEmpty(DelightfulItemTags.NUTS))))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.NUT_BUTTER_BOTTLE.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(DelightfulItemTags.NUTS)
                .addIngredient(DelightfulItemTags.SUGAR)
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/cooking/nut_butter_bottle");
        ConditionalRecipe.builder()
            .addCondition(and(new EnabledCondition("nut_butter_and_jelly_sandwich"), not(tagEmpty(DelightfulItemTags.NUTS))))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.NUT_BUTTER_AND_JELLY_SANDWICH.get())
                .requires(ForgeTags.BREAD)
                .requires(DelightfulItems.NUT_BUTTER_BOTTLE.get())
                .requires(DelightfulItems.JELLY_BOTTLE.get())
                .unlockedBy("has_nut_butter", has(DelightfulItems.NUT_BUTTER_BOTTLE.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/nut_butter_and_jelly_sandwich");
        ConditionalRecipe.builder()
            .addCondition(new EnabledCondition("cheeseburger"))
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
            .addCondition(new EnabledCondition("cheeseburger"))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.CHEESEBURGER.get())
                .requires(ModItems.HAMBURGER.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .unlockedBy("has_hamburger", has(ModItems.HAMBURGER.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/cheeseburger_from_hamburger");
        ConditionalRecipe.builder()
            .addCondition(new EnabledCondition("deluxe_cheeseburger"))
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
            .addCondition(new EnabledCondition("deluxe_cheeseburger"))
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
            .addCondition(new EnabledCondition("marshmallow_stick"))
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
            .addCondition(new EnabledCondition("marshmallow_stick"))
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
            .addCondition(new EnabledCondition("smore"))
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
            .addCondition(and(new EnabledCondition("prickly_pear_juice"), itemExists("ecologics", "cooked_prickly_pear")))
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
            .addCondition(and(new EnabledCondition("crab_rangoon"), not(tagEmpty(DelightfulItemTags.COOKED_CRAB))))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.CRAB_RANGOON.get())
                .requires(ModItems.WHEAT_DOUGH.get())
                .requires(DelightfulItemTags.CHEESE_OR_MILK)
                .requires(DelightfulItemTags.COOKED_CRAB)
                .unlockedBy("has_wheat_dough", has(ModItems.WHEAT_DOUGH.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/crab_rangoon");
        ConditionalRecipe.builder()
            .addCondition(and(new EnabledCondition("chunkwich"), itemExists("rottenleather", "sweetened_chunk")))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(DelightfulItems.CHUNKWICH.get())
                .requires(ForgeTags.BREAD)
                .requires(RottenLeatherItems.SWEETENED_CHUNK.get())
                .unlockedBy("has_sweetened_chunk", has(RottenLeatherItems.SWEETENED_CHUNK.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/chunkwich");
        ConditionalRecipe.builder()
            .addCondition(and(new EnabledCondition("chunk_nugget"), itemExists("rottenleather", "sweetened_chunk")))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                DelightfulItems.CHUNK_NUGGET.get(), 6, CookingRecipes.FAST_COOKING, 0.25F)
                .addIngredient(ForgeTags.BREAD)
                .addIngredient(RottenLeatherItems.SWEETENED_CHUNK.get())
                .addIngredient(RottenLeatherItems.SWEETENED_CHUNK.get())
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/chunk_nugget");
        ConditionalRecipe.builder()
            .addCondition(new EnabledCondition("cooked_marshmallow_stick"))
            .addRecipe(f -> SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(DelightfulItems.MARSHMALLOW_STICK.get()),
                DelightfulItems.COOKED_MARSHMALLOW_STICK.get(), 0.5F, 600)
                .unlockedBy("has_marshmallow_stick", has(DelightfulItems.MARSHMALLOW_STICK.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/cooked_marshmallow_stick");
        ConditionalRecipe.builder()
            .addCondition(and(new EnabledCondition("azalea_tea"), itemExists("ecologics", "azalea_flower")))
            .addRecipe(f -> CookingPotRecipeBuilder.cookingPotRecipe(
                    DelightfulItems.AZALEA_TEA.get(), 1, CookingRecipes.NORMAL_COOKING, 0.35F)
                .addIngredient(NBTIngredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER)))
                .addIngredient(DelightfulItemTags.TEA_LEAVES_GREEN)
                .addIngredient(modItem("ecologics", "azalea_flower"))
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/azalea_tea_from_water_bottle");
        ConditionalRecipe.builder()
            .addCondition(and(new EnabledCondition("azalea_tea"), itemExists("ecologics", "azalea_flower"), modLoaded("farmersrespite")))
            .addRecipe(f -> KettleRecipeBuilder.kettleRecipe(DelightfulItems.AZALEA_TEA.get(), 1, 2400, 0.35F, false, Items.GLASS_BOTTLE)
                .addIngredient(DelightfulItemTags.TEA_LEAVES_GREEN)
                .addIngredient(modItem("ecologics", "azalea_flower"))
                .build(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "food/kettle/azalea_tea_from_water_bottle");
        ConditionalRecipe.builder()
            .addCondition(and(new EnabledCondition("honey_glazed_walnut"), not(tagEmpty(DelightfulItemTags.NUTS_WALNUT))))
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
            .addCondition(new EnabledCondition("matcha_latte"))
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
            .addCondition(new EnabledCondition("berry_matcha_latte"))
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
            .addCondition(new EnabledCondition("matcha"))
            .addRecipe(f -> SimpleCookingRecipeBuilder.smelting(Ingredient.of(DelightfulItemTags.TEA_LEAVES_GREEN),
                DelightfulItems.MATCHA.get(), 0.1F, 200)
                .unlockedBy("has_green_tea_leaf", has(DelightfulItems.GREEN_TEA_LEAF.get()))
                .save(f, new ResourceLocation(Delightful.MODID, "smelting/green_tea_leaf")))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "smelting/green_tea_leaf");
        ConditionalRecipe.builder()
            .addCondition(new EnabledCondition("matcha"))
            .addRecipe(f -> SimpleCookingRecipeBuilder.blasting(Ingredient.of(DelightfulItemTags.TEA_LEAVES_GREEN),
                    DelightfulItems.MATCHA.get(), 0.1F, 100)
                .unlockedBy("has_green_tea_leaf", has(DelightfulItems.GREEN_TEA_LEAF.get()))
                .save(f, new ResourceLocation(Delightful.MODID, "blasting/green_tea_leaf")))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "blasting/green_tea_leaf");
        ConditionalRecipe.builder()
            .addCondition(itemExists("farmersrespite", "green_tea_leaves"))
            .addRecipe(f -> ShapelessRecipeBuilder.shapeless(FRItems.GREEN_TEA_LEAVES.get(), 2)
                .requires(DelightfulItems.GREEN_TEA_LEAF.get())
                .requires(DelightfulItems.GREEN_TEA_LEAF.get())
                .unlockedBy("has_green_tea_leaf", has(DelightfulItems.GREEN_TEA_LEAF.get()))
                .save(f))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "green_tea_leaves_from_green_tea_leaf");
        CookingPotRecipeBuilder.cookingPotRecipe(
              Items.MILK_BUCKET, 1, CookingRecipes.NORMAL_COOKING, 0.35F, Items.BUCKET)
            .addIngredient(DelightfulItemTags.WATER)
            .addIngredient(DelightfulItemTags.NUTS)
            .addIngredient(DelightfulItemTags.NUTS)
            .addIngredient(DelightfulItemTags.SUGAR)
            .build(finished);
        ShapedRecipeBuilder.shaped(Items.TORCH, 8)
          .define('o', DelightfulItems.ANIMAL_OIL_BOTTLE.get())
          .define('s', Tags.Items.RODS_WOODEN)
          .pattern("o")
          .pattern("s")
          .unlockedBy("has_oil_bottle", inventoryTrigger(ItemPredicate.Builder.item()
            .of(DelightfulItems.ANIMAL_OIL_BOTTLE.get()).build()))
          .save(finished, "torch_from_animal_oil_bottle");
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
            .addCondition(new EnabledCondition(path))
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
            .addCondition(and(new EnabledCondition(path)))
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
                and(new EnabledCondition(path), modLoaded(cki.getModid()), not(tagEmpty(add))) :
                and(new EnabledCondition(path), not(tagEmpty(add)));
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
            .addCondition(and(new EnabledCondition(knife.getRegistryName().getPath()), itemExists(nugget.getNamespace(), nugget.getPath())))
            .addRecipe(f -> SimpleCookingRecipeBuilder.smelting(Ingredient.of(knife), Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(nugget)), 0.1F, 200)
                .unlockedBy("has_" + metal + "_knife", InventoryChangeTrigger.TriggerInstance.hasItems(knife))
                .save(f, new ResourceLocation(Delightful.MODID, "knives/smelting/" + metal + "_" + nugget.getNamespace())))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "knives/smelting/" + metal + "_" + nugget.getNamespace());
        ConditionalRecipe.builder()
            .addCondition(and(new EnabledCondition(knife.getRegistryName().getPath()), itemExists(nugget.getNamespace(), nugget.getPath())))
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
            .addCondition(and(new EnabledCondition(path), not(tagEmpty(add))))
            .addRecipe(f -> UpgradeRecipeBuilder.smithing(knife.getIngredient().get(), Ingredient.of(add), knife)
                .unlocks("has_metal", inventoryTrigger(ItemPredicate.Builder.item().of(add).build()))
                .save(f, new ResourceLocation(Delightful.MODID, path + "_smithing")))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "knives/" + path + "_smithing");
    }
}
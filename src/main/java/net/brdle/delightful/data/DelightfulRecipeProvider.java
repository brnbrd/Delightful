package net.brdle.delightful.data;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.block.DelightfulCabinetBlock;
import net.brdle.delightful.common.config.EnabledCondition;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import net.brdle.delightful.common.item.knife.DelightfulKnifeItem;
import net.brdle.delightful.common.item.knife.TaggedKnifeItem;
import net.brdle.delightful.compat.nuggets.Nugget;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
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
    }

    private void cabinet(DelightfulCabinetBlock block, Consumer<FinishedRecipe> finished) {
        String path = block.getRegistryName().getPath();
        ConditionalRecipe.builder()
            .addCondition(new EnabledCondition(path))
            .addRecipe(f -> ShapedRecipeBuilder.shaped(block)
                .define('b', block.getIngredient().get())
                .define('c', TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("farmersdelight", "cabinets/wooden")))
                .pattern("bbb")
                .pattern("bcb")
                .pattern("bbb")
                .unlockedBy("has_cabinet", inventoryTrigger(ItemPredicate.Builder.item()
                    .of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("farmersdelight", "cabinets"))).build()))
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
        var add = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), knife.getTag());
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
                                .of(TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), knife.getTag())).build()))
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
        var add = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), knife.getTag());
        ConditionalRecipe.builder()
            .addCondition(and(new EnabledCondition(path), not(tagEmpty(add))))
            .addRecipe(f -> UpgradeRecipeBuilder.smithing(knife.getIngredient().get(), Ingredient.of(add), knife)
                .unlocks("has_metal", inventoryTrigger(ItemPredicate.Builder.item().of(add).build()))
                .save(f, new ResourceLocation(Delightful.MODID, path + "_smithing")))
            .generateAdvancement()
            .build(finished, Delightful.MODID, "knives/" + path + "_smithing");
    }
}
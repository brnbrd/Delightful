package net.brnbrd.delightful.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.vanilla.IJeiAnvilRecipe;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeRegistration;
import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.IConfigured;
import net.brnbrd.delightful.common.item.knife.DelightfulKnifeItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.common.utility.TextUtils;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@JeiPlugin
@ParametersAreNonnullByDefault
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin
{
    private static final ResourceLocation ID = Util.rl(Delightful.MODID, "jei_plugin");
    private static final Minecraft MC = Minecraft.getInstance();

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        // Remove all disabled Items from JEI
        List<ItemStack> remove = DelightfulItems.ITEMS.getEntries().stream()
            .filter(i -> (i.get() instanceof IConfigured c) ? !c.isEnabled() : !Util.enabled(i)) // Keep items not enabled
            .map(Util::gs) // Get ItemStack
            .toList();
        registration.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, remove);

        registerAnvilRecipes(registration);

        // Add Knife translations
        DelightfulItems.ITEMS.getEntries().stream()
            .map(RegistryObject::get)
            .filter(k -> k instanceof DelightfulKnifeItem && ((DelightfulKnifeItem) k).isEnabled())
            .map(ItemStack::new)
            .forEach((k -> registration.addIngredientInfo(k, VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.knife"))));

        // Add other descriptions
        if (Util.enabled(DelightfulItems.GREEN_TEA_LEAF) && !Mods.loaded(Mods.FR)) {
            registration.addIngredientInfo(DelightfulItems.GREEN_TEA_LEAF.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".green_tea_leaf.desc"));
        }
        if (Util.enabled(DelightfulItems.ACORN)) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.ACORN), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".acorn.desc"));
        }
        if (Util.enabled(DelightfulItems.SALMONBERRIES)) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.SALMONBERRIES), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".salmonberries.desc"));
        }
        if (Util.enabled(DelightfulItems.MINI_MELON)) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.MINI_MELON), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".mini_melon.desc"));
        }
        if (Util.enabled(DelightfulItems.CANTALOUPE)) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.CANTALOUPE), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".cantaloupe.desc").append(" ").append(Component.translatable(Delightful.MODID + ".sliceable.desc")));
        }
        if (Util.enabled(DelightfulItems.ANIMAL_FAT)) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.ANIMAL_FAT), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".animal_fat.desc"));
        }
        if (Util.enabled(DelightfulItems.ANIMAL_OIL_BOTTLE)) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.ANIMAL_OIL_BOTTLE), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".animal_oil_bottle.desc"));
        }
        registration.addIngredientInfo(Items.MELON.getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".sliceable.desc"));
        registration.addIngredientInfo(Items.PUMPKIN.getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".sliceable.desc"));
    }

    private void registerAnvilRecipes(@Nonnull IRecipeRegistration registration) {
        IVanillaRecipeFactory fact = registration.getVanillaRecipeFactory();
        List<IJeiAnvilRecipe> repairs = new ArrayList<>();
        DelightfulItems.ITEMS.getEntries().stream() // Delightful knives
            .map(RegistryObject::get)
            .filter(k -> (
                k instanceof DelightfulKnifeItem &&
                    ((DelightfulKnifeItem) k).isEnabled() &&
                    ((DelightfulKnifeItem) k).getTag() != null
            )).map(ItemStack::new)
            .forEach((k -> addAnvils(repairs, fact, k)));
        ForgeRegistries.ITEMS.tags().getTag(ForgeTags.TOOLS_KNIVES) // forge:tools/knives (filtering out my own)
            .stream()
            .filter(k -> !(k instanceof DelightfulKnifeItem))
            .map(ItemStack::new)
            .forEach((k -> addAnvils(repairs, fact, k)));
        registration.addRecipes(RecipeTypes.ANVIL, repairs);
    }

    private void addAnvils(
        List<IJeiAnvilRecipe> repairs, // This list will be mutated
        IVanillaRecipeFactory factory,
        ItemStack input // Should be a fresh ItemStack that can be modified
    ) {
        if (input.getItem() instanceof TieredItem knifeItem) {
            ItemStack out = input.copy();
            ItemStack combined = input.copy();
            int max = input.getMaxDamage();
            input.setDamageValue(max - 1);
            out.setDamageValue(max - (int) Math.ceil(max / 4D));
            combined.setDamageValue(max - (int) Math.ceil(max / 2D));
            repairs.add(factory.createAnvilRecipe(
                out,
                Collections.singletonList(out),
                Collections.singletonList(combined))
            );
            repairs.add(factory.createAnvilRecipe(
                input,
                Arrays.asList(knifeItem.getTier().getRepairIngredient().getItems()),
                Collections.singletonList(out))
            );
        }
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ID;
    }
}
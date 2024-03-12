package net.brnbrd.delightful.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.IConfigured;
import net.brnbrd.delightful.common.item.food.GreenTeaLeavesItem;
import net.brnbrd.delightful.common.item.knife.DelightfulKnifeItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.utility.TextUtils;
import javax.annotation.ParametersAreNonnullByDefault;
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
        registration.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK,
            DelightfulItems.ITEMS.getEntries().stream()
                .filter(i -> (i.get() instanceof IConfigured c) ? !c.enabled() : !Util.enabled(i)) // Keep items not enabled
                .map(Util::gs) // Get ItemStack
                .toList()
        );

        // Add Knife translations
        DelightfulItems.ITEMS.getEntries().stream()
            .map(RegistryObject::get)
            .filter(k -> k instanceof DelightfulKnifeItem dk && dk.enabled())
            .map(ItemStack::new)
            .forEach(i -> registration.addIngredientInfo(i, VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.knife")));

        // Add other descriptions
        if (Util.enabled(DelightfulItems.SALMONBERRIES)) {
            registration.addIngredientInfo(
                List.of(Util.gs(DelightfulItems.SALMONBERRIES), Util.gs(DelightfulItems.WILD_SALMONBERRIES)),
                VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".salmonberries.desc")
            );
        }
        if (((GreenTeaLeavesItem)DelightfulItems.GREEN_TEA_LEAF.get()).enabled()) {
            registration.addIngredientInfo(DelightfulItems.GREEN_TEA_LEAF.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".green_tea_leaf.desc"));
        }
        if (Util.enabled(DelightfulItems.ACORN)) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.ACORN), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".acorn.desc"));
        }
        if (Util.enabled(DelightfulItems.MINI_MELON)) {
            registration.addIngredientInfo(
                List.of(Items.MELON_SLICE.getDefaultInstance(), Util.gs(DelightfulItems.MINI_MELON)),
                VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".mini_melon.desc"));
        }
        if (Util.enabled(DelightfulItems.CANTALOUPE)) {
            registration.addIngredientInfo(
                List.of(Util.gs(DelightfulItems.CANTALOUPE_SLICE), Util.gs(DelightfulItems.CANTALOUPE)),
                VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".cantaloupe.desc").append(" ").append(Component.translatable(Delightful.MODID + ".sliceable.desc")));
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

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ID;
    }
}
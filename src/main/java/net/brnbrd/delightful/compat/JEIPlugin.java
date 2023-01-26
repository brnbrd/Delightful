package net.brnbrd.delightful.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.DelightfulConfig;
import net.brnbrd.delightful.common.item.CompatItem;
import net.brnbrd.delightful.common.item.DelightfulItems;
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
                .filter(i -> {
                    if (i.get() instanceof DelightfulKnifeItem k) {
                       return !k.isEnabled(); // Keep knife if it is disabled
                    } else if (i.get() instanceof CompatItem c) {
                        return !c.isEnabled(); // Keep item if it is disabled
                    }
                    return !(DelightfulConfig.verify(i)); // Keep only Items that are not enabled in the config
                })
                .map(Util::gs) // Get ItemStack
                .toList());
        // Add Knife translations
        DelightfulItems.ITEMS.getEntries().stream()
            .map(RegistryObject::get)
            .filter(k -> k instanceof DelightfulKnifeItem && ((DelightfulKnifeItem) k).isEnabled())
            .map(ItemStack::new)
            .forEach((k -> registration.addIngredientInfo(k, VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.knife"))));
        if (DelightfulConfig.verify("green_tea_leaf") && !Mods.loaded(Mods.FR)) {
            registration.addIngredientInfo(DelightfulItems.GREEN_TEA_LEAF.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".green_tea_leaf.desc"));
        }
        if (DelightfulConfig.verify("acorn")) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.ACORN), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".acorn.desc"));
        }
        if (DelightfulConfig.verify("salmonberries")) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.SALMONBERRIES), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".salmonberries.desc"));
        }
        if (DelightfulConfig.verify("mini_melon")) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.MINI_MELON), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".mini_melon.desc"));
        }
        if (DelightfulConfig.verify("cantaloupe")) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.CANTALOUPE), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".cantaloupe.desc").append(" ").append(Component.translatable(Delightful.MODID + ".sliceable.desc")));
        }
        if (DelightfulConfig.verify("animal_fat")) {
            registration.addIngredientInfo(Util.gs(DelightfulItems.ANIMAL_FAT), VanillaTypes.ITEM_STACK, Component.translatable(Delightful.MODID + ".animal_fat.desc"));
        }
        if (DelightfulConfig.verify("animal_oil_bottle")) {
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
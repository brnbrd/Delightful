package net.brdle.delightful.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.MethodsReturnNonnullByDefault;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.brdle.delightful.common.item.DelightfulItems;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.utility.TextUtils;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;

@JeiPlugin
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin
{
    private static final ResourceLocation ID = new ResourceLocation(FarmersDelight.MODID, "jei_plugin");
    private static final Minecraft MC = Minecraft.getInstance();

    public static final Item[] knives = new Item[]{
        DelightfulItems.COPPER_KNIFE.get(),
        DelightfulItems.STEEL_KNIFE.get(),
        DelightfulItems.ENDERITE_KNIFE.get(),
        DelightfulItems.OBSIDIAN_INFUSED_ENDERITE_KNIFE.get(),
        DelightfulItems.BRONZE_KNIFE.get(),
        DelightfulItems.LAPIS_LAZULI_KNIFE.get(),
        DelightfulItems.OSMIUM_KNIFE.get(),
        DelightfulItems.REFINED_OBSIDIAN_KNIFE.get(),
        DelightfulItems.REFINED_OBSIDIAN_KNIFE.get()
    };

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        Arrays.stream(knives)
            .map(ItemStack::new)
            .forEach((k -> registration.addIngredientInfo(k, VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.knife"))));
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }
}
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
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.utility.TextUtils;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;

@JeiPlugin
@ParametersAreNonnullByDefault
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin
{
    private static final ResourceLocation ID = new ResourceLocation(FarmersDelight.MODID, "jei_plugin");
    private static final Minecraft MC = Minecraft.getInstance();

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        DelightfulItems.knives.stream()
            .map(RegistryObject::get)
            .map(ItemStack::new)
            .forEach((k -> registration.addIngredientInfo(k, VanillaTypes.ITEM_STACK, TextUtils.getTranslation("jei.info.knife"))));
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }
}
package net.brdle.delightful.common;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.config.EnabledCondition;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.world.DelightfulWildCropGeneration;
import net.brdle.delightful.compat.RootsCompat;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid=Delightful.MODID)
public class Events {

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent e) {
        e.enqueueWork(() -> {
            DelightfulWildCropGeneration.registerWildCropGeneration();
            //Flammables

            //Compostables
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRIES.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_PIPS.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.MINI_MELON.get(), 0.65F);
        });
    }

    // Adds delightful conditions
    @SubscribeEvent
    public static void registerSerializers(RegisterEvent event) {
        if (event.getRegistryKey() == ForgeRegistries.RECIPE_SERIALIZERS.getRegistryKey()) {
            CraftingHelper.register(EnabledCondition.Serializer.INSTANCE);
        }
    }
}

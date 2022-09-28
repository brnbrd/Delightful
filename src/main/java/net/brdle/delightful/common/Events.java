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
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.ANIMAL_FAT.get(), 0.1F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.ACORN.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.ANIMAL_FAT.get(), 0.1F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRIES.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRIES.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_PIPS.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_PIE.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_PIE_SLICE.get(), 0.85F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.PUMPKIN_PIE_SLICE.get(), 0.85F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.GREEN_TEA_LEAF.get(), 0.5F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.MATCHA.get(), 0.5F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.CHOPPED_CLOVER.get(), 0.5F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.CACTUS_FLESH.get(), 0.25F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.CACTUS_STEAK.get(), 0.25F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.CANTALOUPE_SLICE.get(), 0.6F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.MINI_MELON.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.CANTALOUPE.get(), 0.75F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_SACK.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.ACORN_SACK.get(), 1.0F);

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

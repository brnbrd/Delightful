package net.brdle.delightful.common;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.brdle.delightful.common.config.EnabledCondition;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.world.DelightfulWildCropGeneration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.List;

@Mod.EventBusSubscriber(modid=Delightful.MODID)
public class Events {

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent e) {
        e.enqueueWork(() -> {
            DelightfulWildCropGeneration.registerWildCropGeneration();
            //Flammables
        });
    }

    private static final List<String> portedMods = List.of("coppersdelight", "steelsdelight", "enderitesdelight");

    // Remaps any blocks ids to use "delightful" namespace
    @SubscribeEvent
    public static void onMissingBlockMappings(RegistryEvent.MissingMappings<Block> e) {
        for (var mapping : e.getAllMappings()) {
            if (portedMods.contains(mapping.key.getNamespace())) {
                var remap = new ResourceLocation(Delightful.MODID, mapping.key.getPath());
                if (ForgeRegistries.BLOCKS.containsKey(remap)) {
                    mapping.remap(ForgeRegistries.BLOCKS.getValue(remap));
                } else {
                    mapping.warn();
                }
            }
        }
    }

    // Remaps any item ids to use "delightful" namespace
    @SubscribeEvent
    public static void onMissingItemMappings(RegistryEvent.MissingMappings<Item> e) {
        for (var mapping : e.getAllMappings()) {
            if (portedMods.contains(mapping.key.getNamespace())) {
                var remap = new ResourceLocation(Delightful.MODID, mapping.key.getPath());
                if (ForgeRegistries.ITEMS.containsKey(remap)) {
                    mapping.remap(ForgeRegistries.ITEMS.getValue(remap));
                } else {
                    mapping.warn();
                }
            } else if (mapping.key.getNamespace().equals(Delightful.MODID) &&
              mapping.key.getPath().equals("salmonberry")) {
                mapping.remap(DelightfulItems.SALMONBERRIES.get());
            }
        }
    }

    // Adds delightful conditions
    @SubscribeEvent
    public static void registerSerializers(RegistryEvent.Register<RecipeSerializer<?>> event) {
        CraftingHelper.register(EnabledCondition.Serializer.INSTANCE);
    }

}

package net.brdle.delightful.common;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.config.BlockEnabledCondition;
import net.brdle.delightful.common.config.FoodEnabledCondition;
import net.brdle.delightful.common.config.KnifeEnabledCondition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.List;

@Mod.EventBusSubscriber(modid=Delightful.MODID)
public class Events {

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
            }
        }
    }

    // Adds delightful conditions
    @SubscribeEvent
    public static void registerSerializers(RegistryEvent.Register<RecipeSerializer<?>> event) {
        CraftingHelper.register(KnifeEnabledCondition.Serializer.INSTANCE);
        CraftingHelper.register(FoodEnabledCondition.Serializer.INSTANCE);
        CraftingHelper.register(BlockEnabledCondition.Serializer.INSTANCE);
    }

}

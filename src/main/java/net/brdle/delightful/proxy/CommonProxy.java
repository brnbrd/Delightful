package net.brdle.delightful.proxy;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.data.DataGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.List;

public class CommonProxy {

    public void start() {
        final var modBus = FMLJavaModLoadingContext.get().getModEventBus();
        //final IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        //DelightfulTiles.create(modBus);
        DelightfulBlocks.create(modBus);
        DelightfulItems.create(modBus);
        modBus.register(this);
        modBus.register(DataGenerators.class);
    }

    /*@SubscribeEvent
    public void setup(FMLCommonSetupEvent e) {
        e.enqueueWork(() -> {
            //Flammables
        });
    }*/

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
}
package net.brdle.delightful.data;

import net.brdle.delightful.Delightful;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid=Delightful.MODID)
public class Generators {

    // Gathering data
    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        var gen = e.getGenerator();
        var helper = e.getExistingFileHelper();
        if (e.includeServer()) {
            e.getGenerator().addProvider(new DelightfulLootTableProvider(gen));
            e.getGenerator().addProvider(new DelightfulRecipeProvider(gen));
            e.getGenerator().addProvider(new DelightfulAdvancementProvider(gen, helper));
            DelightfulBlockTagProvider blockTag = new DelightfulBlockTagProvider(gen, helper);
            e.getGenerator().addProvider(blockTag);
            e.getGenerator().addProvider(new DelightfulItemTagProvider(gen, blockTag, helper));
            e.getGenerator().addProvider(new DelightfulLootModifierProvider(gen));
        }
        if (e.includeClient()) {
            e.getGenerator().addProvider(new DelightfulBlockStateProvider(gen, helper));
            e.getGenerator().addProvider(new DelightfulBlockModelProvider(gen, helper));
            e.getGenerator().addProvider(new DelightfulItemModelProvider(gen, helper));
        }
    }
}

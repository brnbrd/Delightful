package net.brdle.delightful.data.gen;

import net.brdle.delightful.Delightful;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=Delightful.MODID)
public class Generators {

    // Gathering data
    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        var gen = e.getGenerator();
        var helper = e.getExistingFileHelper();
        e.getGenerator().addProvider(e.includeServer(), new DelightfulLootTableProvider(gen));
        e.getGenerator().addProvider(e.includeServer(), new DelightfulRecipeProvider(gen));
        e.getGenerator().addProvider(e.includeServer(), new DelightfulAdvancementProvider(gen, helper));
        DelightfulBlockTagProvider blockTag = new DelightfulBlockTagProvider(gen, helper);
        e.getGenerator().addProvider(e.includeServer(), blockTag);
        e.getGenerator().addProvider(e.includeServer(), new DelightfulItemTagProvider(gen, blockTag, helper));
        e.getGenerator().addProvider(e.includeServer(), new DelightfulEntityTagProvider(gen, helper));
        e.getGenerator().addProvider(e.includeServer(), new DelightfulLootModifierProvider(gen));

        e.getGenerator().addProvider(e.includeClient(), new DelightfulBlockStateProvider(gen, helper));
        e.getGenerator().addProvider(e.includeClient(), new DelightfulBlockModelProvider(gen, helper));
        e.getGenerator().addProvider(e.includeClient(), new DelightfulItemModelProvider(gen, helper));
    }
}

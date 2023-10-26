package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid= Delightful.MODID)
public class Generators {

    // Gathering data
    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        DataGenerator gen = e.getGenerator();
        PackOutput output = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookup = e.getLookupProvider();
        ExistingFileHelper helper = e.getExistingFileHelper();

        gen.addProvider(e.includeServer(), new LootTableProvider(output, Collections.emptySet(),
            List.of(new LootTableProvider.SubProviderEntry(DelightfulBlockLoot::new, LootContextParamSets.BLOCK))));
        e.getGenerator().addProvider(e.includeServer(), new DelightfulRecipeProvider(output));
        e.getGenerator().addProvider(e.includeServer(), new ForgeAdvancementProvider(output, lookup, helper,
            List.of(new DelightfulAdvancementProvider())));
        DelightfulBlockTagProvider blockTag = new DelightfulBlockTagProvider(output, lookup, helper);
        e.getGenerator().addProvider(e.includeServer(), blockTag);
        e.getGenerator().addProvider(e.includeServer(), new DelightfulItemTagProvider(output, lookup, blockTag.contentsGetter(), helper));
        e.getGenerator().addProvider(e.includeServer(), new DelightfulEntityTagProvider(output, lookup, helper));
        e.getGenerator().addProvider(e.includeServer(), new DelightfulLootModifierProvider(output));

        e.getGenerator().addProvider(e.includeClient(), new DelightfulBlockStateProvider(output, helper));
        e.getGenerator().addProvider(e.includeClient(), new DelightfulItemModelProvider(output, helper));
        e.getGenerator().addProvider(e.includeClient(), new DelightfulLanguageProvider(output));
    }
}

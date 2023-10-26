package net.brnbrd.delightful.common.events;

import net.brnbrd.delightful.common.crafting.EnabledCondition;
import net.brnbrd.delightful.common.item.DItem;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.IConfigured;
import net.brnbrd.delightful.network.DPacketHandler;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.registry.ModCreativeTabs;

public class ModEvents {

    @SubscribeEvent
    void setup(FMLCommonSetupEvent e) {
        DPacketHandler.init(); // Botania
        e.enqueueWork(() -> {
            // Flammables

            // Compostables
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.ACORN.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRIES.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_PIPS.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_PIE.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_PIE_SLICE.get(), 0.85F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.PUMPKIN_PIE_SLICE.get(), 0.85F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SOURCE_BERRY_PIE_SLICE.get(), 0.85F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.GLOOMGOURD_PIE_SLICE.get(), 0.85F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.CRIMSON_BERRY_PIE_SLICE.get(), 0.85F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.NIGHTSHADE_BERRY_PIE_SLICE.get(), 0.85F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.BLUEBERRY_PIE_SLICE.get(), 0.85F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.GREEN_APPLE_PIE_SLICE.get(), 0.85F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.GREEN_TEA_LEAF.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.MATCHA.get(), 0.6F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.CHOPPED_CLOVER.get(), 0.5F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.CACTUS_FLESH.get(), 0.25F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.CACTUS_STEAK.get(), 0.25F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.CANTALOUPE_SLICE.get(), 0.6F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.MINI_MELON.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.CANTALOUPE.get(), 0.75F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.WILD_SALMONBERRIES.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_GUMMY.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SMORE.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.ACORN_SACK.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_SACK.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.NIGHTSHADE_BERRY_SACK.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.BLUEBERRY_SACK.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.CRIMSON_BERRY_SACK.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.GREEN_APPLE_CRATE.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.JOSHUA_FRUIT_CRATE.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.BAOBAB_FRUIT_CRATE.get(), 1.0F);
        });
    }

    // Adds Delightful conditions
    @SubscribeEvent
    void registerSerializers(RegisterEvent e) {
        if (e.getRegistryKey() == ForgeRegistries.RECIPE_SERIALIZERS.getRegistryKey()) {
            CraftingHelper.register(EnabledCondition.Serializer.INSTANCE);
        }
    }

    @SubscribeEvent
    public void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == ModCreativeTabs.TAB_FARMERS_DELIGHT.getKey()) {
            DelightfulItems.ITEMS.getEntries().stream().filter(RegistryObject::isPresent).forEach((item) -> {
                if (item.get() instanceof IConfigured conf) {
                    if (conf.isEnabled()) {
                        event.accept(item);
                    }
                } else {
                    event.accept(item);
                }
            });
        }
    }
}

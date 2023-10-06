package net.brnbrd.delightful.common.events;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.common.crafting.ElseRecipe;
import net.brnbrd.delightful.common.crafting.EnabledCondition;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.world.DelightfulWildCropGeneration;
import net.brnbrd.delightful.network.DPacketHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public class ModEvents {

    @SubscribeEvent
    void setup(FMLCommonSetupEvent e) {
        DPacketHandler.init();
        e.enqueueWork(() -> {
            DelightfulWildCropGeneration.registerWildCropGeneration();
            //Flammables

            //Compostables
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
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_SACK.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.ACORN_SACK.get(), 1.0F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.WILD_SALMONBERRIES.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SALMONBERRY_GUMMY.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(DelightfulItems.SMORE.get(), 1.0F);
        });
    }

    // Adds Delightful conditions
    @SubscribeEvent
    void registerSerializers(RegisterEvent event) {
        if (event.getRegistryKey() == ForgeRegistries.RECIPE_SERIALIZERS.getRegistryKey()) {
            CraftingHelper.register(EnabledCondition.Serializer.INSTANCE);
            event.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS, new ResourceLocation(Delightful.MODID, "else"), ElseRecipe.Serializer::new);
        }
    }
}

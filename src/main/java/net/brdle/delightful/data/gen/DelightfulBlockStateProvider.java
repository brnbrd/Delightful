package net.brdle.delightful.data.gen;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.CabinetBlock;

public class DelightfulBlockStateProvider extends BlockStateProvider {
    public DelightfulBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Delightful.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        DelightfulBlocks.BLOCKS.getEntries().stream()
                .filter(entry -> entry.get() instanceof CabinetBlock)
                .forEach(cab -> cabinet(cab.get()));
        this.wildCropBlock(DelightfulBlocks.WILD_SALMONBERRIES.get());
        this.simpleBlock(DelightfulBlocks.SALMONBERRY_SACK.get(),
          models().cubeBottomTop("salmonberry_sack",
            new ResourceLocation(Delightful.MODID, "block/salmonberry_sack"),
            new ResourceLocation(Delightful.MODID, "block/salmonberry_sack_bottom"),
            new ResourceLocation(Delightful.MODID, "block/salmonberry_sack_top")));
        this.simpleBlock(DelightfulBlocks.ACORN_SACK.get(),
            models().cubeBottomTop("acorn_sack",
                new ResourceLocation(Delightful.MODID, "block/acorn_sack"),
                new ResourceLocation(Delightful.MODID, "block/acorn_sack_bottom"),
                new ResourceLocation(Delightful.MODID, "block/acorn_sack_top")));
    }

    public void wildCropBlock(Block block) {
        this.simpleBlock(block, models().singleTexture(Util.name(block), new ResourceLocation(FarmersDelight.MODID, "bush_crop"), "crop", resourceBlock(Util.name(block))));
    }

    // Adapted from: https://github.com/vectorwing/FarmersDelight/blob/1.18.2/src/main/java/vectorwing/farmersdelight/data/BlockStates.java
    public ResourceLocation resourceBlock(String path) {
        return new ResourceLocation(Delightful.MODID, "block/" + path);
    }

    // Adapted from: https://github.com/vectorwing/FarmersDelight/blob/1.18.2/src/main/java/vectorwing/farmersdelight/data/BlockStates.java
    public void cabinet(Block block) {
        String path = Util.name(block);
        String type = path.replace("_cabinet", "").trim();
        this.horizontalBlock(block, state -> {
            String suffix = state.getValue(CabinetBlock.OPEN) ? "_open" : "";
            return models().orientable(path + suffix,
                    resourceBlock(type + "_cabinet_side"),
                    resourceBlock(type + "_cabinet_front" + suffix),
                    resourceBlock(type + "_cabinet_top"));
        });
    }
}

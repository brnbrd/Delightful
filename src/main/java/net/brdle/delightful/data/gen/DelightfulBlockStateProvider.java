package net.brdle.delightful.data.gen;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.block.SlicedMelonBlock;
import net.brdle.delightful.common.block.SlicedMiniMelonBlock;
import net.brdle.delightful.common.block.SlicedPumpkinBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.Objects;

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
            Util.rl(Delightful.MODID, "block/salmonberry_sack"),
            Util.rl(Delightful.MODID, "block/salmonberry_sack_bottom"),
            Util.rl(Delightful.MODID, "block/salmonberry_sack_top")));
        this.simpleBlock(DelightfulBlocks.ACORN_SACK.get(),
            models().cubeBottomTop("acorn_sack",
                Util.rl(Delightful.MODID, "block/acorn_sack"),
                Util.rl(Delightful.MODID, "block/acorn_sack_bottom"),
                Util.rl(Delightful.MODID, "block/acorn_sack_top")));
        this.miniMelonBlock((SlicedMiniMelonBlock) DelightfulBlocks.SLICED_MINI_MELON.get());
        this.miniMelonBlock((SlicedMiniMelonBlock) DelightfulBlocks.SLICED_CANTALOUPE.get());
        this.melonBlock((SlicedMelonBlock) DelightfulBlocks.SLICED_MELON.get());
        this.pumpkinBlock((SlicedPumpkinBlock) DelightfulBlocks.SLICED_PUMPKIN.get());
        this.pieBlock(DelightfulBlocks.SALMONBERRY_PIE.get());
        this.pieBlock(DelightfulBlocks.PUMPKIN_PIE.get());
        this.pieBlock(DelightfulBlocks.SOURCE_BERRY_PIE.get());
    }

    public void wildCropBlock(Block block) {
        this.simpleBlock(block, models().singleTexture(Util.name(block), Util.rl(FarmersDelight.MODID, "bush_crop"), "crop", resourceBlock(Util.name(block))).renderType("cutout"));
    }

    // Adapted from: https://github.com/vectorwing/FarmersDelight/blob/1.18.2/src/main/java/vectorwing/farmersdelight/data/BlockStates.java
    public ResourceLocation resourceBlock(String path) {
        return Util.rl(Delightful.MODID, "block/" + path);
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

    public ModelFile existingModel(String path) {
        return new ModelFile.ExistingModelFile(resourceBlock(path), models().existingFileHelper);
    }

    public void miniMelonBlock(SlicedMiniMelonBlock block) {
        getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
            .modelFile(existingModel(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath() + "_bite" + state.getValue(SlicedMiniMelonBlock.BITES)))
            .build()
        );
    }

    public void melonBlock(SlicedMelonBlock block) {
        getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
            .modelFile(existingModel(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath() + "_bite" + state.getValue(SlicedMelonBlock.BITES)))
            .build()
        );
    }

    public void pumpkinBlock(SlicedPumpkinBlock block) {
        getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder()
            .modelFile(existingModel(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath() + "_bite" + state.getValue(SlicedPumpkinBlock.BITES)))
            .build()
        );
    }

    public void pieBlock(Block block) {
        getVariantBuilder(block)
            .forAllStates(state -> {
                    int bites = state.getValue(PieBlock.BITES);
                    String suffix = bites > 0 ? "_slice" + bites : "";
                    return ConfiguredModel.builder()
                        .modelFile(existingModel(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath() + suffix))
                        .rotationY(((int) state.getValue(PieBlock.FACING).toYRot() + 180) % 360)
                        .build();
                }
            );
    }
}

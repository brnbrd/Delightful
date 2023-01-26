package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
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
        this.wildCropBlock(DelightfulBlocks.WILD_SALMONBERRIES.get());
        this.stageBlock(DelightfulBlocks.SALMONBERRY_BUSH.get(), SalmonberryBushBlock.AGE);
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
        this.simpleBlock(DelightfulBlocks.CANTALOUPE.get(), existingModel("cantaloupe"));
        this.simpleBlock(DelightfulBlocks.MINI_MELON.get(), existingModel("mini_melon"));
        this.miniMelonBlock((SlicedMiniMelonBlock) DelightfulBlocks.SLICED_MINI_MELON.get());
        this.miniMelonBlock((SlicedMiniMelonBlock) DelightfulBlocks.SLICED_CANTALOUPE.get());
        this.melonBlock((SlicedMelonBlock) DelightfulBlocks.SLICED_MELON.get());
        this.pumpkinBlock((SlicedPumpkinBlock) DelightfulBlocks.SLICED_PUMPKIN.get());
        this.pieBlock(DelightfulBlocks.SALMONBERRY_PIE);
        this.pieBlock(DelightfulBlocks.PUMPKIN_PIE);
        this.pieBlock(DelightfulBlocks.SOURCE_BERRY_PIE);
        this.pieBlock(DelightfulBlocks.GREEN_APPLE_PIE);
        this.pieBlock(DelightfulBlocks.BLUEBERRY_PIE);
        this.pieBlock(DelightfulBlocks.CRIMSON_BERRY_PIE);
        this.pieBlock(DelightfulBlocks.NIGHTSHADE_BERRY_PIE);
        this.cabinet(DelightfulBlocks.BASALT_CABINET.get());
        this.cabinet(DelightfulBlocks.QUARTZ_CABINET.get());
    }

    // Adapted from: https://github.com/vectorwing/FarmersDelight/blob/1.19/src/main/java/vectorwing/farmersdelight/data/BlockStates.java
    public void stageBlock(Block block, IntegerProperty ageProperty) {
        getVariantBuilder(block).forAllStates(state -> {
            String stageName = Util.name(block) + "_stage" + state.getValue(ageProperty);
            return ConfiguredModel.builder()
                .modelFile(models().cross(stageName, resourceBlock(stageName)).renderType("cutout")).build();
        });
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

    // Adapted from: https://github.com/vectorwing/FarmersDelight/blob/1.19/src/main/java/vectorwing/farmersdelight/data/BlockStates.java
    public void pieBlock(RegistryObject<Block> block) {
        getVariantBuilder(block.get()).forAllStates(state -> {
                int bites = state.getValue(PieBlock.BITES);
                String name = Util.name(block);
                String suffix = bites > 0 ? "_slice" + bites : "";
                var mod = models()
                    .withExistingParent("block/" + name + suffix, Util.rl(FarmersDelight.MODID, "pie" + suffix))
                    .texture("top", resourceBlock(name + "_top"))
                    .texture("bottom", resourceBlock(name + "_bottom"))
                    .texture("side", resourceBlock(name + "_side"))
                    .texture("particle", resourceBlock(name + "_top"));
                if (bites > 0) {
                    mod.texture("inner", resourceBlock(name + "_inner"));
                }
                return ConfiguredModel.builder().modelFile(mod)
                    .rotationY(((int) state.getValue(PieBlock.FACING).toYRot() + 180) % 360).build();
            }
        );
    }
}

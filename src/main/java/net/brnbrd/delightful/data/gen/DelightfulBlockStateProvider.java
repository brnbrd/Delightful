package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.*;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
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
    public DelightfulBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Delightful.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.wildCropBlock(DelightfulBlocks.WILD_SALMONBERRIES.get());
        this.stageBlock(DelightfulBlocks.SALMONBERRY_BUSH.get(), SalmonberryBushBlock.AGE);
        this.stageBlock(DelightfulBlocks.CANTALOUPE_PLANT.get(), CantaloupePlantBlock.AGE);
        this.sackBlock(DelightfulBlocks.SALMONBERRY_SACK.get(), "salmonberry");
        this.sackBlock(DelightfulBlocks.ACORN_SACK.get(), "acorn");
        this.miniBlock((MiniBlock) DelightfulBlocks.CANTALOUPE.get(),
            resourceBlock("cantaloupe_side_small"), resourceBlock("cantaloupe_top"));
        this.miniBlock((MiniBlock) DelightfulBlocks.MINI_MELON.get(),
            Util.rl("minecraft", "block/melon_side"), Util.rl("minecraft", "block/melon_top"));
        this.slicedMiniMelonBlock((SlicedMiniMelonBlock) DelightfulBlocks.SLICED_MINI_MELON.get(),
            "melon", Util.rl("minecraft", "block/melon_side"), Util.rl("minecraft", "block/melon_top"));
        this.slicedMiniMelonBlock((SlicedMiniMelonBlock) DelightfulBlocks.SLICED_CANTALOUPE.get(),
            "cantaloupe", resourceBlock("cantaloupe_side_small"), resourceBlock("cantaloupe_top"));
        this.bigSlicedBlock(DelightfulBlocks.SLICED_MELON.get(),
            resourceBlock("melon_inside_rind"), Util.rl("minecraft", "block/melon_side"), Util.rl("minecraft", "block/melon_top"));
        this.bigSlicedBlock(DelightfulBlocks.SLICED_PUMPKIN.get(),
            resourceBlock("pumpkin_inside_rind"), Util.rl("minecraft", "block/pumpkin_side"), Util.rl("minecraft", "block/pumpkin_top"));
        this.bigSlicedBlock(DelightfulBlocks.SLICED_GLOOMGOURD.get(),
            resourceBlock("gloomgourd_inside_rind"), Util.rl(Mods.UG, "block/gloomgourd_side"), Util.rl(Mods.UG, "block/gloomgourd_top"));
        this.pieBlock(DelightfulBlocks.SALMONBERRY_PIE);
        this.pieBlock(DelightfulBlocks.PUMPKIN_PIE);
        this.pieBlock(DelightfulBlocks.SOURCE_BERRY_PIE);
        this.pieBlock(DelightfulBlocks.GLOOMGOURD_PIE);
        this.pieBlock(DelightfulBlocks.GREEN_APPLE_PIE);
        this.pieBlock(DelightfulBlocks.BLUEBERRY_PIE);
        this.pieBlock(DelightfulBlocks.CRIMSON_BERRY_PIE);
        this.pieBlock(DelightfulBlocks.NIGHTSHADE_BERRY_PIE);
        this.cabinet(DelightfulBlocks.BASALT_CABINET.get());
        this.cabinet(DelightfulBlocks.QUARTZ_CABINET.get());
        this.simpleBlock(DelightfulBlocks.SALMONBERRY_ICE_CREAM_BLOCK.get());
        this.simpleBlock(DelightfulBlocks.MATCHA_ICE_CREAM_BLOCK.get());
        this.sackBlock(DelightfulBlocks.BLUEBERRY_SACK.get(), "blueberry");
        this.sackBlock(DelightfulBlocks.CRIMSON_BERRY_SACK.get(), "crimson_berry");
        this.sackBlock(DelightfulBlocks.NIGHTSHADE_BERRY_SACK.get(), "nightshade_berry");
        this.crateBlock(DelightfulBlocks.GREEN_APPLE_CRATE.get(), "green_apple", true);
        this.crateBlock(DelightfulBlocks.JOSHUA_FRUIT_CRATE.get(), "joshua_fruit", false);
        this.crateBlock(DelightfulBlocks.BAOBAB_FRUIT_CRATE.get(), "baobab_fruit", false);
    }

    // Adapted from: https://github.com/vectorwing/FarmersDelight/blob/1.19/src/main/java/vectorwing/farmersdelight/data/BlockStates.java
    public void stageBlock(Block block, IntegerProperty ageProperty) {
        getVariantBuilder(block).forAllStates(state -> {
            String stageName = Util.name(block) + "_stage" + state.getValue(ageProperty);
            return ConfiguredModel.builder()
                .modelFile(models().cross(stageName, resourceBlock(stageName)).renderType("cutout")).build();
        });
    }

    public void sackBlock(Block block, String cropName) {
        this.simpleBlock(block,
            models().cubeBottomTop(Util.name(block),
                resourceBlock(cropName + "_sack"),
                resourceBlock(cropName + "_sack_bottom"),
                resourceBlock(cropName + "_sack_top"))
        );
    }

    public void crateBlock(Block block, String cropName, boolean customBottom) {
        this.simpleBlock(block,
            models().cubeBottomTop(Util.name(block),
                resourceBlock(cropName + "_crate_side"),
                customBottom ?
                    resourceBlock(cropName + "_crate_bottom") :
                    Util.rl(FarmersDelight.MODID, "block/crate_bottom"),
                resourceBlock(cropName + "_crate_top")));
    }

    public void wildCropBlock(Block block) {
        this.simpleBlock(block, models().cross(Util.name(block), resourceBlock(Util.name(block))).renderType("cutout"));
    }

    public void cabinet(Block block) {
        String path = Util.name(block);
        String type = path.replace("_cabinet", "").trim();
        this.horizontalBlock(block, state -> {
            String suffix = state.getValue(CabinetBlock.OPEN) ? "_open" : "";
            return models().orientableWithBottom(path + suffix,
                resourceBlock(type + "_cabinet_side"),
                resourceBlock(type + "_cabinet_front" + suffix),
                resourceBlock(type + "_cabinet_bottom"),
                resourceBlock(type + "_cabinet_top"));
        });
    }

    public void miniBlock(MiniBlock block, ResourceLocation side, ResourceLocation top) {
        this.simpleBlock(block, models().getBuilder(Util.name(block))
            .ao(false)
            .texture("side", side)
            .texture("top", top)
            .texture("particle", side)
            .element().from(3f, 0f, 3f).to(13f, 10f, 13f)
                .face(Direction.NORTH).texture("#side").end()
                .face(Direction.EAST).texture("#side").end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.UP).texture("#top").end()
                .face(Direction.DOWN).texture("#top").end().end()
            .transforms()
            .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
            .rotation(75f, 45f, 0f).translation(0f, 2.5f, 0f)
            .scale(0.375f, 0.375f, 0.375f).end()
            .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
            .rotation(75f, 45f, 0f).translation(0f, 2.5f, 0f)
            .scale(0.375f, 0.375f, 0.375f).end()
            .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
            .rotation(0f, 45f, 0f).scale(0.4f, 0.4f, 0.4f).end()
            .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
            .rotation(0f, 225f, 0f).scale(0.4f, 0.4f, 0.4f).end()
            .transform(ItemDisplayContext.GROUND)
            .translation(0f, 3f, 0f).scale(0.25f, 0.25f, 0.25f).end()
            .transform(ItemDisplayContext.GUI)
            .rotation(30f, 225f, 0f).scale(0.625f, 0.625f, 0.625f).end()
            .transform(ItemDisplayContext.HEAD)
            .translation(0f, 14.25f, 0f).end()
            .transform(ItemDisplayContext.FIXED)
            .scale(0.5f, 0.5f, 0.5f).end().end()
        );
    }

    public void bigSlicedBlock(Block block, ResourceLocation inside, ResourceLocation side, ResourceLocation top) {
        if (block instanceof ISliceable slice) {
            String name = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
            getVariantBuilder(block).forAllStates(state -> {
                int bites = state.getValue(slice.getBitesProperty());
                return ConfiguredModel.builder().modelFile(models().getBuilder(name + "_bite" + bites)
                    .ao(false)
                    .texture("inside", inside)
                    .texture("side", side)
                    .texture("top", top)
                    .texture("particle", side)
                    .element().from(0f, 0f, 0f).to(16f, slice.getHeight(bites), 16f)
                    .face(Direction.NORTH).texture("#side").end()
                    .face(Direction.EAST).texture("#side").end()
                    .face(Direction.SOUTH).texture("#side").end()
                    .face(Direction.WEST).texture("#side").end()
                    .face(Direction.UP).texture("#inside").end()
                    .face(Direction.DOWN).texture("#top").end().end()
                    ).build();
                }
            );
        }
    }

    public void slicedMiniMelonBlock(SlicedMiniMelonBlock block, String alias, ResourceLocation side, ResourceLocation top) {
        String name = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
        getVariantBuilder(block).forAllStates(state -> {
                int bites = state.getValue(block.getBitesProperty());
                return ConfiguredModel.builder().modelFile(models().getBuilder(name + "_bite" + bites)
                    .ao(false)
                    .texture("inside", resourceBlock(alias + "_inside_small"))
                    .texture("side", side)
                    .texture("top", top)
                    .texture("particle", side)
                    .element().from(3f, 0f, 3f).to(13f, block.getHeight(bites), 13f)
                        .face(Direction.NORTH).texture("#side").end()
                        .face(Direction.EAST).texture("#side").end()
                        .face(Direction.SOUTH).texture("#side").end()
                        .face(Direction.WEST).texture("#side").end()
                        .face(Direction.UP).uvs(3f, 6f, 13f, 16f).texture("#inside").end()
                        .face(Direction.DOWN).texture("#top").end().end()
                    .transforms()
                        .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                            .rotation(75f, 45f, 0f).translation(0f, 2.5f, 0f)
                            .scale(0.375f, 0.375f, 0.375f).end()
                        .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                            .rotation(75f, 45f, 0f).translation(0f, 2.5f, 0f)
                            .scale(0.375f, 0.375f, 0.375f).end()
                        .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                            .rotation(0f, 45f, 0f).scale(0.4f, 0.4f, 0.4f).end()
                        .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                            .rotation(0f, 225f, 0f).scale(0.4f, 0.4f, 0.4f).end()
                        .transform(ItemDisplayContext.GROUND)
                            .translation(0f, 3f, 0f).scale(0.25f, 0.25f, 0.25f).end()
                        .transform(ItemDisplayContext.GUI)
                            .rotation(30f, 225f, 0f).scale(0.625f, 0.625f, 0.625f).end()
                        .transform(ItemDisplayContext.HEAD)
                            .translation(0f, 14.25f, 0f).end()
                        .transform(ItemDisplayContext.FIXED)
                            .scale(0.5f, 0.5f, 0.5f).end().end()
                ).build();
            }
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

    public ModelFile existingModel(String path) {
        return new ModelFile.ExistingModelFile(resourceBlock(path), models().existingFileHelper);
    }

    public ResourceLocation resourceBlock(String path) {
        return Util.rl(Delightful.MODID, "block/" + path);
    }
}

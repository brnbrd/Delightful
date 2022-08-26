package net.brdle.delightful.data;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import vectorwing.farmersdelight.common.block.CabinetBlock;

public class DelightfulBlockModelProvider extends BlockModelProvider {
    public DelightfulBlockModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Delightful.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        DelightfulBlocks.BLOCKS.getEntries().stream()
                .filter(entry -> entry.get() instanceof CabinetBlock)
                .forEach(cab -> cabinet(cab.getId().getPath()));
        bush("salmonberry");
    }

    public void bush(String name) {
        for (int i = 0; i <= 3; i++) {
            String stage = "block/" + name + "_bush_stage" + i;
            cross(stage, new ResourceLocation(this.modid, stage)).renderType("cutout");
        }
    }

    public void cabinet(String name) {
        orientable(name,
                new ResourceLocation(this.modid, "block/" + name + "_side"),
                new ResourceLocation(this.modid, "block/" + name + "_front"),
                new ResourceLocation(this.modid, "block/" + name + "_top"));
        orientable(name + "_open",
                new ResourceLocation(this.modid, "block/" + name + "_side"),
                new ResourceLocation(this.modid, "block/" + name + "_front_open"),
                new ResourceLocation(this.modid, "block/" + name + "_top"));
    }
}
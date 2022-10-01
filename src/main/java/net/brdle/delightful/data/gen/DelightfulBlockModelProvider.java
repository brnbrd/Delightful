package net.brdle.delightful.data.gen;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.minecraft.data.DataGenerator;
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
            cross(stage, Util.rl(this.modid, stage)).renderType("cutout");
        }
    }

    public void cabinet(String name) {
        orientable(name,
                Util.rl(this.modid, "block/" + name + "_side"),
                Util.rl(this.modid, "block/" + name + "_front"),
                Util.rl(this.modid, "block/" + name + "_top"));
        orientable(name + "_open",
                Util.rl(this.modid, "block/" + name + "_side"),
                Util.rl(this.modid, "block/" + name + "_front_open"),
                Util.rl(this.modid, "block/" + name + "_top"));
    }
}
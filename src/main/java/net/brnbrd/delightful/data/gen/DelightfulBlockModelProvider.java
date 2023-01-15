package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
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
        bush("salmonberry", 4);
    }

    public void bush(String name, int stages) {
        for (int i = 0; i <= stages; i++) {
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
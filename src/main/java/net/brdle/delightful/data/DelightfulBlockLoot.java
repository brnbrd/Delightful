package net.brdle.delightful.data;

import net.brdle.delightful.common.block.DelightfulBlocks;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class DelightfulBlockLoot extends BlockLoot {

    @Override
    protected void addTables() {
        this.dropSelf(DelightfulBlocks.QUARTZ_CABINET.get());
        this.dropSelf(DelightfulBlocks.BASALT_CABINET.get());
        this.dropSelf(DelightfulBlocks.PIZZA_PEEL.get());
        this.dropSelf(DelightfulBlocks.PIZZA_STONE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return DelightfulBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}

package net.brdle.delightful.data;

import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.block.DelightfulCabinetBlock;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class DelightfulBlockLoot extends BlockLoot {

    @Override
    protected void addTables() {
        // Cabinets
        DelightfulBlocks.BLOCKS.getEntries().stream()
                .map(RegistryObject::get)
                .filter(block -> block instanceof DelightfulCabinetBlock)
                .forEach(this::dropSelf);

        this.dropSelf(DelightfulBlocks.PIZZA_PEEL.get());
        this.dropSelf(DelightfulBlocks.PIZZA_STONE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return DelightfulBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}

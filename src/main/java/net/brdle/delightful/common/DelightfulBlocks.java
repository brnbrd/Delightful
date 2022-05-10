package net.brdle.delightful.common;

import net.brdle.delightful.Delightful;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DelightfulBlocks {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Delightful.MODID);

    public static void create(IEventBus bus) {
        BLOCKS.register(bus);
    }
}

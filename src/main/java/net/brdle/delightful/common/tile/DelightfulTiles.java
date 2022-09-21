package net.brdle.delightful.common.tile;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DelightfulTiles {

    private static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Delightful.MODID);

    public static final RegistryObject<BlockEntityType<PizzaStoneBlockEntity>> PIZZA_STONE = TILES.register("pizza_stone",
            () -> BlockEntityType.Builder.of(PizzaStoneBlockEntity::new, DelightfulBlocks.PIZZA_STONE.get()).build(null));
    public static final RegistryObject<BlockEntityType<PizzaPeelBlockEntity>> PIZZA_PEEL = TILES.register("pizza_peel",
            () -> BlockEntityType.Builder.of(PizzaPeelBlockEntity::new, DelightfulBlocks.PIZZA_PEEL.get()).build(null));

    public static void create(IEventBus bus) {
        TILES.register(bus);
    }
}

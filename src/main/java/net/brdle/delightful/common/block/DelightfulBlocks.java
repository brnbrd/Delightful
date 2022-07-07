package net.brdle.delightful.common.block;

import net.brdle.delightful.Delightful;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import java.util.function.Supplier;

public class DelightfulBlocks {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Delightful.MODID);
    public static final RegistryObject<Block> PIZZA_STONE = registerBlock("pizza_stone", () -> new PizzaStoneBlock(BlockBehaviour.Properties.of(Material.STONE).strength(0.5F, 6.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> PIZZA_PEEL = registerBlock("pizza_peel", () -> new PizzaPeelBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 6.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> QUARTZ_CABINET = registerBlock("quartz_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).requiresCorrectToolForDrops().strength(0.8F)));
    public static final RegistryObject<Block> BASALT_CABINET = registerBlock("basalt_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(1.25F, 4.2F).sound(SoundType.BASALT)));

    public static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
        return BLOCKS.register(name, block);
    }

    public static void create(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
package net.brdle.delightful.common.block;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.item.DelightfulItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.FarmersDelight;
import java.util.HashMap;
import java.util.Map;

public class DelightfulBlocks {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Delightful.MODID);
    private static final Map<Block, RegistryObject<Item>> items = new HashMap<>();

    public static final RegistryObject<Block> PIZZA_STONE = registerBlock("pizza_stone", new PizzaStoneBlock(BlockBehaviour.Properties.of(Material.STONE).strength(0.5F, 6.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> PIZZA_PEEL = registerBlock("pizza_peel", new PizzaPeelBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 6.0F).sound(SoundType.WOOD).noOcclusion()));

    private static RegistryObject<Block> registerBlock(String name, Block block) {
        var returnBlock = BLOCKS.register(name, () -> block);
        registerBlockItem(name, block);
        return returnBlock;
    }

    private static void registerBlockItem(String name, Block block) {
        items.put(block, DelightfulItems.registerItem(name, new BlockItem(block, new Item.Properties().tab(FarmersDelight.CREATIVE_TAB))));
    }

    public static Item getItem(Block block) {
        return items.get(block).get();
    }

    public static void create(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
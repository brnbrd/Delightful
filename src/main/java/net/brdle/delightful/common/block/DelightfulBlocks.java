package net.brdle.delightful.common.block;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.item.DelightfulItems;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.block.WildCropBlock;
import java.util.function.Supplier;

public class DelightfulBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Delightful.MODID);

    public static final RegistryObject<Block> PIZZA_STONE = registerBlock("pizza_stone",
      () -> new PizzaStoneBlock(BlockBehaviour.Properties.of(Material.STONE).strength(0.5F, 6.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> PIZZA_PEEL = registerBlock("pizza_peel",
      () -> new PizzaPeelBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 6.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> QUARTZ_CABINET = registerBlock("quartz_cabinet",
      () -> new DelightfulCabinetBlock(DelightfulItems.getGem("quartz"), BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).requiresCorrectToolForDrops().strength(0.8F)));
    public static final RegistryObject<Block> BASALT_CABINET = registerBlock("basalt_cabinet",
      () -> new DelightfulCabinetBlock(() -> Ingredient.of(Items.BASALT), BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(1.25F, 4.2F).sound(SoundType.BASALT)));
    public static final RegistryObject<Block> WILD_SALMONBERRIES = BLOCKS.register("wild_salmonberries",
      () -> new WildCropBlock(MobEffects.REGENERATION, 6, Block.Properties.copy(Blocks.TALL_GRASS), false));
    public static final RegistryObject<Block> MINI_MELON = BLOCKS.register("mini_melon",
      () -> new MiniMelonBlock(BlockBehaviour.Properties.copy(Blocks.MELON).noOcclusion()));
    public static final RegistryObject<Block> SALMONBERRY_SACK = BLOCKS.register("salmonberry_sack",
      () -> new Block(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK)));

    public static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
        return BLOCKS.register(name, block);
    }

    public static void create(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
package net.brdle.delightful;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.Objects;

public class Util {

  public static ObjectArrayList<ItemStack> with(ObjectArrayList<ItemStack> before, ItemStack addition) {
    before.add(addition);
    return before;
  }

  public static String name(Item item) {
    //return Registry.ITEM.getKey(item).getPath();
    return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath();
  }

  public static String name(Block block) {
    return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
  }
}

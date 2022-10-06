package net.brdle.delightful;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import java.util.Objects;

public class Util {

  public static ResourceLocation rl(String modid, String path) {
    return new ResourceLocation(modid, path);
  }

  public static ResourceLocation rl(String separated) {
    return new ResourceLocation(separated);
  }

  public static TagKey<Item> it(String modid, String path) {
    return ItemTags.create(rl(modid, path));
  }

  public static ObjectArrayList<ItemStack> with(ObjectArrayList<ItemStack> before, ItemStack addition) {
    before.add(addition);
    return before;
  }

  @Nullable
  public static Item item(String modid, String path) {
    return item(rl(modid, path));
  }

  @Nullable
  public static Item item(ResourceLocation rl) {
    return ForgeRegistries.ITEMS.getValue(rl);
  }

  public static String name(Item item) {
    return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath();
  }

  public static String name(Block block) {
    return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath();
  }

  public static void dropOrGive(ItemStack stack, Level world, BlockPos drop, Player give) {
    if (DelightfulConfig.GIVE_SLICED_DIRECTLY.get()) {
      ItemHandlerHelper.giveItemToPlayer(give, stack, 0);
    } else {
      Containers.dropItemStack(world, drop.getX(), drop.getY() + 0.25F, drop.getZ(), stack);
    }
  }
}

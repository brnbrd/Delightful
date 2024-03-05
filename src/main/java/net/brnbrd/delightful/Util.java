package net.brnbrd.delightful;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.brnbrd.delightful.common.DelightfulConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

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

  public static TagKey<EntityType<?>> et(String modid, String path) {
    return TagKey.create(Registries.ENTITY_TYPE, rl(modid, path));
  }

  public static ObjectArrayList<ItemStack> with(ObjectArrayList<ItemStack> before, ItemStack addition) {
    before.add(addition);
    return before;
  }

  @Nullable public static Item item(String modid, String path) {
    return item(rl(modid, path));
  }

  @Nullable public static Item item(ResourceLocation rl) {
    return ForgeRegistries.ITEMS.getValue(rl);
  }

  @Nullable public static Block block(String modid, String path) {
    return block(rl(modid, path));
  }

  @Nullable public static Block block(ResourceLocation rl) {
    return ForgeRegistries.BLOCKS.getValue(rl);
  }

  public static ItemStack gs(RegistryObject<Item> r) {
    return r.get().getDefaultInstance();
  }

  public static String name(Item item) {
    return (ForgeRegistries.ITEMS.containsValue(item)) ?
      Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath() : "";
  }

  public static String name(Block block) {
    return (ForgeRegistries.BLOCKS.containsValue(block)) ?
      Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath() : "";
  }

  public static String name(RegistryObject<?> reg) {
    return reg.getId().getPath();
  }

  public static Ingredient ing(Supplier<? extends ItemLike> i) {
    return Ingredient.of(i.get());
  }

  public static ItemStack enchant(ItemStack stack, Enchantment enchantment, int level) {
    ItemStack enchanted = stack.copy();
    enchanted.enchant(enchantment, level);
    return enchanted;
  }

  public static void dropOrGive(ItemStack stack, Level world, BlockPos drop, Player give) {
    if (DelightfulConfig.GIVE_SLICED_DIRECTLY.get()) {
      ItemHandlerHelper.giveItemToPlayer(give, stack, 0);
    } else {
      Containers.dropItemStack(world, drop.getX(), drop.getY() + 0.25F, drop.getZ(), stack);
    }
  }

  public static boolean hasTagString(ItemStack stack, String key, String value) {
    if (stack.hasTag()) {
      CompoundTag tag = stack.getTag();
      return tag != null && tag.contains(key) && tag.getString(key).equals(value);
    }
    return false;
  }

  public static boolean enabled(String item) {
    return DelightfulConfig.CONFIG.verify(item);
  }

  public static boolean enabled(RegistryObject<Item> item) {
    return enabled(item.getId().getPath());
  }

  public static boolean enabled(Item item) {
    return enabled(Util.name(item));
  }
}

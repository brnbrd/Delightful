package net.brnbrd.delightful;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.brnbrd.delightful.common.DelightfulConfig;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.IConfigured;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
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
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.tags.ITagManager;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Util {
	public static final String EMPTY_STR = "";
	public static final Modid[] EMPTY = new Modid[]{};
	public static final String MC = "minecraft";
	public static final String LOADER = "forge";
	public static final UUID BLOCK_REACH = UUID.fromString("C18598A9-F66A-44E7-9CE1-99B1EE178678");
	public static final UUID ENTITY_REACH = UUID.fromString("61F992E6-276F-4D2B-88A7-823CB64BA459");

	public static ResourceLocation rl(@NotNull String modid, @NotNull String path) {
		return new ResourceLocation(modid, path);
	}

	public static ResourceLocation rl(@NotNull Modid modid, @NotNull String path) {
		return rl(modid.get(), path);
	}

	public static ResourceLocation rl(@NotNull String separated) {
		return new ResourceLocation(separated);
	}

	public static ResourceLocation rl(ItemLike itemLike) {
		if (itemLike instanceof Item item) {
			IForgeRegistry<Item> reg = ForgeRegistries.ITEMS;
			if (reg.containsValue(item)) return reg.getKey(item);
		} else if (itemLike instanceof Block block) {
			IForgeRegistry<Block> reg = ForgeRegistries.BLOCKS;
			if (reg.containsValue(block)) return reg.getKey(block);
		}
		return rl(EMPTY_STR, EMPTY_STR);
	}

	public static ResourceLocation delight(String path) {
		return rl(Delightful.MODID, path);
	}

	public static TagKey<Item> it(String id, String path) {
		return ItemTags.create(rl(id, path));
	}

	public static TagKey<EntityType<?>> et(Modid modid, String path) {
		return TagKey.create(Registries.ENTITY_TYPE, rl(modid, path));
	}

	public static TagKey<EntityType<?>> et(String id, String path) {
		return TagKey.create(Registries.ENTITY_TYPE, rl(id, path));
	}

	// Returns true if tag is empty or null
	public static boolean tagEmpty(@Nullable TagKey<Item> tag) {
		if (tag == null) {
			return true;
		}
		ITagManager<Item> tags = ForgeRegistries.ITEMS.tags();
		return tags != null && (!tags.isKnownTagName(tag) || tags.getTag(tag).isEmpty());
	}

	// Returns true if tag has an entry or is null
	public static boolean tagPopulated(@Nullable TagKey<Item> tag) {
		if (tag == null) {
			return true;
		}
		ITagManager<Item> tags = ForgeRegistries.ITEMS.tags();
		return tags != null && tags.isKnownTagName(tag) && !tags.getTag(tag).isEmpty();
	}

	public static String tagName(TagKey<?> tagKey) {
		return tagKey.location().toString();
	}

	public static MutableComponent tagComponent(TagKey<?> tagKey) {
		return Component.literal(tagName(tagKey));
	}

	public static ObjectArrayList<ItemStack> with(ObjectArrayList<ItemStack> before, ItemStack addition) {
		before.add(addition);
		return before.clone();
	}

	public static ObjectArrayList<ItemStack> with(ObjectArrayList<ItemStack> before, Item addition, int count) {
		return (count < 1) ? before : with(before, new ItemStack(addition, count));
	}

	public static ObjectArrayList<ItemStack> with(ObjectArrayList<ItemStack> before, Item addition, RandomSource rand, int min, int max) {
		if (max < min) {
			return before;
		}
		return with(before, addition, (max == min) ? min : rand.nextIntBetweenInclusive(min, max));
	}

	public static ObjectArrayList<ItemStack> with(ObjectArrayList<ItemStack> before, Item addition) {
		return with(before, addition, 1);
	}

	public static boolean itemExists(ResourceLocation location) {
		return Mods.stringLoaded(location.getNamespace()) && ForgeRegistries.ITEMS.containsKey(location);
	}

	public static boolean blockExists(ResourceLocation location) {
		return Mods.stringLoaded(location.getNamespace()) && ForgeRegistries.BLOCKS.containsKey(location);
	}

	@Nullable
	public static Item item(ResourceLocation rl) {
		return ForgeRegistries.ITEMS.getValue(rl);
	}

	@Nullable
	public static Item item(String id, String path) {
		return item(rl(id, path));
	}

	@NotNull
	public static Item item(ResourceLocation location, @NotNull Item backup) {
		if (itemExists(location)) {
			Item item = item(location);
			if (item != null) {
				return item;
			}
		}
		return backup;
	}

	@NotNull
	public static Item item(ResourceLocation location, @NotNull Supplier<Item> backup) {
		return item(location, backup.get());
	}

	@NotNull
	public static ItemStack itemStack(ResourceLocation location, @NotNull ItemStack backup) {
		if (itemExists(location)) {
			Item returnItem = item(location);
			if (returnItem != null) {
				return new ItemStack(returnItem);
			}
		}
		return backup;
	}

	public static boolean itemStackIs(ItemStack stack, ResourceLocation location) {
		return itemExists(location) && stack.is(item(location));
	}

	@Nullable
	public static Block block(Modid modid, String path) {
		return block(rl(modid, path));
	}

	@Nullable
	public static Block block(String id, String path) {
		return block(rl(id, path));
	}

	@Nullable
	public static Block block(ResourceLocation rl) {
		return ForgeRegistries.BLOCKS.getValue(rl);
	}

	public static boolean effectExists(ResourceLocation effect) {
		return Mods.stringLoaded(effect.getNamespace()) && ForgeRegistries.MOB_EFFECTS.containsKey(effect);
	}

	@Nullable
	private static MobEffect getBackup(@Nullable MobEffect[] backup) {
		return (
			(backup != null && backup.length > 0) ?
			backup[0] :
			null
		);
	}

	@Nullable
	public static MobEffect effect(ResourceLocation effLocation, MobEffect... backup) {
		return (
			effectExists(effLocation) ?
			ForgeRegistries.MOB_EFFECTS.getValue(effLocation) :
			getBackup(backup)
		);
	}

	@Nullable
	public static MobEffect effect(String id, String name, MobEffect... backup) {
		return effect(Util.rl(id, name), backup);
	}

	@Nullable
	public static MobEffect effect(Modid modid, String name, MobEffect... backup) {
		return (
			modid.loaded() ?
			effect(Util.rl(modid, name), backup) :
			getBackup(backup)
		);
	}

	// Will not add if effect is null
	public static void addEffect(LivingEntity entity, @Nullable MobEffect effect, int duration, int amp) {
		if (effect != null) entity.addEffect(new MobEffectInstance(effect, duration, amp));
	}

	public static void addEffect(LivingEntity entity, String modid, String name, int duration, int amp, MobEffect... backup) {
		MobEffect me = effect(modid, name, backup);
		if (me != null) addEffect(entity, me, duration, amp);
	}

	public static ItemStack getStack(@Nullable Supplier<@Nullable Item> r, int... count) { // Only considers first vararg entry
		if (r == null || r.get() == null) return ItemStack.EMPTY;
		return new ItemStack(Objects.requireNonNull(r.get()), count.length > 0 ? count[0] : 1);
	}

	public static String nameSpace(ItemLike itemLike) {
		return rl(itemLike).getNamespace();
	}

	public static String name(ItemLike itemLike) {
		return rl(itemLike).getPath();
	}

	public static String name(RegistryObject<?> reg) {
		return reg.getId().getPath();
	}

	public static String nameSpace(ItemStack stack) {
		return nameSpace(stack.getItem());
	}

	public static String name(ItemStack stack) {
		return name(stack.getItem());
	}

	// modid:item
	public static String id(ItemLike item) {
		return rl(item).toString();
	}

	public static Ingredient ing(Supplier<? extends ItemLike> i) {
		return Ingredient.of(i.get());
	}

	public static ItemStack enchant(ItemStack stack, Enchantment enchantment, int level) {
		ItemStack enchanted = stack.copy();
		enchanted.enchant(enchantment, level);
		return enchanted;
	}

	public static void drop(Level level, ItemStack stack, BlockPos pos, Direction dir) {
		RandomSource random = level.getRandom();
		ItemEntity dropItem = new ItemEntity(
			level,
			pos.getX() + 0.5D + dir.getStepX() * 0.65D,
			pos.getY() + 0.1D,
			pos.getZ() + 0.5D + dir.getStepZ() * 0.65D,
			stack
		);
		dropItem.setDeltaMovement(
			0.05D * dir.getStepX() + random.nextDouble() * 0.02D,
			0.05D,
			0.05D * dir.getStepZ() + random.nextDouble() * 0.02D
		);
		level.addFreshEntity(dropItem);
	}

	public static void dropOrGive(ItemStack stack, Level level, BlockPos pos, Direction direction, Player give) {
		if (stack == null || stack.isEmpty() || stack.getCount() < 1) return;
		if (DelightfulConfig.GIVE_SLICED_DIRECTLY.get()) {
			ItemHandlerHelper.giveItemToPlayer(give, stack, 0);
		} else {
			drop(level, stack, pos, direction);
		}
	}

	public static void dropOrGive(ItemStack stack, Level level, BlockPos pos, Player give) {
		dropOrGive(stack, level, pos, give.getDirection().getOpposite(), give);
	}

	public static boolean hasTagString(ItemStack stack, String key, String value) {
		if (stack.hasTag()) {
			CompoundTag tag = stack.getTag();
			return tag != null && tag.contains(key) && tag.getString(key).equals(value);
		}
		return false;
	}

	public static boolean configEnabled(String item) {
		return DelightfulConfig.CONFIG.verify(item);
	}

	public static boolean configEnabled(Item item) {
		return configEnabled(Util.name(item));
	}

	public static boolean enabled(Item item) {
		return (item instanceof IConfigured conf) ? conf.enabled() : configEnabled(item);
	}

	public static boolean enabled(Supplier<Item> item) {
		return enabled(item.get());
	}

	public static boolean enabled(String item) {
		return (
			DelightfulItems.ITEMS.getEntries().stream()
				.filter(reg -> reg.getId().getPath().equals(item))
				.map(Util::enabled)
				.findAny()
				.orElse(configEnabled(item))
		);
	}

	public static MutableComponent translation(String modid, String key) {
		return Component.translatable(modid + "." + key);
	}

	public static MutableComponent translation(String modid, String prefix, String key) {
		return Component.translatable(prefix + "." + modid + "." + key);
	}

	public static MutableComponent description(String key) {
		return Component.translatable("desc." + Delightful.MODID + "." + key);
	}

	public static MutableComponent tooltip(String key) {
		return Component.translatable("tooltip." + Delightful.MODID + "." + key);
	}
}
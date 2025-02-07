package net.brnbrd.delightful;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.brnbrd.delightful.common.DelightfulConfig;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.IConfigured;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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
import net.minecraftforge.registries.tags.ITagManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

public class Util {

	public static final String LOADER = "forge";
	public static final UUID BLOCK_REACH = UUID.fromString("C18598A9-F66A-44E7-9CE1-99B1EE178678");
	public static final UUID ENTITY_REACH = UUID.fromString("61F992E6-276F-4D2B-88A7-823CB64BA459");

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
		return ForgeRegistries.ITEMS.containsKey(location);
	}

	@Nullable
	public static Item item(ResourceLocation rl) {
		return ForgeRegistries.ITEMS.getValue(rl);
	}

	@Nullable
	public static Item item(String modid, String path) {
		return item(rl(modid, path));
	}

	@NotNull
	public static Item item(ResourceLocation location, @NotNull Item backup) {
		return itemExists(location) ? Objects.requireNonNull(item(location)) : backup;
	}

	@NotNull
	public static Item item(ResourceLocation location, @NotNull Supplier<Item> backup) {
		return item(location, backup.get());
	}

	@NotNull
	public static ItemStack itemStack(ResourceLocation location, @NotNull ItemStack backup) {
		return (
			itemExists(location) ?
			new ItemStack(Objects.requireNonNull(item(location))) :
			backup
		);
	}

	public static boolean itemStackIs(ItemStack stack, ResourceLocation location) {
		return (
			Mods.loaded(location.getNamespace()) &&
			itemExists(location) &&
			stack.is(item(location))
		);
	}

	@Nullable
	public static Block block(String modid, String path) {
		return block(rl(modid, path));
	}

	@Nullable
	public static Block block(ResourceLocation rl) {
		return ForgeRegistries.BLOCKS.getValue(rl);
	}

	public static boolean effectExists(ResourceLocation effect) {
		return Mods.loaded(effect.getNamespace()) && ForgeRegistries.MOB_EFFECTS.containsKey(effect);
	}

	@Nullable
	public static MobEffect effect(String modid, String name, @Nullable MobEffect backup) {
		ResourceLocation effLocation = Util.rl(modid, name);
		return (
			effectExists(effLocation) ?
			ForgeRegistries.MOB_EFFECTS.getValue(effLocation) :
			backup
		);
	}

	public static void addEffect(LivingEntity entity, @Nullable MobEffect effect, int duration, int amp) {
		if (effect != null) {
			entity.addEffect(new MobEffectInstance(effect, duration, amp));
		}
	}

	public static void addEffect(LivingEntity entity, String modid, String name, int duration, int amp, MobEffect... backup) {
		MobEffect me = backup.length >= 1 ? effect(modid, name, backup[0]) : effect(modid, name, null);
		if (me != null) {
			addEffect(entity, me, duration, amp);
		}
	}

	public static ItemStack gs(RegistryObject<Item> r, int count) {
		return new ItemStack(r.get(), count);
	}

	public static ItemStack gs(RegistryObject<Item> r) {
		return gs(r, 1);
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

	public static boolean configEnabled(String item) {
		return DelightfulConfig.CONFIG.verify(item);
	}

	public static boolean configEnabled(Item item) {
		return configEnabled(Util.name(item));
	}

	public static boolean enabled(Item item) {
		return (item instanceof IConfigured conf) ? conf.enabled() : configEnabled(item);
	}

	public static boolean enabled(RegistryObject<Item> item) {
		return enabled(item.get());
	}

	public static boolean enabled(String item) {
		return (
			DelightfulItems.ITEMS.getEntries()
				.stream()
				.filter(reg -> reg.getId().getPath().equals(item))
				.map(Util::enabled)
				.findAny()
				.orElse(configEnabled(item))
		);
	}
}
package net.brdle.delightful.common.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import vectorwing.farmersdelight.FarmersDelight;

public class DelightfulItemTags {

	// Delightful

	// FarmersDelight
	public static final TagKey<Item> CABINETS = bind(FarmersDelight.MODID, "cabinets");
	public static final TagKey<Item> CABINETS_WOODEN = bind(FarmersDelight.MODID, "cabinets/wooden");
	public static final TagKey<Item> CABINETS_STONE = bind(FarmersDelight.MODID, "cabinets/stone");

	// Forge
	public static final TagKey<Item> FRUITS = bind("forge", "fruits");
	public static final TagKey<Item> FRUITS_APPLE = bind("forge", "fruits/apple");
	public static final TagKey<Item> FRUITS_KIWI = bind("forge", "fruits/kiwi");
	public static final TagKey<Item> FRUITS_MELON = bind("forge", "fruits/melon");
	public static final TagKey<Item> FRUITS_SWEET_BERRIES = bind("forge", "fruits/sweet_berries");
	public static final TagKey<Item> FRUITS_GLOW_BERRIES = bind("forge", "fruits/glow_berries");
	public static final TagKey<Item> FRUITS_SALMONBERRY = bind("forge", "fruits/salmonberry");
	public static final TagKey<Item> FRUITS_SWEET = bind("forge", "fruits/sweet");
	public static final TagKey<Item> FRUITS_PRICKLY_PEAR = bind("forge", "fruits/prickly_pear");
	public static final TagKey<Item> COOKED_PRICKLY_PEAR = bind("ecologics", "cooked_prickly_pear");
	public static final TagKey<Item> FRUITS_BERRIES = bind("forge", "fruits/berries");
	public static final TagKey<Item> CHEESE = bind("forge", "cheese");
	public static final TagKey<Item> CHEESE_OR_MILK = bind("forge", "cheese_or_milk");
	public static final TagKey<Item> COOKED_CRAB = bind("forge", "cooked_crab");
	public static final TagKey<Item> NUTS = bind("forge", "nuts");
	public static final TagKey<Item> NUTS_WALNUT = bind("forge", "nuts/walnut");
	public static final TagKey<Item> SUGAR = bind("forge", "sugar");
	public static final TagKey<Item> WATER = bind("forge", "water");
	public static final TagKey<Item> INGOTS_STEEL = bind("forge", "ingots/steel");
	public static final TagKey<Item> TEA_LEAVES_GREEN = bind("forge", "tea_leaves/green");
	public static final TagKey<Item> JELLY = bind("forge", "jelly");

	// Minecraft


	private static TagKey<Item> bind(String modid, String name) {
		return ItemTags.create(new ResourceLocation(modid, name));
	}
}

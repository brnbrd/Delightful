package net.brnbrd.delightful.common;

import com.google.common.collect.ImmutableList;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.minecraftforge.common.ForgeConfigSpec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DelightfulConfig {
	public static final DelightfulConfig CONFIG;
	public static final ForgeConfigSpec SPEC;
	private static final ImmutableList<String> DEFAULT_DISABLED = ImmutableList.of(
			Util.name(Knives.COPPER),
			Util.name(Knives.AMETHYST),
			Util.name(Knives.EMERALD),
			Util.name(Knives.LAPIS_LAZULI),
			Util.name(Knives.NETHER_QUARTZ)
	);
	private static final ImmutableList<String> BAKED_GOODS = ImmutableList.of(
		Util.name(DelightfulItems.PUMPKIN_PIE_SLICE),
		Util.name(DelightfulItems.BLUEBERRY_PIE_SLICE),
		Util.name(DelightfulItems.GREEN_APPLE_PIE_SLICE),
		Util.name(DelightfulItems.GLOOMGOURD_PIE_SLICE),
		Util.name(DelightfulItems.SOURCE_BERRY_PIE_SLICE),
		Util.name(DelightfulItems.CHORUS_PIE_SLICE),
		Util.name(DelightfulItems.MULBERRY_PIE_SLICE),
		Util.name(DelightfulItems.PASSION_FRUIT_TART_SLICE)
	);
	public static ForgeConfigSpec.BooleanValue CRAFT_NUT_MILK;
	public static ForgeConfigSpec.BooleanValue COOK_CLOVER_HONEY;
	public static ForgeConfigSpec.BooleanValue GIVE_SLICED_DIRECTLY;
	public static ForgeConfigSpec.BooleanValue MELON_JUICING;

	static {
		var pair = new ForgeConfigSpec.Builder().configure(DelightfulConfig::new);
		SPEC = pair.getRight();
		CONFIG = pair.getLeft();
	}

	private final Map<String, ForgeConfigSpec.BooleanValue> stuff = new HashMap<>();

	DelightfulConfig(ForgeConfigSpec.Builder builder) {
		List<String> items = DelightfulItems.ITEMS.getEntries().stream()
				.map(obj -> obj.getId().getPath())
				.sorted()
				.toList();
		builder.comment(" Let's Configure Delightful");
		stuff.clear();
		builder.push("Knives");
		items.stream()
				.filter(path -> path.contains("_knife"))
				.forEach(knife -> put(builder, stuff, knife, !DEFAULT_DISABLED.contains(knife)));
		builder.pop();
		builder.push("Baked Good Overhauls");
		items.stream()
				.filter(BAKED_GOODS::contains)
				.forEach(knife -> put(builder, stuff, knife, !DEFAULT_DISABLED.contains(knife)));
		builder.pop();
		builder.push("Registry & Recipes");
		items.stream()
				.filter(path -> !path.contains("_knife") && !BAKED_GOODS.contains(path))
				.forEach(not -> put(builder, stuff, not, !DEFAULT_DISABLED.contains(not)));
		CRAFT_NUT_MILK = builder
				.comment("Allow cooking milk from nuts")
				.define("nut_milk", true);
		stuff.put("nut_milk", CRAFT_NUT_MILK);
		COOK_CLOVER_HONEY = builder
				.comment("Allow cooking honey from honey and clovers")
				.define("clover_honey", false);
		stuff.put("clover_honey", COOK_CLOVER_HONEY);
		GIVE_SLICED_DIRECTLY = builder
				.comment("Give items that are sliced off of blocks directly to player's inventory instead of dropping")
				.define("give_sliced_directly", false);
		MELON_JUICING = builder
				.comment("Allow sliced melons to be juiced in-world (right click)")
				.define("melon_juicing", true);
		builder.pop();
	}

	private static void put(ForgeConfigSpec.Builder builder, Map<String, ForgeConfigSpec.BooleanValue> map, String name, boolean def) {
		map.put(name, builder.define(name, def));
	}

	public boolean verify(String item) {
		return CONFIG.stuff.containsKey(item) && CONFIG.stuff.get(item).get();
	}
}
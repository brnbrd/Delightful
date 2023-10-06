package net.brnbrd.delightful.common;

import com.google.common.collect.ImmutableList;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.knife.Knives;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.RegistryObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DelightfulConfig {

    public static final DelightfulConfig CONFIG;
    public static final ForgeConfigSpec SPEC;
    private final Map<String, ForgeConfigSpec.BooleanValue> stuff = new HashMap<>();
    public static ForgeConfigSpec.IntValue CHANCE_WILD_SALMONBERRIES;
    public static ForgeConfigSpec.IntValue CHANCE_MINI_MELON;
    public static ForgeConfigSpec.IntValue CHANCE_CANTALOUPE;
    public static ForgeConfigSpec.BooleanValue CRAFT_NUT_MILK;
    public static ForgeConfigSpec.BooleanValue COOK_CLOVER_HONEY;
    public static ForgeConfigSpec.BooleanValue GIVE_SLICED_DIRECTLY;
    public static ForgeConfigSpec.BooleanValue MELON_JUICING;
    public static ForgeConfigSpec.BooleanValue USE_MILK_TAG;
    private static final ImmutableList<String> disabled_by_default_items = ImmutableList.of(
        Util.name(Knives.COPPER),
        Util.name(Knives.BONE),
        Util.name(Knives.AMETHYST),
        Util.name(Knives.EMERALD),
        Util.name(Knives.LAPIS_LAZULI),
        Util.name(DelightfulItems.RAW_GOAT),
        Util.name(DelightfulItems.COOKED_GOAT),
        Util.name(DelightfulItems.GREEN_TEA_LEAF)
    );

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
                .forEach(knife -> put(builder, stuff, knife, !disabled_by_default_items.contains(knife)));
        builder.pop();
        builder.push("Pie Overhauls");
            items.stream()
                .filter(path -> path.contains("_pie_slice"))
                .forEach(knife -> put(builder, stuff, knife, !disabled_by_default_items.contains(knife)));
        builder.pop();
        builder.push("Registry & Recipes");
            items.stream()
                .filter(path -> !path.contains("_knife") && !path.contains("_pie_slice"))
                .forEach(not -> put(builder, stuff, not, !disabled_by_default_items.contains(not)));
            USE_MILK_TAG = builder
                .comment("Force the replacement of forge:cheese item tag in recipes with forge:milk")
                .define("use_milk_tag", false);
            stuff.put("use_milk_tag", USE_MILK_TAG);
            CRAFT_NUT_MILK = builder
              .comment("Allow cooking milk from nuts")
              .define("nut_milk", true);
            stuff.put("nut_milk", CRAFT_NUT_MILK);
            COOK_CLOVER_HONEY = builder
              .comment("Allow cooking honey from honey and clovers")
              .define("clover_honey", true);
            stuff.put("clover_honey", COOK_CLOVER_HONEY);
            GIVE_SLICED_DIRECTLY = builder
                .comment("Give items that are sliced off of blocks directly to player's inventory instead of dropping")
                .define("give_sliced_directly", false);
            MELON_JUICING = builder
                .comment("Allow sliced melons to be juiced in-world (right click)")
                .define("melon_juicing", true);
        builder.pop();
        builder.push("Generation");
            CHANCE_WILD_SALMONBERRIES = builder
              .comment("Chance of generating clusters. Smaller value = more frequent (once every ...). To disable, set the item Salmonberries to false above.")
              .defineInRange("chance_wild_salmonberries", 35, 0, Integer.MAX_VALUE);
            CHANCE_MINI_MELON = builder
              .comment("Chance of generating clusters. Smaller value = more frequent (once every ...). To disable, set the item Mini Melon to false above.")
              .defineInRange("chance_mini_melon", 35, 0, Integer.MAX_VALUE);
            CHANCE_CANTALOUPE = builder
            .comment("Chance of generating clusters. Smaller value = more frequent (once every ...). To disable, set the item Cantaloupe to false above.")
            .defineInRange("chance_cantaloupe", 55, 0, Integer.MAX_VALUE);
        builder.pop();
    }

    private static void put(ForgeConfigSpec.Builder builder, Map<String, ForgeConfigSpec.BooleanValue> map, String name, boolean def) {
        map.put(name, builder.define(name, def));
    }

    public boolean verify(String item) {
        return CONFIG.stuff.get(item).get();
    }

    public boolean verify(RegistryObject<Item> item) {
        return verify(item.getId().getPath());
    }

    public boolean verify(Item item) {
        return verify(Util.name(item));
    }

    static {
        var pair = new ForgeConfigSpec.Builder().configure(DelightfulConfig::new);
        SPEC = pair.getRight();
        CONFIG = pair.getLeft();
    }
}
package net.brdle.delightful.common.config;

import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.knife.DelightfulKnifeItem;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class DelightfulConfig {

    public static final DelightfulConfig CONFIG;
    public static final ForgeConfigSpec SPEC;
    public final Map<String, ForgeConfigSpec.BooleanValue> stuff;
    public static ForgeConfigSpec.IntValue CHANCE_WILD_SALMONBERRIES;
    public static ForgeConfigSpec.IntValue CHANCE_MINI_MELON;
    public static ForgeConfigSpec.BooleanValue CRAFT_NUT_MILK;
    public static ForgeConfigSpec.BooleanValue COOK_CLOVER_HONEY;

    DelightfulConfig(ForgeConfigSpec.Builder builder) {
        var items = DelightfulItems.ITEMS.getEntries();
        builder.comment(" Let's Configure Delightful");
        stuff = new HashMap<>();
        builder.push("Knives");
            items.stream()
                .map(obj -> obj.getId().getPath())
                .filter(path -> path.contains("_knife"))
                .sorted()
                .forEach(knife -> put(builder, stuff, knife, switch (knife) {
                    case "disabled_knife" -> false;
                    default -> true;
                }));
        builder.pop();
        builder.push("Cabinets");
            items.stream()
                    .map(obj -> obj.getId().getPath())
                    .filter(path -> path.contains("_cabinet"))
                    .sorted()
                    .forEach(cabinet -> put(builder, stuff, cabinet, true));
        builder.pop();
        builder.push("Foods and Other Items");
            items.stream()
                    .map(obj -> obj.getId().getPath())
                    .filter(path -> !path.contains("_knife") && !path.contains("_cabinet") && !path.equals("pizza"))
                    .sorted()
                    .forEach(not -> put(builder, stuff, not, true));
            CRAFT_NUT_MILK = builder
              .comment("Allow cooking milk from nuts")
              .define("nut_milk", true);
            stuff.put("nut_milk", CRAFT_NUT_MILK);
            COOK_CLOVER_HONEY = builder
              .comment("Allow cooking honey from honey and clovers")
              .define("clover_honey", true);
            stuff.put("clover_honey", COOK_CLOVER_HONEY);
        builder.pop();
        builder.push("Generation");
            CHANCE_WILD_SALMONBERRIES = builder
              .comment("Chance of generating clusters. Smaller value = more frequent.")
              .defineInRange("chance_wild_salmonberries", 30, 0, Integer.MAX_VALUE);
            CHANCE_MINI_MELON = builder
              .comment("Chance of generating clusters. Smaller value = more frequent.")
              .defineInRange("chance_mini_melon", 30, 0, Integer.MAX_VALUE);
        builder.pop();
    }

    private static void put(ForgeConfigSpec.Builder builder, Map<String, ForgeConfigSpec.BooleanValue> map, String name, boolean def) {
        map.put(name, builder.define(name, def));
    }

    static {
        var pair = new ForgeConfigSpec.Builder().configure(DelightfulConfig::new);
        SPEC = pair.getRight();
        CONFIG = pair.getLeft();
    }
}
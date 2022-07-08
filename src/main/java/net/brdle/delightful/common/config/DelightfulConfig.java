package net.brdle.delightful.common.config;

import net.brdle.delightful.common.item.DelightfulItems;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import java.util.HashMap;
import java.util.Map;

public class DelightfulConfig {

    public static final DelightfulConfig CONFIG;
    public static final ForgeConfigSpec SPEC;

    public final Map<String, ForgeConfigSpec.ConfigValue<Boolean>> knives;
    public final Map<String, ForgeConfigSpec.ConfigValue<Boolean>> foods;
    public final Map<String, ForgeConfigSpec.ConfigValue<Boolean>> blocks;

    DelightfulConfig(ForgeConfigSpec.Builder builder) {
        builder.comment(" Let's Configure Delightful");
        builder.push("Knives");
            knives = new HashMap<>();
            DelightfulItems.knives.stream()
                    .map(r -> r.getId().getPath())
                    .sorted()
                    .forEach(knife -> put(builder, knives, knife, switch (knife) {
                        case "disabled_knife" -> false;
                        default -> true;
                    }));
        builder.pop();
        builder.push("Foods");
            foods = new HashMap<>();
            put(builder, foods, "cheeseburger", true);
            put(builder, foods, "deluxe_cheeseburger", true);
            put(builder, foods, "ender_nectar", true);
            put(builder, foods, "crab_rangoon", true);
            put(builder, foods, "prickly_pear_juice", true);
            put(builder, foods, "chunkwich", true);
        builder.pop();
        builder.push("Blocks");
            blocks = new HashMap<>();
            put(builder, blocks, "basalt_cabinet", true);
            put(builder, blocks, "quartz_cabinet", true);
        builder.pop();
    }

    private static void put(ForgeConfigSpec.Builder builder, Map<String, ForgeConfigSpec.ConfigValue<Boolean>> map, String name, boolean def) {
        map.put(name, builder
                //.comment(name + " enabled") // redundant info
                .define(name, def));
    }

    static {
        Pair<DelightfulConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(DelightfulConfig::new);
        SPEC = pair.getRight();
        CONFIG = pair.getLeft();
    }
}
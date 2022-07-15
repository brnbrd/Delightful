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
    public final Map<String, ForgeConfigSpec.ConfigValue<Boolean>> stuff;

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
        builder.push("Other");
            items.stream()
                    .map(obj -> obj.getId().getPath())
                    .filter(path -> !path.contains("_knife") && !path.contains("_cabinet") && !path.equals("pizza"))
                    .sorted()
                    .forEach(not -> put(builder, stuff, not, true));
        builder.pop();
    }

    private static void put(ForgeConfigSpec.Builder builder, Map<String, ForgeConfigSpec.ConfigValue<Boolean>> map, String name, boolean def) {
        map.put(name, builder.define(name, def));
    }

    static {
        var pair = new ForgeConfigSpec.Builder().configure(DelightfulConfig::new);
        SPEC = pair.getRight();
        CONFIG = pair.getLeft();
    }
}
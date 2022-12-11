package net.brdle.delightful.common.item;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.item.food.CaffeinatedItem;
import net.brdle.delightful.common.item.food.DrinkItem;
import net.brdle.delightful.common.item.food.EnderNectarItem;
import net.brdle.delightful.common.item.food.FoodValues;
import net.brdle.delightful.compat.ArsNouveauCompat;
import net.brdle.delightful.compat.BYGCompat;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.registry.ModEffects;
import java.util.function.Supplier;

public class DelightfulItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Delightful.MODID);

    // Crafting Items (can be food)
    public static final RegistryObject<Item> ANIMAL_FAT = registerFood("animal_fat", FoodValues.ANIMAL_FAT);
    public static final RegistryObject<Item> ANIMAL_OIL_BOTTLE = registerItem("animal_oil_bottle", () -> new FurnaceFuelItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).tab(FarmersDelight.CREATIVE_TAB), 3200));
    public static final RegistryObject<Item> ACORN = registerFood("acorn", FoodValues.ACORN);
    public static final RegistryObject<Item> SALMONBERRIES = registerFood("salmonberries", FoodValues.SALMONBERRIES);
    public static final RegistryObject<Item> SALMONBERRY_PIPS = registerItem("salmonberry_pips", () -> new ItemNameBlockItem(DelightfulBlocks.SALMONBERRY_BUSH.get(), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> SALMONBERRY_PIE = registerItem("salmonberry_pie", () -> new BlockItem(DelightfulBlocks.SALMONBERRY_PIE.get(), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> SALMONBERRY_PIE_SLICE = registerFood("salmonberry_pie_slice", vectorwing.farmersdelight.common.FoodValues.PIE_SLICE);
    public static final RegistryObject<Item> PUMPKIN_PIE_SLICE = registerFood("pumpkin_pie_slice", vectorwing.farmersdelight.common.FoodValues.PIE_SLICE);
    public static final RegistryObject<Item> SOURCE_BERRY_PIE_SLICE = registerCompatFood(ArsNouveauCompat.slice, ArsNouveauCompat.getPieSlice().get(),
        ArsNouveauCompat.modid);
    public static final RegistryObject<Item> BLUEBERRY_PIE_SLICE = registerCompatFood(BYGCompat.blueberry_pie_slice, BYGCompat.BLUEBERRY_PIE_SLICE.get(),
        BYGCompat.modid);
    public static final RegistryObject<Item> GREEN_APPLE_PIE_SLICE = registerCompatFood(BYGCompat.green_apple_pie_slice, BYGCompat.GREEN_APPLE_PIE_SLICE.get(),
        BYGCompat.modid);
    public static final RegistryObject<Item> NIGHTSHADE_BERRY_PIE_SLICE = registerCompatFood(BYGCompat.nightshade_berry_pie_slice, BYGCompat.NIGHTSHADE_BERRY_PIE_SLICE.get(),
        BYGCompat.modid);
    public static final RegistryObject<Item> CRIMSON_BERRY_PIE_SLICE = registerCompatFood(BYGCompat.crimson_berry_pie_slice, BYGCompat.CRIMSON_BERRY_PIE_SLICE.get(),
        BYGCompat.modid);
    public static final RegistryObject<Item> GREEN_TEA_LEAF = registerFood("green_tea_leaf",
        FoodValues.GREEN_TEA_LEAF);
    public static final RegistryObject<Item> MATCHA = registerItem("matcha", () -> new DescriptItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB), Component.translatable("delightful.matcha.desc").withStyle(ChatFormatting.GRAY)));
    public static final RegistryObject<Item> MATCHA_ICE_CREAM = registerItem("matcha_ice_cream", () -> new BowlFoodItem((new Item.Properties()).food(FoodValues.MATCHA_ICE_CREAM).craftRemainder(Items.BOWL).stacksTo(1).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> SALMONBERRY_ICE_CREAM = registerItem("salmonberry_ice_cream", () -> new BowlFoodItem((new Item.Properties()).food(FoodValues.SALMONBERRY_ICE_CREAM).craftRemainder(Items.BOWL).stacksTo(1).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> COCONUT_CURRY = registerItem("coconut_curry", () -> new BowlFoodItem(compat("ecologics").food(FoodValues.COCONUT_CURRY).stacksTo(16).craftRemainder(Items.BOWL)));
    public static final RegistryObject<Item> SINIGANG = registerItem("sinigang", () -> new BowlFoodItem((new Item.Properties()).food(FoodValues.COCONUT_CURRY).craftRemainder(Items.BOWL).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> CHOPPED_CLOVER = registerCompatFood("chopped_clover", FoodValues.CHOPPED_CLOVER,
        "biomesoplenty");
    public static final RegistryObject<Item> CACTUS_FLESH = registerFood("cactus_flesh", FoodValues.CACTUS_FLESH);
    public static final RegistryObject<Item> CACTUS_STEAK = registerFood("cactus_steak", FoodValues.CACTUS_STEAK);
    public static final RegistryObject<Item> FIELD_SALAD = registerFood("field_salad", FoodValues.FIELD_SALAD, Items.BOWL);
    public static final RegistryObject<Item> LAVENDER_TEA = registerItem("lavender_tea", () -> new CaffeinatedItem(compat("biomesoplenty").craftRemainder(Items.GLASS_BOTTLE).stacksTo(16), () -> MobEffects.SATURATION, 100, 1, 2.0F, 1));
    public static final RegistryObject<Item> AZALEA_TEA = registerItem("azalea_tea", () -> new CaffeinatedItem(compat("ecologics").craftRemainder(Items.GLASS_BOTTLE).stacksTo(16), () -> MobEffects.SATURATION, 100, 1, 2.0F, 1));
    public static final RegistryObject<Item> PRICKLY_PEAR_JUICE = registerItem("prickly_pear_juice", () -> new DrinkItem(compat("ecologics").craftRemainder(Items.GLASS_BOTTLE).stacksTo(16), () -> MobEffects.SATURATION, 400, 1));
    public static final RegistryObject<Item> ENDER_NECTAR = registerItem("ender_nectar", () -> new EnderNectarItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> MATCHA_LATTE = registerItem("matcha_latte", () -> new CaffeinatedItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(FarmersDelight.CREATIVE_TAB), ModEffects.COMFORT, 1200, 1, 2.0F, 7));
    public static final RegistryObject<Item> BERRY_MATCHA_LATTE = registerItem("berry_matcha_latte", () -> new CaffeinatedItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(FarmersDelight.CREATIVE_TAB), ModEffects.COMFORT, 1800, 1, 2.0F, 9));
    public static final RegistryObject<Item> JELLY_BOTTLE = registerItem("jelly_bottle", () -> new ConsumableItem((new Item.Properties()).food(FoodValues.JELLY_BOTTLE).craftRemainder(Items.GLASS_BOTTLE).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> GLOW_JELLY_BOTTLE = registerItem("glow_jelly_bottle", () -> new ConsumableItem((new Item.Properties()).food(FoodValues.GLOW_JELLY_BOTTLE).craftRemainder(Items.GLASS_BOTTLE).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> NUT_BUTTER_BOTTLE = registerItem("nut_butter_bottle", () -> new ConsumableItem((new Item.Properties()).food(FoodValues.NUT_BUTTER_BOTTLE).craftRemainder(Items.GLASS_BOTTLE).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> NUT_BUTTER_AND_JELLY_SANDWICH = registerItem("nut_butter_and_jelly_sandwich", () -> new ConsumableItem((new Item.Properties()).food(FoodValues.NUT_BUTTER_AND_JELLY_SANDWICH).tab(FarmersDelight.CREATIVE_TAB), true));
    public static final RegistryObject<Item> CHEESEBURGER = registerItem("cheeseburger", () -> new ConsumableItem((new Item.Properties()).food(FoodValues.CHEESEBURGER).tab(FarmersDelight.CREATIVE_TAB), true));
    public static final RegistryObject<Item> DELUXE_CHEESEBURGER = registerItem("deluxe_cheeseburger", () -> new ConsumableItem((new Item.Properties()).food(FoodValues.DELUXE_CHEESEBURGER).tab(FarmersDelight.CREATIVE_TAB), true));
    public static final RegistryObject<Item> CHUNKWICH = registerCompatFood("chunkwich", FoodValues.CHUNKWICH,
        "rottenleather");
    public static final RegistryObject<Item> CHUNK_NUGGET = registerCompatFood("chunk_nugget", FoodValues.CHUNK_NUGGET,
        "rottenleather");
    public static final RegistryObject<Item> ROCK_CANDY = registerFood("rock_candy", FoodValues.ROCK_CANDY, Items.STICK);
    public static final RegistryObject<Item> MARSHMALLOW_STICK = registerFood("marshmallow_stick", FoodValues.MARSHMALLOW_STICK, Items.STICK);
    public static final RegistryObject<Item> COOKED_MARSHMALLOW_STICK = registerFood("cooked_marshmallow_stick", FoodValues.COOKED_MARSHMALLOW_STICK, Items.STICK);
    public static final RegistryObject<Item> SMORE = registerFood("smore", FoodValues.SMORE);
    public static final RegistryObject<Item> CRAB_RANGOON = registerFood("crab_rangoon", FoodValues.CRAB_RANGOON);
    public static final RegistryObject<Item> HONEY_GLAZED_WALNUT = registerFood("honey_glazed_walnut", FoodValues.HONEY_GLAZED_WALNUT);
    public static final RegistryObject<Item> VENISON_CHOPS = registerFood("venison_chops", FoodValues.VENISON_CHOPS);
    public static final RegistryObject<Item> COOKED_VENISON_CHOPS = registerFood("cooked_venison_chops", FoodValues.COOKED_VENISON_CHOPS);
    public static final RegistryObject<Item> RAW_GOAT = registerFood("raw_goat", FoodValues.RAW_GOAT);
    public static final RegistryObject<Item> COOKED_GOAT = registerFood("cooked_goat", FoodValues.COOKED_GOAT);
    public static final RegistryObject<Item> CANTALOUPE_SLICE = registerFood("cantaloupe_slice", FoodValues.CANTALOUPE_SLICE);
    public static final RegistryObject<Item> CANTALOUPE = registerItem("cantaloupe", () ->
        new BlockItem(DelightfulBlocks.CANTALOUPE.get(), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> MINI_MELON = registerItem("mini_melon", () ->
        new BlockItem(DelightfulBlocks.MINI_MELON.get(), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> SALMONBERRY_SACK = registerItem("salmonberry_sack", () ->
        new BlockItem(DelightfulBlocks.SALMONBERRY_SACK.get(), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> ACORN_SACK = registerItem("acorn_sack", () ->
        new BlockItem(DelightfulBlocks.ACORN_SACK.get(), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));

    // Cabinets
    public static final RegistryObject<Item> QUARTZ_CABINET = registerItem("quartz_cabinet",
            () -> new BlockItem(DelightfulBlocks.QUARTZ_CABINET.get(), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> BASALT_CABINET = registerItem("basalt_cabinet",
            () -> new BlockItem(DelightfulBlocks.BASALT_CABINET.get(), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));

    // Registers a food to Farmer's Delight tab, optional craftRemainder
    public static RegistryObject<Item> registerFood(String name, FoodProperties food, Item... remainder) {
        if (remainder.length > 0) {
            if (remainder[0].equals(Items.BOWL)) {
                return registerItem(name,
                    () -> new BowlFoodItem((new Item.Properties()).food(food).craftRemainder(Items.BOWL).tab(FarmersDelight.CREATIVE_TAB)));
            }
            return registerItem(name,
                () -> new ConsumableItem((new Item.Properties()).food(food).craftRemainder(remainder[0]).tab(FarmersDelight.CREATIVE_TAB)));
        }
        return registerItem(name, (new Item.Properties()).food(food).tab(FarmersDelight.CREATIVE_TAB));
    }

    // Sets creative tab to Farmer's Delight
    public static RegistryObject<Item> registerItem(String name, Item.Properties props) {
        return registerItem(name, () -> new Item(props.tab(FarmersDelight.CREATIVE_TAB)));
    }

    // Sets dynamic creative tab
    public static RegistryObject<Item> registerCompatItem(String name, Item.Properties props, String modid) {
        return registerItem(name, () -> new CompatItem(props, modid));
    }

    // Sets dynamic creative tab
    public static RegistryObject<Item> registerCompatFood(String name, FoodProperties food, String modid) {
        return registerItem(name, () -> new CompatItem((new Item.Properties().food(food)), modid));
    }

    public static Item.Properties compat(String modid) {
        return (ModList.get().isLoaded(modid)) ?
            (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB) :
            (new Item.Properties());
    }

    // Creative tab should be set before calling this function
    public static RegistryObject<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(name, item);
    }

    public static TagKey<Item> ingot(String name) {
        return Util.it("forge", "ingots/" + name);
    }

    public static TagKey<Item> gem(String name) {
        return Util.it("forge", "gems/" + name);
    }

    public static Supplier<Ingredient> getIngot(String name) {
        return Util.ing(ingot(name));
    }

    public static Supplier<Ingredient> getGem(String name) {
        return Util.ing(gem(name));
    }

    public static void create(IEventBus bus) {
        ITEMS.register(bus);
    }
}
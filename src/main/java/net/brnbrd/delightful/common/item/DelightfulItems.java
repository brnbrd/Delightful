package net.brnbrd.delightful.common.item;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.block.DelightfulBlocks;
import net.brnbrd.delightful.common.item.food.DrinkItem;
import net.brnbrd.delightful.common.item.food.EnderNectarItem;
import net.brnbrd.delightful.common.item.food.GummyItem;
import net.brnbrd.delightful.common.item.food.Nutrition;
import net.brnbrd.delightful.compat.ArsNouveauCompat;
import net.brnbrd.delightful.compat.BYGCompat;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.registry.ModItems;
import java.util.function.Supplier;

public class DelightfulItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Delightful.MODID);

    // Crafting Items (can be food)
    public static final RegistryObject<Item> ANIMAL_FAT = registerFood("animal_fat", Nutrition.ANIMAL_FAT);
    public static final RegistryObject<Item> ANIMAL_OIL_BOTTLE = registerItem("animal_oil_bottle",
        () -> new FurnaceFuelItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).tab(FarmersDelight.CREATIVE_TAB), 3200));
    public static final RegistryObject<Item> ACORN = registerFood("acorn", Nutrition.ACORN);
    public static final RegistryObject<Item> SALMONBERRIES = registerFood("salmonberries", Nutrition.SALMONBERRIES);
    public static final RegistryObject<Item> SALMONBERRY_PIPS = registerItem("salmonberry_pips",
        () -> new ItemNameBlockItem(DelightfulBlocks.SALMONBERRY_BUSH.get(), ModItems.basicItem()));
    public static final RegistryObject<Item> WILD_SALMONBERRIES = registerItem("wild_salmonberries",
        () -> new BlockItem(DelightfulBlocks.WILD_SALMONBERRIES.get(), ModItems.basicItem()));
    public static final RegistryObject<Item> SALMONBERRY_GUMMY = registerItem("salmonberry_gummy",
        () -> new GummyItem((new Item.Properties()).food(Nutrition.SALMONBERRY_GUMMY).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> SALMONBERRY_PIE = registerItem("salmonberry_pie",
        () -> new BlockItem(DelightfulBlocks.SALMONBERRY_PIE.get(), ModItems.basicItem()));
    public static final RegistryObject<Item> SALMONBERRY_PIE_SLICE = registerFood("salmonberry_pie_slice", Nutrition.SALMONBERRY_PIE_SLICE);
    public static final RegistryObject<Item> PUMPKIN_PIE_SLICE = registerFood("pumpkin_pie_slice", vectorwing.farmersdelight.common.FoodValues.PIE_SLICE);
    public static final RegistryObject<Item> SOURCE_BERRY_PIE_SLICE = registerCompatFood(ArsNouveauCompat.slice, ArsNouveauCompat.getPieSlice().get(),
        Mods.AN);
    public static final RegistryObject<Item> BLUEBERRY_PIE_SLICE = registerCompatFood(BYGCompat.blueberry_pie_slice, BYGCompat.BLUEBERRY_PIE_SLICE.get(),
        Mods.BYG);
    public static final RegistryObject<Item> GREEN_APPLE_PIE_SLICE = registerCompatFood(BYGCompat.green_apple_pie_slice, BYGCompat.GREEN_APPLE_PIE_SLICE.get(),
        Mods.BYG);
    public static final RegistryObject<Item> NIGHTSHADE_BERRY_PIE_SLICE = registerCompatFood(BYGCompat.nightshade_berry_pie_slice, BYGCompat.NIGHTSHADE_BERRY_PIE_SLICE.get(),
        Mods.BYG);
    public static final RegistryObject<Item> CRIMSON_BERRY_PIE_SLICE = registerCompatFood(BYGCompat.crimson_berry_pie_slice, BYGCompat.CRIMSON_BERRY_PIE_SLICE.get(),
        Mods.BYG);
    public static final RegistryObject<Item> GREEN_TEA_LEAF = registerFood("green_tea_leaf",
        Nutrition.GREEN_TEA_LEAF);
    public static final RegistryObject<Item> MATCHA = registerItem("matcha", () -> new DescriptItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB), Component.translatable("delightful.matcha.desc").withStyle(ChatFormatting.GRAY)));
    public static final RegistryObject<Item> MATCHA_ICE_CREAM = registerItem("matcha_ice_cream", () -> new BowlFoodItem((new Item.Properties()).food(Nutrition.MATCHA_ICE_CREAM).craftRemainder(Items.BOWL).stacksTo(1).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> MATCHA_ICE_CREAM_BLOCK = registerItem("matcha_ice_cream_block", () ->
        new BlockItem(DelightfulBlocks.MATCHA_ICE_CREAM_BLOCK.get(), Mods.loaded(Mods.N) ?
            (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS) :
            (new Item.Properties())
        ));
    public static final RegistryObject<Item> SALMONBERRY_ICE_CREAM = registerItem("salmonberry_ice_cream", () -> new BowlFoodItem((new Item.Properties()).food(Nutrition.SALMONBERRY_ICE_CREAM).craftRemainder(Items.BOWL).stacksTo(1).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> SALMONBERRY_ICE_CREAM_BLOCK = registerItem("salmonberry_ice_cream_block", () ->
        new BlockItem(DelightfulBlocks.SALMONBERRY_ICE_CREAM_BLOCK.get(), Mods.loaded(Mods.N) ?
            (new Item.Properties()).tab(CreativeModeTab.TAB_BUILDING_BLOCKS) :
            (new Item.Properties())
    ));
    public static final RegistryObject<Item> COCONUT_CURRY = registerItem("coconut_curry", () -> new ConsumableItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB).food(Nutrition.COCONUT_CURRY).stacksTo(16).craftRemainder(Items.BOWL), true));
    public static final RegistryObject<Item> SINIGANG = registerItem("sinigang", () -> new ConsumableItem((new Item.Properties()).food(Nutrition.SINIGANG).craftRemainder(Items.BOWL).stacksTo(16).tab(FarmersDelight.CREATIVE_TAB), true, true));
    public static final RegistryObject<Item> CHOPPED_CLOVER = registerCompatFood("chopped_clover", Nutrition.CHOPPED_CLOVER,
        Mods.BOP);
    public static final RegistryObject<Item> CACTUS_FLESH = registerFood("cactus_flesh", Nutrition.CACTUS_FLESH);
    public static final RegistryObject<Item> CACTUS_STEAK = registerFood("cactus_steak", Nutrition.CACTUS_STEAK);
    public static final RegistryObject<Item> FIELD_SALAD = registerItem("field_salad", () -> new ConsumableItem((new Item.Properties()).food(Nutrition.FIELD_SALAD).stacksTo(16).craftRemainder(Items.BOWL).tab(FarmersDelight.CREATIVE_TAB), true));
    public static final RegistryObject<Item> LAVENDER_TEA = registerItem("lavender_tea", () -> new DrinkItem((new Item.Properties()).food(Nutrition.LAVENDER_TEA).tab(FarmersDelight.CREATIVE_TAB).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16), 0.0F, true, false));
    public static final RegistryObject<Item> AZALEA_TEA = registerItem("azalea_tea", () -> new DrinkItem((new Item.Properties()).food(Nutrition.AZALEA_TEA).tab(FarmersDelight.CREATIVE_TAB).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16), 0.0F, true, false));
    public static final RegistryObject<Item> PRICKLY_PEAR_JUICE = registerItem("prickly_pear_juice", () -> new DrinkItem((new Item.Properties()).food(Nutrition.PRICKLY_PEAR_JUICE).tab(FarmersDelight.CREATIVE_TAB).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16), 0.0F, true, false));
    public static final RegistryObject<Item> ENDER_NECTAR = registerItem("ender_nectar", () -> new EnderNectarItem((new Item.Properties()).food(Nutrition.ENDER_NECTAR).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> MATCHA_LATTE = registerItem("matcha_latte", () -> new DrinkItem((new Item.Properties()).food(Nutrition.MATCHA_LATTE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(FarmersDelight.CREATIVE_TAB), 2.0F, true, true));
    public static final RegistryObject<Item> BERRY_MATCHA_LATTE = registerItem("berry_matcha_latte", () -> new DrinkItem((new Item.Properties()).food(Nutrition.BERRY_MATCHA_LATTE).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(FarmersDelight.CREATIVE_TAB), 2.0F, true, true));
    public static final RegistryObject<Item> JELLY_BOTTLE = registerItem("jelly_bottle", () -> new ConsumableItem((new Item.Properties()).food(Nutrition.JELLY_BOTTLE).craftRemainder(Items.GLASS_BOTTLE).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> GLOW_JELLY_BOTTLE = registerItem("glow_jelly_bottle", () -> new ConsumableItem((new Item.Properties()).food(Nutrition.GLOW_JELLY_BOTTLE).craftRemainder(Items.GLASS_BOTTLE).tab(FarmersDelight.CREATIVE_TAB), true, false));
    public static final RegistryObject<Item> NUT_BUTTER_BOTTLE = registerItem("nut_butter_bottle", () -> new ConsumableItem((new Item.Properties()).food(Nutrition.NUT_BUTTER_BOTTLE).craftRemainder(Items.GLASS_BOTTLE).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> NUT_BUTTER_AND_JELLY_SANDWICH = registerItem("nut_butter_and_jelly_sandwich", () -> new ConsumableItem((new Item.Properties()).food(Nutrition.NUT_BUTTER_AND_JELLY_SANDWICH).tab(FarmersDelight.CREATIVE_TAB), true));
    public static final RegistryObject<Item> CHEESEBURGER = registerItem("cheeseburger", () -> new ConsumableItem((new Item.Properties()).food(Nutrition.CHEESEBURGER).tab(FarmersDelight.CREATIVE_TAB), true));
    public static final RegistryObject<Item> DELUXE_CHEESEBURGER = registerItem("deluxe_cheeseburger", () -> new ConsumableItem((new Item.Properties()).food(Nutrition.DELUXE_CHEESEBURGER).tab(FarmersDelight.CREATIVE_TAB), true));
    public static final RegistryObject<Item> CHUNKWICH = registerCompatFood("chunkwich", Nutrition.CHUNKWICH,
        Mods.RL);
    public static final RegistryObject<Item> CHUNK_NUGGET = registerCompatFood("chunk_nugget", Nutrition.CHUNK_NUGGET,
        Mods.RL);
    public static final RegistryObject<Item> ROCK_CANDY = registerItem("rock_candy",
        () -> new ConsumableItem((new Item.Properties()).food(Nutrition.ROCK_CANDY).craftRemainder(Items.STICK).tab(FarmersDelight.CREATIVE_TAB), true));
    public static final RegistryObject<Item> MARSHMALLOW_STICK = registerFood("marshmallow_stick", Nutrition.MARSHMALLOW_STICK, Items.STICK);
    public static final RegistryObject<Item> COOKED_MARSHMALLOW_STICK = registerFood("cooked_marshmallow_stick", Nutrition.COOKED_MARSHMALLOW_STICK, Items.STICK);
    public static final RegistryObject<Item> SMORE = registerItem("smore",
        () -> new ConsumableItem((new Item.Properties()).food(Nutrition.SMORE).tab(FarmersDelight.CREATIVE_TAB), true));
    public static final RegistryObject<Item> CRAB_RANGOON = registerFood("crab_rangoon", Nutrition.CRAB_RANGOON);
    public static final RegistryObject<Item> HONEY_GLAZED_WALNUT = registerFood("honey_glazed_walnut", Nutrition.HONEY_GLAZED_WALNUT);
    public static final RegistryObject<Item> VENISON_CHOPS = registerFood("venison_chops", Nutrition.VENISON_CHOPS);
    public static final RegistryObject<Item> COOKED_VENISON_CHOPS = registerFood("cooked_venison_chops", Nutrition.COOKED_VENISON_CHOPS);
    public static final RegistryObject<Item> RAW_GOAT = registerFood("raw_goat", Nutrition.RAW_GOAT);
    public static final RegistryObject<Item> COOKED_GOAT = registerFood("cooked_goat", Nutrition.COOKED_GOAT);
    public static final RegistryObject<Item> CANTALOUPE_SLICE = registerFood("cantaloupe_slice", Nutrition.CANTALOUPE_SLICE);
    public static final RegistryObject<Item> CANTALOUPE = registerItem("cantaloupe", () ->
        new BlockItem(DelightfulBlocks.CANTALOUPE.get(), ModItems.basicItem()));
    public static final RegistryObject<Item> MINI_MELON = registerItem("mini_melon", () ->
        new BlockItem(DelightfulBlocks.MINI_MELON.get(), ModItems.basicItem()));
    public static final RegistryObject<Item> SALMONBERRY_SACK = registerItem("salmonberry_sack", () ->
        new BlockItem(DelightfulBlocks.SALMONBERRY_SACK.get(), ModItems.basicItem()));
    public static final RegistryObject<Item> ACORN_SACK = registerItem("acorn_sack", () ->
        new BlockItem(DelightfulBlocks.ACORN_SACK.get(), ModItems.basicItem()));

    // Cabinets
    public static final RegistryObject<Item> QUARTZ_CABINET = registerItem("quartz_cabinet",
            () -> new BlockItem(DelightfulBlocks.QUARTZ_CABINET.get(), ModItems.basicItem()));
    public static final RegistryObject<Item> BASALT_CABINET = registerItem("basalt_cabinet",
            () -> new BlockItem(DelightfulBlocks.BASALT_CABINET.get(), ModItems.basicItem()));

    // Registers food to Farmer's Delight tab, optional craftRemainder
    public static RegistryObject<Item> registerFood(String name, FoodProperties food, Item... remainder) {
        if (remainder.length > 0) {
            if (remainder[0].equals(Items.BOWL)) {
                return registerItem(name,
                    () -> new BowlFoodItem((new Item.Properties()).food(food).craftRemainder(Items.BOWL).tab(FarmersDelight.CREATIVE_TAB)));
            }
            return registerItem(name,
                () -> new ConsumableItem((new Item.Properties()).food(food).craftRemainder(remainder[0]).tab(FarmersDelight.CREATIVE_TAB)));
        }
        return registerItem(name, (new Item.Properties()).food(food));
    }

    // Sets creative tab to Farmer's Delight
    public static RegistryObject<Item> registerItem(String name, Item.Properties props) {
        return registerItem(name, () -> new DItem(props));
    }

    // Sets dynamic creative tab
    @SuppressWarnings("unused")
    public static RegistryObject<Item> registerCompatItem(String name, Item.Properties props, String modid) {
        return registerItem(name, () -> new CompatItem(props, modid));
    }

    // Sets dynamic creative tab
    public static RegistryObject<Item> registerCompatFood(String name, FoodProperties food, String modid) {
        return registerItem(name, () -> new CompatItem((new Item.Properties().food(food)), modid));
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
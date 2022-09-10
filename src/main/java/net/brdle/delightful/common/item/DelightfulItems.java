package net.brdle.delightful.common.item;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.item.food.*;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import net.brdle.delightful.common.item.knife.DelightfulKnifeItem;
import net.brdle.delightful.common.item.knife.TaggedKnifeItem;
import net.brdle.delightful.common.tag.DelightfulItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.Locale;
import java.util.function.Supplier;

public class DelightfulItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Delightful.MODID);

    // Crafting Items (can be food)
    public static final RegistryObject<Item> ANIMAL_FAT = registerFood("animal_fat", FoodValues.ANIMAL_FAT);
    public static final RegistryObject<Item> ANIMAL_OIL_BOTTLE = registerItem("animal_oil_bottle", () -> new FurnaceFuelItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).tab(FarmersDelight.CREATIVE_TAB), 3200));
    public static final RegistryObject<Item> SALMONBERRIES = registerFood("salmonberries",
      FoodValues.SALMONBERRIES);
    public static final RegistryObject<Item> GREEN_TEA_LEAF = registerFood("green_tea_leaf",
      FoodValues.GREEN_TEA_LEAF);
    public static final RegistryObject<Item> MATCHA = registerItem("matcha", () -> new Item((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> CHOPPED_CLOVER = registerFood("chopped_clover", FoodValues.CHOPPED_CLOVER);
    public static final RegistryObject<Item> CACTUS_FLESH = registerFood("cactus_flesh", FoodValues.CACTUS_FLESH);
    public static final RegistryObject<Item> CACTUS_STEAK = registerFood("cactus_steak", FoodValues.CACTUS_STEAK);
    public static final RegistryObject<Item> FIELD_SALAD = registerFood("field_salad", FoodValues.FIELD_SALAD, Items.BOWL);
    public static final RegistryObject<Item> AZALEA_TEA = registerCompatItem("azalea_tea", "ecologics",
      () -> new CaffeinatedItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(FarmersDelight.CREATIVE_TAB), () -> MobEffects.SATURATION, 100, 1, 2.0F, 1),
      () -> new CaffeinatedItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16), () -> MobEffects.SATURATION, 100, 1, 2.0F, 1));
    public static final RegistryObject<Item> LAVENDER_TEA = registerCompatItem("lavender_tea", "biomesoplenty",
      () -> new CaffeinatedItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(FarmersDelight.CREATIVE_TAB), () -> MobEffects.REGENERATION, 100, 1, 2.0F, 1),
      () -> new CaffeinatedItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16), () -> MobEffects.SATURATION, 100, 1, 2.0F, 1));
    public static final RegistryObject<Item> PRICKLY_PEAR_JUICE = registerCompatItem("prickly_pear_juice", "ecologics",
      () -> new DrinkItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(FarmersDelight.CREATIVE_TAB), () -> MobEffects.SATURATION, 400, 1),
      () -> new DrinkItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16), () -> MobEffects.SATURATION, 400, 1));
    public static final RegistryObject<Item> ENDER_NECTAR = registerItem("ender_nectar",
      () -> new EnderNectarItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> MATCHA_LATTE = registerItem("matcha_latte",
      () -> new CaffeinatedItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(FarmersDelight.CREATIVE_TAB), () -> MobEffects.SATURATION, 100, 1, 2.0F, 7));
    public static final RegistryObject<Item> BERRY_MATCHA_LATTE = registerItem("berry_matcha_latte",
      () -> new CaffeinatedItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(FarmersDelight.CREATIVE_TAB), () -> MobEffects.SATURATION, 200, 1, 2.0F, 9));
    public static final RegistryObject<Item> JELLY_BOTTLE = registerItem("jelly_bottle",
      () -> new ConsumableItem((new Item.Properties())
        .food(FoodValues.JELLY_BOTTLE).craftRemainder(Items.GLASS_BOTTLE).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> GLOW_JELLY_BOTTLE = registerItem("glow_jelly_bottle",
      () -> new ConsumableItem((new Item.Properties())
        .food(FoodValues.GLOW_JELLY_BOTTLE).craftRemainder(Items.GLASS_BOTTLE).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> NUT_BUTTER_BOTTLE = registerItem("nut_butter_bottle",
      () -> new ConsumableItem((new Item.Properties())
        .food(FoodValues.NUT_BUTTER_BOTTLE).craftRemainder(Items.GLASS_BOTTLE).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> NUT_BUTTER_AND_JELLY_SANDWICH = registerItem("nut_butter_and_jelly_sandwich",
      () -> new ConsumableItem((new Item.Properties()).food(FoodValues.NUT_BUTTER_AND_JELLY_SANDWICH).tab(FarmersDelight.CREATIVE_TAB), true));
    public static final RegistryObject<Item> CHEESEBURGER = registerItem("cheeseburger",
      () -> new ConsumableItem((new Item.Properties()).food(FoodValues.CHEESEBURGER).tab(FarmersDelight.CREATIVE_TAB), true));
    public static final RegistryObject<Item> DELUXE_CHEESEBURGER = registerItem("deluxe_cheeseburger",
      () -> new ConsumableItem((new Item.Properties()).food(FoodValues.DELUXE_CHEESEBURGER).tab(FarmersDelight.CREATIVE_TAB), true));
    public static final RegistryObject<Item> CHUNKWICH = registerCompatFood("chunkwich", "rottenleather",
      FoodValues.CHUNKWICH);
    public static final RegistryObject<Item> CHUNK_NUGGET = registerFood("chunk_nugget",
      FoodValues.CHUNK_NUGGET);
    public static final RegistryObject<Item> ROCK_CANDY = registerFood("rock_candy", FoodValues.ROCK_CANDY, Items.STICK);
    public static final RegistryObject<Item> MARSHMALLOW_STICK = registerFood("marshmallow_stick",
      FoodValues.MARSHMALLOW_STICK, Items.STICK);
    public static final RegistryObject<Item> COOKED_MARSHMALLOW_STICK = registerFood("cooked_marshmallow_stick",
      FoodValues.COOKED_MARSHMALLOW_STICK, Items.STICK);
    public static final RegistryObject<Item> SMORE = registerFood("smore",
      FoodValues.SMORE);
    public static final RegistryObject<Item> CRAB_RANGOON = registerCompatFood("crab_rangoon", "ecologics",
      FoodValues.CRAB_RANGOON);
    public static final RegistryObject<Item> HONEY_GLAZED_WALNUT = registerFood("honey_glazed_walnut",
      FoodValues.HONEY_GLAZED_WALNUT);
    public static final RegistryObject<Item> ACORN = registerFood("acorn",
      FoodValues.ACORN);
    public static final RegistryObject<Item> MINI_MELON = registerItem("mini_melon", () ->
      new BlockItem(DelightfulBlocks.MINI_MELON.get(), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> SALMONBERRY_SACK = registerItem("salmonberry_sack", () ->
      new BlockItem(DelightfulBlocks.SALMONBERRY_SACK.get(), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> SALMONBERRY_PIPS = registerItem("salmonberry_pips", () -> new ItemNameBlockItem(DelightfulBlocks.SALMONBERRY_BUSH.get(), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));

    // WIP
    public static final RegistryObject<Item> PIZZA = registerItem("pizza",
            () -> new PizzaItem((new Item.Properties())
            //.tab(FarmersDelight.CREATIVE_TAB)
    ));

    // Cabinets
    public static final RegistryObject<Item> QUARTZ_CABINET = registerItem("quartz_cabinet",
            () -> new BlockItem(DelightfulBlocks.QUARTZ_CABINET.get(), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> BASALT_CABINET = registerItem("basalt_cabinet",
            () -> new BlockItem(DelightfulBlocks.BASALT_CABINET.get(), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));

    // Knives
    public static final RegistryObject<Item> BONE_KNIFE = registerKnife("bone",
      () -> Ingredient.of(Tags.Items.BONES));
    public static final RegistryObject<Item> COPPER_KNIFE = registerIngotKnife("copper");
    public static final RegistryObject<Item> BLACK_OPAL_KNIFE = registerGemKnife("black_opal");
    public static final RegistryObject<Item> TIN_KNIFE = registerIngotKnife("tin");
    public static final RegistryObject<Item> STEEL_KNIFE = registerIngotKnife("steel");
    public static final RegistryObject<Item> SILVER_KNIFE = registerIngotKnife("silver");
    public static final RegistryObject<Item> BRASS_KNIFE = registerIngotKnife("brass");
    public static final RegistryObject<Item> BRONZE_KNIFE = registerIngotKnife("bronze");
    public static final RegistryObject<Item> CONSTANTAN_KNIFE = registerIngotKnife("constantan");
    public static final RegistryObject<Item> ELECTRUM_KNIFE = registerIngotKnife("electrum");
    public static final RegistryObject<Item> INVAR_KNIFE = registerIngotKnife("invar");
    public static final RegistryObject<Item> LEAD_KNIFE = registerIngotKnife("lead");
    public static final RegistryObject<Item> NICKEL_KNIFE = registerIngotKnife("nickel");
    public static final RegistryObject<Item> LAPIS_LAZULI_KNIFE = registerCompatKnife("lapis_lazuli", "mekanismtools", Tags.Items.GEMS_LAPIS.location());
    public static final RegistryObject<Item> OSMIUM_KNIFE = registerIngotKnife("osmium");
    public static final RegistryObject<Item> REFINED_GLOWSTONE_KNIFE = registerIngotKnife("refined_glowstone");
    public static final RegistryObject<Item> REFINED_OBSIDIAN_KNIFE = registerIngotKnife("refined_obsidian");
    public static final RegistryObject<Item> LARGE_AMETHYST_KNIFE = registerGemKnife("large_amethyst");
    public static final RegistryObject<Item> ENDERITE_KNIFE = registerSmithedKnife("enderite",
      () -> Ingredient.of(ModItems.NETHERITE_KNIFE.get()), ingot("enderite"));
    public static final RegistryObject<Item> OBSIDIAN_INFUSED_ENDERITE_KNIFE = registerSmithedKnife("obsidian_infused_enderite",
      () -> Ingredient.of(ENDERITE_KNIFE.get()), ingot("obsidian_infused_enderite"));
    public static final RegistryObject<Item> NETHERITE_OPAL_KNIFE = registerSmithedKnife("netherite_opal",
      () -> Ingredient.of(BLACK_OPAL_KNIFE.get()), Tags.Items.INGOTS_NETHERITE.location());
    public static final RegistryObject<Item> FIERY_KNIFE = registerCompatKnife("fiery", "twilightforest", new ResourceLocation("forge", "ingots/fiery"));
    public static final RegistryObject<Item> IRONWOOD_KNIFE = registerCompatKnife("ironwood", "twilightforest", new ResourceLocation("forge", "ingots/ironwood"));
    public static final RegistryObject<Item> KNIGHTMETAL_KNIFE = registerCompatKnife("knightmetal", "twilightforest", new ResourceLocation("forge", "ingots/knightmetal"));
    public static final RegistryObject<Item> STEELEAF_KNIFE = registerCompatKnife("steeleaf", "twilightforest", new ResourceLocation("forge", "ingots/steeleaf"));

    // Registers a knife to Farmer's Delight tab, requiring modid
    public static RegistryObject<Item> registerCompatKnife(String name, String modid, ResourceLocation tag) {
        return registerItem(name + "_knife", () -> new CompatKnifeItem(modid, tag, DelightfulTiers.valueOf(name.toUpperCase(Locale.ROOT)), 0.5F, -2.0F, (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    }

    // Registers a knife to Farmer's Delight tab, requiring non-empty ingot tag
    public static RegistryObject<Item> registerIngotKnife(String name) {
        return registerItem(name + "_knife", () -> new TaggedKnifeItem(ingot(name), DelightfulTiers.valueOf(name.toUpperCase(Locale.ROOT)), 0.5F, -2.0F, (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    }

    // Registers a knife to Farmer's Delight tab, requiring non-empty gem tag
    public static RegistryObject<Item> registerGemKnife(String name) {
        return registerItem(name + "_knife", () -> new TaggedKnifeItem(gem(name), DelightfulTiers.valueOf(name.toUpperCase(Locale.ROOT)), 0.5F, -2.0F, (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    }

    // Registers a knife to Farmer's Delight tab, requiring non-empty ingot tag
    public static RegistryObject<Item> registerSmithedKnife(String name, Supplier<Ingredient> base, ResourceLocation addition) {
        return registerItem(name + "_knife", () -> new TaggedKnifeItem(base, addition, DelightfulTiers.valueOf(name.toUpperCase(Locale.ROOT)), 0.5F, -2.0F, (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    }

    // Registers a knife to Farmer's Delight tab
    public static RegistryObject<Item> registerKnife(String name, Supplier<Ingredient> ingredient) {
        return registerItem(name + "_knife", () -> new DelightfulKnifeItem(ingredient, DelightfulTiers.valueOf(name.toUpperCase(Locale.ROOT)), 0.5F, -2.0F, (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    }

    // Registers a food to Farmer's Delight tab, optional craftRemainder
    public static RegistryObject<Item> registerFood(String name, FoodProperties food, Item... remainder) {
        return registerItem(name, (remainder.length > 0) ?
                (new Item.Properties()).food(food).craftRemainder(remainder[0]) :
                (new Item.Properties()).food(food));
    }

    // Sets creative tab to Farmer's Delight
    public static RegistryObject<Item> registerItem(String name, Item.Properties props) {
        return registerItem(name, () -> new Item(props.tab(FarmersDelight.CREATIVE_TAB)));
    }

    // Sets no creative tab
    public static RegistryObject<Item> registerCompatFood(String name, String modid, FoodProperties food) {
        if (ModList.get().isLoaded(modid)) {
            return registerItem(name, () -> new Item((new Item.Properties().food(food).tab(FarmersDelight.CREATIVE_TAB))));
        }
        return registerItem(name, () -> new Item((new Item.Properties().food(food))));
    }

    // Sets no creative tab
    public static RegistryObject<Item> registerCompatItem(String name, String modid, Supplier<Item> loaded, Supplier<Item> notLoaded) {
        if (ModList.get().isLoaded(modid)) {
            return registerItem(name, loaded);
        }
        return registerItem(name, notLoaded);
    }

    // Creative tab should be set before calling this function
    public static RegistryObject<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(name, item);
    }

    public static final ResourceLocation ingot(String name) {
        return new ResourceLocation("forge", "ingots/" + name);
    }

    public static final ResourceLocation gem(String name) {
        return new ResourceLocation("forge", "gems/" + name);
    }

    public static final Supplier<Ingredient> getIngot(String name) {
        return () -> Ingredient.of(ItemTags.create(ingot(name)));
    }

    public static final Supplier<Ingredient> getGem(String name) {
        return () -> Ingredient.of(ItemTags.create(gem(name)));
    }

    public static void create(IEventBus bus) {
        ITEMS.register(bus);
    }
}
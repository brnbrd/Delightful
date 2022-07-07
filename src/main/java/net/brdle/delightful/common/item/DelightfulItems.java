package net.brdle.delightful.common.item;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.DelightfulTiers;
import net.brdle.delightful.common.FoodValues;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.KnifeItem;
import java.util.ArrayList;
import java.util.function.Supplier;

public class DelightfulItems {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Delightful.MODID);

    // Knives
    public static final ArrayList<RegistryObject<Item>> knives = new ArrayList<>();

    public static final RegistryObject<Item> COPPER_KNIFE = registerKnife("copper_knife", DelightfulTiers.COPPER, 0.5F, -2.0F);
    public static final RegistryObject<Item> BONE_KNIFE = registerKnife("bone_knife", DelightfulTiers.BONE, 0.5F, -2.0F);

    public static final RegistryObject<Item> TIN_KNIFE = registerTaggedKnife("tin_knife", DelightfulTiers.TIN, 0.5F, -2.0F,
            new ResourceLocation("forge", "ingots/tin"));
    public static final RegistryObject<Item> STEEL_KNIFE = registerTaggedKnife("steel_knife", DelightfulTiers.STEEL, 0.5F, -2.0F,
            new ResourceLocation("forge", "ingots/steel"));
    public static final RegistryObject<Item> SILVER_KNIFE = registerTaggedKnife("silver_knife", DelightfulTiers.SILVER, 0.5F, -2.0F,
            new ResourceLocation("forge", "ingots/silver"));
    public static final RegistryObject<Item> BRASS_KNIFE = registerTaggedKnife("brass_knife", DelightfulTiers.BRASS, 0.5F, -2.0F,
            new ResourceLocation("forge", "ingots/brass"));
    public static final RegistryObject<Item> ENDERITE_KNIFE = registerTaggedKnife("enderite_knife", DelightfulTiers.ENDERITE, 0.5F, -2.0F,
            new ResourceLocation("forge", "ingots/enderite"));
    public static final RegistryObject<Item> BRONZE_KNIFE = registerTaggedKnife("bronze_knife", DelightfulTiers.BRONZE, 0.5F, -2.0F,
            new ResourceLocation("forge", "ingots/bronze"));
    public static final RegistryObject<Item> CONSTANTAN_KNIFE = registerTaggedKnife("constantan_knife", DelightfulTiers.CONSTANTAN, 0.5F, -2.0F,
            new ResourceLocation("forge", "ingots/constantan"));
    public static final RegistryObject<Item> ELECTRUM_KNIFE = registerTaggedKnife("electrum_knife", DelightfulTiers.ELECTRUM, 0.5F, -2.0F,
            new ResourceLocation("forge", "ingots/electrum"));
    public static final RegistryObject<Item> INVAR_KNIFE = registerTaggedKnife("invar_knife", DelightfulTiers.INVAR, 0.5F, -2.0F,
            new ResourceLocation("forge", "ingots/invar"));
    public static final RegistryObject<Item> LEAD_KNIFE = registerTaggedKnife("lead_knife", DelightfulTiers.LEAD, 0.5F, -2.0F,
            new ResourceLocation("forge", "ingots/lead"));
    public static final RegistryObject<Item> NICKEL_KNIFE = registerTaggedKnife("nickel_knife", DelightfulTiers.NICKEL, 0.5F, -2.0F,
            new ResourceLocation("forge", "ingots/nickel"));

    public static final RegistryObject<Item> OBSIDIAN_INFUSED_ENDERITE_KNIFE = registerCompatKnife("obsidian_infused_enderite_knife", DelightfulTiers.OBSIDIAN_INFUSED_ENDERITE, 0.5F, -2.0F,
            "lolenderite");
    public static final RegistryObject<Item> LAPIS_LAZULI_KNIFE = registerCompatKnife("lapis_lazuli_knife", DelightfulTiers.LAPIS_LAZULI, 0.5F, -2.0F,
            "mekanismtools");
    public static final RegistryObject<Item> OSMIUM_KNIFE = registerCompatKnife("osmium_knife", DelightfulTiers.OSMIUM, 0.5F, -2.0F,
            "mekanismtools");
    public static final RegistryObject<Item> REFINED_GLOWSTONE_KNIFE = registerCompatKnife("refined_glowstone_knife", DelightfulTiers.REFINED_GLOWSTONE, 0.5F, -2.0F,
            "mekanismtools");
    public static final RegistryObject<Item> REFINED_OBSIDIAN_KNIFE = registerCompatKnife("refined_obsidian_knife", DelightfulTiers.REFINED_OBSIDIAN, 0.5F, -2.0F,
            "mekanismtools");
    public static final RegistryObject<Item> LARGE_AMETHYST_KNIFE = registerCompatKnife("large_amethyst_knife", DelightfulTiers.LARGE_AMETHYST, 0.5F, -2.0F,
            "oresabovediamonds");
    public static final RegistryObject<Item> BLACK_OPAL_KNIFE = registerTaggedKnife("black_opal_knife", DelightfulTiers.BLACK_OPAL, 0.5F, -2.0F,
            new ResourceLocation("forge", "gems/black_opal"));
    public static final RegistryObject<Item> NETHERITE_OPAL_KNIFE = registerCompatKnife("netherite_opal_knife", DelightfulTiers.NETHERITE_OPAL, 0.5F, -2.0F,
            "oresabovediamonds");

    // Foods
    public static final RegistryObject<Item> CHEESEBURGER = registerItem("cheeseburger", () -> new ConsumableItem((new Item.Properties()).food(FoodValues.CHEESEBURGER).tab(FarmersDelight.CREATIVE_TAB), true));
    public static final RegistryObject<Item> DELUXE_CHEESEBURGER = registerItem("deluxe_cheeseburger", () -> new ConsumableItem((new Item.Properties()).food(FoodValues.DELUXE_CHEESEBURGER).tab(FarmersDelight.CREATIVE_TAB), true));
    public static final RegistryObject<Item> MARSHMALLOW_STICK = registerFood("marshmallow_stick", FoodValues.MARSHMALLOW_STICK, Items.STICK);
    public static final RegistryObject<Item> COOKED_MARSHMALLOW_STICK = registerFood("cooked_marshmallow_stick", FoodValues.COOKED_MARSHMALLOW_STICK, Items.STICK);
    public static final RegistryObject<Item> SMORE = registerFood("smore", FoodValues.SMORE);
    public static final RegistryObject<Item> CRAB_RANGOON = registerCompatFood("crab_rangoon", "ecologics", FoodValues.CRAB_RANGOON);
    public static final RegistryObject<Item> PRICKLY_PEAR_JUICE = registerCompatItem("prickly_pear_juice", "ecologics",
            () -> new PricklyPearJuiceItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(FarmersDelight.CREATIVE_TAB)),
            () -> new PricklyPearJuiceItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
    public static final RegistryObject<Item> CHUNKWICH = registerCompatFood("chunkwich", "rottenleather", FoodValues.CHUNKWICH);
    public static final RegistryObject<Item> JELLY_BOTTLE = registerItem("jelly_bottle",
            () -> new ConsumableItem((new Item.Properties())
                    .food(FoodValues.JELLY_BOTTLE).craftRemainder(Items.GLASS_BOTTLE).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> NUT_BUTTER_BOTTLE = registerItem("nut_butter_bottle",
            () -> new ConsumableItem((new Item.Properties())
                    .food(FoodValues.NUT_BUTTER_BOTTLE).craftRemainder(Items.GLASS_BOTTLE).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> NUT_BUTTER_AND_JELLY_SANDWICH = registerItem("nut_butter_and_jelly_sandwich", () -> new ConsumableItem((new Item.Properties()).food(FoodValues.NUT_BUTTER_AND_JELLY_SANDWICH).tab(FarmersDelight.CREATIVE_TAB), true));
    public static final RegistryObject<Item> ENDER_NECTAR = registerItem("ender_nectar", () -> new EnderNectarItem((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).tab(FarmersDelight.CREATIVE_TAB)));

    // WIP
    public static final RegistryObject<Item> PIZZA = registerItem("pizza", () -> new PizzaItem((new Item.Properties())
            //.tab(FarmersDelight.CREATIVE_TAB)
    ));

    // Cabinets
    public static final RegistryObject<Item> QUARTZ_CABINET = registerItem("quartz_cabinet", () -> new BlockItem(DelightfulBlocks.QUARTZ_CABINET.get(), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> BASALT_CABINET = registerItem("basalt_cabinet", () -> new BlockItem(DelightfulBlocks.BASALT_CABINET.get(), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));

    // Registers a knife to Farmer's Delight tab, requiring modid
    public static RegistryObject<Item> registerCompatKnife(String name, Tier tier, float attackDamageIn, float attackSpeedIn, String modid) {
        var knife = registerItem(name, () ->
                ModList.get().isLoaded(modid) ?
                        new KnifeItem(tier, attackDamageIn, attackSpeedIn, (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)) :
                        new CompatKnifeItem(tier, attackDamageIn, attackSpeedIn, (new Item.Properties()), modid));
        knives.add(knife);
        return knife;
    }

    // Registers a knife to Farmer's Delight tab, requiring non-empty tag
    public static RegistryObject<Item> registerTaggedKnife(String name, Tier tier, float attackDamageIn, float attackSpeedIn, ResourceLocation tag) {
        var knife = registerItem(name, () -> new TaggedKnifeItem(tier, attackDamageIn, attackSpeedIn, (new Item.Properties()), tag));
        knives.add(knife);
        return knife;
    }

    // Registers a knife to Farmer's Delight tab
    public static RegistryObject<Item> registerKnife(String name, Tier tier, float attackDamageIn, float attackSpeedIn) {
        var knife = registerItem(name, () -> new KnifeItem(tier, attackDamageIn, attackSpeedIn, (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
        knives.add(knife);
        return knife;
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

    public static void create(IEventBus bus) {
        ITEMS.register(bus);
    }
}
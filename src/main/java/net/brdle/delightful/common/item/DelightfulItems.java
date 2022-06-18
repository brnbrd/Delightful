package net.brdle.delightful.common.item;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.DelightfulTiers;
import net.brdle.delightful.common.FoodValues;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.KnifeItem;
import java.util.function.Supplier;

public class DelightfulItems {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Delightful.MODID);

    // Knives
    public static final RegistryObject<Item> COPPER_KNIFE = registerKnife("copper_knife", DelightfulTiers.COPPER, 0.5F, -2.0F);
    public static final RegistryObject<Item> STEEL_KNIFE = registerTaggedKnife("steel_knife", DelightfulTiers.STEEL, 0.5F, -2.0F,
            new ResourceLocation("forge", "ingots/steel"));
    public static final RegistryObject<Item> ENDERITE_KNIFE = registerTaggedKnife("enderite_knife", DelightfulTiers.ENDERITE, 0.5F, -2.0F,
            new ResourceLocation("forge", "ingots/enderite"));
    public static final RegistryObject<Item> OBSIDIAN_INFUSED_ENDERITE_KNIFE = registerCompatKnife("obsidian_infused_enderite_knife", DelightfulTiers.OBSIDIAN_INFUSED_ENDERITE, 0.5F, -2.0F,
            "lolenderite");

    // Foods
    public static final RegistryObject<Item> CHEESEBURGER = registerFood("cheeseburger", FoodValues.CHEESEBURGER);
    public static final RegistryObject<Item> DELUXE_CHEESEBURGER = registerFood("deluxe_cheeseburger", FoodValues.DELUXE_CHEESEBURGER);
    public static final RegistryObject<Item> MARSHMALLOW_STICK = registerFood("marshmallow_stick", FoodValues.MARSHMALLOW_STICK, Items.STICK);
    public static final RegistryObject<Item> COOKED_MARSHMALLOW_STICK = registerFood("cooked_marshmallow_stick", FoodValues.COOKED_MARSHMALLOW_STICK, Items.STICK);
    public static final RegistryObject<Item> SMORE = registerFood("smore", FoodValues.SMORE);
    public static final RegistryObject<Item> PIZZA = registerItem("pizza", () -> new PizzaItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));

    // Registers a knife to Farmer's Delight tab, requiring modid
    public static RegistryObject<Item> registerCompatKnife(String name, Tier tier, float attackDamageIn, float attackSpeedIn, String modid) {
        return registerItem(name, () ->
                ModList.get().isLoaded(modid) ?
                        new KnifeItem(tier, attackDamageIn, attackSpeedIn, (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)) :
                        new CompatKnifeItem(tier, attackDamageIn, attackSpeedIn, (new Item.Properties()), modid));
    }

    // Registers a knife to Farmer's Delight tab, requiring non-empty tag
    public static RegistryObject<Item> registerTaggedKnife(String name, Tier tier, float attackDamageIn, float attackSpeedIn, ResourceLocation tag) {
        return registerItem(name, () -> new TaggedKnifeItem(tier, attackDamageIn, attackSpeedIn, (new Item.Properties()), tag));
    }

    // Registers a knife to Farmer's Delight tab
    public static RegistryObject<Item> registerKnife(String name, Tier tier, float attackDamageIn, float attackSpeedIn) {
        return registerItem(name, () -> new KnifeItem(tier, attackDamageIn, attackSpeedIn, (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
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

    // Creative tab should be set before calling this function
    public static RegistryObject<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(name, item);
    }

    public static void create(IEventBus bus) {
        ITEMS.register(bus);
    }
}
package net.brdle.delightful.common;

import net.brdle.delightful.Delightful;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.KnifeItem;

public class DelightfulItems {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Delightful.MODID);

    // Knives
    public static final RegistryObject<Item> COPPER_KNIFE = ITEMS.register("copper_knife", () ->
            new KnifeItem(DelightfulTiers.COPPER, 0.5F, -2.0F, new Item.Properties()
                    .tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> STEEL_KNIFE = ITEMS.register("steel_knife", () ->
            new KnifeItem(DelightfulTiers.STEEL, 0.5F, -2.0F, new Item.Properties()
                    .tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> ENDERITE_KNIFE = ITEMS.register("enderite_knife", () ->
            new KnifeItem(DelightfulTiers.ENDERITE, 0.5F, -2.0F, new Item.Properties()
                    .tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> OBSIDIAN_INFUSED_ENDERITE_KNIFE = ITEMS.register("obsidian_infused_enderite_knife", () ->
            new KnifeItem(DelightfulTiers.OBSIDIAN_INFUSED_ENDERITE, 0.5F, -2.0F, new Item.Properties()
                    .tab(FarmersDelight.CREATIVE_TAB)));

    // Foods
    public static final RegistryObject<Item> CHEESEBURGER = ITEMS.register("cheeseburger", () -> new Item((new Item.Properties()).food(FoodValues.CHEESEBURGER).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> DELUXE_CHEESEBURGER = ITEMS.register("deluxe_cheeseburger", () -> new Item((new Item.Properties()).food(FoodValues.DELUXE_CHEESEBURGER).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> MARSHMALLOW_STICK = ITEMS.register("marshmallow_stick", () -> new Item((new Item.Properties()).food(FoodValues.MARSHMALLOW_STICK).craftRemainder(Items.STICK).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> COOKED_MARSHMALLOW_STICK = ITEMS.register("cooked_marshmallow_stick", () -> new Item((new Item.Properties()).food(FoodValues.COOKED_MARSHMALLOW_STICK).craftRemainder(Items.STICK).tab(FarmersDelight.CREATIVE_TAB)));
    public static final RegistryObject<Item> SMORE = ITEMS.register("smore", () -> new Item((new Item.Properties()).food(FoodValues.SMORE).tab(FarmersDelight.CREATIVE_TAB)));

    public static void create(IEventBus bus) {
        ITEMS.register(bus);
    }
}
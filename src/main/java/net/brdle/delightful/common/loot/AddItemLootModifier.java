package net.brdle.delightful.common.loot;

import com.google.gson.JsonObject;
import net.brdle.delightful.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class AddItemLootModifier extends LootModifier {
    private final Item item;
    private final int minAmount;
    private final int maxAmount;
    private final boolean unique;

    public AddItemLootModifier(LootItemCondition[] conditions, Item item, int minAmount, int maxAmount, boolean unique) {
        super(conditions);
        this.item = item;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.unique = unique;
    }

    @Nonnull
    @Override
    public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        if ((this.unique && generatedLoot.stream().anyMatch(stack -> stack.getItem().equals(this.item))) || (this.maxAmount < 1)) {
            return generatedLoot;
        }
        int amount = this.minAmount == this.maxAmount ? this.minAmount : context.getRandom().nextInt(this.maxAmount + 1 - this.minAmount) + this.minAmount;
        return (amount >= 1) ? Util.with(generatedLoot, new ItemStack(this.item, amount)) : generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<AddItemLootModifier> {

        @Override
        public AddItemLootModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditions) {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(object, "item"))));
            int minAmount = GsonHelper.getAsInt(object, "minAmount");
            int maxAmount = GsonHelper.getAsInt(object, "maxAmount");
            boolean unique = GsonHelper.getAsBoolean(object, "unique");
            return new AddItemLootModifier(conditions, item, minAmount, maxAmount, unique);
        }

        @Override
        public JsonObject write(AddItemLootModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("item", ForgeRegistries.ITEMS.getKey(instance.item).toString());
            json.addProperty("minAmount", instance.minAmount);
            json.addProperty("maxAmount", instance.maxAmount);
            json.addProperty("unique", instance.unique);
            return json;
        }
    }
}
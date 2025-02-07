package net.brnbrd.delightful.common.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.brnbrd.delightful.Util;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import org.jetbrains.annotations.NotNull;

public class LootItemEnabledCondition implements LootItemCondition {
	final String item;

	public LootItemEnabledCondition(String item) {
		this.item = item;
	}

	@Override
	public @NotNull LootItemConditionType getType() {
		return DelightfulLootItemConditions.ENABLED.get();
	}

	@Override
	public boolean test(LootContext lootContext) {
		return Util.enabled(this.item);
	}

	public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<LootItemEnabledCondition> {
		public void serialize(JsonObject object, LootItemEnabledCondition cond, @NotNull JsonSerializationContext context) {
			object.addProperty("item", cond.item);
		}

		public @NotNull LootItemEnabledCondition deserialize(@NotNull JsonObject object, @NotNull JsonDeserializationContext context) {
			return new LootItemEnabledCondition(GsonHelper.getAsString(object, "item"));
		}
	}
}
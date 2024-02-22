package net.brnbrd.delightful.common.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import org.jetbrains.annotations.NotNull;

public class LootItemModLoadedCondition implements LootItemCondition {

	final String modid;
	LootItemModLoadedCondition(String modid) {
		this.modid = modid;
	}

	public static LootItemModLoadedCondition loaded(String modid) {
		return new LootItemModLoadedCondition(modid);
	}

	@Override
	public @NotNull LootItemConditionType getType() {
		return DelightfulLootItemConditions.MOD_LOADED.get();
	}

	/**
	 * Evaluates this predicate on the given argument.
	 *
	 * @param lootContext the input argument
	 * @return {@code true} if the input argument matches the predicate,
	 * otherwise {@code false}
	 */
	@Override
	public boolean test(LootContext lootContext) {
		return Mods.loaded(this.modid);
	}

	public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<LootItemModLoadedCondition> {
		/**
		 * Serialize the value by putting its data into the JsonObject.
		 */
		public void serialize(JsonObject object, LootItemModLoadedCondition cond, @NotNull JsonSerializationContext context) {
			object.addProperty("modid", cond.modid);
		}

		/**
		 * Deserialize a value by reading it from the JsonObject.
		 */
		public @NotNull LootItemModLoadedCondition deserialize(@NotNull JsonObject object, @NotNull JsonDeserializationContext context) {
			return new LootItemModLoadedCondition(GsonHelper.getAsString(object, "modid"));
		}
	}
}

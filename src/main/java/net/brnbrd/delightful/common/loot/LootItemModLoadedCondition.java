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

	public LootItemModLoadedCondition(String modid) {
		this.modid = modid;
	}

	@Override
	public @NotNull LootItemConditionType getType() {
		return DelightfulLootItemConditions.MOD_LOADED.get();
	}

	@Override
	public boolean test(LootContext lootContext) {
		return Mods.stringLoaded(this.modid);
	}

	public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<LootItemModLoadedCondition> {
		public void serialize(JsonObject object, LootItemModLoadedCondition cond, @NotNull JsonSerializationContext context) {
			object.addProperty("modid", cond.modid);
		}

		public @NotNull LootItemModLoadedCondition deserialize(@NotNull JsonObject object, @NotNull JsonDeserializationContext context) {
			return new LootItemModLoadedCondition(GsonHelper.getAsString(object, "modid"));
		}
	}
}
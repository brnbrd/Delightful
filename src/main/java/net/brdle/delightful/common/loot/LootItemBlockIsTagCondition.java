package net.brdle.delightful.common.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import org.jetbrains.annotations.NotNull;

public class LootItemBlockIsTagCondition implements LootItemCondition {

	final TagKey<Item> tag;
	LootItemBlockIsTagCondition(TagKey<Item> tag) {
		this.tag = tag;
	}

	public static LootItemBlockIsTagCondition isTag(TagKey<Item> tag) {
		return new LootItemBlockIsTagCondition(tag);
	}

	@Override
	public LootItemConditionType getType() {
		return DelightfulLootItemConditions.IS_TAG.get();
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
		BlockState state = lootContext.getParamOrNull(LootContextParams.BLOCK_STATE);
		return state != null && new ItemStack(state.getBlock()).is(this.tag);
	}

	public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<LootItemBlockIsTagCondition> {
		/**
		 * Serialize the value by putting its data into the JsonObject.
		 */
		public void serialize(JsonObject object, LootItemBlockIsTagCondition cond, @NotNull JsonSerializationContext context) {
			object.addProperty("tag", cond.tag.location().toString());
		}

		/**
		 * Deserialize a value by reading it from the JsonObject.
		 */
		public LootItemBlockIsTagCondition deserialize(@NotNull JsonObject object, @NotNull JsonDeserializationContext context) {
			return new LootItemBlockIsTagCondition(ItemTags.create(new ResourceLocation(GsonHelper.getAsString(object, "tag"))));
		}
	}
}

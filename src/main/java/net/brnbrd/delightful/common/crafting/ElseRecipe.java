package net.brnbrd.delightful.common.crafting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.ConditionalAdvancement;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.registries.ObjectHolder;
import org.jetbrains.annotations.Nullable;

public class ElseRecipe
{
	@ObjectHolder(registryName = "recipe_serializer", value = "delightful:else")
	public static final RecipeSerializer<Recipe<?>> SERIALZIER = null;

	public static ElseRecipe.Builder builder()
	{
		return new ElseRecipe.Builder();
	}

	public static class Serializer<T extends Recipe<?>> implements RecipeSerializer<T>
	{
		@Override
		public T fromJson(ResourceLocation recipeId, JsonObject json)
		{
			return fromJson(recipeId, json, ICondition.IContext.EMPTY);
		}

		@SuppressWarnings("unchecked") // We return a nested one, so we can't know what type it is.
		@Override
		public T fromJson(ResourceLocation recipeId, JsonObject json, ICondition.IContext context)
		{
			if (!json.isJsonObject())
				throw new JsonSyntaxException("Invalid recipe entry. Must be JsonObject");
			if (CraftingHelper.processConditions(GsonHelper.getAsJsonArray(json.getAsJsonObject(), "conditions"), context)) {
				return (T) RecipeManager.fromJson(recipeId, GsonHelper.getAsJsonObject(json.getAsJsonObject(), "recipe"));
			} else {
				return (T) RecipeManager.fromJson(recipeId, GsonHelper.getAsJsonObject(json.getAsJsonObject(), "else"));
			}
		}

		//Should never get here as we return one of the recipes we wrap.
		@Override public T fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) { return null; }
		@Override public void toNetwork(FriendlyByteBuf buffer, T recipe) {}
	}

	public static class Builder
	{
		private ICondition[] conditions;
		private FinishedRecipe recipe;
		private FinishedRecipe elseRecipe;
		private ResourceLocation advId;
		private ConditionalAdvancement.Builder adv;

		private final List<ICondition> currentConditions = new ArrayList<>();

		public ElseRecipe.Builder addCondition(ICondition condition) {
			currentConditions.add(condition);
			return this;
		}

		public ElseRecipe.Builder setRecipe(Consumer<Consumer<FinishedRecipe>> callable) {
			callable.accept(this::setRecipe);
			return this;
		}

		public ElseRecipe.Builder setRecipe(FinishedRecipe recipe) {
			this.recipe = recipe;
			return this;
		}

		public ElseRecipe.Builder setElseRecipe(Consumer<Consumer<FinishedRecipe>> callable) {
			callable.accept(this::setElseRecipe);
			return this;
		}

		public ElseRecipe.Builder setElseRecipe(FinishedRecipe elseRecipe) {
			this.elseRecipe = elseRecipe;
			return this;
		}

		public ElseRecipe.Builder generateAdvancement() {
			return generateAdvancement(null);
		}

		public ElseRecipe.Builder generateAdvancement(@Nullable ResourceLocation id) {
			ConditionalAdvancement.Builder builder = ConditionalAdvancement.builder();
			for (ICondition cond : currentConditions) {
				builder.addCondition(cond);
			}
			builder = builder.addAdvancement(recipe);
			return setAdvancement(id, builder);
		}

		public ElseRecipe.Builder setAdvancement(ConditionalAdvancement.Builder advancement) {
			return setAdvancement(null, advancement);
		}

		public ElseRecipe.Builder setAdvancement(String namespace, String path, ConditionalAdvancement.Builder advancement) {
			return setAdvancement(new ResourceLocation(namespace, path), advancement);
		}

		public ElseRecipe.Builder setAdvancement(@Nullable ResourceLocation id, ConditionalAdvancement.Builder advancement) {
			if (this.adv != null)
				throw new IllegalStateException("Invalid ConditionalRecipeBuilder, Advancement already set");
			this.advId = id;
			this.adv = advancement;
			return this;
		}

		public void build(Consumer<FinishedRecipe> consumer, String namespace, String path) {
			build(consumer, new ResourceLocation(namespace, path));
		}

		public void build(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
			if (!currentConditions.isEmpty())
				throw new IllegalStateException("Invalid ConditionalRecipe builder, Orphaned conditions");
			if (advId == null && adv != null) {
				advId = new ResourceLocation(id.getNamespace(), "recipes/" + id.getPath());
			}
			consumer.accept(new ElseRecipe.Finished(id, currentConditions, recipe, elseRecipe, advId, adv));
		}
	}

	private static class Finished implements FinishedRecipe {
		private final ResourceLocation id;
		private final List<ICondition> conditions;
		private final FinishedRecipe recipe;
		private final FinishedRecipe elseRecipe;
		private final ResourceLocation advId;
		private final ConditionalAdvancement.Builder adv;

		private Finished(ResourceLocation id, List<ICondition> conditions, FinishedRecipe recipe, FinishedRecipe elseRecipe, @Nullable ResourceLocation advId, @Nullable ConditionalAdvancement.Builder adv) {
			this.id = id;
			this.conditions = conditions;
			this.recipe = recipe;
			this.elseRecipe = elseRecipe;
			this.advId = advId;
			this.adv = adv;
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			JsonArray conds = new JsonArray();
			for (ICondition c : conditions)
				conds.add(CraftingHelper.serialize(c));
			json.add("conditions", conds);
			json.add("recipe", recipe.serializeRecipe());
			json.add("else", elseRecipe.serializeRecipe());
		}

		@Override
		public ResourceLocation getId() {
			return id;
		}

		@Override
		public RecipeSerializer<?> getType()
		{
			return SERIALZIER;
		}

		@Override
		public JsonObject serializeAdvancement() {
			return adv == null ? null : adv.write();
		}

		@Override
		public ResourceLocation getAdvancementId() {
			return advId;
		}
	}
}

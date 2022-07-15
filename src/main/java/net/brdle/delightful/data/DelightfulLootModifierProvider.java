package net.brdle.delightful.data;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.loot.AddItemLootModifier;
import net.brdle.delightful.common.loot.DelightfulLootModifiers;
import net.brdle.delightful.common.loot.LootItemBlockIsTagCondition;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import vectorwing.farmersdelight.common.tag.ForgeTags;

public class DelightfulLootModifierProvider extends GlobalLootModifierProvider {
	public DelightfulLootModifierProvider(DataGenerator gen) {
		super(gen, Delightful.MODID);
	}

	/**
	 * Call {@link #add} here, which will pass in the necessary information to write the jsons.
	 */
	@Override
	protected void start() {
		add("green_tea_leaf", DelightfulLootModifiers.ADD_ITEM.get(), new AddItemLootModifier(
			new LootItemCondition[]{
				LootItemRandomChanceCondition.randomChance(0.12F).build(),
				MatchTool.toolMatches(ItemPredicate.Builder.item().of(ForgeTags.TOOLS_KNIVES)).build(),
				LootItemBlockIsTagCondition.isTag(ItemTags.LEAVES)
			},
			DelightfulItems.GREEN_TEA_LEAF.get(), 1, 1, true
		));
	}
}

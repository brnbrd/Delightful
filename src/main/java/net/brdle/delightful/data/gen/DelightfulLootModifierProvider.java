package net.brdle.delightful.data.gen;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.loot.AddItemLootModifier;
import net.brdle.delightful.common.loot.LootItemBlockIsTagCondition;
import net.brdle.delightful.common.loot.LootItemEnabledCondition;
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraftforge.common.Tags;
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
		add("green_tea_leaf", new AddItemLootModifier(
			new LootItemCondition[]{
				LootItemEnabledCondition.enabled("green_tea_leaf"),
				LootItemRandomChanceCondition.randomChance(0.08F).build(),
				MatchTool.toolMatches(ItemPredicate.Builder.item().of(ForgeTags.TOOLS_KNIVES)).build(),
				LootItemBlockIsTagCondition.isTag(ItemTags.LEAVES)
			},
			DelightfulItems.GREEN_TEA_LEAF.get(), 1, 1, true
		));
		add("acorn", new AddItemLootModifier(
			new LootItemCondition[]{
				LootItemEnabledCondition.enabled("acorn"),
				LootItemRandomChanceCondition.randomChance(0.05F).build(),
				MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS)).invert().build(),
				MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.ANY))).invert().build(),
				LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.OAK_LEAVES)
					.or(LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DARK_OAK_LEAVES)).build()
			},
			DelightfulItems.ACORN.get(), 1, 1, true
		));
		add("acorn_from_squirrel", new AddItemLootModifier(
			new LootItemCondition[]{
				LootItemEnabledCondition.enabled("acorn"),
				LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.4F, 2.0F).build(),
				LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(DelightfulEntityTags.DROPS_ACORN)).build()
			},
			DelightfulItems.ACORN.get(), 1, 1, true
		));
		add("animal_fat", new AddItemLootModifier(
			new LootItemCondition[]{
				LootItemEnabledCondition.enabled("animal_fat"),
				LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.3F, 2.0F).build(),
				LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER_PLAYER,
					EntityPredicate.Builder.entity().equipment(
					EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(ForgeTags.TOOLS_KNIVES).build()).build()).build()).build(),
				LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(DelightfulEntityTags.FATTY_ANIMALS)).build()
			},
			DelightfulItems.ANIMAL_FAT.get(), 2, 3, true
		));
		add("raw_goat", new AddItemLootModifier(
			new LootItemCondition[]{
				LootItemEnabledCondition.enabled("raw_goat"),
				LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(1.0F, 2.0F).build(),
				LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.GOAT)).build()
			},
			DelightfulItems.RAW_GOAT.get(), 1, 2, true
		));
	}
}

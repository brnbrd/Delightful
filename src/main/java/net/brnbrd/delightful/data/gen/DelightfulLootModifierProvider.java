package net.brnbrd.delightful.data.gen;

import net.brnbrd.delightful.Delightful;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class DelightfulLootModifierProvider extends GlobalLootModifierProvider {
	public DelightfulLootModifierProvider(PackOutput output) {
		super(output, Delightful.MODID);
	}

	@Override
	protected void start() {
		/*add("green_tea_leaf", new AddItemLootModifier(
				new LootItemCondition[]{
						LootItemEnabledCondition.enabled(DelightfulItems.GREEN_TEA_LEAF.getId().getPath()),
						LootItemRandomChanceCondition.randomChance(0.8F).build(),
						MatchTool.toolMatches(ItemPredicate.Builder.item().of(DelightfulItemTags.TOOLS_SCAVENGING)).build(),
						LootItemBlockIsTagCondition.isTag(DelightfulBlockTags.DROPS_GREEN_TEA_LEAF)
				},
				DelightfulItems.GREEN_TEA_LEAF.get(), 1, 1, true
		));
		add("green_tea_leaf_rare", new AddItemLootModifier(
				new LootItemCondition[]{
						LootItemEnabledCondition.enabled(DelightfulItems.GREEN_TEA_LEAF.getId().getPath()),
						LootItemRandomChanceCondition.randomChance(0.005F).build(),
						MatchTool.toolMatches(ItemPredicate.Builder.item().of(DelightfulItemTags.TOOLS_SCAVENGING)).invert().build(),
						LootItemBlockIsTagCondition.isTag(DelightfulBlockTags.DROPS_GREEN_TEA_LEAF)
				},
				DelightfulItems.GREEN_TEA_LEAF.get(), 1, 1, true
		));
		add("acorn", new AddItemLootModifier(
				new LootItemCondition[]{
						LootItemEnabledCondition.enabled(DelightfulItems.ACORN.getId().getPath()),
						LootItemRandomChanceCondition.randomChance(0.01F).build(),
						MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS)).invert().build(),
						MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.ANY))).invert().build(),
						LootItemBlockIsTagCondition.isTag(DelightfulBlockTags.DROPS_ACORN)
				},
				DelightfulItems.ACORN.get(), 1, 1, true
		));
		add("acorn_from_knife", new AddItemLootModifier(
				new LootItemCondition[]{
						LootItemEnabledCondition.enabled(DelightfulItems.ACORN.getId().getPath()),
						LootItemRandomChanceCondition.randomChance(0.05F).build(),
						MatchTool.toolMatches(ItemPredicate.Builder.item().of(DelightfulItemTags.TOOLS_SCAVENGING)).build(),
						MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.ANY))).invert().build(),
						LootItemBlockIsTagCondition.isTag(DelightfulBlockTags.DROPS_ACORN)
				},
				DelightfulItems.ACORN.get(), 1, 1, true
		));
		add("acorn_from_squirrel", new AddItemLootModifier(
				new LootItemCondition[]{
						LootItemEnabledCondition.enabled(DelightfulItems.ACORN.getId().getPath()),
						LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.4F, 2F).build(),
						LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(DelightfulEntityTags.DROPS_ACORN)).build()
				},
				DelightfulItems.ACORN.get(), 1, 1, true
		));
		add("acorn_from_drops", new AddItemLootModifier(
				new LootItemCondition[]{
						LootItemEnabledCondition.enabled(DelightfulItems.ACORN.getId().getPath()),
						LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.4F, 2F).build(),
						LootItemBlockIsTagCondition.isTag(DelightfulBlockTags.ADD_ACORN)
				},
				DelightfulItems.ACORN.get(), 1, 1, true
		));
		add("animal_fat", new AddItemLootModifier(
				new LootItemCondition[]{
						LootItemEnabledCondition.enabled(DelightfulItems.ANIMAL_FAT.getId().getPath()),
						LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.6F, 2F).build(),
						LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER_PLAYER,
								EntityPredicate.Builder.entity().equipment(
										EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(DelightfulItemTags.TOOLS_SCAVENGING).build()).build()).build()).build(),
						LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(DelightfulEntityTags.FATTY_ANIMALS)).build()
				},
				DelightfulItems.ANIMAL_FAT.get(), 1, 3, true
		));
		add("raw_goat", new AddItemLootModifier(
				new LootItemCondition[]{
						LootItemEnabledCondition.enabled(DelightfulItems.RAW_GOAT.getId().getPath()),
						LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(1F, 2F).build(),
						LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(false).build())).build(),
						LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(DelightfulEntityTags.DROPS_RAW_GOAT)).build()
				},
				DelightfulItems.RAW_GOAT.get(), 1, 2, true
		));
		add("cooked_goat", new AddItemLootModifier(
				new LootItemCondition[]{
						LootItemEnabledCondition.enabled(DelightfulItems.COOKED_GOAT.getId().getPath()),
						LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(1F, 2F).build(),
						LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true).build())).build(),
						LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(DelightfulEntityTags.DROPS_RAW_GOAT)).build()
				},
				DelightfulItems.COOKED_GOAT.get(), 1, 2, true
		));
		add("straw_from_compat", new AddItemLootModifier(
				new LootItemCondition[]{
						LootItemRandomChanceCondition.randomChance(0.2F).build(),
						MatchTool.toolMatches(ItemPredicate.Builder.item().of(DelightfulItemTags.TOOLS_SCAVENGING)).build(),
						LootItemBlockIsTagCondition.isTag(DelightfulBlockTags.DROPS_STRAW)
				},
				ModItems.STRAW.get(), 1, 1, true
		));*/
	}
}
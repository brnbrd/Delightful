package net.brnbrd.delightful.common.item.knife.compat.aether;

import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.compat.Strategy;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

public class AetherKnifeItem extends DKnifeItem {
	public AetherKnifeItem(@Nullable TagKey<Item> tag, Tier tier, Properties properties, String modid) {
		super(tag, tier, properties, modid, Mods.AE);
	}

	public AetherKnifeItem(@Nullable TagKey<Item> tag, Tier tier, Properties properties) {
		super(tag, tier, properties, Mods.AE);
	}

	@Override
	public Strategy getStrategy() {
		return Strategy.AND;
	}

	@Override
	public Ingredient getRod() {
		return Ingredient.of(DelightfulItemTags.SKYROOT_STICK);
	}
}
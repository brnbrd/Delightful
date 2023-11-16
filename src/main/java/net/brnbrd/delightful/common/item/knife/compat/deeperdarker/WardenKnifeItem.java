package net.brnbrd.delightful.common.item.knife.compat.deeperdarker;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.apache.commons.lang3.tuple.ImmutablePair;
import vectorwing.farmersdelight.common.registry.ModItems;

public class WardenKnifeItem extends CompatKnifeItem {
	public final static TagKey<Item> upgrade = Util.it(Mods.DD, "warden_upgrade_smithing_template");

	public WardenKnifeItem(Properties properties) {
		super(Mods.DD, DelightfulItemTags.REINFORCED_ECHO_SHARD, DelightfulTiers.WARDEN, properties, ChatFormatting.AQUA);
	}

	@Override
	public ImmutablePair<Ingredient, Ingredient> getSmithing() {
		return new ImmutablePair<>(
			Ingredient.of(upgrade),
			Util.ing(ModItems.NETHERITE_KNIFE)
		);
	}
}

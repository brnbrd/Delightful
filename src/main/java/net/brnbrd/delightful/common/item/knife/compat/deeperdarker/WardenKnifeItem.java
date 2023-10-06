package net.brnbrd.delightful.common.item.knife.compat.deeperdarker;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.common.registry.ModItems;
import java.util.function.Supplier;

public class WardenKnifeItem extends CompatKnifeItem {
	public WardenKnifeItem(Properties properties) {
		super(Mods.DD, DelightfulItemTags.REINFORCED_ECHO_SHARD, DelightfulTiers.WARDEN, properties, ChatFormatting.LIGHT_PURPLE);
	}

	@Override
	public Supplier<Ingredient> getSmithingBase() {
		return Util.ing(ModItems.NETHERITE_KNIFE);
	}
}

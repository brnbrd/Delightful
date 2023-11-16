package net.brnbrd.delightful.common.item.knife.compat.aether;

import net.brnbrd.delightful.common.item.knife.CompatKnifeItem;
import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

public class AetherKnifeItem extends CompatKnifeItem {
	public AetherKnifeItem(String modid, @Nullable TagKey<Item> tag, Tier tier, Properties properties) {
		super(new String[]{ modid, Mods.AE }, tag, tier, properties);
	}

	public AetherKnifeItem(@Nullable TagKey<Item> tag, Tier tier, Properties properties) {
		super(Mods.AE, tag, tier, properties);
	}

	@Override
	public Ingredient getRod() {
		return Ingredient.of(DelightfulItemTags.SKYROOT_STICK);
	}
}

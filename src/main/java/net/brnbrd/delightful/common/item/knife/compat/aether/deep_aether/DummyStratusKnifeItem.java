package net.brnbrd.delightful.common.item.knife.compat.aether.deep_aether;

import net.brnbrd.delightful.common.item.knife.compat.aether.AetherKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class DummyStratusKnifeItem extends AetherKnifeItem {

	public DummyStratusKnifeItem(Properties props, Tier tier) {
		super(DelightfulItemTags.INGOTS_STRATUS, tier, props, Modid.DA);
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return RecipeType.SMITHING;
	}
}
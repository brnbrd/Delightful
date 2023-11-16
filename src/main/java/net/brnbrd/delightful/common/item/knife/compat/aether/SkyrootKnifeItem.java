package net.brnbrd.delightful.common.item.knife.compat.aether;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import javax.annotation.Nullable;

public class SkyrootKnifeItem extends AetherKnifeItem {
	public SkyrootKnifeItem(Properties properties) {
		super(DelightfulItemTags.SKYROOT_TOOL_CRAFTING, DelightfulTiers.SKYROOT, properties);
	}

	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
		return 200;
	}
}

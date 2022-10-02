package net.brdle.delightful.common.item.knife.allthemodium;

import net.brdle.delightful.common.item.DelightfulTiers;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import net.brdle.delightful.data.DelightfulItemTags;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public class AllthemodiumKnifeItem extends CompatKnifeItem {
	public AllthemodiumKnifeItem(Properties properties) {
		super("allthemodium", DelightfulItemTags.PLATES_ALLTHEMODIUM.location(), DelightfulTiers.ALLTHEMODIUM, 0.5F, -2.0F, properties, Component.translatable("indestructible"));
	}

	@Override
	public Supplier<Ingredient> getRod() {
		return () -> Ingredient.of(DelightfulItemTags.RODS_ALLTHEMODIUM);
	}

	@Override
	public boolean canBeDepleted() {
		return false;
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}
}

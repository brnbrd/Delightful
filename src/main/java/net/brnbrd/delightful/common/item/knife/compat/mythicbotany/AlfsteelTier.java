package net.brnbrd.delightful.common.item.knife.compat.mythicbotany;

import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.compat.BotaniaCompat;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.TierSortingRegistry;
import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;

public class AlfsteelTier implements Tier {
	private Supplier<Tier> terra() {
		return Mods.loaded(Mods.BTA) ? BotaniaCompat.terrasteel() : () -> DelightfulTiers.STEEL;
	}

	@Override
	public int getUses() {
		return terra().get().getUses();
	}

	@Override
	public float getSpeed() {
		return terra().get().getSpeed();
	}

	@Override
	public float getAttackDamageBonus() {
		return terra().get().getAttackDamageBonus();
	}

	@Override
	public int getLevel() {
		return terra().get().getLevel() + 1;
	}

	@Override
	public int getEnchantmentValue() {
		return terra().get().getEnchantmentValue();
	}

	@Override
	public @NotNull Ingredient getRepairIngredient() {
		return DelightfulItems.getIngot("alfsteel");
	}
}

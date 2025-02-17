package net.brnbrd.delightful.common.item.knife.compat.botania.mythicbotany;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.compat.botania.BotaniaCompat;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;

public class AlfsteelTier implements Tier {
	private Supplier<Tier> terra() {
		return Modid.BTA.loaded() ? BotaniaCompat.terrasteel() : () -> DelightfulTiers.STEEL;
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
		return DelightfulItemTags.getIngot("alfsteel");
	}
}
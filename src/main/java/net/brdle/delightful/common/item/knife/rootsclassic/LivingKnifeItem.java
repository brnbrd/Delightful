package net.brdle.delightful.common.item.knife.rootsclassic;

import net.brdle.delightful.common.item.DelightfulTiers;
import net.brdle.delightful.common.item.knife.CompatKnifeItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LivingKnifeItem extends CompatKnifeItem {
	public LivingKnifeItem(Properties properties) {
		super("rootsclassic", null, DelightfulTiers.LIVING, properties, null);
	}

	@Override
	public void inventoryTick(ItemStack stack, @NotNull Level levelAccessor, @NotNull Entity entity, int slot, boolean selected) {
		if (stack.isDamaged() && levelAccessor.random.nextInt(80) == 0) {
			stack.setDamageValue(stack.getDamageValue() - 1);
		}
	}

	@Override
	public boolean isValidRepairItem(@NotNull ItemStack toRepair, @NotNull ItemStack repair) {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return this.isLoaded();
	}
}

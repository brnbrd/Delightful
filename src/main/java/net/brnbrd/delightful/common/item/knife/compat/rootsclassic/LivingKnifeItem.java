package net.brnbrd.delightful.common.item.knife.compat.rootsclassic;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LivingKnifeItem extends DKnifeItem {
	public LivingKnifeItem(Properties properties) {
		super(null, DelightfulTiers.LIVING, properties, Modid.RC);
	}

	@Override
	public void inventoryTick(ItemStack stack, @NotNull Level levelAccessor, @NotNull Entity entity, int slot, boolean selected) {
		if (
			stack.isDamaged() &&
			levelAccessor.random.nextInt(80) == 0
		) {
			stack.setDamageValue(stack.getDamageValue() - 1);
		}
	}

	@Override
	public boolean isValidRepairItem(@NotNull ItemStack toRepair, @NotNull ItemStack repair) {
		return false;
	}
}
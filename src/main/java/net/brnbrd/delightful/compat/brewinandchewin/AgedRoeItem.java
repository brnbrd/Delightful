package net.brnbrd.delightful.compat.brewinandchewin;

import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.compat.Mods;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;

public class AgedRoeItem extends DBoozeItem {
	public AgedRoeItem(Fluid fluid, Item.Properties properties) {
		super(fluid, properties);
	}

	@Override
	public @NotNull SoundEvent getEatingSound() {
		return SoundEvents.GENERIC_EAT;
	}

	@Override
	public Modid[] getModid() {
		return new Modid[]{Modid.LFL, Modid.BC};
	}

	@Override
	public Mods.Strategy getStrategy() {
		return Mods.Strategy.AND;
	}
}